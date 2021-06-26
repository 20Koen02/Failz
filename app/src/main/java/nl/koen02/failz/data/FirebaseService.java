package nl.koen02.failz.data;

import android.util.Log;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class FirebaseService {

    private FirebaseFirestore db;

    public FirebaseService() {

        this.db = FirebaseFirestore.getInstance();

    }

    public ArrayList<QueryDocumentSnapshot> getAllSubjects() {

        ArrayList<QueryDocumentSnapshot> documents = new ArrayList<>();

        db.collection("subjects")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                documents.add(document);
                            }
                        } else {
                            Log.w("Error", "Error getting documents.", task.getException());
                        }
                    }
                });

        return documents;

    }
}
