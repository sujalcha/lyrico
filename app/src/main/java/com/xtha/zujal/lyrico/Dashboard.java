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

        final ArrayList<HashMap<String, String>> userList = myDb.GetUsers();
        final ListView lv = (ListView) view.findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(context, userList, R.layout.songinfo,new String[]{"title","lyrics","date"}, new int[]{R.id.name, R.id.designation, R.id.location});


        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                FragmentTransaction fr=getFragmentManager().beginTransaction();

                final int ssid = Integer.parseInt(userList.get(position).get("id"));

                Object listItem = lv.getItemAtPosition(position);

                String po=String.valueOf(position);
             //   Toast.makeText(context,po.toString(), Toast.LENGTH_SHORT).show();

                ArrayList<HashMap<String, String>>userListfromposion=myDb.GetUserByUserId(ssid);

               // Toast.makeText(context,userListfromposion.toString(), Toast.LENGTH_SHORT).show();

                String sid="";
                String stitle="";
                String slyrics="";
                String sdate="";
                if(userListfromposion!= null && userListfromposion.size() !=0) {
                     sid = String.valueOf(userListfromposion.get(0).get("id"));
                     stitle = String.valueOf(userListfromposion.get(0).get("title"));
                     slyrics = String.valueOf(userListfromposion.get(0).get("lyrics"));
                     sdate = String.valueOf(userListfromposion.get(0).get("date"));

                    Toast.makeText(context,sid+stitle+slyrics+sdate.toString(), Toast.LENGTH_SHORT).show();
                }






             //  Toast.makeText(context,stitle+slyrics+sdate.toString(), Toast.LENGTH_SHORT).show();

//                ListAdapter adapter = new SimpleAdapter(context, userListfromposion, R.layout.fragment_read_edit_delete,new String[]{"title","lyrics","date"}, new int[]{R.id.titleedit, R.id.lyricsedit, R.id.dateedit});
//                lv.setAdapter(adapter);

                Bundle bundle=new Bundle();
                bundle.putString("ide",sid);
                bundle.putString("titlee",stitle);
                bundle.putString("lyricse",slyrics);
                bundle.putString("datee",sdate);
                ReadEditDelete readEditDelete=new ReadEditDelete();
                readEditDelete.setArguments(bundle); //data being send to SecondFragment
                fr.replace(R.id.fragment_container, readEditDelete);
                fr.commit();


//                TextView tedit =(TextView)view.findViewById(R.id.titleedit);
//                TextView ledit =(TextView)view.findViewById(R.id.lyricsedit);
//                TextView dedit =(TextView)view.findViewById(R.id.editdate);
//
//                String stitle = tedit.getText().toString();
//                String slyrics = ledit.getText().toString();
//                String sdate = dedit.getText().toString();
//
//                Toast.makeText(context,stitle,Toast.LENGTH_LONG).show();



            }
        });



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
