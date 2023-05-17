package com.ashish.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;

public class JSONFileParsingExample {
	public static void main(String[] args) {
		String filePath = "/Users/a0k00ee/Documents/workspace/javaworkspace/Ashu-OPAI/src/com/ashish/json/data.json";

		try (FileReader fileReader = new FileReader(filePath)) {
			// Parse the JSON file into a JSONObject
			JSONTokener tokener = new JSONTokener(fileReader);
			JSONArray jsonArray = new JSONArray(tokener);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);

				// Extract values from the JSON object
				String name = json.getString("name");
				int age = json.getInt("age");
				String city = json.getString("city");

				// Print the extracted values
				System.out.println("Entry " + (i + 1));
				System.out.println("Name: " + name);
				System.out.println("Age: " + age);
				System.out.println("City: " + city);
				System.out.println();
			}

		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
	}
}
