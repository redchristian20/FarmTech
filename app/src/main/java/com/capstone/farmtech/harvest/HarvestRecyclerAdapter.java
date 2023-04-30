package com.capstone.farmtech.harvest;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.farmtech.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HarvestRecyclerAdapter extends RecyclerView.Adapter<HarvestRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<HarvestItem> harvestItemArraylists;
    DatabaseReference databaseReference;

    public HarvestRecyclerAdapter(Context context, ArrayList<HarvestItem> harvestItemArraylists){
        this.context = context;
        this.harvestItemArraylists = harvestItemArraylists;
        databaseReference = FirebaseDatabase.getInstance("https://farm-tech-71468-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
    }

    @NonNull
    @Override
    public HarvestRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.harvest_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HarvestRecyclerAdapter.ViewHolder holder, int position) {
        HarvestItem harvests = harvestItemArraylists.get(position);

        holder.tvHarvestTitle.setText("Harvest Title: "+ harvests.getHarvestTitle());
        holder.tvHarvestCrop.setText("Crop: "+ harvests.getHarvestCrop());
        holder.tvHarvestDuration.setText("Duration: "+harvests.getHarvestDuration());

        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogUpdate viewDialogUpdate = new ViewDialogUpdate();
                viewDialogUpdate.showDialog(context, harvests.getId(),harvests.getHarvestTitle(),
                        harvests.getHarvestDuration(), harvests.getHarvestCrop());
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogConfirmDelete viewDialogConfirmDelete = new ViewDialogConfirmDelete();
                viewDialogConfirmDelete.showDialog(context, harvests.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return harvestItemArraylists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvHarvestTitle,tvHarvestCrop,tvHarvestDuration;
        Button buttonDelete,buttonUpdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHarvestTitle = itemView.findViewById(R.id.harvestTitle);
            tvHarvestCrop = itemView.findViewById(R.id.harvestCrop);
            tvHarvestDuration = itemView.findViewById(R.id.harvestDuration);

            buttonUpdate = itemView.findViewById(R.id.buttonUpdate);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);

        }
    }

    public class ViewDialogUpdate{
        public void showDialog(Context context, String id, String harvestTitle,String harvestCrop, String harvestDuration){
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_add_new_harvest);

            EditText inputHarvestTitle,inputHarvestCrop,inputHarvestDuration;

            inputHarvestTitle = dialog.findViewById(R.id.input_title);
            inputHarvestCrop = dialog.findViewById(R.id.input_crop);
            inputHarvestDuration = dialog.findViewById(R.id.input_duration);

            inputHarvestTitle.setText(harvestTitle);
            inputHarvestCrop.setText(harvestCrop);
            inputHarvestDuration.setText(harvestDuration);

            Button buttonUpdate = dialog.findViewById(R.id.buttonAdd);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonUpdate.setText("Update");

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String newTitle = inputHarvestTitle.getText().toString();
                    String newCrop = inputHarvestCrop.getText().toString();
                    String newDuration = inputHarvestDuration.getText().toString();

                    if (newTitle.isEmpty() || newCrop.isEmpty() || newDuration.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {

                        if (newTitle.equals(harvestTitle) && newCrop.equals(harvestCrop) && newDuration.equals(harvestDuration)) {
                            Toast.makeText(context, "you don't change anything", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child("harvest_plans").child(id).setValue(new HarvestItem(id, newTitle, newCrop, newDuration));
                            Toast.makeText(context, "Schedule Updated successfully!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }


                    }
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();


        }
    }
    public class ViewDialogConfirmDelete {
        public void showDialog(Context context, String id) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.view_dialog_confirm_delete);

            Button buttonDelete = dialog.findViewById(R.id.button_delete);
            Button buttonCancel = dialog.findViewById(R.id.button_cancel);

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    databaseReference.child("harvest_plans").child(id).removeValue();
                    Toast.makeText(context, "User Deleted successfully!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }


}
