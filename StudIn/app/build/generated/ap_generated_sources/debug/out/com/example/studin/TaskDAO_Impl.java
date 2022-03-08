package com.example.studin;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskDAO_Impl implements TaskDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TaskInPlanner> __insertionAdapterOfTaskInPlanner;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public TaskDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTaskInPlanner = new EntityInsertionAdapter<TaskInPlanner>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `TaskInPlanner` (`id`,`course_name`,`exam_name`,`exam_desc`,`exam_date`,`first_retake_date`,`sources`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TaskInPlanner value) {
        stmt.bindLong(1, value.getId());
        if (value.getCourseName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCourseName());
        }
        if (value.getExamName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getExamName());
        }
        if (value.getExamDesc() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getExamDesc());
        }
        final Long _tmp;
        _tmp = Converters.dateToTimestamp(value.getExamDate());
        if (_tmp == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, _tmp);
        }
        final Long _tmp_1;
        _tmp_1 = Converters.dateToTimestamp(value.getFirstRetakeDate());
        if (_tmp_1 == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, _tmp_1);
        }
        if (value.getSources() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getSources());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM TaskInPlanner";
        return _query;
      }
    };
  }

  @Override
  public void insert(final TaskInPlanner task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTaskInPlanner.insert(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<TaskInPlanner> getAllTasks() {
    final String _sql = "SELECT * from TaskInPlanner";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfCourseName = CursorUtil.getColumnIndexOrThrow(_cursor, "course_name");
      final int _cursorIndexOfExamName = CursorUtil.getColumnIndexOrThrow(_cursor, "exam_name");
      final int _cursorIndexOfExamDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "exam_desc");
      final int _cursorIndexOfExamDate = CursorUtil.getColumnIndexOrThrow(_cursor, "exam_date");
      final int _cursorIndexOfFirstRetakeDate = CursorUtil.getColumnIndexOrThrow(_cursor, "first_retake_date");
      final int _cursorIndexOfSources = CursorUtil.getColumnIndexOrThrow(_cursor, "sources");
      final List<TaskInPlanner> _result = new ArrayList<TaskInPlanner>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TaskInPlanner _item;
        _item = new TaskInPlanner();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpCourseName;
        _tmpCourseName = _cursor.getString(_cursorIndexOfCourseName);
        _item.setCourseName(_tmpCourseName);
        final String _tmpExamName;
        _tmpExamName = _cursor.getString(_cursorIndexOfExamName);
        _item.setExamName(_tmpExamName);
        final String _tmpExamDesc;
        _tmpExamDesc = _cursor.getString(_cursorIndexOfExamDesc);
        _item.setExamDesc(_tmpExamDesc);
        final Date _tmpExamDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfExamDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfExamDate);
        }
        _tmpExamDate = Converters.fromTimestamp(_tmp);
        _item.setExamDate(_tmpExamDate);
        final Date _tmpFirstRetakeDate;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfFirstRetakeDate)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfFirstRetakeDate);
        }
        _tmpFirstRetakeDate = Converters.fromTimestamp(_tmp_1);
        _item.setFirstRetakeDate(_tmpFirstRetakeDate);
        final String _tmpSources;
        _tmpSources = _cursor.getString(_cursorIndexOfSources);
        _item.setSources(_tmpSources);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
