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
        try {
            db.collection(ColectionPath).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            hm = new HashMap();
                            hm.put("Category", document.get("Category").toString());
                            hm.put("Description", document.get("Description").toString());
                            hm.put("Recipe", document.get("Recipe").toString());
                            hm.put("ImagePath", document.get("ImagePath").toString());
                            hm.put("Title", document.get("Title").toString());
                            list.add(hm);
                        }
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    public List<HashMap<String,String>> getList(){
        return this.list;
    }

    public List<HashMap<String,String>> filterData(String filterText){
        List<HashMap<String,String>> result=new ArrayList<>();
        for (HashMap<String,String> item : list) {
            if (item.get("Category").toString().equals(filterText)) {
                result.add(item);
            }
        }
        return result;
    }
}

