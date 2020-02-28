package com.example.facebook;

        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment {

    public static FragmentOne newInstance(){

        FragmentOne fragmentOne = new FragmentOne();
        return fragmentOne;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one,container,false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyDataBase= new FB_Database(getContext(),DB_NAME,null,DB_VERSION);

        name =(EditText) view.findViewById(R.id.name);
        age=(EditText)view.findViewById(R.id.age);
        marks=(EditText)view.findViewById(R.id.marks);
        Button1=(Button)view.findViewById(R.id.btn);
        addData();


    }

    public void addData(){
        Button1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInsert= MyDataBase.insertData(name.getText().toString(),age.getText().toString(),marks.getText().toString());
                        if (isInsert=true)
                            Toast.makeText(getContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getContext(), "Data Inserted", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }


}



}
