package com.events_manager.repository;
import com.events_manager.model.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class userRepository {
    private final Firestore firestore;

    @Autowired
    public userRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    // Save or Update User
    public String saveUser(user user) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("users").document(user.getUserId());
        ApiFuture<WriteResult> future = docRef.set(user);
        return future.get().getUpdateTime().toString();
    }

    // Retrieve User by ID
    public user getUserById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("users").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(user.class);
        } else {
            return null;
        }
    }

    // Delete User by ID
    public String deleteUser(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = firestore.collection("users").document(id).delete();
        return writeResult.get().getUpdateTime().toString();
    }

    // Retrieve All Users
    public List<user> getAllUsers() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection("users").get();
        List<user> userList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            userList.add(document.toObject(user.class));
        }
        return userList;
    }
}
