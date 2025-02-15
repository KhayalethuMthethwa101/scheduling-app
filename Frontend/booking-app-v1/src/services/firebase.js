// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyC8Atd1A2yOVKk6J-wTZFoGjCPXHUM8dLc",
  authDomain: "voting-app-b302c.firebaseapp.com",
  projectId: "voting-app-b302c",
  storageBucket: "voting-app-b302c.firebasestorage.app",
  messagingSenderId: "778426571083",
  appId: "1:778426571083:web:57707466aca061758030ce",
  measurementId: "G-8PY1584NFD"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
export const auth = getAuth(app);