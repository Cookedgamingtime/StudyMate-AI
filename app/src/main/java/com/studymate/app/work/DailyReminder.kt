package com.studymate.app.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import java.util.Calendar
import java.util.concurrent.TimeUnit

class DailyReminder(appContext: Context, params: WorkerParameters)
    : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        showNotification(applicationContext)
        return Result.success()
    }

    private fun showNotification(ctx: Context) {
        val nm = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val chId = "studymate_daily"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nm.createNotificationChannel(
                NotificationChannel(chId, "Daily Reminder", NotificationManager.IMPORTANCE_DEFAULT)
            )
        }
        val messages = listOf(
            "Beta, chalo aaj kuch padhte hain?",
            "Zig wait kar raha hai. Aaja!",
            "Ek lesson, 5 minute. Easy hai.",
            "Streak maintain karo beta. Chalo."
        )
        val body = messages.random()

        val notif = NotificationCompat.Builder(ctx, chId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("StudyMate")
            .setContentText(body)
            .setAutoCancel(true)
            .build()
        nm.notify(100, notif)
    }

    companion object {
        fun schedule(context: Context) {
            val req = PeriodicWorkRequestBuilder<DailyReminder>(1, TimeUnit.DAYS)
                .setInitialDelay(computeDelay(), TimeUnit.MILLISECONDS)
                .build()
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "studymate_daily",
                ExistingPeriodicWorkPolicy.UPDATE,
                req
            )
        }

        private fun computeDelay(): Long {
            val now = Calendar.getInstance()
            val target = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 18)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                if (before(now)) add(Calendar.DAY_OF_MONTH, 1)
            }
            return target.timeInMillis - now.timeInMillis
        }
    }
    }
