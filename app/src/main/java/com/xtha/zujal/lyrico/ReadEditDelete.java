package com.xtha.zujal.lyrico;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ReadEditDelete extends Fragment {

    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_read_edit_delete, container, false);

        TextView tedit =(TextView)view.findViewById(R.id.titleedit);
        TextView ledit =(TextView)view.findViewById(R.id.lyricsedit);
        TextView dedit =(TextView)view.findViewById(R.id.dateedit);


        tedit.setText("");
       Bundle bundle=getArguments();
//

        if (getArguments() != null) {

            tedit.setText(String.valueOf(bundle.getString("titlee")));
            ledit.setText(String.valueOf(bundle.getString("lyricse")));
            dedit.setText(String.valueOf(bundle.getString("datee")));
        }




        return view;



    }


}
