package com.example.testbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahData extends AppCompatActivity {
    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button btSubmit;
    private EditText etKode;
    private EditText etNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        etKode = (EditText) findViewById(R.id.editNo);
        etNama = (EditText) findViewById(R.id.editNama);
        btSubmit = (Button) findViewById(R.id.btnOk);

        // mengambil referensi ke firebase Database
        database = FirebaseDatabase.getInstance().getReference();
        //
        //final Barang barang = (Barang) getIntent().getSerializableExtra("data");

        //
        /*if (barang != null){
            etKode.setText(barang.getKode());
            etNama.setText(barang.getNama());
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    barang.setKode(etKode.getText().toString());
                    barang.setNama(etNama.getText().toString());
                    updateBarang(barang);
                }
            });*/
        }//else {
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (! (etKode.getText().toString().isEmpty()) &&
                            !(etNama.getText().toString().isEmpty()))
                        submitBrg(new Barang(etKode.getText().toString(), etNama.getText().toString()));
                    else
                        Toast.makeText(getApplicationContext(), "Data tidak boleh kosong",
                                Toast.LENGTH_LONG).show();

                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etKode.getWindowToken(), 0);
                }
            });
       // }
    }


    public void submitBrg(Barang brg) {
        /* Berikut ini adalah kode yang digunakan untuk mengirimkan data ke
         * Firebase Realtime Database dan  juga kita set onSuccessListener yang
         * beriisi kode yang akan dijalankan ketika data berhasil ditambahkan*/

        database.child("Barang").push().setValue(brg).addOnSuccessListener(this,
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        etKode.setText("");
                        etNama.setText("");
                        Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan intent
        return new Intent(activity, TambahData.class);
    }

    /*private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);*/
    }
    private void updateBarang(Barang brg) {
        //Bari kode yang digunakan untuk mengupdate data barang
        //yang sudah dimasukan di firebase realtime database
        database.child("Barang")
                .child(brg.getKode())
                .setValue(brg)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Baris kode yang akan dipanggil apabila proses update barang sukse
                        Toast.makeText(getApplicationContext(), "Data berhasil di Update",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }
}
