package com.example.lalamove.ListLoaiXe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lalamove.R;

import java.util.ArrayList;

public class VehicleAdapter extends ArrayAdapter<Vehicle> {
    private int selectedPosition = -1;

    public VehicleAdapter(Context context, ArrayList<Vehicle> vehicles) {
        super(context, 0, vehicles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Vehicle vehicle = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vehicle_list_item, parent, false);
        }

        ImageView vehicleIcon = convertView.findViewById(R.id.vehicle_icon);
        TextView vehicleName = convertView.findViewById(R.id.vehicle_name);
        TextView vehicleDescription = convertView.findViewById(R.id.vehicle_description);
        TextView vehicleDimensions = convertView.findViewById(R.id.vehicle_dimensions);
        ImageView checkIcon = convertView.findViewById(R.id.check_icon);

        vehicleIcon.setImageResource(vehicle.getIcon());
        vehicleName.setText(vehicle.getName());
        vehicleDescription.setText(vehicle.getDescription());
        vehicleDimensions.setText(vehicle.getDimensions());

        if (position == selectedPosition) {
            vehicleDescription.setVisibility(View.VISIBLE);
            vehicleDimensions.setVisibility(View.VISIBLE);
            checkIcon.setVisibility(View.VISIBLE);
            convertView.setBackgroundResource(R.drawable.vehicle_item_background_after);
        } else {
            vehicleDescription.setVisibility(View.GONE);
            vehicleDimensions.setVisibility(View.GONE);
            checkIcon.setVisibility(View.GONE);
            convertView.setBackgroundResource(R.drawable.vehicle_item_background);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = (selectedPosition == position) ? -1 : position;
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}