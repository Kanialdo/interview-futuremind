package pl.krystiankaniowski.futuremind.adapter.delegated;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.krystiankaniowski.futuremind.R;
import pl.krystiankaniowski.futuremind.adapter.ListManager;
import pl.krystiankaniowski.futuremind.adapter.view.ViewType;
import pl.krystiankaniowski.futuremind.adapter.view.ViewTypeDelegateAdapter;
import pl.krystiankaniowski.futuremind.model.database.Row;

public class DataDelegatedAdapter implements ViewTypeDelegateAdapter {

    // =============================================================================================
    //      VARIABLES
    // =============================================================================================

    private ListManager manager;

    // =============================================================================================
    //      CONSTRUCTOR
    // =============================================================================================

    public DataDelegatedAdapter(ListManager manager) {
        this.manager = manager;
    }

    // =============================================================================================
    //      LOGIC
    // =============================================================================================

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, ViewType item) {

        ViewHolder viewHolder = (ViewHolder) holder;
        final Row data = (Row) item;

        // TODO: async load image

        viewHolder.title.setText(data.getTitle());
        viewHolder.description.setText(data.getDescription());
        viewHolder.setOnClickListener(new Runnable() {
            @Override
            public void run() {
                manager.showContent(data.getTitle(), data.getUrl());
            }
        });

    }

    // =============================================================================================
    //      VIEW HOLDER
    // =============================================================================================

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_record_iv_avatar)
        ImageView avatar;

        @BindView(R.id.item_record_tv_title)
        TextView title;

        @BindView(R.id.item_record_tv_description)
        TextView description;

        public ViewHolder(View view) {
            super(view);

            // ButterKnife.bind(this, view);

            // to przez nagłe dziwne problemy z butterknifem

            avatar = ButterKnife.findById(view, R.id.item_record_iv_avatar);
            description = ButterKnife.findById(view, R.id.item_record_tv_title);
            title = ButterKnife.findById(view, R.id.item_record_tv_description);

        }

        public void setOnClickListener(final Runnable runnable) {
            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    runnable.run();
                }
            });
        }

    }

}
