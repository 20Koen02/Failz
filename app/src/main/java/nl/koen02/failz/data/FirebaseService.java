package nl.koen02.failz.data;

import android.util.Log;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;


public class FirebaseService {

    private FirebaseFirestore db;

    private CollectionReference subjectCollection;

    public FirebaseService() {

        this.db = FirebaseFirestore.getInstance();

        this.subjectCollection = this.db.collection("subjects");

    }

    public ArrayList<QueryDocumentSnapshot> getAllSubjects() {

        ArrayList<QueryDocumentSnapshot> documents = new ArrayList<>();

        this.subjectCollection
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

    public void editSubject(String subjectId, Map<String, Object> subjectData) {

        this.subjectCollection.document(subjectId).set(subjectData);

    }

    public void createSubject(Map<String, Object> subjectData) {

        this.subjectCollection.add(subjectData);

    }

    public ArrayList<QueryDocumentSnapshot> getSubjectsForUser(String userId) {

        ArrayList<QueryDocumentSnapshot> documents = new ArrayList<>();

        this.subjectCollection
                .whereEqualTo("userId", userId)
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

    //TODO: create query for one subject
}
