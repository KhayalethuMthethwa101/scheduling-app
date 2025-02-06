package com.events_manager.repository;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.events_manager.model.*;

@Repository
public class visitorRepository {
    private final Firestore firestore;

    @Autowired
    public visitorRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public void saveVisitor(visitor visitor) {
        firestore.collection("visitors").document(visitor.getVisitorId()).set(visitor);
    }
    public visitor findById(String visitorId) throws ExecutionException, InterruptedException {
        return firestore.collection("visitors").document(visitorId).get().get().toObject(visitor.class);
    }

    public CollectionReference findAllVisitors() {
        return firestore.collection("visitors");
    }
}