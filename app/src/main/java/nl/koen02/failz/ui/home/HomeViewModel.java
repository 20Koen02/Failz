package nl.koen02.failz.ui.home;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import nl.koen02.failz.data.FirebaseService;
import nl.koen02.failz.data.model.Subject;
import nl.koen02.failz.ui.shared.ListItemData;

public class HomeViewModel extends ViewModel {

    private static HomeViewModel INSTANCE;
    private final List<ListItemData> itemList;
    private RecyclerViewAdapter recyclerViewAdapter;
    private TextView emptyText;

    public HomeViewModel() {
        itemList = new ArrayList<>();
    }

    public static HomeViewModel getInstance() {
        if (INSTANCE == null) INSTANCE = new HomeViewModel();
        return INSTANCE;
    }

    public List<ListItemData> getItemList() {
        return itemList;
    }

    public void addItemToList(ListItemData item) {
        itemList.add(item);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    public void addItemsToList(List<ListItemData> items) {
        itemList.addAll(items);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    public void resetItemList() {
        itemList.clear();

    }

    public void setRecyclerViewAdapter(RecyclerViewAdapter recyclerViewAdapter) {
        this.recyclerViewAdapter = recyclerViewAdapter;
    }

    public void refreshDataFromFirestore() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            FirebaseService firebaseService = new FirebaseService();

            Task<QuerySnapshot> task = firebaseService.getSubjectsForUser(
                    FirebaseAuth.getInstance().getCurrentUser().getUid()
            );

            task.addOnCompleteListener(task1 -> {
                if (task1.isSuccessful()) {
                    assert task1.getResult() != null;
                    resetItemList();
                    for (QueryDocumentSnapshot document : task1.getResult()) {

                        Subject subject = document.toObject(Subject.class);

                        addItemToList(
                                new ListItemData(
                                        subject.getCode(),
                                        subject.getType(),
                                        subject.getScore(),
                                        subject.getEc(),
                                        subject.getNote(),
                                        document.getId()
                                )
                        );
                    }
                    recyclerViewAdapter.notifyDataSetChanged();
                    if (emptyText != null) emptyText.setVisibility(getItemList().size() > 0 ? View.GONE : View.VISIBLE);
                } else {
                    Log.d("SUBJECT_ERROR", "Error getting documents: ", task1.getException());
                }
            });
        }
    }

    public void setEmptyText(TextView emptyText) {
        this.emptyText = emptyText;
    }
}