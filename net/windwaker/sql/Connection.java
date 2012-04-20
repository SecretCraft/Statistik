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

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Represents a connection between the server and an SQL database.
 */
public class Connection {
	protected final String host;
	protected final String protocol;
	protected final String className;
	protected Statement statement;
	protected java.sql.Connection connection;

	public Connection(String host, Driver driver) {
		this.host = host;
		this.protocol = driver.getProtocol();
		this.className = driver.getClassName();
	}
	
	public Connection(String host, String protocol, String className) {
		this.host = host;
		this.protocol = protocol;
		this.className = className;
	}

	/**
	 * Establishes a connection with the database.
	 *
	 * @param username
	 * @param password
	 */
	public void connect(String username, String password) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
		Class.forName(className).newInstance();
		connection = DriverManager.getConnection("jdbc:" + protocol + "://" + host, username, password);
		statement = connection.createStatement();
	}

	/**
	 * Closes any established connection with the database.
	 *
	 * @throws SQLException
	 */
	public void disconnect() throws SQLException {
		connection.close();
	}

	/**
	 * Executes a query on the connection.
	 *
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ResultSet query(String query) throws SQLException {
		return statement.executeQuery(query);
	}

	/**
	 * Executes an update on the connection.
	 *
	 * @param update
	 * @throws SQLException
	 */
	public void update(String update) throws SQLException {
		statement.executeUpdate(update);
	}

	/**
	 * Gets the host of the connection.
	 *
	 * @return host of connection
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Gets the protocol of the connection such as 'mysql' or 'sqlite'.
	 *
	 * @return SQL protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * Gets the statement of the connection.
	 *
	 * @return
	 */
	public Statement getStatement() {
		return statement;
	}

	/**
	 * Gets the java connection of the connection.
	 *
	 * @return
	 */
	public java.sql.Connection getJavaConnection() {
		return connection;
	}
}
