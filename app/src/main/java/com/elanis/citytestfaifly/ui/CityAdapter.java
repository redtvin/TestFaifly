package com.elanis.citytestfaifly.ui;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elanis.citytestfaifly.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {


    public interface ItemClickListener {
        void onItemClicked(String city);
    }

    private List<String> cityData = new ArrayList<>();
    private ItemClickListener itemClickListener;

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bindData(cityData.get(position),itemClickListener);
    }

    public void setListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return cityData.size();
    }

    public void setData(List<String> cityData) {
        this.cityData = cityData;
        notifyDataSetChanged();
    }

    public void addItem(String city) {
        cityData.add(city);
        notifyItemInserted(cityData.size() - 1);
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_city)
        TextView tvCity;

        public CityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(final String city, final ItemClickListener itemClickListener) {
            tvCity.setText(city);
            tvCity.getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null){
                        itemClickListener.onItemClicked(city);
                    }
                }
            });
        }
    }
}
