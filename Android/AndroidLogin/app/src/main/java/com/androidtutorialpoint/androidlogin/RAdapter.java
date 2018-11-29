package com.androidtutorialpoint.androidlogin;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View.OnClickListener;
import android.util.Log;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;


public class RAdapter extends RecyclerView.Adapter<RAdapter.ViewHolder> {

//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public ConstraintLayout row;
//        public TextView textView;
//        public ImageView img;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            row = (ConstraintLayout) itemView.findViewById(R.id.a_row);
//            textView = (TextView) itemView.findViewById(R.id.text);
//            img = (ImageView) itemView.findViewById(R.id.image);
//        }
//    }

    //public myDbAdapter helper = new myDbAdapter(this);
    //
    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout row;
        public TextView textView,textView1;
        public ImageView img;
        public Button btnViewProfile;
        //private OnClickListener mOnClickListener = new OnClickListener();




        public ViewHolder(View itemView) {
            super(itemView);
            row = (LinearLayout) itemView.findViewById(R.id.a_row);
            textView = (TextView) itemView.findViewById(R.id.textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    int pos = getAdapterPosition();
                    Toast.makeText(v.getContext(), msgList.get(pos), Toast.LENGTH_LONG).show();
                }

            });
            //textView1= (TextView) itemView.findViewById(R.id.text1);
            //img = (ImageView) itemView.findViewById(R.id.imageView);
           // btnViewProfile = (Button) itemView.findViewById(R.id.btn_view_profile); // this button is creating some error
        }



    }
    //

    ArrayList<String> msgList;

    public RAdapter(Context c , myDbAdapter helper) {
        // here the function will come that will fetch the data from the backend.
         //myDbAdapter helper = new myDbAdapter(Context);
        msgList = new ArrayList<String>();

        msgList = helper.getEmails("c++");   // here the programming language from the context will come

        if(msgList.size()==0){   //this is because not proper data..

            msgList.add("One");
            msgList.add("two");
            msgList.add("three");
            msgList.add("four");
            msgList.add("five");
        }
    }

    @Override
    public void onBindViewHolder(RAdapter.ViewHolder viewHolder, int i) {
        Log.d("CREATION" , "Inside onBingViewHolder");
        TextView textView = viewHolder.textView;
        //TextView textView1 = viewHolder.textView1;
       // Button button = viewHolder.btnViewProfile;
        if(msgList.get(i)!=null){
        textView.setText(msgList.get(i));}
      //  textView1.setText(msgList.get(i));
       // button.setText(msgList.get(i));

    }

    @Override
    public int getItemCount() {

         return msgList.size();

    }


    @Override
    public RAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.row, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }
}
