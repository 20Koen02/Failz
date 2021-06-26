package nl.koen02.failz.ui.home;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private static HomeViewModel INSTANCE;
    private List<ListItemData> itemList;

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

    public void prepareList(RecyclerViewAdapter recyclerViewAdapter){
        itemList.clear();
        ListItemData item = new ListItemData("IKPMD", Vaksoort.KEUZEVAK, 9.5f, 3);
        itemList.add(item);
        ListItemData item2 = new ListItemData("ICOMMHA", Vaksoort.HOOFDFASE, 3.2f, 3);
        itemList.add(item2);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}