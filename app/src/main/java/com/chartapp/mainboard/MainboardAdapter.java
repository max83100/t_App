package com.chartapp.mainboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chartapp.R;

import java.util.ArrayList;
import java.util.List;


public class MainboardAdapter extends RecyclerView.Adapter<MainboardAdapter.ViewHolder> implements Filterable {

    private List<MainboardData> exampleList;
    private List<MainboardData> exampleListFull;


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.piccatalog);
            textView1 = itemView.findViewById(R.id.namecatalog);
            textView2 = itemView.findViewById(R.id.countcatalog);
        }
    }

    public MainboardAdapter(List<MainboardData> exampleList) {
        this.exampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MainboardData currentItem = exampleList.get(position);

        holder.imageView.setImageResource(currentItem.getImageView());
        holder.textView1.setText(currentItem.getName());
        holder.textView2.setText(currentItem.getCount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Mainboard_catalog.class);
                switch (position){
                    case 0:
                        intent.putExtra("testNameData", "apple");
                        break;
                    case 1:
                        intent.putExtra("testNameData", "asrock");
                        break;
                    case 2:
                        intent.putExtra("testNameData", "asus");
                        break;
                    case 3:
                        intent.putExtra("testNameData", "ecs");
                        break;
                    case 4:
                        intent.putExtra("testNameData", "foxconn");
                        break;
                    case 5:
                        intent.putExtra("testNameData", "gigabyte");
                        break;


                }

                v.getContext().startActivity(intent);

            }
        });
    }




    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MainboardData> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (MainboardData item : exampleListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}