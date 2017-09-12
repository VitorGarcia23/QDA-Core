package fatec.rest.reader;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonElement;

import fatec.json.handler.QSon;

public class ConfigReader {

	private QSon qson;
	private JsonElement json;

	public ConfigReader(String path) throws IOException {
		qson = new QSon();
		json = qson.fileToJson(new FileReader(getFilePath(path.toLowerCase())));
	}

	public JsonElement getJson() {
		return json;
	}

	private String getFilePath(String path) {
		return String.format("%s/%s", path + "/", "apis.json");
	}

}