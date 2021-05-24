package com.example.recipes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBase {
    FirebaseFirestore db;
    FirebaseFirestoreSettings settings;
    List<HashMap<String,String>> list=new ArrayList<>();
    HashMap hm;

    public DataBase(){
        db = FirebaseFirestore.getInstance();
        settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();

        db.setFirestoreSettings(settings);
    }

    public void GetAll(String ColectionPath){
        db.collection(ColectionPath)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //  Log.d("TAG", document.getData().toString());

                                hm=new HashMap();
                                hm.put("Category", document.get("Category").toString());
                                hm.put("Description",document.get("Description").toString());
                                hm.put("Recipe",document.get("Recipe").toString());
                                hm.put("ImagePath",document.get("ImagePath").toString());
                                hm.put("Title",document.get("Title").toString());
                                list.add(hm);
                                Log.d("TAG", "0: "+list);
                                //Log.d("TAG", document.getId() + " => " + document.getData());
                            }

                        } else {
                            // Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
    public List test(List data){
        return data;
    }

    public List<HashMap<String,String>> getList(){
        Log.d("TAG", "data: "+list);
        return this.list;
    }

    public void Get(String CollactionPath, String index){
        DocumentReference docRef = db.collection(CollactionPath).document(index);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        hm=new HashMap();
                        hm.put("Category", document.get("Category").toString());
                        hm.put("Description",document.get("Description").toString());
                        hm.put("Recipe",document.get("Recipe").toString());
                        hm.put("ImagePath",document.get("ImagePath").toString());
                        hm.put("Title",document.get("Title").toString());
                        list.add(hm);
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
    }
}

