package com.example.recipes;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BillOfFare#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillOfFare extends Fragment {
    HashMap hm;
    FirebaseFirestore db;
    FirebaseFirestoreSettings settings;
    List<HashMap<String,String>> list=new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public String category;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BillOfFare() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BillOfFare newInstance(String param1, String param2) {
        BillOfFare fragment = new BillOfFare();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
                                Log.d("TAG356", " " + document.getData());
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
                            String[] custom={"ImagePath","Title","Description"};
                            int[] customId={R.id.foodImage,R.id.foodTitle,R.id.foodDescription};
                            CustomAdapter adapter = new CustomAdapter(v.getContext(),list);
                            // SimpleAdapter adapter=new SimpleAdapter( getContext() ,list,R.layout.custom_list_filter,custom,customId);
                            lv.setAdapter(adapter);
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    HashMap hm= list.get(position);
                                    Log.d("detail", ""+hm.get("Description").toString());
                                    Log.d("detail", ""+hm.get("Recipe").toString());
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

                        } else {

                            Log.d("TAG356", "Error getting documents: ", task.getException());
                        }
                    }
                });


        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap hm= list.get(position);
                food_details foodDetails = new food_details();
                Bundle args = new Bundle();
                args.putString("Title", hm.get("Title").toString() ) ;
                args.putString("Recipe", hm.get("Recipe").toString() ) ;
                args.putString("Description", hm.get("Description").toString() ) ;
                args.putString("ImagePath", hm.get("ImagePath").toString() ) ;
                foodDetails.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, foodDetails);
                transaction.commit();
            }
        });*/
        return v;
    }
    private void getData(View v) {
        //list=db.filterData(category);
        ListView lv = (ListView) v.findViewById(R.id.foodList);
        String[] custom={"ImagePath","Title","Description"};
        int[] customId={R.id.foodImage,R.id.foodTitle,R.id.foodDescription};
        CustomAdapter adapter = new CustomAdapter(getContext(),list);
        //SimpleAdapter adapter=new SimpleAdapter( getContext() ,list,R.layout.food_custom,custom,customId);
        lv.setAdapter(adapter);
    }
}

