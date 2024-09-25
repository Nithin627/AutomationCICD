package com.nithin.data;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	List<HashMap<String, String>> data;

	public List<HashMap<String, String>> getJsonDataToMap() {

//		read json to string
		try {
			String jsonContent = FileUtils.readFileToString(
					new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\nithin\\data\\purcahse.json"),
					StandardCharsets.UTF_8);

//		convert string to hash map jackson databind

			ObjectMapper mapper = new ObjectMapper();
			data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}
