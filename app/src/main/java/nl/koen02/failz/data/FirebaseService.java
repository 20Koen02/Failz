package nl.koen02.failz.data;


import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class FirebaseService {

    private FirebaseFirestore db;

    private CollectionReference subjectCollection;

    public FirebaseService() {

        this.db = FirebaseFirestore.getInstance();

        this.subjectCollection = this.db.collection("subjects");

    }

    public Task<QuerySnapshot> getAllSubjects() {

        return this.subjectCollection.get();

    }

    public void editSubject(String subjectId, Map<String, Object> subjectData) {

        this.subjectCollection.document(subjectId).set(subjectData);

    }

    public void createSubject(Map<String, Object> subjectData) {

        this.subjectCollection.add(subjectData);

    }

    public Task<QuerySnapshot> getSubjectsForUser(String userId) {

        return this.subjectCollection
                .whereEqualTo("userId", userId)
                .get();

    }

    public Task<DocumentSnapshot> getSubject(String subjectId) {

        return this.subjectCollection
                .document(subjectId)
                .get();

    }

    public void deleteSubject(String subjectId) {
        this.subjectCollection.document(subjectId).delete();
    }
}