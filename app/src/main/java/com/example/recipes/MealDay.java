package com.example.recipes;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MealDay extends Fragment {
    private DataBase db=new DataBase();
    public List<HashMap<String, String>> list =new ArrayList<>();

    public MealDay() { db.GetAll("recipes");}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    Thread.sleep(2000);
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
        });
        return v;
    }


    private void getData(View v) {
        this.randomMenu(db.filterData("Çorba"));
        this.randomMenu(db.filterData("Salata"));
        this.randomMenu(db.filterData("Yemek"));
        this.randomMenu(db.filterData("Tatlı"));
        ListView lv = (ListView) v.findViewById(R.id.mealDayList);
        CustomAdapter adapter = new CustomAdapter(v.getContext(),list);
        lv.setAdapter(adapter);
    }
    public void randomMenu(List<HashMap<String,String>> menuList){
        DateFormat df = new SimpleDateFormat("d");
        String date = df.format(Calendar.getInstance().getTime());
        list.add(menuList.get(Integer.parseInt(date)%menuList.size()-1));
    }
}