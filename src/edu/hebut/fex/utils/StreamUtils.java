package edu.hebut.fex.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtils {
	/**
	 * When get the stream from the remote server.Use this encode to parse
	 * stream to String.
	 */
	private final static String ENCODING = "UTF-8";

	/**
	 * Put the byte array to String and return.
	 * 
	 * @param in
	 *            The inputstream from the remote server.
	 * @param encoding
	 *            The encoding when parse stream to String.
	 * @return JSON String from the remote server.
	 */
	public static String parseStreamToString(InputStream in, String encoding) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			/* get the byte array */
			byte[] res = out.toByteArray();
			/* close the stream */
			in.close();
			out.close();
			return new String(res, ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
