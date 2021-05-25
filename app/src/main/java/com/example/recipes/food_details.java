package com.example.recipes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link food_details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class food_details extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String  Title;
    private String  Description;
    private String  Recipe;
    private String  ImagePath;

    public food_details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment food_details.
     */
    // TODO: Rename and change types and number of parameters
    public static food_details newInstance(String param1, String param2) {
        food_details fragment = new food_details();
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
        Title = getArguments().getString("Title");
        Description = getArguments().getString("Description");
        Recipe = getArguments().getString("Recipe");
        ImagePath = getArguments().getString("ImagePath");
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_food_details, container, false);

        TextView detailTitle=(TextView) v.findViewById(R.id.detailTitle);
        TextView detailDescription=(TextView) v.findViewById(R.id.detailDescription);
        TextView detailRecipe=(TextView) v.findViewById(R.id.detailRecipe);
        detailTitle.setText(Title);
        detailDescription.setText(Description);
        detailRecipe.setText(Recipe);
        return v;
    }
}