package com.example.recipes;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BillOfFare extends Fragment {
    HashMap hm;
    String category;
    FirebaseFirestore db;
    FirebaseFirestoreSettings settings;
    List<HashMap<String,String>> list=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        category = getArguments().getString("Category");
        View v= inflater.inflate(R.layout.fragment_bill_of_fare, container, false);
        ListView lv = (ListView) v.findViewById(R.id.foodList);
        db = FirebaseFirestore.getInstance();
        settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        db.collection("recipes")
                .whereEqualTo("Category", (String) category)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                try{
                                    hm=new HashMap();
                                    hm.put("ImagePath",document.get("ImagePath").toString());
                                    hm.put("Title",document.get("Title").toString());
                                    hm.put("Recipe",document.get("Recipe").toString());
                                    hm.put("Description", document.get("Description").toString());
                                    list.add(hm);
                                }catch(Exception e){
                                }
                            }
                            CustomAdapter adapter = new CustomAdapter(v.getContext(),list);
                            lv.setAdapter(adapter);
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    HashMap hm= list.get(position);
                                    food_details foodDetails = new food_details();
                                    Bundle args = new Bundle();
                                    try{
                                        args.putString("Title", hm.get("Title").toString() ) ;
                                        args.putString("Description", hm.get("Description").toString() ) ;
                                        args.putString("ImagePath", hm.get("ImagePath").toString() ) ;
                                        args.putString("Recipe", hm.get("Recipe").toString() ) ;
                                    }catch(Exception e){}
                                    foodDetails.setArguments(args);
                                    FragmentManager fragmentManager = getFragmentManager();
                                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                                    transaction.replace(R.id.content, foodDetails);
                                    transaction.commit();
                                }
                            });
                        }
                    }
                });
        return v;
    }
}

