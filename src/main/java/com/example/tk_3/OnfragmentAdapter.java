package com.example.tk_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class OnfragmentAdapter extends RecyclerView.Adapter {
    List<javaBean.DataBean> banner = new ArrayList<>();
    List<javaBean.DataBean> reclerview = new ArrayList<>();
    Context context;

    public OnfragmentAdapter(Context context) {
        this.context = context;
    }

    public static final int LIST_VIEW = 0;
    public static final int BANNER_VIEW = 1;

    public void onlistview( List<javaBean.DataBean> reclerview){
        this.reclerview.addAll(reclerview);
        notifyDataSetChanged();
    }
    public void onbanner(List<javaBean.DataBean> banner){
        this.banner.addAll(banner);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == LIST_VIEW) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_view, null);
            return new ListViewHlord(view);
        }
        if (viewType == BANNER_VIEW) {
            View view = LayoutInflater.from(context).inflate(R.layout.bann_view, null);
            return new BannViewHlord(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case BANNER_VIEW:
                break;
            case LIST_VIEW:
                if (banner.size() > 0 && position == position - 1) {

                }
                javaBean.DataBean dataBean = reclerview.get(position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return banner.size() > 0 ? reclerview.size() + 1 : reclerview.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && banner.size() > 0) {
            return BANNER_VIEW;
        } else {
            return LIST_VIEW;
        }
    }

    private class ListViewHlord extends RecyclerView.ViewHolder {

        private final TextView title;

        public ListViewHlord(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }

    private class BannViewHlord extends RecyclerView.ViewHolder {

        private final Banner banner;

        public BannViewHlord(View view) {
            super(view);
            banner = view.findViewById(R.id.banner);
        }
    }
}
