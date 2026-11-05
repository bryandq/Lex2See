package com.example.lex2see.data.auth

import com.example.lex2see.data.remote.firebase.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) : AuthRepository {

    override suspend fun register(
        email: String,
        password: String,
        displayName: String
    ): Result<Unit> {
        return try {

            // 1. Tạo tài khoản Firebase Authentication
            val result = auth
                .createUserWithEmailAndPassword(
                    email.trim(),
                    password
                )
                .await()

            val user = result.user
                ?: throw Exception("Không thể tạo tài khoản")

            // 2. Tạo profile Firestore
            val profile = UserProfile(
                uid = user.uid,
                email = user.email ?: email.trim(),
                displayName = displayName.trim(),
                photoUrl = user.photoUrl?.toString(),
                xp = 0,
                level = 1,
                streak = 0
            )

            // 3. Document ID = Firebase UID
            firestore
                .collection("users")
                .document(user.uid)
                .set(profile)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {
        return try {

            auth
                .signInWithEmailAndPassword(
                    email.trim(),
                    password
                )
                .await()

            ensureUserProfile()
                .getOrThrow()

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun ensureUserProfile(): Result<Unit> {
        return try {

            val user = auth.currentUser
                ?: throw Exception("Người dùng chưa đăng nhập")

            val document = firestore
                .collection("users")
                .document(user.uid)

            val snapshot = document
                .get()
                .await()

            // Nếu profile chưa tồn tại → tạo
            if (!snapshot.exists()) {

                val profile = UserProfile(
                    uid = user.uid,
                    email = user.email ?: "",
                    displayName = user.displayName ?: "",
                    photoUrl = user.photoUrl?.toString(),
                    xp = 0,
                    level = 1,
                    streak = 0
                )

                document
                    .set(profile)
                    .await()
            }

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure( e)
        }
    }


    override fun logout() {
        auth.signOut()
    }
}