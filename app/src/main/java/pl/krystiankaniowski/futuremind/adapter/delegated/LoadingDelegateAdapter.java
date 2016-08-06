package pl.krystiankaniowski.futuremind.adapter.delegated;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import pl.krystiankaniowski.futuremind.R;
import pl.krystiankaniowski.futuremind.adapter.view.ViewType;
import pl.krystiankaniowski.futuremind.adapter.view.ViewTypeDelegateAdapter;

public class LoadingDelegateAdapter implements ViewTypeDelegateAdapter {

    // =============================================================================================
    //      INNER CLASS
    // =============================================================================================

    public static class LoadingItem implements ViewType {
        @Override
        public int getViewType() {
            return ViewType.LOADING;
        }
    }

    // =============================================================================================
    //      LOGIC
    // =============================================================================================

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, ViewType item) {
    }

}
