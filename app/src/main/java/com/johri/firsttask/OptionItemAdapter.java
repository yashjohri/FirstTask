package com.johri.firsttask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class OptionItemAdapter extends RecyclerView.Adapter<OptionItemAdapter.MyHolder> {

    private Context context;
    private ArrayList<FacilityOptionItem> optionItems;
    private HashMap<String, String> facilityOptionMap;
    private int positionSelected;
    private String facility_id;

    public OptionItemAdapter(Context context, ArrayList<FacilityOptionItem> optionItems, HashMap<String, String> facilityOptionMap) {
        this.context = context;
        this.optionItems = optionItems;
        this.facilityOptionMap = facilityOptionMap;
        positionSelected = -1;
    }

    public void setPositionSelected(int positionSelected){
        this.positionSelected = positionSelected;
    }

    public void setFacility_id(String facility_id){
        this.facility_id = facility_id;
    }

    @NonNull
    @Override
    public OptionItemAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_option_item, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionItemAdapter.MyHolder holder, int position) {
        FacilityOptionItem item = optionItems.get(position);
        holder.tvIcon.setText(item.getIcon());
        holder.tvName.setText(item.getName());

        if(positionSelected==position){
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorGrey, null));
        }
        else{
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.white, null));
        }
    }

    @Override
    public int getItemCount() {
        return optionItems.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        TextView tvIcon, tvName;

        public MyHolder(@NonNull final View itemView) {
            super(itemView);

            tvIcon = itemView.findViewById(R.id.tvOptionIcon);
            tvName = itemView.findViewById(R.id.tvOptionName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int previous = positionSelected;
                    positionSelected = getAbsoluteAdapterPosition();

                    FacilityOptionItem optionItem = optionItems.get(positionSelected);
                    facilityOptionMap.put(facility_id, optionItem.getId());

                    notifyItemChanged(previous);
                    notifyItemChanged(positionSelected);
                }
            });
        }
    }
}
