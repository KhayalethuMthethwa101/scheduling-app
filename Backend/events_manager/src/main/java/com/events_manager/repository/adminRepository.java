package com.events_manager.repository;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import java.util.concurrent.ExecutionException;
import com.events_manager.model.*;

@Repository
public class adminRepository {
    private final Firestore db = FirestoreClient.getFirestore();

    public void save(admin adminDto) {
        db.collection("admins").document(adminDto.getAdminId()).set(adminDto);
    }

    public admin findById(String adminId) throws ExecutionException, InterruptedException {
        return db.collection("admins").document(adminId).get().get().toObject(admin.class);
    }
}
