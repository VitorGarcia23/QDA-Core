package fatec.rest.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

@SuppressWarnings("unchecked")
public class Config {

	private Map<String, Object> config;
	private static Gson gson;

	public Config(JsonElement json) {
		gson = new Gson();

		this.config = (Map<String, Object>) gson.fromJson(json, Map.class);
	}

	public Object get(String parent, String child) {
		if (parent == "*") {
			return getFromPattern(child);
		}

		if (config.containsKey(parent)) {
			if (!getAsMap(config.get(parent)).containsKey(child)) {
				throw new RuntimeException(String.format("Key \"%s\" not found in \"%s\".", child, parent));
			}
		} else {
			throw new RuntimeException(String.format("Key \"%s\" not found in json.", child));
		}

		JsonElement json = gson.toJsonTree(config.get(parent));

		if (json.isJsonArray()) {
			return getAsList(json);
		}

		return getAsMap(config.get(parent)).get(child);
	}

	private Map<String, Object> getFromPattern(String child) {
		Map<String, Object> map = new TreeMap<String, Object>();

		config.forEach((key, value) -> {
			map.put(key, getAsMap(value).get(child));
		});

		return map;
	}

	private static Map<String, Object> getAsMap(Object obj) {
		return (Map<String, Object>) obj;
	}

	private static List<Object> getAsList(JsonElement json) {
		return gson.fromJson(json, ArrayList.class);
	}

	public static String getFilePath(ServletContext ctx) {
		return ctx.getRealPath("") + "/apis.json";
	}
}
