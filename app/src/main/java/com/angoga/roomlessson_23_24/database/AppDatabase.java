package com.angoga.roomlessson_23_24.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.angoga.roomlessson_23_24.database.dao.CommentaryDao;
import com.angoga.roomlessson_23_24.database.dao.PublicationDao;
import com.angoga.roomlessson_23_24.database.entity.Commentary;
import com.angoga.roomlessson_23_24.database.entity.Publication;

@Database(entities = { Publication.class, Commentary.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PublicationDao getPublicationDao();
    public abstract CommentaryDao getCommentaryDao();
}
