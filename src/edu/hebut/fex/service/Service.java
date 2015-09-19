package edu.hebut.fex.service;

import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import edu.hebut.fex.exception.InternetConnectionException;
import edu.hebut.fex.utils.ConnectionUtils;
import edu.hebut.fex.utils.JSONUtils;

public class Service {

	/**
	 * The instance of Service
	 */
	private static Service mService;
	/**
	 * Default HttpClient from org.apache
	 */
	private HttpClient mClient;
	/**
	 * The Util which be used to connect to the remote server.
	 */
	private ConnectionUtils mConn;
	/**
	 * The URL for query method.
	 */
	private final String QUERY_URL = "http://192.168.191.1:8080/xdatahelp/connection/conn_executeQuery";
	/**
	 * The URL for update method.
	 */
	private final String UPDATE_URL = "http://192.168.191.1:8080/xdatahelp/connection/conn_executeUpdate";

	/**
	 * Private the constructor method
	 */
	private Service() {
		mConn = new ConnectionUtils();
		mClient = new DefaultHttpClient();
	}

	/**
	 * Get instance of Service.
	 * 
	 * @return a instance of Service.
	 */
	public synchronized static Service getInstance() {
		if (mService == null)
			mService = new Service();
		return mService;
	}

	/**
	 * Query method,this will be execute will you query.
	 * 
	 * @param token
	 *            The key every developer owns,if you don't hava a unique
	 *            token,please go to our website to apply for one.
	 * @param sql
	 *            The sql you want to execute.
	 * @param client
	 *            HttpClient from org.apache.If pass null we will use the
	 *            default HttpClient.
	 * @return A List with Map in it,each map represents a javaBean mapping in
	 *         your database.
	 * @throws InternetConnectionException
	 */
	public List<Map<String, Object>> query(String token, String sql,
			HttpClient client) throws InternetConnectionException {
		/* connection to the server and get the json Strings */
		String json;
		if (client == null)
			json = mConn.connect(this.mClient, sql, token, QUERY_URL);
		else
			json = mConn.connect(client, sql, token, QUERY_URL);
		/* write json string received from server to the console */
		System.out.println("query_json");
		System.out.println(json);
		/* parse the json String to List<Map<String,Object>> */
		List<Map<String, Object>> list = JSONUtils.parseJsonString(json);
		return list;
	}

	/**
	 * Update method,this will be execute will you query.
	 * 
	 * @param token
	 *            The key every developer owns,if you don't hava a unique
	 *            token,please go to our website to apply for one.
	 * @param sql
	 *            The sql you want to execute.
	 * @param client
	 *            HttpClient from org.apache.If pass null we will use the
	 *            default HttpClient.
	 * @return A List with Map in it,each map represents a javaBean mapping in
	 *         your database.
	 * @throws InternetConnectionException
	 */
	public Integer update(String token, String sql, HttpClient client)
			throws InternetConnectionException {
		/* connection to the server and get the json Strings */
		String json;
		if (client == null)
			json = mConn.connect(this.mClient, sql, token, UPDATE_URL);
		else
			json = mConn.connect(client, sql, token, UPDATE_URL);
		/* write json string received from server to the console */
		System.out.println("update_json");
		System.out.println(json);
		/* parse the json String to Integer */
		List<Map<String, Object>> list = JSONUtils.parseJsonString(json);
		if (!(list.size() > 0))
			return null;
		Map<String, Object> map = list.get(0);
		Integer value = (Integer) map.get("count");
		return value;
	}
}
