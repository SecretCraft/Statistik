/* Copyright (c) 2012 Walker Crouse, http://windwaker.net/
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.windwaker.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents a table in an SQL database.
 */
public class Table {
	private final Connection connection;
	private final String name;

	public Table(Connection connection, String name) {
		this.connection = connection;
		this.name = name;
	}
	
	/**
	 * Adds a new entry to the table.
	 * 
	 * @param fields
	 * @param values
	 * @throws SQLException 
	 */
	public void add(String[] fields, String[] values) throws SQLException {
		
		// Add fields to update message.
		StringBuilder update = new StringBuilder("INSERT INTO " + name + " (");
		int x = 0;
		for (String field : fields) {
			x++;
			update.append(field);
			if (x != fields.length) {
				update.append(", ");
			}
		}
		
		// Add values to update message.
		update.append(") VALUES (");
		int y = 0;
		for (String value : values) {
			y++;
			String v = "'" + value + "'";
			update.append(v);
			if (y != values.length) {
				update.append(", ");
			}
		}
		
		update.append(")");
		System.out.println(update);
		connection.update(update.toString());
	}
	
	/**
	 * Clears all contents of the field.
	 * 
	 * @throws SQLException 
	 */
	public void clear(String field) throws SQLException {
		connection.update("DELETE " + field + " FROM " + name);
	}
	
	/**
	 * Clears all contents of the table.
	 * 
	 * @throws SQLException 
	 */
	public void clear() throws SQLException {
		clear("*");
	}

	/**
	 * Checks if the column exists on the database.
	 *
	 * @param field
	 * @return true if field exists.
	 * @throws SQLException
	 */
	public boolean containsField(String field) throws SQLException {
		return connection.getJavaConnection().getMetaData().getColumns(null, null, field, null).next();
	}
	
	/**
	 * Whether or not the table contains a value in the given field.
	 * 
	 * @param field
	 * @param value
	 * @return true if field contains the value.
	 * @throws SQLException 
	 */
	public boolean containsValue(String field, Object value) throws SQLException {
		return values(field).contains(value);
	}
	
	/**
	 * Whether or not the table contains a value.
	 * 
	 * @param value
	 * @return true if table contains a value.
	 * @throws SQLException 
	 */
	public boolean containsValue(Object value) throws SQLException {
		return containsValue("*", value);
	}

	/**
	 * Creates the table on the database.
	 *
	 * @param fieldDataTypeMap
	 * @throws SQLException
	 */
	public void create(Map<String, DataType> fieldDataTypeMap) throws SQLException {
		Set<Map.Entry<String, DataType>> entries = fieldDataTypeMap.entrySet();
		StringBuilder update = new StringBuilder("CREATE TABLE " + name + " (");
		int x = 0;
		for (Map.Entry<String, DataType> entry : entries) {
			x++;
			String e = entry.getKey() + " " + entry.getValue().getUsage();
			update.append(e);
			if (x != entries.size()) {
				update.append(", ");
			}
		}

		update.append(")");
		System.out.println(update.toString());
		connection.update(update.toString());
	}
	
	/**
	 * Check if the table exists on the database.
	 *
	 * @return true if table exists.
	 * @throws SQLException
	 */
	public boolean exists() throws SQLException {
		return connection.getJavaConnection().getMetaData().getTables(null, null, name, null).next();
	}
	
	/**
	 * Gets the field of the table where the otherField equals the value. 
	 * 
	 * @param field
	 * @param otherField
	 * @param value
	 * @return value of given field
	 * @throws SQLException 
	 */
	public Object get(String field, String otherField, String value) throws SQLException {
		ResultSet results = connection.query("SELECT " + field + " FROM " + name + " WHERE " + otherField + "='" + value + "'");
		Object f = null;
		while (results.next()) {
			f = results.getObject(field);
		}
		
		return f;
	}
	
	/**
	 * Gets the field of the table where the otherField equals the value as an integer.
	 * 
	 * @param field
	 * @param otherField
	 * @param value
	 * @param def
	 * @return
	 * @throws SQLException 
	 */
	public int getInteger(String field, String otherField, String value, int def) throws SQLException {
		Object v = get(field, otherField, value);
		if (v instanceof Number) {
			return ((Number) v).intValue();
		}
		
		return def;
	}
	
	/**
	 * Gets the field of the table where the otherField equals the value as an integer.
	 * 
	 * @param field
	 * @param otherField
	 * @param value
	 * @return
	 * @throws SQLException 
	 */
	public int getInteger(String field, String otherField, String value) throws SQLException {
		return getInteger(field, otherField, value, -1);
	}
	
	/**
	 * Gets the field of the table where the otherField equals the value as a long.
	 * 
	 * @param field
	 * @param otherField
	 * @param value
	 * @param def
	 * @return
	 * @throws SQLException 
	 */
	public long getLong(String field, String otherField, String value, long def) throws SQLException {
		Object v = get(field, otherField, value);
		if (v instanceof Number) {
			return ((Number) v).longValue();
		}
		
		return def;
	}
	
	/**
	 * Gets the field of the table where the otherField equals the value as a long.
	 * 
	 * @param field
	 * @param otherField
	 * @param value
	 * @return
	 * @throws SQLException 
	 */
	public long getLong(String field, String otherField, String value) throws SQLException {
		return getLong(field, otherField, value, -1);
	}
	
	/**
	 * Gets the field of the table where the otherField equals the value as a float.
	 * 
	 * @param field
	 * @param otherField
	 * @param value
	 * @param def
	 * @return
	 * @throws SQLException 
	 */
	public float getFloat(String field, String otherField, String value, float def) throws SQLException {
		Object v = get(field, otherField, value);
		if (v instanceof Number) {
			return ((Number) v).floatValue();
		}
		
		return def;
	}
	
	/**
	 * Gets the field of the table where the otherField equals the value as a float.
	 * 
	 * @param field
	 * @param otherField
	 * @param value
	 * @return
	 * @throws SQLException 
	 */
	public float getFloat(String field, String otherField, String value) throws SQLException {
		return getFloat(field, otherField, value, -1);
	}
	
	/**
	 * Gets the field of the table where the otherField equals the value as a double.
	 * 
	 * @param field
	 * @param otherField
	 * @param value
	 * @param def
	 * @return
	 * @throws SQLException 
	 */
	public double getDouble(String field, String otherField, String value, double def) throws SQLException {
		Object v = get(field, otherField, value);
		if (v instanceof Number) {
			return ((Number) v).doubleValue();
		}
		
		return def;
	}
	
	/**
	 * Gets the field of the table where the otherField equals the value as a double.
	 * 
	 * @param field
	 * @param otherField
	 * @param value
	 * @return
	 * @throws SQLException 
	 */
	public double getDouble(String field, String otherField, String value) throws SQLException {
		return getDouble(field, otherField, value, -1);
	}
	
	/**
	 * Gets the field of the table where the otherField equals the value as a boolean.
	 * 
	 * @param field
	 * @param otherField
	 * @param value
	 * @param def
	 * @return
	 * @throws SQLException 
	 */
	public boolean getBoolean(String field, String otherField, String value, boolean def) throws SQLException {
		Object v = get(field, otherField, value);
		if (v instanceof Boolean) {
			return (Boolean) v;
		}
		
		return def;
	}
	
	/**
	 * Gets the field of the table where the otherField equals the value as a boolean.
	 * 
	 * @param field
	 * @param otherField
	 * @param value
	 * @return
	 * @throws SQLException 
	 */
	public boolean getBoolean(String field, String otherField, String value) throws SQLException {
		return getBoolean(field, otherField, value, false);
	}
	
	/**
	 * Gets the field of the table where the otherField equals the value as a string.
	 * 
	 * @param field
	 * @param otherField
	 * @param value
	 * @param def
	 * @return
	 * @throws SQLException 
	 */
	public String getString(String field, String otherField, String value, String def) throws SQLException {
		Object v = get(field, otherField, value);
		if (v instanceof String) {
			return ((String) v);
		}
		
		return def;
	}
	
	/**
	 * Gets the field of the table where the otherField equals the value as a string.
	 * 
	 * @param field
	 * @param otherField
	 * @param value
	 * @return
	 * @throws SQLException 
	 */
	public String getString(String field, String otherField, String value) throws SQLException {
		return getString(field, otherField, value, null);
	}
	
	/**
	 * Removes an entry from the table where the otherField equals the expectedValue.
	 * 
	 * @param field
	 * @param expectedValue
	 * @param expectedValue
	 * @throws SQLException 
	 */
	public void remove(String field, String expectedValue) throws SQLException {
		connection.update("DELETE FROM " + name + " WHERE " + field + " = '" + expectedValue + "'");
	}
	
	/**
	 * Sets the value of the field where the otherField equals the otherValue.
	 * 
	 * @param field
	 * @param value
	 * @param otherField
	 * @param otherValue
	 * @throws SQLException 
	 */
	public void set(String field, String value, String otherField, String otherValue) throws SQLException {
		connection.update("UPDATE " + name + " SET " + field + " = '" + value + "' WHERE " + otherField + " = '" + otherValue + "'");
	}
	
	/**
	 * Gets all the values of the given field.
	 * 
	 * @param field
	 * @return all values of field.
	 * @throws SQLException 
	 */
	public Set<?> values(String field) throws SQLException {
		ResultSet results = connection.query("SELECT " + field + " FROM " + name);
		Set<Object> fields = new HashSet<Object>();
		while (results.next()) {
			fields.add(results.getObject(field));
		}
		
		return fields;		
	}
	
	/**
	 * Gets all the values of all the fields on the table.
	 * 
	 * @return all values of all fields
	 * @throws SQLException 
	 */
	public Set<?> values() throws SQLException {
		return values("*");
	}
}
