package com.example.helloworld;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MatchViewModel {

    private MatchModel matchModel;

    public MatchViewModel() {
        matchModel = new MatchModel();
    }

    public void getMatchItems(Consumer<ArrayList<MatchItem>> responseCallback) {
        matchModel.getMatchItems(
                (QuerySnapshot querySnapshot) -> {
                    if (querySnapshot != null) {
                        ArrayList<MatchItem> matchItems = new ArrayList<>();
                        for (DocumentSnapshot matchSnapshot : querySnapshot.getDocuments()) {
                            MatchItem item = matchSnapshot.toObject(MatchItem.class);
                            assert item != null;
                            item.uid = (matchSnapshot.getId());
                            matchItems.add(item);
                        }
                        responseCallback.accept(matchItems);
                    }
                },
                (databaseError -> System.out.println(Constants.ERR_GETTING_MATCHES + databaseError))
        );
    }

    public void updateMatchItem(MatchItem match) {
        matchModel.updateMatchItemById(match);
    }

    public void clear() {
        matchModel.clear();
    }

}
