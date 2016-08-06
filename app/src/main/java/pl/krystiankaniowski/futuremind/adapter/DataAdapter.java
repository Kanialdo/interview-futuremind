package pl.krystiankaniowski.futuremind.adapter;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.krystiankaniowski.futuremind.R;
import pl.krystiankaniowski.futuremind.adapter.delegated.DataDelegatedAdapter;
import pl.krystiankaniowski.futuremind.adapter.delegated.LoadingDelegateAdapter;
import pl.krystiankaniowski.futuremind.adapter.delegated.MessageDelegateAdapter;
import pl.krystiankaniowski.futuremind.adapter.view.ViewType;
import pl.krystiankaniowski.futuremind.adapter.view.ViewTypeDelegateAdapter;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // =============================================================================================
    //      VARIABLES
    // =============================================================================================

    private SparseArrayCompat<ViewTypeDelegateAdapter> delegatedAdapters = new SparseArrayCompat<>();
    private Context context;
    private List<ViewType> data;

    // =============================================================================================
    //      CONSTRUCTOR
    // =============================================================================================

    public DataAdapter(Context context, ListManager manager, List<ViewType> data) {

        this.context = context;
        this.data = data;

        delegatedAdapters.put(ViewType.ROW, new DataDelegatedAdapter(manager));
        delegatedAdapters.put(ViewType.MESSAGE, new MessageDelegateAdapter());
        delegatedAdapters.put(ViewType.LOADING, new LoadingDelegateAdapter());

        if (this.data == null) {
            this.data = new ArrayList<>();
            this.data.add(new MessageDelegateAdapter.MessageItem(context.getString(R.string.info_data_loading)));
            this.data.add(new LoadingDelegateAdapter.LoadingItem());
        }

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
        return data != null ? data.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getViewType();
    }

    public <Type extends ViewType> void setData(List<Type> data) {

        if (this.data != null) {
            this.data.clear();
            this.data.addAll(data);
        } else {
            this.data = new ArrayList<ViewType>(data);
        }

        notifyDataSetChanged();

    }

    public void setError(String error) {

        if (this.data != null) {
            this.data.clear();
        } else {
            this.data = new ArrayList<ViewType>(data);
        }

        data.add(new MessageDelegateAdapter.MessageItem(error));

        notifyDataSetChanged();

    }

}
