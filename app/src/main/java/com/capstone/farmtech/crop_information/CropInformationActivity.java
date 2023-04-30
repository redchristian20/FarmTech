package com.capstone.farmtech.crop_information;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.farmtech.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class CropInformationActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_information);
        mDatabase = FirebaseDatabase.getInstance("https://farm-tech-71468-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
        recyclerView = findViewById(R.id.list);
        linearLayoutManager = new LinearLayoutManager(CropInformationActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();
    }
    /*
    ------------------------------------------------------------------------------------------------
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView cropName,cropDescription;
        public Button cropMoreInfo;
        public ImageView cropImage;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list);

            cropName = itemView.findViewById(R.id.crop_name_tv);
            cropDescription = itemView.findViewById(R.id.crop_description_tv);
            cropImage = itemView.findViewById(R.id.crop_image);
        }
        public void setCropName(String string) {
            cropName.setText(string);
        }
        public void setCropDescription(String string) {
            cropDescription.setText(string);
        }
        public void setCropImage(String string,String link){

            DatabaseReference dbRef = FirebaseDatabase.getInstance("https://farm-tech-71468-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("crop_images").child(string).child("image");
            Toast.makeText(CropInformationActivity.this, ""+link, Toast.LENGTH_SHORT).show();
            Picasso.get().load(link).into(cropImage);
        }

    }
    private void fetch() {
        Query query = mDatabase.child("crops");
        FirebaseRecyclerOptions<Crops> options =
                new FirebaseRecyclerOptions.Builder<Crops>()
                        .setQuery(query, new SnapshotParser<Crops>() {
                            @NonNull
                            @Override
                            public Crops parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Crops(
                                        snapshot.getKey(),
                                        snapshot.child("description").getValue().toString(),
                                        snapshot.child("image").getValue().toString());
                            }
                        })
                        .build();
        adapter = new FirebaseRecyclerAdapter<Crops, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_crop_object, parent, false);
                return new ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(ViewHolder holder, final int position, final Crops model) {
                holder.setCropName(model.getmCropName());
                holder.setCropDescription(model.getmCropDescription());
                holder.setCropImage(model.getmCropName(),model.getmCropImageLink());
            }
        };
        recyclerView.setAdapter(adapter);
    }
    /*
     */
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}