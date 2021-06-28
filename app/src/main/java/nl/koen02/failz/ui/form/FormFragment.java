package nl.koen02.failz.ui.form;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import nl.koen02.failz.R;
import nl.koen02.failz.data.FirebaseService;
import nl.koen02.failz.databinding.FormFragmentBinding;
import nl.koen02.failz.ui.shared.ListItemData;

public class FormFragment extends Fragment {

    private FormFragmentBinding binding;
    private ListItemData listItemData;
    private boolean isEditing;
    private final FirebaseService firebaseService = new FirebaseService();
    FirebaseUser currentUser;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FormFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        assert currentUser != null;

        isEditing = requireArguments().getBoolean("isEditing");
        binding.deleteVak.setVisibility(isEditing ? View.VISIBLE : View.GONE);

        if (isEditing) {
            listItemData = (ListItemData) requireArguments().getSerializable("listItemData");

            binding.deleteVak.setOnClickListener(v -> {
                firebaseService.deleteSubject(listItemData.getId());
                NavHostFragment.findNavController(this).navigate(R.id.action_formFragment_to_nav_home);
                Toast.makeText(getActivity(), "Het vak is verwijderd", Toast.LENGTH_SHORT).show();
            });

            binding.formTitle.setText(R.string.menu_form_edit_title);
            binding.editCode.setText(listItemData.getCode());
            binding.editCijfer.setText(String.format(Locale.ENGLISH, "%.1f", listItemData.getCijfer()));
            binding.editEc.setText(String.format(Locale.ENGLISH, "%d", listItemData.getEc()));
            binding.editNote.setText(listItemData.getNote());

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

            binding.submitButton.setOnClickListener(v -> {
                if (hasEmptyFields()) return;
                firebaseService.editSubject(listItemData.getId(), getSubjectData());
                success();
            });

        } else {
            AppCompatActivity activity = (AppCompatActivity) requireActivity();
            assert activity.getSupportActionBar() != null;
            activity.getSupportActionBar().setTitle(R.string.menu_form_add);
            binding.submitButton.setOnClickListener(v -> {
                if (hasEmptyFields()) return;
                firebaseService.createSubject(getSubjectData());
                success();
            });
        }

        return root;
    }

    private void success() {
        NavHostFragment.findNavController(this).navigate(R.id.action_formFragment_to_nav_home);
        Toast.makeText(getActivity(), isEditing ? "Successvol het vak geweizigd" : "Successvol het vak toegevoegd", Toast.LENGTH_SHORT).show();
    }

    private Map<String, Object> getSubjectData() {
        String vaksoort = "HOOFDFASE";
        if (binding.choicePropedeuse.isChecked()) vaksoort = "PROPEDEUSE";
        if (binding.choiceKeuzevak.isChecked()) vaksoort = "KEUZEVAK";

        Map<String, Object> subject = new HashMap<>();

        String note = binding.editNote.getText().toString();
        if (TextUtils.isEmpty(note)) note = "";
        subject.put("note", note.trim());
        subject.put("code", binding.editCode.getText().toString().trim());
        subject.put("ec", Integer.parseInt(binding.editEc.getText().toString()));
        subject.put("score", Float.parseFloat(binding.editCijfer.getText().toString()));
        subject.put("type", vaksoort);
        subject.put("userId", currentUser.getUid());

        return subject;
    }

    private boolean hasEmptyFields() {
        if (TextUtils.isEmpty(binding.editCode.getText().toString())
                || TextUtils.isEmpty(binding.editCijfer.getText().toString())
                || TextUtils.isEmpty(binding.editEc.getText().toString())) {
            Toast.makeText(getActivity(), "Please fill in empty fields", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}