# TopicPush
Test Topic Push

It subscribs a topic "TopicForMan".

The Admin can send a topic message from firebase console.

The client app will only receive the suscribed topic message.

Official document:

https://firebase.google.com/docs/cloud-messaging/android/send-multiple

the key modification:

```
    override fun onCreate(savedInstanceState: Bundle?) {    <--- OnCreate of the MainActivity
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        Firebase.messaging.subscribeToTopic("TopicForMan")  <--- subscribe the topic
            .addOnCompleteListener { task ->                <--- callback for debug
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d(TAG, msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
            
    }

```

