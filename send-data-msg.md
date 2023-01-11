# How to send the Data Message by calling Http API and process it in client app
This story will show you how to make it through following steps:
1. The client side preparations 
2. The server side preparations
3. Calling Http API

## The client side preparations
### 1. Create an App  
We should create an App which can receive the common Push Notification Messages.  
We may create one by following the instructions of the [official documents](https://firebase.google.com/docs/cloud-messaging/android/client?hl=en&authuser=0).  
(Or you may follow other instructing documents.)  
### 2. Handle the Data Message
We have do some coding work to handle the Data Message in our app.

#### 2.1 Enable the ability of Subcribing a Topic
To receive the Data Message, our App must be able to receive a Topic Message.  
Normaly, a Topic Message body contains a field with the name "Notification".  
We must send our Data Message as a Topic Message, but, with the "Notificaiton" feild be removed.  
That's important.  

#### 2.2 Handle the Data
There is a FCM callback function 
```
override fun onMessageReceived(remoteMessage: RemoteMessage) {
...
}
```
which is used to handle messages sent from server side.  
...

## The server side preparations
In order to send the Data Message by calling Http API from "Post Man" application, or from a web browser plugin (such as RESTer in Firefox brower), we should use the "FCM legacy HTTP API".

to be continued...
