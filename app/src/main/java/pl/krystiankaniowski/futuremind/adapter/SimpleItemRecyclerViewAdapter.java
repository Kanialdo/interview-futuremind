package pl.krystiankaniowski.futuremind.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pl.krystiankaniowski.futuremind.ItemDetailFragment;
import pl.krystiankaniowski.futuremind.R;
import pl.krystiankaniowski.futuremind.dummy.DummyContent;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    // =============================================================================================
    //      VARIABLES
    // =============================================================================================

    private ListManager listManager;
    private final List<DummyContent.DummyItem> mValues;

    // =============================================================================================
    //      CONSTRUCTOR
    // =============================================================================================

    public SimpleItemRecyclerViewAdapter(ListManager listManager, List<DummyContent.DummyItem> items) {
        this.listManager = listManager;
        mValues = items;
    }

    // =============================================================================================
    //      LOGIC
    // =============================================================================================

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listManager.showContent(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    // =============================================================================================
    //      VIEW HOLDER
    // =============================================================================================

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}