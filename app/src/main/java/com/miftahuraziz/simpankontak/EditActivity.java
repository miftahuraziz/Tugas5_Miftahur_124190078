package com.miftahuraziz.simpankontak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miftahuraziz.simpankontak.entity.AppDatabase;
import com.miftahuraziz.simpankontak.entity.DataContact;

public class EditActivity extends AppCompatActivity {
    private EditText etNama, etNomor;
    private Button btnUpdate;
    private AppDatabase appDatabase;
    private DataContact dataContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setTitle("Edit Kontak");

        etNama = findViewById(R.id.et_nama);
        etNomor = findViewById(R.id.et_nomor);

        int id = getIntent().getIntExtra("id",0);
        String nama = getIntent().getStringExtra("nama");
        String nomor = getIntent().getStringExtra("nomor");

        etNama.setText(nama);
        etNomor.setText(nomor);

        btnUpdate = findViewById(R.id.button_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (etNama.getText().toString().equals("") || etNomor.getText().toString().equals("")) {
                        Toast.makeText(EditActivity.this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        dataContact.setId(id);
                        dataContact.setNama(etNama.getText().toString());
                        dataContact.setNomor(etNomor.getText().toString());

                        appDatabase.dao().updateData(dataContact);

                        Log.d("EditActivity", "Sukses mengupdate kontak");
                        Toast.makeText(EditActivity.this, "Sukses mengupdate kontak", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }catch (Exception ex) {
                    Log.e("EditActivity", "Gagal mengupdate kontak, msg: "+ex.getMessage());
                    Toast.makeText(EditActivity.this, "Gagal mengupdate kontak", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}