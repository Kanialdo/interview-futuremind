package pl.krystiankaniowski.futuremind.adapter;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import pl.krystiankaniowski.futuremind.adapter.delegated.DataDelegatedAdapter;
import pl.krystiankaniowski.futuremind.adapter.view.ViewType;
import pl.krystiankaniowski.futuremind.adapter.view.ViewTypeDelegateAdapter;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // =============================================================================================
    //      VARIABLES
    // =============================================================================================

    private SparseArrayCompat<ViewTypeDelegateAdapter> delegatedAdapters = new SparseArrayCompat<>();
    private List<ViewType> data;

    // =============================================================================================
    //      CONSTRUCTOR
    // =============================================================================================

    public DataAdapter(List<ViewType> data) {

        this.data = data;

        delegatedAdapters.put(ViewType.ROW, new DataDelegatedAdapter());

    }

    // =============================================================================================
    //      LOGIC
    // =============================================================================================

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatedAdapters.get(viewType).onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewType item = data.get(position);
        delegatedAdapters.get(item.getViewType()).onBindViewHolder(holder, item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}