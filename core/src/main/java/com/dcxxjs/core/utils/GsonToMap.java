package com.dcxxjs.core.utils;
/**
 * 
 * @author Administrator
 *
 */

import java.lang.reflect.Type;
import java.util.Map;
import java.util.SortedMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GsonToMap {
	
	public static Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
	
	public static <T> SortedMap<String, String> toMap(T entity)
	{
		Type type = new TypeToken<SortedMap<String, String>>() {}.getType();
		SortedMap<String, String> result = gson.fromJson(gson.toJson(entity), type);
		return result;
	}
	
	public static String toString(Map<String,Object> entity)
	{
		return gson.toJson(entity);
	}
	
	public static <T> SortedMap<String, String> toMap(String json)
	{
		Type type = new TypeToken<SortedMap<String, String>>() {}.getType();
		SortedMap<String, String> result = gson.fromJson(json, type);
		return result;
	}
}
