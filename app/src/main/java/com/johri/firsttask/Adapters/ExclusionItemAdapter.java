package com.johri.firsttask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.johri.firsttask.ListItems.ExclusionItem;
import com.johri.firsttask.Utils.FacilityMapObject;
import com.johri.firsttask.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ExclusionItemAdapter extends RecyclerView.Adapter<ExclusionItemAdapter.MyHolder> {

    private Context context;
    private ArrayList<ExclusionItem> exclusionItems;
    private HashMap<String, FacilityMapObject> facilitiesMap;

    public ExclusionItemAdapter(Context context, ArrayList<ExclusionItem> exclusionItems, HashMap<String, FacilityMapObject> facilitiesMap) {
        this.context = context;
        this.exclusionItems = exclusionItems;
        this.facilitiesMap = facilitiesMap;
    }

    public ExclusionItemAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_exclusion_item, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExclusionItemAdapter.MyHolder holder, int position) {
        ExclusionItem exclusionItem = exclusionItems.get(position);

        String firstSelection = facilitiesMap.get(exclusionItem.getFacility_id_1()).getFacility_name()+" : "+facilitiesMap.get(exclusionItem.getFacility_id_1()).getOptionsMap().get(exclusionItem.getOption_id_1()).getName();
        String secondSelection = facilitiesMap.get(exclusionItem.getFacility_id_2()).getFacility_name()+" : "+facilitiesMap.get(exclusionItem.getFacility_id_2()).getOptionsMap().get(exclusionItem.getOption_id_2()).getName();

        holder.tvFirstPair.setText(firstSelection);
        holder.tvSecondPair.setText(secondSelection);
    }

    @Override
    public int getItemCount() {
        return exclusionItems.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder{

        TextView tvFirstPair, tvSecondPair;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tvFirstPair = itemView.findViewById(R.id.tvFirstPair);
            tvSecondPair = itemView.findViewById(R.id.tvSecondPair);
        }
    }
}
