package com.example.helloworld;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MatchModel {

    private FirebaseFirestore db;
    private List<ListenerRegistration> listeners;

    public MatchModel() {
        db = FirebaseFirestore.getInstance();
        listeners = new ArrayList<>();
    }

    public void getMatchItems(Consumer<QuerySnapshot> dataChangedCallback, Consumer<FirebaseFirestoreException> dataErrorCallback) {
        ListenerRegistration listener;
        listener = db.collection(Constants.COLLECTION_MATCHES)
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        dataErrorCallback.accept(e);
                    }

                    dataChangedCallback.accept(queryDocumentSnapshots);
                });
        listeners.add(listener);
    }

    public void updateMatchItemById(MatchItem item) {
        DocumentReference matchItemRef = db.collection(Constants.COLLECTION_MATCHES).document(item.uid);
        Map<String, Object> data = new HashMap<>();
        data.put(Constants.collection_liked, item.liked);
        matchItemRef.update(data);
    }

    public void clear() {
        listeners.forEach(ListenerRegistration::remove);
    }

}
