package com.example.studin;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile TaskDAO _taskDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TaskInPlanner` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `course_name` TEXT NOT NULL, `exam_name` TEXT NOT NULL, `exam_desc` TEXT NOT NULL, `exam_date` INTEGER NOT NULL, `first_retake_date` INTEGER NOT NULL, `sources` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e6af9ab25893b950f3ca49988cc5cffc')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `TaskInPlanner`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTaskInPlanner = new HashMap<String, TableInfo.Column>(7);
        _columnsTaskInPlanner.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTaskInPlanner.put("course_name", new TableInfo.Column("course_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTaskInPlanner.put("exam_name", new TableInfo.Column("exam_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTaskInPlanner.put("exam_desc", new TableInfo.Column("exam_desc", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTaskInPlanner.put("exam_date", new TableInfo.Column("exam_date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTaskInPlanner.put("first_retake_date", new TableInfo.Column("first_retake_date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTaskInPlanner.put("sources", new TableInfo.Column("sources", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTaskInPlanner = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTaskInPlanner = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTaskInPlanner = new TableInfo("TaskInPlanner", _columnsTaskInPlanner, _foreignKeysTaskInPlanner, _indicesTaskInPlanner);
        final TableInfo _existingTaskInPlanner = TableInfo.read(_db, "TaskInPlanner");
        if (! _infoTaskInPlanner.equals(_existingTaskInPlanner)) {
          return new RoomOpenHelper.ValidationResult(false, "TaskInPlanner(com.example.studin.TaskInPlanner).\n"
                  + " Expected:\n" + _infoTaskInPlanner + "\n"
                  + " Found:\n" + _existingTaskInPlanner);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "e6af9ab25893b950f3ca49988cc5cffc", "ba43eb25344f4296f0752247eed527b5");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "TaskInPlanner");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `TaskInPlanner`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public TaskDAO taskDAO() {
    if (_taskDAO != null) {
      return _taskDAO;
    } else {
      synchronized(this) {
        if(_taskDAO == null) {
          _taskDAO = new TaskDAO_Impl(this);
        }
        return _taskDAO;
      }
    }
  }
}
