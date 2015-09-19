package edu.hebut.fex;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.hebut.fex.exception.InternetConnectionException;
import edu.hebut.fex.service.Service;
import edu.hebut.fex.utils.ConnectionUtils;
import edu.hebut.fex.utils.JSONUtils;
import edu.hebut.fex.utils.StreamUtils;

public class FexUtils {

	/**
	 * The token which developer owns.
	 */
	private String mToken;
	/**
	 * This is a important class,each query or update call should through it.
	 */
	private Service mService;

	/**
	 * Private the constructor method
	 * 
	 * @param token
	 *            The key every developer owns,if you don't hava a unique
	 *            token,please go to our website to apply for one.
	 */
	private FexUtils(String token) {
		this.mToken = token;
		mService = Service.getInstance();
	}

	/**
	 * Regist by Token and return the instance of the class of FexUtils.This is
	 * the entrance of FexUtils
	 * 
	 * @param token
	 *            The key every developer owns,if you don't hava a unique
	 *            token,please go to our website to apply for one.
	 * 
	 * @return The instance of the class of FexUtils
	 */
	public static FexUtils regist(String token) {
		return new FexUtils(token);
	}

	/*************************************************************************************************/
	/**
	 * Execute the query SQL language,we will use the default HttpClient.
	 * 
	 * @param sql
	 *            The sql you want to execute.
	 * @return A List with Map in it,each map represents a javaBean mapping in
	 *         your database.
	 * @throws InternetConnectionException
	 */
	public List<Map<String, Object>> query(String sql)
			throws InternetConnectionException {
		return query(sql, null);
	}

	/**
	 * Execute the query SQL language,we will use the HttpClient you passed.If
	 * client is null,we will use the default HttpClient.
	 * 
	 * @param sql
	 *            The sql you want to execute.
	 * @param client
	 *            HttpClient from org.apache.
	 * @return A List with Map in it,each map represents a javaBean mapping in
	 *         your database.
	 * @throws InternetConnectionException
	 */
	public List<Map<String, Object>> query(String sql, HttpClient client)
			throws InternetConnectionException {
		return mService.query(this.mToken, sql, client);
	}

	/*************************************************************************************************/
	/**
	 * Execute the update SQL language,we will use the default HttpClient.
	 * 
	 * @param sql
	 *            The sql you want to execute.
	 * @return A List with Map in it,each map represents a javaBean mapping in
	 *         your database.
	 * @throws InternetConnectionException
	 */
	public Integer update(String sql) throws InternetConnectionException {
		return update(sql, null);
	}

	/**
	 * Execute the update SQL language,we will use the HttpClient you passed.If
	 * client is null,we will use the default HttpClient.
	 * 
	 * @param sql
	 *            The sql you want to execute.
	 * @param client
	 *            HttpClient from org.apache.
	 * @return A List with Map in it,each map represents a javaBean mapping in
	 *         your database.
	 * @throws InternetConnectionException
	 */
	public Integer update(String sql, HttpClient client)
			throws InternetConnectionException {
		return mService.update(this.mToken, sql, client);
	}
	/*************************************************************************************************/
}
