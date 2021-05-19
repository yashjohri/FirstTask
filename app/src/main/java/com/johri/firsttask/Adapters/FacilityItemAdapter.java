package com.johri.firsttask.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.johri.firsttask.ListItems.FacilityItem;
import com.johri.firsttask.Utils.FacilityMapObject;
import com.johri.firsttask.ListItems.FacilityOptionItem;
import com.johri.firsttask.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FacilityItemAdapter extends RecyclerView.Adapter<FacilityItemAdapter.MyHolder> {

    private Context context;
    private ArrayList<FacilityItem> facilityItems;
    private HashMap<String, String> exclusionMap;
    private HashMap<String, FacilityMapObject> facilitiesMap;
    private AlertDialog alertDialog;
    private RecyclerView rvOptions;
    private ArrayList<FacilityOptionItem> optionItems;
    private OptionItemAdapter rvAdapter;
    private HashMap<String, String> facilityOptionMap;
    private int currentPosition;

    public FacilityItemAdapter(final Context context, ArrayList<FacilityItem> facilityItems, HashMap<String, String> exclusionMap, HashMap<String, FacilityMapObject> facilitiesMap) {
        this.context = context;
        this.facilityItems = facilityItems;
        this.exclusionMap = exclusionMap;
        this.facilityOptionMap = new HashMap<>();
        this.facilitiesMap = facilitiesMap;
        this.currentPosition = -1;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog_layout, null);
        rvOptions = view.findViewById(R.id.rvOptions);
        Button btn = view.findViewById(R.id.btnOK);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                notifyItemChanged(currentPosition);
                validateSelection();
            }
        });
        builder.setView(view);
        alertDialog = builder.create();

        optionItems = new ArrayList<>();
        rvAdapter = new OptionItemAdapter(context, optionItems, facilityOptionMap);
        rvOptions.setLayoutManager(new LinearLayoutManager(context));
        rvOptions.setAdapter(rvAdapter);
    }

    @NonNull
    @Override
    public FacilityItemAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_facility_item, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FacilityItemAdapter.MyHolder holder, int position) {
        FacilityItem facilityItem = facilityItems.get(position);
        String optionSelected = "";
        if(facilityOptionMap.get(facilityItem.getFacility_id())!=null){
            FacilityMapObject mapObject = facilitiesMap.get(facilityItem.getFacility_id());
            FacilityOptionItem optionItem = mapObject.getOptionsMap().get(facilityOptionMap.get(facilityItem.getFacility_id()));
            optionSelected = " - "+optionItem.getName();
        }
        holder.tvFacilityName.setText(facilityItem.getName()+optionSelected);
    }

    @Override
    public int getItemCount() {
        return facilityItems.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        TextView tvFacilityName;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tvFacilityName = itemView.findViewById(R.id.tvFacilityName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentPosition = getAbsoluteAdapterPosition();
                    optionItems.clear();
                    optionItems.addAll(facilityItems.get(currentPosition).getOptions());
                    rvAdapter.setFacility_id(facilityItems.get(currentPosition).getFacility_id());
                    rvAdapter.setPositionSelected(-1);
                    rvAdapter.notifyDataSetChanged();
                    alertDialog.show();
                }
            });
        }
    }

    private void validateSelection(){
        //Toast.makeText(context, exclusionMap.toString(), Toast.LENGTH_LONG).show();
        Set<String> facilityOptionSet = new HashSet<>();

        for(String facilityId:facilityOptionMap.keySet()){
            facilityOptionSet.add(facilityId+facilityOptionMap.get(facilityId));
        }

        for(String facilityOptionIds:exclusionMap.keySet()){
            if(facilityOptionSet.contains(facilityOptionIds) && facilityOptionSet.contains(exclusionMap.get(facilityOptionIds))){
                Toast.makeText(context, "Invalid Selection!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
