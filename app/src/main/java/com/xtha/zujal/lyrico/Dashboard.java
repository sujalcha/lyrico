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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class Dashboard extends Fragment {
    DatabaseHelper myDb;
    Context context;
    int ssid;
    String sid="";
    String stitle="";
    String slyrics="";
    String sdate="";


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

        final ArrayList<HashMap<String, String>> userList = myDb.GetUsers();
        final ListView lv = (ListView) view.findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(context, userList, R.layout.songinfo,new String[]{"title","lyrics","date"}, new int[]{R.id.name, R.id.designation, R.id.location});


        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();

                ssid = Integer.parseInt(userList.get(position).get("id"));

                String po=String.valueOf(position);
                //Toast.makeText(context,po.toString(), Toast.LENGTH_SHORT).show();

                ArrayList<HashMap<String, String>>userListfromposion=myDb.GetUserByUserId(ssid);

                //Toast.makeText(context,userListfromposion.toString(), Toast.LENGTH_SHORT).show();


                if(userListfromposion!= null && userListfromposion.size() !=0) {
                     sid = String.valueOf(userListfromposion.get(0).get("id"));
                     stitle = String.valueOf(userListfromposion.get(0).get("title"));
                     slyrics = String.valueOf(userListfromposion.get(0).get("lyrics"));
                     sdate = String.valueOf(userListfromposion.get(0).get("date"));

                    Toast.makeText(context,sid+stitle+slyrics+sdate.toString(), Toast.LENGTH_SHORT).show();
                }

                Bundle bundle=new Bundle();
                bundle.putString("ide",sid);
                bundle.putString("titlee",stitle);
                bundle.putString("lyricse",slyrics);
                bundle.putString("datee",sdate);
                ReadEditDelete readEditDelete=new ReadEditDelete();
                readEditDelete.setArguments(bundle); //data being send to SecondFragment
                fr.replace(R.id.fragment_container, readEditDelete);
                fr.commit();

            }
        });
        return view;
    }




}
