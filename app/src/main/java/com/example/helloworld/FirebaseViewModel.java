package com.example.helloworld;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

public class FirebaseViewModel {

    private FirebaseModel model;

    public FirebaseViewModel() { model = new FirebaseModel(); }

    public void addMatch(MatchItem item) {
        model.addMatch(item);
    }

    public void getMatchItems(Consumer<ArrayList<MatchItem>> responseCallback) {
        model.getMatchItems(
                (QuerySnapshot querySnapshot) -> {
                    if (querySnapshot != null) {
                        ArrayList<MatchItem> matches = new ArrayList<>();
                        for (DocumentSnapshot matchesSnapshot : querySnapshot.getDocuments()) {
                            MatchItem item = matchesSnapshot.toObject(MatchItem.class);
                            assert item != null;
                            item.setUid(matchesSnapshot.getId());
                            matches.add(item);
                        }
                        responseCallback.accept(matches);
                    }
                },
                (databaseError -> System.out.println(Constants.ERR_GETTING_MATCHES + databaseError))
        );
    }

    public void clear() { model.clear(); }

    public void updateLiked(MatchItem item) { model.updateMatches(item); }
}
