package com.example.facebook;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.facebook.database.myDataBase;
import com.example.facebook.entities.StudentList;

import java.util.List;

import static android.content.Context.SEARCH_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class FragmentTwo extends Fragment {
    public static FragmentTwo newInstance(){
        FragmentTwo fragmentTwo = new FragmentTwo();
        return fragmentTwo;
    }

    private ListView searchListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        searchListView = (ListView)view.findViewById(R.id.searchView);

        return view;
    }

    private void initView(){
        searchListView = getView().findViewById(R.id.searchView);
        registerForContextMenu(searchListView);
    }



//    private void loadData(){
//
//        myDataBase dataBase = new myDataBase(getActivity().getApplicationContext());
//        List<StudentList> studentList = dataBase.getAllStudent();
//    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchBox).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchStudent(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchStudent(newText);
                return false;
            }
        });
    }

    private void searchStudent(String keyword){
        myDataBase dataBase = new myDataBase(getActivity().getApplicationContext());
        List<StudentList> studentList = dataBase.search(keyword);
        if (studentList != null){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.id.searchView,studentList);
            searchListView.setAdapter(adapter);
        }
    }

    protected int getFragment2(){
        return R.layout.fragment_two;
    }

}
