package com.xtha.zujal.lyrico;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;


public class ReadEditDelete extends Fragment {

    Context context;
    EditText edituptitle;
    EditText editupdes;
    TextView tedit;
    TextView ledit;
    TextView dedit;
    Button edit;
    Button delete;
    Button update;

    DatabaseHelper myDb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context =container.getContext();

        View view = inflater.inflate(R.layout.fragment_read_edit_delete, container, false);

        tedit =(TextView)view.findViewById(R.id.titleedit);
        ledit =(TextView)view.findViewById(R.id.lyricsedit);
        dedit =(TextView)view.findViewById(R.id.dateedit);

        edituptitle =(EditText)view.findViewById(R.id.edituptitle);
        editupdes =(EditText)view.findViewById(R.id.editupdes);

        edituptitle.setVisibility(View.GONE);
        editupdes.setVisibility(View.GONE);


       Bundle bundle=getArguments();

       final String sid=bundle.getString("ide");
//

        if (getArguments() != null) {

            tedit.setText(String.valueOf(bundle.getString("titlee")));
            ledit.setText(String.valueOf(bundle.getString("lyricse")));
            dedit.setText(String.valueOf(bundle.getString("datee")));

            edituptitle.setText(String.valueOf(bundle.getString("titlee")));
            editupdes.setText(String.valueOf(bundle.getString("lyricse")));
        }

        delete =(Button) view.findViewById(R.id.deletebut);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db= new DatabaseHelper(context);
                boolean isDeleted=  db.deleteData(sid);

                if(isDeleted == true) {
                    Toast.makeText(context, "Data Deleted", Toast.LENGTH_LONG).show();

                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new Dashboard());
                    fr.commit();
                }
                else
                    Toast.makeText(context,"Data not Deleted",Toast.LENGTH_LONG).show();


            }
        });

        edit =(Button) view.findViewById(R.id.editbut);
        update =(Button) view.findViewById(R.id.updatebut);
        update.setVisibility(View.GONE);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update.setVisibility(View.VISIBLE);
                edituptitle.setVisibility(View.VISIBLE);
                editupdes.setVisibility(View.VISIBLE);
                tedit.setVisibility(View.GONE);
                ledit.setVisibility(View.GONE);
                edit.setVisibility(View.GONE);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDb = new DatabaseHelper(context);
                long date = System.currentTimeMillis();

                SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy hh:mm ");
                String dateStringup = sdf.format(date);

                Toast.makeText(context,dateStringup,Toast.LENGTH_LONG).show();

                boolean isupdated = myDb.updateData( sid,
                        edituptitle.getText().toString(),
                        editupdes.getText().toString(),
                        dateStringup );


                if(isupdated == true)
                    Toast.makeText(context,"Data updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context,"Data not updated",Toast.LENGTH_LONG).show();


                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Dashboard());
                fr.commit();

            }
        });


        return view;



    }


}
