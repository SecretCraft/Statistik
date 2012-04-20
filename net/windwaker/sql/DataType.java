/**
 * The Permissions project.
 * Copyright (C) 2012 Walker Crouse
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.windwaker.sql;

/**
 * Represents a SQL data type.
 */
public class DataType {
	public static final String CHARACTER = "CHAR";
	public static final String VARIABLE_CHARACTER = "VARCHAR";
	public static final String TINY_TEXT = "TINYTEXT";
	public static final String TEXT = "TEXT";
	public static final String BLOB = "BLOB";
	public static final String MEDIUM_TEXT = "MEDIUMTEXT";
	public static final String MEDIUM_BLOB = "MEDIUMBLOB";
	public static final String LONG_TEXT = "LONGTEXT";
	public static final String LONG_BLOB = "LONGBLOB";
	public static final String ENUM = "ENUM";
	public static final String SET = "SET";
	public static final String TINY_INTEGER = "TINYINT";
	public static final String SMALL_INTEGER = "SMALLINT";
	public static final String MEDIUM_INTEGER = "MEDIUMINT";
	public static final String INTEGER = "INT";
	public static final String BIG_INTEGER = "BIGINT";
	public static final String FLOAT = "FLOAT";
	public static final String DOUBLE = "DOUBLE";
	public static final String DECIMAL = "DECIMAL";
	public static final String DATE = "DATE";
	public static final String DATE_TIME = "DATETIME";
	public static final String TIME_STAMP = "TIMESTAMP";
	public static final String TIME = "TIME";
	public static final String YEAR = "YEAR";

	private final String syntax;
	private String parameters;
	
	public DataType(String syntax, String parameters) {
		this.syntax = syntax;
		this.parameters = parameters;
	}
	
	public DataType(String syntax) {
		this(syntax, null);
	}

	/**
	 * Gets the keyword associated with this data type.
	 *
	 * @return keyword
	 */
	public String getSyntax() {
		return syntax;
	}

	/**
	 * Returns an array of parameters
	 *
	 * @return params
	 */
	public String[] getParameters() {
		return parameters.split(",");
	}

	/**
	 * Sets the parameters of the keyword, separated by commas.
	 *
	 * @param parameters
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	/**
	 * Gets the final usage of the keyword and parameters.
	 *
	 * @return usage
	 */
	public String getUsage() {
		if (parameters != null) {
			return syntax + "(" + parameters + ")";
		}

		return syntax;
	}
}
