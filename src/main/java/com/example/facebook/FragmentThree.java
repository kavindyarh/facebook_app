package com.example.facebook;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.facebook.Database.Db_Name;

public class FragmentThree extends Fragment {

    Database db;
    TextView textView;

    public FragmentThree() {
    }


    public static FragmentThree newInstance() {
        FragmentThree fragmentThree = new FragmentThree();
        return fragmentThree;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container , false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new Database(getContext(),Db_Name,null,1);
        textView = view.findViewById(R.id.viewAllRecords);
        viewAllData();

    }

    public void viewAllData(){

        Cursor resultSet = db.getAllRecords();



        StringBuffer buffer = new StringBuffer();


        while (resultSet.moveToNext()){
            buffer.append(resultSet.getString(1)+"\n");
            buffer.append(resultSet.getString(2)+"\n");
            buffer.append(resultSet.getString(3)+"\n");

        }
        textView.setText(buffer.toString());


    }
}
