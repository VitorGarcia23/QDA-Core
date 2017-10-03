package fatec.rest.reader;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonElement;

import fatec.json.handler.QSon;

public class ConfigReader {

	private QSon qson;
	private JsonElement json;

	public ConfigReader(String path) throws IOException {
		this.qson = new QSon();
		this.json = qson.fileToJson(new FileReader(path));
	}

	public JsonElement getJson() {
		return this.json;
	}
}