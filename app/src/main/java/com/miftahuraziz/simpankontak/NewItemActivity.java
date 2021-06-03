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

public class NewItemActivity extends AppCompatActivity {
    private EditText etNama, etNomor;
    private Button btnSave;
    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        etNama = findViewById(R.id.et_nama);
        etNomor = findViewById(R.id.et_nomor);
        btnSave = findViewById(R.id.button_save);
        appDatabase = AppDatabase.db(getApplicationContext());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (etNama.getText().toString().equals("") || etNomor.getText().toString().equals("")) {
                        Toast.makeText(NewItemActivity.this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        DataContact dataContact = new DataContact();
                        dataContact.setNama(etNama.getText().toString());
                        dataContact.setNomor(etNomor.getText().toString());

                        appDatabase.dao().insertData(dataContact);

                        Log.d("NewItemActivity", "Sukses menyimpan kontak");
                        Toast.makeText(NewItemActivity.this, "Sukses menyimpan kontak", Toast.LENGTH_SHORT).show();
                        resetForm();
                    }
                }catch (Exception ex) {
                    Log.e("NewItemActivity", "Gagal menyimpan kontak, msg: "+ex.getMessage());
                    Toast.makeText(NewItemActivity.this, "Gagal menyimpan kontak", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void resetForm() {
        etNama.setText("");
        etNomor.setText("");
    }
}