package com.miftahuraziz.simpankontak.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataContactDAO {
    @Insert
    long insertData(DataContact dataContact);
    @Query("SELECT * FROM tb_contact ORDER BY nama ASC")
    List<DataContact> getData();
    @Update
    int updateData(DataContact item);
    @Delete
    int deleteData(DataContact item);
}
