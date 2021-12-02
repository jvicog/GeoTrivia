package com.example.practica1;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<User> __deletionAdapterOfUser;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfSetAllInactive;

  private final SharedSQLiteStatement __preparedStmtOfSetActiveUser;

  private final SharedSQLiteStatement __preparedStmtOfChangeImage;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `user_table` (`username`,`avatar`,`isActive`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        if (value.mUsername == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.mUsername);
        }
        stmt.bindLong(2, value.mAvatar);
        final int _tmp;
        _tmp = value.mIsActive ? 1 : 0;
        stmt.bindLong(3, _tmp);
      }
    };
    this.__deletionAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `user_table` WHERE `username` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        if (value.mUsername == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.mUsername);
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM user_table";
        return _query;
      }
    };
    this.__preparedStmtOfSetAllInactive = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE user_table SET isActive =?";
        return _query;
      }
    };
    this.__preparedStmtOfSetActiveUser = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE user_table SET isActive =? WHERE username =?";
        return _query;
      }
    };
    this.__preparedStmtOfChangeImage = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE user_table SET avatar =? WHERE username =?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfUser.handle(user);
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
  public void setAllInactive(final boolean b) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetAllInactive.acquire();
    int _argIndex = 1;
    final int _tmp;
    _tmp = b ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetAllInactive.release(_stmt);
    }
  }

  @Override
  public void setActiveUser(final boolean b, final String name) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetActiveUser.acquire();
    int _argIndex = 1;
    final int _tmp;
    _tmp = b ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 2;
    if (name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, name);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetActiveUser.release(_stmt);
    }
  }

  @Override
  public void changeImage(final int img, final String name) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfChangeImage.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, img);
    _argIndex = 2;
    if (name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, name);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfChangeImage.release(_stmt);
    }
  }

  @Override
  public List<User> getAll() {
    final String _sql = "SELECT * FROM user_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfMAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
      final int _cursorIndexOfMIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        final String _tmpMUsername;
        if (_cursor.isNull(_cursorIndexOfMUsername)) {
          _tmpMUsername = null;
        } else {
          _tmpMUsername = _cursor.getString(_cursorIndexOfMUsername);
        }
        _item = new User(_tmpMUsername);
        _item.mAvatar = _cursor.getInt(_cursorIndexOfMAvatar);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfMIsActive);
        _item.mIsActive = _tmp != 0;
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getAllUsernames() {
    final String _sql = "SELECT username FROM user_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getString(0);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getActiveUser() {
    final String _sql = "SELECT * FROM user_table WHERE isActive";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfMAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
      final int _cursorIndexOfMIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpMUsername;
        if (_cursor.isNull(_cursorIndexOfMUsername)) {
          _tmpMUsername = null;
        } else {
          _tmpMUsername = _cursor.getString(_cursorIndexOfMUsername);
        }
        _result = new User(_tmpMUsername);
        _result.mAvatar = _cursor.getInt(_cursorIndexOfMAvatar);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfMIsActive);
        _result.mIsActive = _tmp != 0;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getSpecificUser(final String name) {
    final String _sql = "SELECT * FROM user_table WHERE username =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfMAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
      final int _cursorIndexOfMIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpMUsername;
        if (_cursor.isNull(_cursorIndexOfMUsername)) {
          _tmpMUsername = null;
        } else {
          _tmpMUsername = _cursor.getString(_cursorIndexOfMUsername);
        }
        _result = new User(_tmpMUsername);
        _result.mAvatar = _cursor.getInt(_cursorIndexOfMAvatar);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfMIsActive);
        _result.mIsActive = _tmp != 0;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
