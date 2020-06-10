package com.example.testbarang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LihatBarang extends AppCompatActivity {
    // Inisialisasi variable yang dipakai
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Barang> daftarBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_barang);

        //Inisialisasi RecylerView dan komponennya

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        //Inisialisasi dan mengambil firebase database reference
        database = FirebaseDatabase.getInstance().getReference();

        //Mengambil data barang dari firebase realtime db
        database.child("Barang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Saat data baru, masukan datanya ke Array list
                daftarBarang = new ArrayList<>();
                for (DataSnapshot noteDataSnapShot : dataSnapshot.getChildren()){
                    //Mapping data pada DataSnapshot ke dalam object barang
                    //dan juga menyimpan primary key pada object barang
                    // untuk keperluan edit dan delete data
                    Barang barang = noteDataSnapShot.getValue(Barang.class);
                    barang.setKode(noteDataSnapShot.getKey());

                    //Menambahkan object barang yang sudah dimapping
                    //kedalam arraylist
                    daftarBarang.add(barang);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        })
    }
}
