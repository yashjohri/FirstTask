package com.johri.firsttask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.johri.firsttask.Adapters.ExclusionItemAdapter;
import com.johri.firsttask.Adapters.FacilityItemAdapter;
import com.johri.firsttask.ListItems.ExclusionItem;
import com.johri.firsttask.ListItems.FacilityItem;
import com.johri.firsttask.ListItems.FacilityOptionItem;
import com.johri.firsttask.Utils.ApiResult;
import com.johri.firsttask.Utils.FacilityMapObject;
import com.johri.firsttask.Utils.FacilityOptionPair;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvFacilities;
    FacilityItemAdapter rvFacilitiesAdapter;
    ArrayList<FacilityItem> facilityItems;
    private static final String api_url = "https://my-json-server.typicode.com/ricky1550/pariksha/db";
    HashMap<String, FacilityMapObject> facilitiesMap;

    RecyclerView rvExclusions;
    ExclusionItemAdapter rvExclusionsAdapter;
    ArrayList<ExclusionItem> exclusionItems;
    HashMap<String, String> exclusionMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        facilityItems = new ArrayList<>();
        facilitiesMap = new HashMap<>();
        exclusionMap = new HashMap<>();
        rvFacilities = findViewById(R.id.rv_facilities);
        rvFacilitiesAdapter = new FacilityItemAdapter(this, facilityItems, exclusionMap, facilitiesMap);
        rvFacilities.setLayoutManager(new LinearLayoutManager(this));
        rvFacilities.setAdapter(rvFacilitiesAdapter);
        fetchFacilities();
    }

    private void fetchFacilities(){
        OkHttpClient okHttpClient=new OkHttpClient();
        final Request request=new Request.Builder().url(api_url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(MainActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result=response.body().string();

                Gson gson=new Gson();
                final ApiResult apiResult=gson.fromJson(result, ApiResult.class);

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        facilityItems.addAll(apiResult.getFacilities());
                        rvFacilitiesAdapter.notifyDataSetChanged();
                        createFacilityMap();
                        fetchExclusions(apiResult.getExclusions());
                    }
                });
            }
        });
    }

    private void createFacilityMap(){

        for(FacilityItem facilityItem:facilityItems){

            String facility_id = facilityItem.getFacility_id();
            HashMap<String, FacilityOptionItem> optionsMap = new HashMap<>();

            for(FacilityOptionItem optionItem:facilityItem.getOptions()){
                optionsMap.put(optionItem.getId(), optionItem);
            }

            FacilityMapObject facilityMapObject = new FacilityMapObject(facilityItem.getName(), facility_id, optionsMap);
            facilitiesMap.put(facility_id, facilityMapObject);
        }
    }

    private void fetchExclusions(ArrayList<ArrayList<FacilityOptionPair>> exclusions){

        exclusionItems = new ArrayList<>();
        rvExclusions = findViewById(R.id.rv_exclusions);
        rvExclusionsAdapter = new ExclusionItemAdapter(this, exclusionItems, facilitiesMap);
        rvExclusions.setLayoutManager(new LinearLayoutManager(this));
        rvExclusions.setAdapter(rvExclusionsAdapter);

        for(ArrayList<FacilityOptionPair> facilityOptionPairs: exclusions){

            FacilityOptionPair pair1 = facilityOptionPairs.get(0);
            FacilityOptionPair pair2 = facilityOptionPairs.get(1);

            exclusionMap.put(pair1.getFacility_id()+pair1.getOptions_id(), pair2.getFacility_id()+pair2.getOptions_id());

            ExclusionItem exclusionItem = new ExclusionItem(pair1.getFacility_id(), pair2.getFacility_id(), pair1.getOptions_id(), pair2.getOptions_id());
            exclusionItems.add(exclusionItem);
        }

        rvExclusionsAdapter.notifyDataSetChanged();
    }
}