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
import android.widget.TextView;
import android.widget.Toast;


public class ReadEditDelete extends Fragment {

    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context =container.getContext();

        View view = inflater.inflate(R.layout.fragment_read_edit_delete, container, false);

        TextView tedit =(TextView)view.findViewById(R.id.titleedit);
        TextView ledit =(TextView)view.findViewById(R.id.lyricsedit);
        TextView dedit =(TextView)view.findViewById(R.id.dateedit);



       Bundle bundle=getArguments();

       final String sid=bundle.getString("id");
//

        if (getArguments() != null) {

            tedit.setText(String.valueOf(bundle.getString("titlee")));
            ledit.setText(String.valueOf(bundle.getString("lyricse")));
            dedit.setText(String.valueOf(bundle.getString("datee")));
        }

        Button delete =(Button) view.findViewById(R.id.deletebut);

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



        return view;



    }


}
