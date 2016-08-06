package pl.krystiankaniowski.futuremind.adapter.delegated;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.krystiankaniowski.futuremind.R;
import pl.krystiankaniowski.futuremind.adapter.view.ViewType;
import pl.krystiankaniowski.futuremind.adapter.view.ViewTypeDelegateAdapter;

public class MessageDelegateAdapter implements ViewTypeDelegateAdapter {

    // =============================================================================================
    //      INNER CLASS
    // =============================================================================================

    public static class MessageItem implements ViewType {

        private final String message;

        public MessageItem(String message) {
            this.message = message;
        }

        @Override
        public int getViewType() {
            return ViewType.MESSAGE;
        }

        public String getMessage() {
            return message;
        }

    }

    // =============================================================================================
    //      LOGIC
    // =============================================================================================

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, ViewType item) {

        ViewHolder viewHolder = (ViewHolder) holder;
        final MessageItem data = (MessageItem) item;

        viewHolder.message.setText(data.getMessage());

    }

    // =============================================================================================
    //      VIEW HOLDER
    // =============================================================================================

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_message_tv_message)
        TextView message;

        public ViewHolder(View view) {
            super(view);
            message = ButterKnife.findById(view, R.id.item_message_tv_message);
        }

    }

}