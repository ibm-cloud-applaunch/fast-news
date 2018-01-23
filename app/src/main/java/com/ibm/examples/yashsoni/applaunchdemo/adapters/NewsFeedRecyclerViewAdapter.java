package com.ibm.examples.yashsoni.applaunchdemo.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibm.examples.yashsoni.applaunchdemo.R;
import com.ibm.examples.yashsoni.applaunchdemo.interfaces.OnItemClickListener;
import com.ibm.examples.yashsoni.applaunchdemo.models.NewsFeedModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yashsoni on 22/01/18.
 */

public class NewsFeedRecyclerViewAdapter extends Adapter<NewsFeedRecyclerViewAdapter.NewsFeedViewHolder> {

    private List<NewsFeedModel> dataList;
    private OnItemClickListener clickListener;

    public NewsFeedRecyclerViewAdapter(OnItemClickListener clickListener){
        this.dataList = new ArrayList<>();
        this.clickListener = clickListener;
    }

    public List<NewsFeedModel> getDataList() {
        return dataList;
    }

    public void setDataList(List<NewsFeedModel> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
    }

    @Override
    public NewsFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_feed_list_item, parent, false);
        return new NewsFeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsFeedViewHolder holder, int position) {
        NewsFeedModel feedModel = dataList.get(position);
        holder.bindData(feedModel, clickListener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class NewsFeedViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout itemView;
        TextView tvNewsTitle;
        TextView tvNewsDesc;
        ImageView ivNewsImage;
        ImageView ivAudioAvailable;

        public NewsFeedViewHolder(View view) {
            super(view);
            itemView = view.findViewById(R.id.rl_list_item);
            tvNewsTitle = view.findViewById(R.id.tv_news_title);
            tvNewsDesc = view.findViewById(R.id.tv_news_desc);
            ivNewsImage = view.findViewById(R.id.iv_news_image);
            ivAudioAvailable = view.findViewById(R.id.iv_audio_available);
        }

        public void bindData(final NewsFeedModel feedModel, final OnItemClickListener clickListener){
            tvNewsTitle.setText(feedModel.title);

            if(feedModel.isAudioAvailable){
                tvNewsDesc.setVisibility(View.GONE);
                ivAudioAvailable.setVisibility(View.VISIBLE);
            } else {
                ivAudioAvailable.setVisibility(View.GONE);
                tvNewsDesc.setVisibility(View.VISIBLE);
                tvNewsDesc.setText(feedModel.desc);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(feedModel);
                }
            });
        }
    }
}
