package com.example.recipes;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Categories#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Categories extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Categories() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    public static Categories newInstance(String param1, String param2) {
        Categories fragment = new Categories();
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
    public void click(){

    }
}