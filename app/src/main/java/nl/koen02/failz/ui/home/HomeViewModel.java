package nl.koen02.failz.ui.home;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private static HomeViewModel INSTANCE;
    private List<ListItemData> itemList;
    private RecyclerViewAdapter recyclerViewAdapter;

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

    public void setRecyclerViewAdapter(RecyclerViewAdapter recyclerViewAdapter) {
        this.recyclerViewAdapter = recyclerViewAdapter;
    }
}