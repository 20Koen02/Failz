package nl.koen02.failz.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import nl.koen02.failz.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private List<ListItemData> itemList;
    private ClickListener<ListItemData> clickListener;

    RecyclerViewAdapter(List<ListItemData> itemList){
        this.itemList = itemList;
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_main,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, final int position) {

        final ListItemData data = itemList.get(position);

        holder.code.setText(data.getCode());
        switch (data.getVaksoort()) {
            case PROPEDEUSE:
                holder.vaksoort.setText(R.string.vaksoort_propedeuse);
                break;
            case HOOFDFASE:
                holder.vaksoort.setText(R.string.vaksoort_hoofdfase);
                break;
            case KEUZEVAK:
                holder.vaksoort.setText(R.string.vaksoort_keuzevak);
                break;
        }
        holder.cijfer.setText(String.format(Locale.ENGLISH, "%.1f", data.getCijfer()));
        holder.ec.setText(String.format(Locale.ENGLISH, "%d EC", data.getEc()));
        holder.cardView.setOnClickListener(v -> clickListener.onItemClick(data));


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setOnItemClickListener(ClickListener<ListItemData> movieClickListener) {
        this.clickListener = movieClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView code;
        private final TextView vaksoort;
        private final TextView cijfer;
        private final TextView ec;
        private final CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            code = itemView.findViewById(R.id.code);
            vaksoort = itemView.findViewById(R.id.vaksoort);
            cijfer = itemView.findViewById(R.id.cijfer);
            ec = itemView.findViewById(R.id.ec);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}

interface ClickListener<T> {
    void onItemClick(T data);
}