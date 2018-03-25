package com.example.zulfikaranshari.zulfikaransharioktafinawan_1202154136_modul5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zulfikaranshari on 24/03/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {
    private LayoutInflater mInflater;
    private List<ListModel> mModel;
    private Context mContext;
    int id;
    DbHelper dbHelper;
    MainActivity main = new MainActivity();

    public Adapter(Context context, List<ListModel> listModel){
        mInflater = LayoutInflater.from(context);
        this.mModel = listModel;
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItem = mInflater.inflate(R.layout.item_list, parent, false);
        return new AdapterViewHolder(mItem);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        ListModel listModel = this.mModel.get(position);
        id=listModel.getID();
        holder.title.setText(listModel.getName());
        holder.desc.setText(listModel.getDesc());
        holder.priority.setText(listModel.getPriority());
    }

    @Override
    public int getItemCount() {
        return mModel.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView desc;
        public TextView priority;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titleTxt);
            desc = (TextView) itemView.findViewById(R.id.dscTxt);
            priority = (TextView) itemView.findViewById(R.id.priorityTxt);

        }
    }
}
