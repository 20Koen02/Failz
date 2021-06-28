package nl.koen02.failz.ui.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.Objects;

import nl.koen02.failz.R;
import nl.koen02.failz.databinding.FormFragmentBinding;
import nl.koen02.failz.databinding.FragmentHomeBinding;
import nl.koen02.failz.ui.home.ListItemData;

public class FormFragment extends Fragment {

    private FormFragmentBinding binding;
    private FormViewModel mViewModel;
    private ListItemData listItemData;

    public static FormFragment newInstance() {
        return new FormFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FormFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listItemData = (ListItemData) requireArguments().getSerializable("listItemData");

        System.out.println(listItemData.getCode());

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FormViewModel.class);
    }

}