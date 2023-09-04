package com.ruoyi.system.handle;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.ibatis.type.*;

@MappedTypes(LocalDateTime.class)
@MappedJdbcTypes(JdbcType.INTEGER)
public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setInt(i, (int) parameter.toEpochSecond(ZoneOffset.UTC));
  }

  @Override
  public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
    int timestamp = rs.getInt(columnName);
    if (rs.wasNull()) {
      return null;
    } else {
      return LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC);
    }
  }

  @Override
  public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    int timestamp = rs.getInt(columnIndex);
    if (rs.wasNull()) {
      return null;
    } else {
      return LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC);
    }
  }

  @Override
  public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    int timestamp = cs.getInt(columnIndex);
    if (cs.wasNull()) {
      return null;
    } else {
      return LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC);
    }
  }
}
