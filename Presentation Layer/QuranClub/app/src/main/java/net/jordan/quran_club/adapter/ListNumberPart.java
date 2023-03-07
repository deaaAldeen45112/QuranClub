package net.jordan.quran_club.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.jordan.quran_club.R;

import java.util.ArrayList;

public class ListNumberPart extends RecyclerView.Adapter<ListNumberPart.NumberPartViewHolder> {

    int resource;
    Context context;
    ArrayList<String>numberParts=new ArrayList<>();

    public void setNumberParts(ArrayList<String> numberParts) {
        this.numberParts = numberParts;
        Toast.makeText(context, ""+numberParts.size(), Toast.LENGTH_SHORT).show();
        this.notifyDataSetChanged();
    }

    public ListNumberPart(Context context, int resource){this.context=context;this.resource=resource;}

    @NonNull
    @Override
    public NumberPartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resource,parent,false);
        ListNumberPart.NumberPartViewHolder numberPartViewHolder=new ListNumberPart.NumberPartViewHolder(view);
        return numberPartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberPartViewHolder holder, int position) {

        String numberPart=numberParts.get(position);
        // *********************textView*****************
        TextView textViewNumberPart = holder.textViewNumberPart;
       textViewNumberPart.setText(numberPart);


    }

    @Override
    public int getItemCount() {
        return numberParts.size();
    }

    class NumberPartViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumberPart;

        public NumberPartViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textViewNumberPart=itemView.findViewById(R.id.textView_number_part);
        }
    }

}
