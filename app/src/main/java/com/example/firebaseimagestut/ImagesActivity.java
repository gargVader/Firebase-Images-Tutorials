package com.example.firebaseimagestut;

import static com.example.firebaseimagestut.MainActivity.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CollectionReference collectionReference;
    List<Upload> uploadList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        uploadList = new ArrayList<>();
        UploadAdapter uploadAdapter = new UploadAdapter(uploadList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(uploadAdapter);

        collectionReference = FirebaseFirestore.getInstance().collection("uploads");
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (QueryDocumentSnapshot documentSnapshot : value) {
                            Upload upload = documentSnapshot.toObject(Upload.class);
                            uploadList.add(upload);
                        }
                        uploadAdapter.notifyDataSetChanged();
            }
        });
//        collectionReference.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                            Upload upload = documentSnapshot.toObject(Upload.class);
//                            uploadList.add(upload);
//                        }
//                        uploadAdapter.notifyDataSetChanged();
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d(TAG, "onFailure: " + e.getMessage());
//                    }
//                });

    }
}