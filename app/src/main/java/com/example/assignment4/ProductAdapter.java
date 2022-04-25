package com.example.assignment4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {


    List<StockModel> list;

    public ProductAdapter(List<StockModel> list) {

        this.list = list;
    }

    // Inner class - Provide a reference to each item/row
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtView, txtView2, txtView3, txtView4, txtView5;

        public MyViewHolder(View itemView) {
            super(itemView); //itemView corresponds to all views defined in row layout txt
            txtView = (TextView) itemView.findViewById(R.id.title); //text View is id of text view defined
            txtView2 = (TextView) itemView.findViewById(R.id.manufacturer);
            txtView3 = (TextView) itemView.findViewById(R.id.category);
            txtView4 = (TextView) itemView.findViewById(R.id.price);
            txtView5 = (TextView) itemView.findViewById(R.id.quantity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getLayoutPosition();

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new viewLayout
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.products_layout, parent, false); //false: inflate the row
        // layout to parent and return view, if true return parent+view
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;

        viewHolder.txtView.setText(list.get(position).getTitle());
        viewHolder.txtView2.setText(list.get(position).getManufacturer());
        viewHolder.txtView3.setText(list.get(position).getCategory());
        viewHolder.txtView4.setText(list.get(position).getPrice());
        viewHolder.txtView5.setText(list.get(position).getQuantity());


    }


    @Override
    public int getItemCount() {

        return list.size();
    }


    public void filterList(ArrayList<StockModel> filterllist) {
        list = filterllist;
        notifyDataSetChanged();
    }

    public void addItemtoEnd(StockModel place) { //these functions are user-defined
        list.add(place);
        notifyItemInserted(list.size());
    }

    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void update(StockModel place, int position) {
        list.set(position, place);
        notifyItemChanged(position);
    }
}
