package com.example.practica1;

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
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserRoomDatabase_Impl extends UserRoomDatabase {
  private volatile UserDao _userDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `user_table` (`username` TEXT NOT NULL, `avatar` INTEGER NOT NULL, `isActive` INTEGER NOT NULL, PRIMARY KEY(`username`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `question_table` (`ID` INTEGER NOT NULL, `Type` INTEGER NOT NULL, `Quest` TEXT, `Answer1` TEXT, `Answer2` TEXT, `Answer3` TEXT, `Answer4` TEXT, `Image1` TEXT, `Image2` TEXT, `Image3` TEXT, `Image4` TEXT, PRIMARY KEY(`ID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7d0d4bcbdd143bf4db54beeb80bc9b91')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `user_table`");
        _db.execSQL("DROP TABLE IF EXISTS `question_table`");
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
        final HashMap<String, TableInfo.Column> _columnsUserTable = new HashMap<String, TableInfo.Column>(3);
        _columnsUserTable.put("username", new TableInfo.Column("username", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("avatar", new TableInfo.Column("avatar", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserTable = new TableInfo("user_table", _columnsUserTable, _foreignKeysUserTable, _indicesUserTable);
        final TableInfo _existingUserTable = TableInfo.read(_db, "user_table");
        if (! _infoUserTable.equals(_existingUserTable)) {
          return new RoomOpenHelper.ValidationResult(false, "user_table(com.example.practica1.User).\n"
                  + " Expected:\n" + _infoUserTable + "\n"
                  + " Found:\n" + _existingUserTable);
        }
        final HashMap<String, TableInfo.Column> _columnsQuestionTable = new HashMap<String, TableInfo.Column>(11);
        _columnsQuestionTable.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuestionTable.put("Type", new TableInfo.Column("Type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuestionTable.put("Quest", new TableInfo.Column("Quest", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuestionTable.put("Answer1", new TableInfo.Column("Answer1", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuestionTable.put("Answer2", new TableInfo.Column("Answer2", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuestionTable.put("Answer3", new TableInfo.Column("Answer3", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuestionTable.put("Answer4", new TableInfo.Column("Answer4", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuestionTable.put("Image1", new TableInfo.Column("Image1", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuestionTable.put("Image2", new TableInfo.Column("Image2", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuestionTable.put("Image3", new TableInfo.Column("Image3", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuestionTable.put("Image4", new TableInfo.Column("Image4", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysQuestionTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesQuestionTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoQuestionTable = new TableInfo("question_table", _columnsQuestionTable, _foreignKeysQuestionTable, _indicesQuestionTable);
        final TableInfo _existingQuestionTable = TableInfo.read(_db, "question_table");
        if (! _infoQuestionTable.equals(_existingQuestionTable)) {
          return new RoomOpenHelper.ValidationResult(false, "question_table(com.example.practica1.Question).\n"
                  + " Expected:\n" + _infoQuestionTable + "\n"
                  + " Found:\n" + _existingQuestionTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "7d0d4bcbdd143bf4db54beeb80bc9b91", "07b7594ee9cc23cea40e9b8a0bfff556");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "user_table","question_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `user_table`");
      _db.execSQL("DELETE FROM `question_table`");
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
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }
}
