package com.example.weatherapp.db;

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
import com.example.weatherapp.db.dao.cityDAO;
import com.example.weatherapp.db.dao.cityDAO_Impl;
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
public final class WeatherDatabase_Impl extends WeatherDatabase {
  private volatile cityDAO _cityDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `city_weather` (`recordid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `city_id` INTEGER NOT NULL, `record_date` TEXT, `temperature` TEXT, `description` TEXT, `windSpeed` TEXT, `windDir` TEXT DEFAULT 'windDir', `percip` TEXT DEFAULT 'precip', `humidity` TEXT DEFAULT 'humidity')");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `cityName` (`cityid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `city_name` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c405de4fc254b4c0f15d6f04515e47fe')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `city_weather`");
        _db.execSQL("DROP TABLE IF EXISTS `cityName`");
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
        final HashMap<String, TableInfo.Column> _columnsCityWeather = new HashMap<String, TableInfo.Column>(9);
        _columnsCityWeather.put("recordid", new TableInfo.Column("recordid", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCityWeather.put("city_id", new TableInfo.Column("city_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCityWeather.put("record_date", new TableInfo.Column("record_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCityWeather.put("temperature", new TableInfo.Column("temperature", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCityWeather.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCityWeather.put("windSpeed", new TableInfo.Column("windSpeed", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCityWeather.put("windDir", new TableInfo.Column("windDir", "TEXT", false, 0, "'windDir'", TableInfo.CREATED_FROM_ENTITY));
        _columnsCityWeather.put("percip", new TableInfo.Column("percip", "TEXT", false, 0, "'precip'", TableInfo.CREATED_FROM_ENTITY));
        _columnsCityWeather.put("humidity", new TableInfo.Column("humidity", "TEXT", false, 0, "'humidity'", TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCityWeather = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCityWeather = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCityWeather = new TableInfo("city_weather", _columnsCityWeather, _foreignKeysCityWeather, _indicesCityWeather);
        final TableInfo _existingCityWeather = TableInfo.read(_db, "city_weather");
        if (! _infoCityWeather.equals(_existingCityWeather)) {
          return new RoomOpenHelper.ValidationResult(false, "city_weather(com.example.weatherapp.db.entities.cityWeather).\n"
                  + " Expected:\n" + _infoCityWeather + "\n"
                  + " Found:\n" + _existingCityWeather);
        }
        final HashMap<String, TableInfo.Column> _columnsCityName = new HashMap<String, TableInfo.Column>(2);
        _columnsCityName.put("cityid", new TableInfo.Column("cityid", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCityName.put("city_name", new TableInfo.Column("city_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCityName = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCityName = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCityName = new TableInfo("cityName", _columnsCityName, _foreignKeysCityName, _indicesCityName);
        final TableInfo _existingCityName = TableInfo.read(_db, "cityName");
        if (! _infoCityName.equals(_existingCityName)) {
          return new RoomOpenHelper.ValidationResult(false, "cityName(com.example.weatherapp.db.entities.cityName).\n"
                  + " Expected:\n" + _infoCityName + "\n"
                  + " Found:\n" + _existingCityName);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "c405de4fc254b4c0f15d6f04515e47fe", "47a08ca6e826a8d88eafae10e8ea944e");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "city_weather","cityName");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `city_weather`");
      _db.execSQL("DELETE FROM `cityName`");
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
    _typeConvertersMap.put(cityDAO.class, cityDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public cityDAO CityDAO() {
    if (_cityDAO != null) {
      return _cityDAO;
    } else {
      synchronized(this) {
        if(_cityDAO == null) {
          _cityDAO = new cityDAO_Impl(this);
        }
        return _cityDAO;
      }
    }
  }
}
