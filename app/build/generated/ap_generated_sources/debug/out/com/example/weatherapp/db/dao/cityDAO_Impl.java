package com.example.weatherapp.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.weatherapp.db.entities.cityName;
import com.example.weatherapp.db.entities.cityWeather;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class cityDAO_Impl implements cityDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<cityName> __insertionAdapterOfcityName;

  private final EntityInsertionAdapter<cityWeather> __insertionAdapterOfcityWeather;

  private final EntityDeletionOrUpdateAdapter<cityName> __deletionAdapterOfcityName;

  private final EntityDeletionOrUpdateAdapter<cityWeather> __deletionAdapterOfcityWeather;

  private final EntityDeletionOrUpdateAdapter<cityName> __updateAdapterOfcityName;

  private final EntityDeletionOrUpdateAdapter<cityWeather> __updateAdapterOfcityWeather;

  public cityDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfcityName = new EntityInsertionAdapter<cityName>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `cityName` (`cityid`,`city_name`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, cityName value) {
        stmt.bindLong(1, value.cityid);
        if (value.cityName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.cityName);
        }
      }
    };
    this.__insertionAdapterOfcityWeather = new EntityInsertionAdapter<cityWeather>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `city_weather` (`recordid`,`city_id`,`record_date`,`temperature`,`description`,`windSpeed`,`windDir`,`percip`,`humidity`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, cityWeather value) {
        stmt.bindLong(1, value.recordid);
        stmt.bindLong(2, value.cityid);
        if (value.recordDate == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.recordDate);
        }
        if (value.temperature == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.temperature);
        }
        if (value.description == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.description);
        }
        if (value.windSpeed == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.windSpeed);
        }
        if (value.windDir == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.windDir);
        }
        if (value.percip == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.percip);
        }
        if (value.humidity == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.humidity);
        }
      }
    };
    this.__deletionAdapterOfcityName = new EntityDeletionOrUpdateAdapter<cityName>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `cityName` WHERE `cityid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, cityName value) {
        stmt.bindLong(1, value.cityid);
      }
    };
    this.__deletionAdapterOfcityWeather = new EntityDeletionOrUpdateAdapter<cityWeather>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `city_weather` WHERE `recordid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, cityWeather value) {
        stmt.bindLong(1, value.recordid);
      }
    };
    this.__updateAdapterOfcityName = new EntityDeletionOrUpdateAdapter<cityName>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `cityName` SET `cityid` = ?,`city_name` = ? WHERE `cityid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, cityName value) {
        stmt.bindLong(1, value.cityid);
        if (value.cityName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.cityName);
        }
        stmt.bindLong(3, value.cityid);
      }
    };
    this.__updateAdapterOfcityWeather = new EntityDeletionOrUpdateAdapter<cityWeather>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `city_weather` SET `recordid` = ?,`city_id` = ?,`record_date` = ?,`temperature` = ?,`description` = ?,`windSpeed` = ?,`windDir` = ?,`percip` = ?,`humidity` = ? WHERE `recordid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, cityWeather value) {
        stmt.bindLong(1, value.recordid);
        stmt.bindLong(2, value.cityid);
        if (value.recordDate == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.recordDate);
        }
        if (value.temperature == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.temperature);
        }
        if (value.description == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.description);
        }
        if (value.windSpeed == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.windSpeed);
        }
        if (value.windDir == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.windDir);
        }
        if (value.percip == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.percip);
        }
        if (value.humidity == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.humidity);
        }
        stmt.bindLong(10, value.recordid);
      }
    };
  }

  @Override
  public void insert(final cityName city) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfcityName.insert(city);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final cityWeather weather) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfcityWeather.insert(weather);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final cityName city) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfcityName.handle(city);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final cityWeather weather) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfcityWeather.handle(weather);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final cityName city) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfcityName.handle(city);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final cityWeather weather) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfcityWeather.handle(weather);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<cityName> getAll() {
    final String _sql = "SELECT * FROM cityName";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCityid = CursorUtil.getColumnIndexOrThrow(_cursor, "cityid");
      final int _cursorIndexOfCityName = CursorUtil.getColumnIndexOrThrow(_cursor, "city_name");
      final List<cityName> _result = new ArrayList<cityName>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final cityName _item;
        _item = new cityName();
        _item.cityid = _cursor.getInt(_cursorIndexOfCityid);
        if (_cursor.isNull(_cursorIndexOfCityName)) {
          _item.cityName = null;
        } else {
          _item.cityName = _cursor.getString(_cursorIndexOfCityName);
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
  public cityName getCity(final String name) {
    final String _sql = "Select * from cityName where city_name=?";
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
      final int _cursorIndexOfCityid = CursorUtil.getColumnIndexOrThrow(_cursor, "cityid");
      final int _cursorIndexOfCityName = CursorUtil.getColumnIndexOrThrow(_cursor, "city_name");
      final cityName _result;
      if(_cursor.moveToFirst()) {
        _result = new cityName();
        _result.cityid = _cursor.getInt(_cursorIndexOfCityid);
        if (_cursor.isNull(_cursorIndexOfCityName)) {
          _result.cityName = null;
        } else {
          _result.cityName = _cursor.getString(_cursorIndexOfCityName);
        }
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
  public cityName getCitybyID(final int id) {
    final String _sql = "Select * from cityName where cityid=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCityid = CursorUtil.getColumnIndexOrThrow(_cursor, "cityid");
      final int _cursorIndexOfCityName = CursorUtil.getColumnIndexOrThrow(_cursor, "city_name");
      final cityName _result;
      if(_cursor.moveToFirst()) {
        _result = new cityName();
        _result.cityid = _cursor.getInt(_cursorIndexOfCityid);
        if (_cursor.isNull(_cursorIndexOfCityName)) {
          _result.cityName = null;
        } else {
          _result.cityName = _cursor.getString(_cursorIndexOfCityName);
        }
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
  public List<String> getCityNames() {
    final String _sql = "SELECT city_name from cityName";
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
  public List<cityWeather> getAllRecord() {
    final String _sql = "SELECT * FROM city_weather";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfRecordid = CursorUtil.getColumnIndexOrThrow(_cursor, "recordid");
      final int _cursorIndexOfCityid = CursorUtil.getColumnIndexOrThrow(_cursor, "city_id");
      final int _cursorIndexOfRecordDate = CursorUtil.getColumnIndexOrThrow(_cursor, "record_date");
      final int _cursorIndexOfTemperature = CursorUtil.getColumnIndexOrThrow(_cursor, "temperature");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfWindSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "windSpeed");
      final int _cursorIndexOfWindDir = CursorUtil.getColumnIndexOrThrow(_cursor, "windDir");
      final int _cursorIndexOfPercip = CursorUtil.getColumnIndexOrThrow(_cursor, "percip");
      final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
      final List<cityWeather> _result = new ArrayList<cityWeather>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final cityWeather _item;
        _item = new cityWeather();
        _item.recordid = _cursor.getInt(_cursorIndexOfRecordid);
        _item.cityid = _cursor.getInt(_cursorIndexOfCityid);
        if (_cursor.isNull(_cursorIndexOfRecordDate)) {
          _item.recordDate = null;
        } else {
          _item.recordDate = _cursor.getString(_cursorIndexOfRecordDate);
        }
        if (_cursor.isNull(_cursorIndexOfTemperature)) {
          _item.temperature = null;
        } else {
          _item.temperature = _cursor.getString(_cursorIndexOfTemperature);
        }
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _item.description = null;
        } else {
          _item.description = _cursor.getString(_cursorIndexOfDescription);
        }
        if (_cursor.isNull(_cursorIndexOfWindSpeed)) {
          _item.windSpeed = null;
        } else {
          _item.windSpeed = _cursor.getString(_cursorIndexOfWindSpeed);
        }
        if (_cursor.isNull(_cursorIndexOfWindDir)) {
          _item.windDir = null;
        } else {
          _item.windDir = _cursor.getString(_cursorIndexOfWindDir);
        }
        if (_cursor.isNull(_cursorIndexOfPercip)) {
          _item.percip = null;
        } else {
          _item.percip = _cursor.getString(_cursorIndexOfPercip);
        }
        if (_cursor.isNull(_cursorIndexOfHumidity)) {
          _item.humidity = null;
        } else {
          _item.humidity = _cursor.getString(_cursorIndexOfHumidity);
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
  public List<cityWeather> getAllRecordC(final int id) {
    final String _sql = "SELECT * FROM city_weather where city_id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfRecordid = CursorUtil.getColumnIndexOrThrow(_cursor, "recordid");
      final int _cursorIndexOfCityid = CursorUtil.getColumnIndexOrThrow(_cursor, "city_id");
      final int _cursorIndexOfRecordDate = CursorUtil.getColumnIndexOrThrow(_cursor, "record_date");
      final int _cursorIndexOfTemperature = CursorUtil.getColumnIndexOrThrow(_cursor, "temperature");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfWindSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "windSpeed");
      final int _cursorIndexOfWindDir = CursorUtil.getColumnIndexOrThrow(_cursor, "windDir");
      final int _cursorIndexOfPercip = CursorUtil.getColumnIndexOrThrow(_cursor, "percip");
      final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
      final List<cityWeather> _result = new ArrayList<cityWeather>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final cityWeather _item;
        _item = new cityWeather();
        _item.recordid = _cursor.getInt(_cursorIndexOfRecordid);
        _item.cityid = _cursor.getInt(_cursorIndexOfCityid);
        if (_cursor.isNull(_cursorIndexOfRecordDate)) {
          _item.recordDate = null;
        } else {
          _item.recordDate = _cursor.getString(_cursorIndexOfRecordDate);
        }
        if (_cursor.isNull(_cursorIndexOfTemperature)) {
          _item.temperature = null;
        } else {
          _item.temperature = _cursor.getString(_cursorIndexOfTemperature);
        }
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _item.description = null;
        } else {
          _item.description = _cursor.getString(_cursorIndexOfDescription);
        }
        if (_cursor.isNull(_cursorIndexOfWindSpeed)) {
          _item.windSpeed = null;
        } else {
          _item.windSpeed = _cursor.getString(_cursorIndexOfWindSpeed);
        }
        if (_cursor.isNull(_cursorIndexOfWindDir)) {
          _item.windDir = null;
        } else {
          _item.windDir = _cursor.getString(_cursorIndexOfWindDir);
        }
        if (_cursor.isNull(_cursorIndexOfPercip)) {
          _item.percip = null;
        } else {
          _item.percip = _cursor.getString(_cursorIndexOfPercip);
        }
        if (_cursor.isNull(_cursorIndexOfHumidity)) {
          _item.humidity = null;
        } else {
          _item.humidity = _cursor.getString(_cursorIndexOfHumidity);
        }
        _result.add(_item);
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
