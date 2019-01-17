package com.xtha.zujal.lyrico;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class Dashboard extends Fragment {
    DatabaseHelper myDb;
    Context context;
    Button btnviewAll;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context =container.getContext();

        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);


        myDb = new DatabaseHelper(this.context);

        Button Addsong=(Button)view.findViewById(R.id.addsong);
        Addsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new WriteSong());
                fr.commit();

            }
        });

        ArrayList<HashMap<String, String>> userList = myDb.GetUsers();
        ListView lv = (ListView) view.findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(context, userList, R.layout.songinfo,new String[]{"title","lyrics","date"}, new int[]{R.id.name, R.id.designation, R.id.location});
        lv.setAdapter(adapter);



        viewAll();
        return view;
    }


    public void viewAll() {

//        btnviewAll.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        myDb = new DatabaseHelper(context);
//
//                        Cursor res = myDb.getAllData();
//                        if(res.getCount() == 0) {
//                            // show message
//                            showMessage("Error","Nothing found");
//                            return;
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while (res.moveToNext()) {
//                            buffer.append("Id :"+ res.getString(0)+"\n");
//                            buffer.append("Name :"+ res.getString(1)+"\n");
//                            buffer.append("Surname :"+ res.getString(2)+"\n");
//                            buffer.append("Marks :"+ res.getString(3)+"\n\n");
//                        }
//
//                        // Show all data
//                        showMessage("Data",buffer.toString());
//                    }
//                }
//        );


    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
