package nl.koen02.failz.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

import nl.koen02.failz.R;
import nl.koen02.failz.data.FirebaseService;
import nl.koen02.failz.data.model.Subject;
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

        binding.fab.setOnClickListener(view -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_nav_home_to_formFragment);
        });

        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewAdapter = new RecyclerViewAdapter(homeViewModel.getItemList());

        homeViewModel.setRecyclerViewAdapter(recyclerViewAdapter);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            FirebaseService firebaseService = new FirebaseService();

            Task<QuerySnapshot> task = firebaseService.getSubjectsForUser(
                    FirebaseAuth.getInstance().getCurrentUser().getUid()
            );

            task.addOnCompleteListener(task1 -> {
                if (task1.isSuccessful()) {
                    assert task1.getResult() != null;
                    homeViewModel.resetItemList();
                    for (QueryDocumentSnapshot document : task1.getResult()) {

                        Subject subject = document.toObject(Subject.class);

                        homeViewModel.addItemToList(
                                new ListItemData(
                                        subject.getCode(),
                                        subject.getType(),
                                        subject.getScore(),
                                        subject.getEc(),
                                        document.getId()
                                )
                        );
                    }
                } else {
                    Log.d("SUBJECT_ERROR", "Error getting documents: ", task1.getException());
                }
            });
        }

        recyclerViewAdapter.setOnItemClickListener(data -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("listItemData", data);
            bundle.putBoolean("isEditing", true);
            NavHostFragment.findNavController(this).navigate(R.id.action_nav_home_to_formFragment, bundle);
        });

        recyclerView.setAdapter(recyclerViewAdapter);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}