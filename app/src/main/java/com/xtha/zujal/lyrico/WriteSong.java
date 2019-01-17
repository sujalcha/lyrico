package com.xtha.zujal.lyrico;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;


public class WriteSong extends Fragment {

    Button btnAddSong;
    DatabaseHelper myDb;
    EditText edittitle,editlyrics;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        context =container.getContext();

        View view = inflater.inflate(R.layout.fragment_write_song,container,false);

        edittitle = (EditText)view.findViewById(R.id.edittitle);
        editlyrics = (EditText)view.findViewById(R.id.editlyrics);
        btnAddSong = (Button)view.findViewById(R.id.SaveSong);

        SaveSong();
        return view;


    }


    public void SaveSong() {
        btnAddSong.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        myDb = new DatabaseHelper(context);
                        long date = System.currentTimeMillis();

                        SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy hh:mm ");
                        String dateString = sdf.format(date);

                        Toast.makeText(context,dateString,Toast.LENGTH_LONG).show();

                        boolean isInserted = myDb.insertData(   edittitle.getText().toString(),
                                                                editlyrics.getText().toString(),
                                                                dateString );


                       if(isInserted == true)
                            Toast.makeText(context,"Data Inserted",Toast.LENGTH_LONG).show();
                      else
                            Toast.makeText(context,"Data not Inserted",Toast.LENGTH_LONG).show();


                        FragmentTransaction fr=getFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container, new Dashboard());
                        fr.commit();

                    }
                }
        );
    }
}
