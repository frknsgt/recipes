package com.example.recipes;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MealDay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealDay extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DataBase db=new DataBase();
    public List<HashMap<String, String>> listSoups =new ArrayList<>();
    public List<HashMap<String, String>> listFoods =new ArrayList<>();
    public List<HashMap<String, String>> listSalads =new ArrayList<>();
    public List<HashMap<String, String>> listDeserts =new ArrayList<>();
    public List<HashMap<String, String>> list =new ArrayList<>();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MealDay() {
            db.GetAll("recipes");
            // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MealDay.
     */
    // TODO: Rename and change types and number of parameters
    public static MealDay newInstance(String param1, String param2) {
        MealDay fragment = new MealDay();
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
        View v= inflater.inflate(R.layout.fragment_meal_day, container, false);

        AsyncTask<Void, Void, Void> task= new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void result){
                getData(v);
            }
        };
        task.execute();
        ListView lv = (ListView) v.findViewById(R.id.mealDayList);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap hm= list.get(position);
            }
        });
        return v;
    }


    private void getData(View v) {
        this.randomMenu(db.filterData("Çorba"));
        this.randomMenu(db.filterData("Salata"));
        this.randomMenu(db.filterData("Yemek"));
        this.randomMenu(db.filterData("Tatlı"));
        ListView lv = (ListView) v.findViewById(R.id.mealDayList);
        String[] custom={"ImagePath","Title","Description"};
        int[] customId={R.id.foodImage,R.id.foodTitle,R.id.foodDescription};
        SimpleAdapter adapter=new SimpleAdapter( getContext() ,list,R.layout.food_custom,custom,customId);
        lv.setAdapter(adapter);
    }
    public void randomMenu(List<HashMap<String,String>> menuList){
        Random r = new Random();
        int index = r.nextInt(menuList.size());
        list.add(menuList.get(index));
    }
}