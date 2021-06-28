package nl.koen02.failz.ui.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Locale;

import nl.koen02.failz.R;
import nl.koen02.failz.databinding.ActivityMainBinding;
import nl.koen02.failz.databinding.FormFragmentBinding;
import nl.koen02.failz.ui.home.ListItemData;

public class FormFragment extends Fragment {

    private FormFragmentBinding binding;
    private FormViewModel mViewModel;
    private ListItemData listItemData;
    private boolean isEditing;

    public static FormFragment newInstance() {
        return new FormFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FormFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        isEditing = requireArguments().getBoolean("isEditing");

        if (isEditing) {
            listItemData = (ListItemData) requireArguments().getSerializable("listItemData");

            binding.formTitle.setText(R.string.menu_form_edit_title);
            binding.editCode.setText(listItemData.getCode());
            binding.editCijfer.setText(String.format(Locale.ENGLISH, "%f", listItemData.getCijfer()));
            binding.editEc.setText(String.format(Locale.ENGLISH, "%d", listItemData.getEc()));

            switch (listItemData.getVaksoort()) {
                case PROPEDEUSE:
                    binding.choicePropedeuse.toggle();
                    break;
                case HOOFDFASE:
                    binding.choiceHoofdfase.toggle();
                    break;
                case KEUZEVAK:
                    binding.choiceKeuzevak.toggle();
                    break;
            }
        } else {
            AppCompatActivity activity = (AppCompatActivity) requireActivity();
            assert activity.getSupportActionBar() != null;
            activity.getSupportActionBar().setTitle(R.string.menu_form_add);
        }

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FormViewModel.class);
    }

}