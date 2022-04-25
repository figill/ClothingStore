package com.example.assignment4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    public static final String KEY1 = "id";
    List<Cart> list;
    Context context;

    int totalPrice = 0;

    public CartAdapter(Context context, List<Cart> list) {
        this.list = list;
        this.context = context;
    }

    public CartAdapter(List<Cart> list) {
        this.list = list;
    }

    // Inner class - Provide a reference to each item/row
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtView, txtView2, txtView3;

        public MyViewHolder(View itemView) {
            super(itemView); //itemView corresponds to all views defined in row layout txt
            txtView = (TextView) itemView.findViewById(R.id.title);
            txtView2 = (TextView) itemView.findViewById(R.id.price);
            txtView3 = (TextView) itemView.findViewById(R.id.textView3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getLayoutPosition();
            Intent i = new Intent(view.getContext(), ShoppingCart.class);
            i.putExtra("Total Price", String.valueOf(totalPrice));
            context.startActivity(i);
        }


    }

    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new viewLayout
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cart_layout, parent, false); //false: inflate the row
        // layout to parent and return view, if true return parent+view
        CartAdapter.MyViewHolder viewHolder = new CartAdapter.MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {
        CartAdapter.MyViewHolder viewHolder = (CartAdapter.MyViewHolder) holder;

        viewHolder.txtView.setText(list.get(position).getTitle());
        viewHolder.txtView2.setText(list.get(position).getPrice());

       // int productPrice = (Integer.valueOf(list.get(position).getPrice())) * (Integer.valueOf(list.get(position).getQuantity()));
       // totalPrice = totalPrice + productPrice;

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public void filterList(ArrayList<Cart> filterllist) {
        list = filterllist;
        notifyDataSetChanged();
    }

    public void addItemtoEnd(Cart place) { //these functions are user-defined
        list.add(place);
        notifyItemInserted(list.size());
    }

    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void update(Cart place, int position) {
        list.set(position, place);
        notifyItemChanged(position);
    }
}
