package com.example.assignment4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReceiptAdpater extends RecyclerView.Adapter<ReceiptAdpater.MyViewHolder>{


        List<OrderReceipt> list;

public ReceiptAdpater(List<OrderReceipt> list){

        this.list=list;
        }

// Inner class - Provide a reference to each item/row
public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtView, txtView2, txtView3, txtView4, txtView5;

    public MyViewHolder(View itemView) {
        super(itemView); //itemView corresponds to all views defined in row layout txt
        txtView = (TextView) itemView.findViewById(R.id.custID); //text View is id of text view defined
        txtView2 = (TextView) itemView.findViewById(R.id.email);
        txtView3 = (TextView) itemView.findViewById(R.id.payment);
        txtView4 = (TextView) itemView.findViewById(R.id.price);
        txtView5 = (TextView) itemView.findViewById(R.id.date);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int position = this.getLayoutPosition();

    }

}

    @Override
    public ReceiptAdpater.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new viewLayout
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.receipts_layout, parent, false); //false: inflate the row
        // layout to parent and return view, if true return parent+view
        ReceiptAdpater.MyViewHolder viewHolder = new ReceiptAdpater.MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptAdpater.MyViewHolder holder, int position) {
        ReceiptAdpater.MyViewHolder viewHolder = (ReceiptAdpater.MyViewHolder) holder;

        viewHolder.txtView.setText(list.get(position).getUserID());
        viewHolder.txtView2.setText(list.get(position).getUserEmail());
        viewHolder.txtView3.setText(list.get(position).getPaymentType());
        viewHolder.txtView4.setText(list.get(position).getTotalPrice());
        viewHolder.txtView5.setText(list.get(position).getDate());

    }


    @Override
    public int getItemCount() {

        return list.size();
    }


    public void filterList(ArrayList<OrderReceipt> filterllist) {
        list = filterllist;
        notifyDataSetChanged();
    }

    public void addItemtoEnd(OrderReceipt place) { //these functions are user-defined
        list.add(place);
        notifyItemInserted(list.size());
    }

    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void update(OrderReceipt place, int position) {
        list.set(position, place);
        notifyItemChanged(position);
    }
}
