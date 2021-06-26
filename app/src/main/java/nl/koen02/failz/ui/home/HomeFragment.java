package nl.koen02.failz.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import nl.koen02.failz.R;
import nl.koen02.failz.data.FirebaseService;
import nl.koen02.failz.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private FirebaseService firebaseService = new FirebaseService();

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel = HomeViewModel.getInstance();

        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewAdapter = new RecyclerViewAdapter(homeViewModel.getItemList());
        homeViewModel.prepareList(recyclerViewAdapter);

        recyclerViewAdapter.setOnItemClickListener(data -> Toast.makeText(getActivity(), data.getCode(), Toast.LENGTH_SHORT).show());

        recyclerView.setAdapter(recyclerViewAdapter);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}