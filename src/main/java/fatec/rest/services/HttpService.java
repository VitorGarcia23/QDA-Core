package fatec.rest.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fatec.json.handler.QSon;
import fatec.json.model.QSonElement;
import fatec.rest.helpers.HttpHelper;
import fatec.rest.helpers.UrlParser;
import fatec.rest.reader.Config;
import fatec.rest.reader.ConfigReader;

@SuppressWarnings("unchecked")
public abstract class HttpService {

	public static JsonElement proccess(Map<String, String> params, String path) {
		ConfigReader reader;

		try {
			reader = new ConfigReader(path);
			Config config = new Config(reader.getJson());

			Map<String, Object> endpoints = (Map<String, Object>) config.get("*", "url");
			UrlParser parser = new UrlParser();

			Map<String, Object> parsed = parser.parse(params, endpoints);
			List<QSonElement> elements = new ArrayList<QSonElement>();

			QSon qson = new QSon();

			parsed.forEach((key, url) -> {
				elements.add(new QSonElement(key, qson.stringToJson(HttpHelper.get(url.toString()))));
			});

			JsonObject obj = qson.merge(elements).getAsJsonObject();

			obj.addProperty("bulkResponse", new Boolean(true));
			obj.addProperty("providedBy", "QDA Core Service");
			obj.addProperty("creators", "Guilherme Vasconcellos and Vitor Garcia");

			return obj;
		} catch (IOException e) {
			JsonObject obj = new JsonObject();
			obj.addProperty("error", "Internal Server Error");
			
			return obj;
		}
	}
}
