package com.example.helloworld;

import com.google.firebase.firestore.CollectionReference;
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

public class FirebaseModel {


    private FirebaseFirestore db;
    private List<ListenerRegistration> listeners;

    public FirebaseModel() {
        db = FirebaseFirestore.getInstance();
        listeners = new ArrayList<>();
    }

    public void addMatch(MatchItem match) {
        CollectionReference matchRef = db.collection(Constants.COLLECTION_MATCHES);
        matchRef.add(match);
    }

    public void getMatchItems(Consumer<QuerySnapshot> dataChangedCallback, Consumer<FirebaseFirestoreException> dataErrorCallback) {
        ListenerRegistration listener = db.collection(Constants.COLLECTION_MATCHES)
                .addSnapshotListener(((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        dataErrorCallback.accept(e);
                    }
                    dataChangedCallback.accept(queryDocumentSnapshots);
                }));
        listeners.add(listener);
    }

    public void updateMatches(MatchItem match) {
        DocumentReference dr = db.collection(Constants.COLLECTION_MATCHES).document(match.getUid());
        Map<String, Object> data = new HashMap<>();

        data.put("uid", match.getUid());

        dr.update(data);
    }

    public void clear() {
        listeners.forEach(ListenerRegistration::remove);
    }
}
