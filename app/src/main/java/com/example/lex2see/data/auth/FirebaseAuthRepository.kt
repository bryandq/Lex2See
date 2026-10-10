package com.example.lex2see.data.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FirebaseAuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) : AuthRepository {
    override val currentUserId: String? get() = auth.currentUser?.uid

    override suspend fun signInWithEmail(email: String, password: String): AuthResult = try {
        awaitAuth { done -> auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(done) }
        ensureProfile()
    } catch (e: Exception) { AuthResult.Error(e.localizedMessage ?: "Đăng nhập thất bại") }

    override suspend fun register(email: String, password: String, displayName: String): AuthResult = try {
        awaitAuth { done -> auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(done) }
        ensureProfile(displayName)
    } catch (e: Exception) { AuthResult.Error(e.localizedMessage ?: "Đăng ký thất bại") }

    override suspend fun signInWithGoogle(idToken: String): AuthResult = try {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        awaitAuth { done -> auth.signInWithCredential(credential).addOnCompleteListener(done) }
        ensureProfile()
    } catch (e: Exception) { AuthResult.Error(e.localizedMessage ?: "Google Login thất bại") }

    override suspend fun resetPassword(email: String): AuthResult = try {
        suspendCancellableCoroutine<Unit> { cont ->
            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (cont.isActive) {
                    if (task.isSuccessful) cont.resume(Unit)
                    else cont.cancel(task.exception ?: IllegalStateException("Không thể gửi email"))
                }
            }
        }
        AuthResult.Success
    } catch (e: Exception) { AuthResult.Error(e.localizedMessage ?: "Không thể gửi email") }

    override fun signOut() = auth.signOut()

    private suspend fun ensureProfile(name: String = ""): AuthResult {
        val user = auth.currentUser ?: return AuthResult.Error("Không có phiên đăng nhập")
        val ref = firestore.collection("users").document(user.uid)
        return try {
            // Merge preserves existing XP, streak, and other user-owned fields.
            val fields = mapOf(
                "uid" to user.uid,
                "email" to (user.email ?: ""),
                "displayName" to (name.ifBlank { user.displayName ?: "" }),
                "photoUrl" to (user.photoUrl?.toString() ?: ""),
                "updatedAt" to com.google.firebase.firestore.FieldValue.serverTimestamp()
            )
            suspendCancellableCoroutine<Unit> { cont ->
                ref.set(fields, com.google.firebase.firestore.SetOptions.merge()).addOnCompleteListener { task ->
                    if (cont.isActive) {
                        if (task.isSuccessful) cont.resume(Unit)
                        else cont.cancel(task.exception ?: IllegalStateException("Không thể lưu hồ sơ"))
                    }
                }
            }
            AuthResult.Success
        } catch (e: Exception) { AuthResult.Error(e.localizedMessage ?: "Không thể đồng bộ hồ sơ") }
    }

    private suspend fun awaitAuth(
        start: ((com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult>) -> Unit) -> Unit
    ) = suspendCancellableCoroutine<Unit> { cont ->
        start { task ->
            if (cont.isActive) {
                if (task.isSuccessful) cont.resume(Unit)
                else cont.cancel(task.exception ?: IllegalStateException("Xác thực thất bại"))
            }
        }
    }
}
