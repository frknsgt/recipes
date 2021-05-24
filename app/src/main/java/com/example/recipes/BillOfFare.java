package com.example.recipes;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BillOfFare#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillOfFare extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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
        View v= inflater.inflate(R.layout.fragment_bill_of_fare, container, false);
        ListView lv = (ListView) v.findViewById(R.id.foodList);
        String[] custom={"ImagePath","Title","Description"};
        int[] customId={R.id.foodImage,R.id.foodTitle,R.id.foodDescription};
        List<HashMap<String,String>> list=new ArrayList<>();

        HashMap hm=new HashMap();
        hm.put("ImagePath",R.drawable.cake);
        hm.put("Title","Baslik");
        hm.put("Description","Lorem Ipsum, dizgi ve baskı endüstrdir. Lorem Ipsum, adırıştırdığı 1500'lerden");
        list.add(hm);

        hm=new HashMap();
        hm.put("ImagePath",R.drawable.cake);
        hm.put("Title","Baslik");
        hm.put("Description","Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mrem Ipsum, adı bilinmı 1500'lerden");
        list.add(hm);

        hm=new HashMap();
        hm.put("ImagePath",R.drawable.cake);
        hm.put("Title","Baslik");
        hm.put("Description","Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgıırdığı 1500'lerden");
        list.add(hm);
        hm=new HashMap();
        hm.put("ImagePath",R.drawable.cake);
        hm.put("Title","Baslik");
        hm.put("Description","Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinl bilinmeyen bir matbn");
        list.add(hm);
        hm=new HashMap();
        hm.put("ImagePath",R.drawable.cake);
        hm.put("Title","Baslik");
        hm.put("Description","Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinleilinmeyen bir matbaac");
        list.add(hm);

        SimpleAdapter adapter=new SimpleAdapter( getContext() ,list,R.layout.food_custom,custom,customId);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap hm= list.get(position);
                String value=(String) hm.get("Title").toString() ;
                Log.i("DENEME",value);
            }
        });
        return v;
    }
}

