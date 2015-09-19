package edu.hebut.fex.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import edu.hebut.fex.exception.InternetConnectionException;

public class ConnectionUtils {

	/**
	 * The encoding when sending data to remote server.
	 */
	private final String ENCODING = "UTF-8";

	/**
	 * Connecting to the Internet and then execute the SQL.In the end,get the
	 * String from the server;
	 * 
	 * @param client
	 *            A HttpClient.
	 * @param sql
	 *            The sql you want to execute.
	 * @param token
	 *            The key every developer owns,if you don't hava a unique
	 *            token,please go to our website to apply for one.
	 * @return String from the remote server.
	 * @throws InternetConnectionException
	 */
	public String connect(HttpClient client, String sql, String token,
			String url) throws InternetConnectionException {
		String json = null;
		HttpPost post = new HttpPost(url);
		try {
			/* add the parameters to the postMethod */
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("token", token));
			parameters.add(new BasicNameValuePair("sql", sql));
			post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));

			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				InputStream in = response.getEntity().getContent();
				json = StreamUtils.parseStreamToString(in, ENCODING);
			} else {
				throw new InternetConnectionException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
