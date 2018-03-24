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

/**
 * Created by zulfikaranshari on 24/03/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {

    private ArrayList<ListModel> mModel;
    private Context mContext;

    Adapter(Context context, ArrayList<ListModel> listData){
        this.mModel = listData;
        this.mContext = context;
    }
    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new AdapterViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(Adapter.AdapterViewHolder holder, int position) {
        ListModel currentModel = mModel.get(position);

        holder.bindTo(currentModel);
//        Glide.with(mContext).load()
    }

    @Override
    public int getItemCount() {
        return mModel.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
//        private TextView mID;
        private TextView mDesc;
        private TextView mPriority;
        private Context mContext;
        private ListModel mModel;

        public AdapterViewHolder(Context context, View itemView) {
            super(itemView);
//            mID = (TextView) itemView.findViewById(R.id.IDtxt) ;
            mTitle = (TextView) itemView.findViewById(R.id.titleTxt);
            mDesc = (TextView) itemView.findViewById(R.id.dscTxt);
            mPriority = (TextView) itemView.findViewById(R.id.priorityTxt);

            mContext = context;

        }

        void bindTo(ListModel currentModel){
//            mID.setText(currentModel.getID());
            mTitle.setText(currentModel.getName());
            mDesc.setText(currentModel.getDesc());
            mPriority.setText(currentModel.getPriority());

            mModel = currentModel;
        }
    }
}
