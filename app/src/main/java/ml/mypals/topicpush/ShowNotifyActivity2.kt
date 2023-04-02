package ml.mypals.topicpush

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ShowNotifyActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_notify2)
        val myTextView = findViewById<TextView>(R.id.content_textView)
        if (intent.extras != null) {
            // Get the custom data from the intent extras, when wake up from background
            val contentOfNotify = intent.getStringExtra("contentOfNotify")
            myTextView.text = contentOfNotify
        }

    }
}