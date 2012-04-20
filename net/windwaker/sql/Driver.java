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

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a JDBC driver's info. Convenience method for quick driver selection.
 */
public enum Driver {
	MySQL("mysql", "com.mysql.jdbc.Driver"),
	SQLite("sqlite", "org.sqlite.JDBC"),
	PostgreSQL("postgre", "org.postgresql.Driver");
	
	private final String protocol;
	private final String classname;
	private static final Map<String, Driver> protocolMap = new HashMap<String, Driver>();
	
	private Driver(String protocol, String classname) {
		this.protocol = protocol;
		this.classname = classname;
	}

	/**
	 * Gets the protocol tag of the driver.
	 *
	 * @return protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * Gets the name of the class the driver resides in.
	 *
	 * @return classname
	 */
	public String getClassName() {
		return classname;
	}

	/**
	 * Gets a driver by it's protocol.
	 *
	 * @param protocol
	 * @return driver
	 */
	public static Driver getByProtocol(String protocol) {
		return protocolMap.get(protocol);
	}
	
	static {
		for (Driver driver : Driver.values()) {
			protocolMap.put(driver.getProtocol(), driver);
		}
	}
}
