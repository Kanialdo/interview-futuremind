package pl.krystiankaniowski.futuremind.adapter.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface ViewTypeDelegateAdapter {

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(RecyclerView.ViewHolder holder, ViewType item);

}