package com.angoga.roomlessson_23_24.database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.angoga.roomlessson_23_24.database.entity.Publication;

import java.util.List;

@Dao
public interface PublicationDao {
    @Insert
    void insert(Publication entity);

    @Update
    void update(Publication entity);

    @Delete
    void delete(Publication publication);


    @Query("SELECT * FROM PUBLICATION WHERE id = :id")
    Publication findById(int id);

    @Query("SELECT * FROM PUBLICATION")
    List<Publication> findAll();
}
