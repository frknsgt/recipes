package com.example.recipes;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class food_details extends Fragment {
    List<HashMap<String,String>> list=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_food_details, container, false);
        HashMap hm=new HashMap();
        hm.put("ImagePath", getArguments().getString("ImagePath"));
        hm.put("Title", getArguments().getString("Title"));
        hm.put("Recipe", getArguments().getString("Recipe"));
        hm.put("Description",  getArguments().getString("Description"));
        list.add(hm);
        CustomDetailAdapter adapter = new CustomDetailAdapter(v.getContext(),list);
        ListView lv = (ListView) v.findViewById(R.id.detailFood);
        lv.setAdapter(adapter);
        return v;
    }
}