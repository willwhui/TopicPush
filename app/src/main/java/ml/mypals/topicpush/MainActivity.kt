package ml.mypals.topicpush

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class MainActivity : AppCompatActivity() {
    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
*/
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // response to UI subscription checkbox
    public fun onCheckSubscribeTopic(view: View) {
        if (view is CheckBox) {
            when (view.id) {
                R.id.checkBoxForMan -> {
                    subscribeTopic("TopicForMan", view.isChecked)
                }
                R.id.checkBoxForWoman -> {
                    subscribeTopic("TopicForWoman", view.isChecked)
                }
                R.id.checkBoxForMarried -> {
                    subscribeTopic("TopicForMarried", view.isChecked)
                }
            }
        }
    }

    private fun subscribeTopic(topic: String, bSubscribe: Boolean) {
        when (bSubscribe) {
            true -> Firebase.messaging.subscribeToTopic(topic)
                    .addOnCompleteListener { task ->
                        toastMessage(topic, true, task.isSuccessful)
                    }
            false -> Firebase.messaging.unsubscribeFromTopic(topic)
                    .addOnCompleteListener { task ->
                        toastMessage(topic, false, task.isSuccessful)
                    }
        }
    }

    private fun toastMessage(topic: String, bSubscribe: Boolean, bSucceeded: Boolean){
        val suffixSucceeded = when (bSubscribe) {
            true -> ": Subscribed."
            false -> ": unsubscribed."
        }

        val suffixFailed = when (bSubscribe) {
            true -> ": Subscribe failed."
            false -> ": Unsubscribe failed."
        }

        val msg = when (bSucceeded) {
            true -> topic + suffixSucceeded
            false -> topic +suffixFailed
        }

        Log.d(TAG, msg)
        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
    }
}