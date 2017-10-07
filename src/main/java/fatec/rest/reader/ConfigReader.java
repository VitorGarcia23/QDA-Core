package fatec.rest.reader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonElement;

import fatec.json.handler.QSon;

public class ConfigReader {

	private QSon qson;
	private JsonElement json;

	public ConfigReader(String path) throws IOException {
		File file = new File(path);
		
		if (file.exists()) {			
			this.qson = new QSon();
			this.json = qson.fileToJson(new FileReader(path));
		} else {
			throw new IOException("Config file not found.");
		}
	}

	public JsonElement getJson() {
		return this.json;
	}
}