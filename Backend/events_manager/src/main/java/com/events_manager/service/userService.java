package com.events_manager.service;
import com.events_manager.model.user;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class userService {
    Firestore firestore = FirestoreClient.getFirestore();

    public String addUser(user user) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("users").document(user.getUserId());
        ApiFuture<WriteResult> future = docRef.set(user);
        return future.get().getUpdateTime().toString();
    }

    public user getUser(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("users").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(user.class);
        } else {
            return null;
        }
    }

    public String deleteUser(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = firestore.collection("users").document(id).delete();
        return writeResult.get().getUpdateTime().toString();
    }

    public List<user> getAllUsers() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection("users").get();
        List<user> userList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            userList.add(document.toObject(user.class));
        }
        return userList;
    }
}
