package com.androidtutorialpoint.androidlogin;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
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

    //
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ConstraintLayout row;
        public TextView textView;
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);

            row = (ConstraintLayout) itemView.findViewById(R.id.a_row);
            textView = (TextView) itemView.findViewById(R.id.text);
            img = (ImageView) itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick (View v) {
            int pos = getAdapterPosition();
            Toast.makeText(v.getContext(), msgList.get(pos), Toast.LENGTH_LONG).show();
        }
    }

    //

    ArrayList<String> msgList;

    public RAdapter(Context c) {
        msgList = new ArrayList<String>();
        msgList.add("Hello");
        msgList.add("How are you");
        msgList.add("Gooood!");
    }

    @Override
    public void onBindViewHolder(RAdapter.ViewHolder viewHolder, int i) {
        TextView textView = viewHolder.textView;
        textView.setText(msgList.get(i));

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
