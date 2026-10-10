package com.example.lex2see.data.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.lex2see.data.local.DatabaseProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/** Upload-only outbox. Downloads and conflict resolution are intentionally NOT implemented yet. */
class VocabularySyncWorker(context: Context, params: WorkerParameters): CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return Result.success()
        val dao = DatabaseProvider.get(applicationContext).userVocabularyDao()
        val firestore = FirebaseFirestore.getInstance()
        return try {
            for (word in dao.pending(uid)) {
                if (word.syncStatus == "PENDING_DELETE") {
                    await { done -> firestore.collection("users").document(uid)
                        .collection("vocabularies").document(word.userVocabularyId).delete().addOnCompleteListener(done) }
                } else {
                    val fields = mapOf(
                        "normalizedWord" to word.normalizedWord,
                        "customWord" to word.customWord,
                        "customMeaning" to word.customMeaning,
                        "source" to word.source,
                        "status" to word.status,
                        "srsStage" to word.srsStage,
                        "nextReviewAt" to word.nextReviewAt,
                        "forgetCount" to word.forgetCount,
                        "updatedAt" to word.updatedAt
                    )
                    await { done -> firestore.collection("users").document(uid)
                        .collection("vocabularies").document(word.userVocabularyId)
                        .set(fields).addOnCompleteListener(done) }
                }
                dao.markSynced(word.userVocabularyId, word.updatedAt)
            }
            Result.success()
        } catch (e: Exception) { Result.retry() }
    }

    private suspend fun await(start: ((com.google.android.gms.tasks.Task<Void>) -> Unit) -> Unit) =
        suspendCancellableCoroutine<Unit> { cont ->
            start { task -> if(cont.isActive) {
                if(task.isSuccessful) cont.resume(Unit)
                else cont.cancel(task.exception ?: IllegalStateException("Firestore write failed"))
            } }
        }
}

object SyncScheduler {
    fun enqueue(context: Context) {
        val request = OneTimeWorkRequestBuilder<VocabularySyncWorker>()
            .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
            .build()
        WorkManager.getInstance(context).enqueueUniqueWork("lex2see-vocabulary-upload", ExistingWorkPolicy.KEEP, request)
    }
}
