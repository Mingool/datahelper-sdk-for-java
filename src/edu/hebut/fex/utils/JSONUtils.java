package edu.hebut.fex.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {

	/**
	 * Parse the json String to List<Map<String,Object>> and return. return null
	 * if error happens.
	 * 
	 * @param json
	 *            The JSON String from the remote server.
	 * @return A List with Map in it,each map represents a javaBean mapping in
	 *         your database.
	 */
	public static List<Map<String, Object>> parseJsonString(String json) {
		if (json == null || "".equals(json))
			return null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			/* first we should make a JSONArray */
			JSONArray array = new JSONArray(json.trim());
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				/* now let's traverse the JSONObject */
				Iterator it = object.keys();
				while (it.hasNext()) {
					String key = (String) it.next();
					Object value = object.get(key);
					/* now let's make a new Map and add it to the ArrayList */
					Map<String, Object> map = new HashMap<String, Object>();
					map.put(key, value);
					list.add(map);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		/*
		 * check the size of the List<Map<String,Object>> and return the right
		 * result
		 */
		return list.size() > 0 ? list : null;
	}
}
