package com.example.administrator.daiylywriting.BooksSqilte;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;



// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table BIG_DATA.
*/
public class BigDataDao extends AbstractDao<BigData, Long> {

    public static final String TABLENAME = "BIG_DATA";

    /**
     * Properties of entity BigData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property OneDayNumber = new Property(1, Integer.class, "OneDayNumber", false, "ONE_DAY_NUMBER");
        public final static Property TimeDate = new Property(2, String.class, "TimeDate", false, "TIME_DATE");
    };


    public BigDataDao(DaoConfig config) {
        super(config);
    }
    
    public BigDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'BIG_DATA' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'ONE_DAY_NUMBER' INTEGER," + // 1: OneDayNumber
                "'TIME_DATE' TEXT);"); // 2: TimeDate
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'BIG_DATA'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, BigData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer OneDayNumber = entity.getOneDayNumber();
        if (OneDayNumber != null) {
            stmt.bindLong(2, OneDayNumber);
        }
 
        String TimeDate = entity.getTimeDate();
        if (TimeDate != null) {
            stmt.bindString(3, TimeDate);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public BigData readEntity(Cursor cursor, int offset) {
        BigData entity = new BigData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // OneDayNumber
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // TimeDate
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, BigData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setOneDayNumber(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setTimeDate(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(BigData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(BigData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
