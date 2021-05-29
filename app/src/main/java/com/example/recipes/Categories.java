package com.example.recipes;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Categories extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_menu, container, false);
        CardView soup=(CardView) v.findViewById(R.id.categorySoups);
        soup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillOfFare billOfFare = new BillOfFare();
                Bundle args = new Bundle();
                args.putString("Category", "Çorba");
                billOfFare.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, billOfFare);
                transaction.commit();
            }
        });
        CardView food=(CardView) v.findViewById(R.id.categoryFood);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillOfFare billOfFare = new BillOfFare();
                Bundle args = new Bundle();
                args.putString("Category", "Yemek");
                billOfFare.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, billOfFare);
                transaction.commit();
            }
        });
        CardView salad=(CardView) v.findViewById(R.id.categorySalads);
        salad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillOfFare billOfFare = new BillOfFare();
                Bundle args = new Bundle();
                args.putString("Category", "Salata");
                billOfFare.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, billOfFare);
                transaction.commit();
            }
        });
        CardView desert=(CardView) v.findViewById(R.id.categoryDeserts);
        desert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillOfFare billOfFare = new BillOfFare();
                Bundle args = new Bundle();
                args.putString("Category", "Tatlı");
                billOfFare.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, billOfFare);
                transaction.commit();
            }
        });
        return v;
    }
}