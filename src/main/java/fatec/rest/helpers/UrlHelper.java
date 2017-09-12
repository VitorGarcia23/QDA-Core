package fatec.rest.helpers;

import java.io.IOException;
import java.util.Map;

import fatec.rest.interfaces.QDAParameterizable;
import fatec.rest.reader.Config;
import fatec.rest.reader.ConfigReader;

public abstract class UrlHelper {
	
	public static String formatUrl(QDAParameterizable parameterClass, String endpoint) {
		try {
			return formatUrlByParameter(parameterClass.getParameters(), getEndpoint(endpoint));
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

	}

	private static String formatUrlByParameter(Map<String, String> parameters, String url) {
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			url = url.replace(entry.getKey(), entry.getValue());
		}

		return url;
	}

	@SuppressWarnings("unchecked")
	private static String getEndpoint(String endpoint) throws IOException {
		ConfigReader reader = new ConfigReader("config");
		Config config = new Config(reader.getJson());

		Map<String, String> endpointsMap = (Map<String, String>) config.get("*", "url");

		return endpointsMap.get(endpoint);
	}
}
