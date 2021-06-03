package com.miftahuraziz.simpankontak.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.miftahuraziz.simpankontak.EditActivity;
import com.miftahuraziz.simpankontak.R;
import com.miftahuraziz.simpankontak.entity.AppDatabase;
import com.miftahuraziz.simpankontak.entity.DataContact;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    private Context context;
    private final ArrayList<DataContact> list = new ArrayList<>();
    private Button btnDelete;
    private LinearLayout linearLayout;
    private AppDatabase appDatabase;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<DataContact> item) {
        list.clear();
        list.addAll(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final DataContact item = list.get(position);
        holder.tvNama.setText(item.getNama());
        holder.tvNomor.setText(item.getNomor());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToEdit = new Intent(context.getApplicationContext(), EditActivity.class);
                moveToEdit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                moveToEdit.putExtra("id", item.getId());
                moveToEdit.putExtra("nama", item.getNama());
                moveToEdit.putExtra("nomor", item.getNomor());
                context.getApplicationContext().startActivity(moveToEdit);
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DataContact dataContact = new DataContact();
                    dataContact.setId(item.getId());
                    dataContact.setNama(item.getNama());
                    dataContact.setNomor(item.getNomor());

                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(context.getApplicationContext());
                    builder.setTitle("Menghapus Kontak")
                            .setMessage("Anda yakin ingin menghapus kontak ini?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    appDatabase.dao().deleteData(dataContact);
                                    Log.d("MainAdapter", "Sukses menghapus kontak");
                                    Toast.makeText(context, "Sukses menghapus kontak", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }catch (Exception ex) {
                    Log.e("MainAdapter", "Gagal mengahapus kontak, msg: "+ex.getMessage());
                    Toast.makeText(context, "Gagal menghapus kontak", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvNama, tvNomor;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNomor = itemView.findViewById(R.id.tv_nomor);
            btnDelete = itemView.findViewById(R.id.button_delete);
            linearLayout = itemView.findViewById(R.id.recyclerview);
        }
    }
}
