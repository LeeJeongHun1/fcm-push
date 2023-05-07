importScripts('https://www.gstatic.com/firebasejs/5.9.2/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/5.9.2/firebase-messaging.js');

// Initialize Firebase
const firebaseConfig = {
    apiKey: "AIzaSyC47WjV1sndK5hd_ngs7DfKg1r8FPTA588",
    authDomain: "web-push-76110.firebaseapp.com",
    projectId: "web-push-76110",
    storageBucket: "web-push-76110.appspot.com",
    messagingSenderId: "271762138787",
    appId: "1:271762138787:web:1316caf3ebade25322c116",
    measurementId: "G-0B8ZCRX7BY"
};
firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();