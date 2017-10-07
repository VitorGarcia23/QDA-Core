package fatec.rest.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
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

	public static JsonElement proccess(Map<String, String> params, String path) throws IOException {
		ConfigReader reader = new ConfigReader(path);
		Config config = new Config(reader.getJson());

		Map<String, Object> endpoints = (Map<String, Object>) config.get("*", "url");
		UrlParser parser = new UrlParser();

		Map<String, Object> parsed = parser.parse(params, endpoints);
		List<QSonElement> elements = new ArrayList<QSonElement>();

		QSon qson = new QSon();
		JsonArray errors = new JsonArray();

		parsed.forEach((key, url) -> {			
			String response;
			
			try {
				response = HttpHelper.get(url.toString());
				elements.add(new QSonElement(key, qson.stringToJson(response)));
			} catch (IOException e) {
				errors.add(key);
			}
		});

		JsonObject obj = null;
		
		if (!elements.isEmpty()) {			
			obj = qson.merge(elements).getAsJsonObject();
		} else {
			obj = new JsonObject();
		}

		obj.add("requestErrors", errors);
		obj.addProperty("bulkResponse", new Boolean(errors.size() == 0));
		obj.addProperty("providedBy", "QDA Core Service");
		obj.addProperty("creators", "Guilherme Vasconcellos and Vitor Garcia");

		return obj;
	}
}
