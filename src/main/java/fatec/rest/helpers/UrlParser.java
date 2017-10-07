package fatec.rest.helpers;

import java.util.Map;
import java.util.TreeMap;

public class UrlParser {

	public Map<String, Object> parse(Map<String, String> params, Map<String, Object> endpoints) {
		Object[] urls = endpoints.values().toArray();
		Map<String, Object> parsed = new TreeMap<String, Object>();

		String pattern = "\\{\\{%s\\}\\}";
		
		for (int i = 0; i < urls.length; i++) {			
			for (Map.Entry<String, String> entry : params.entrySet()) {
				urls[i] = urls[i].toString().replaceAll(String.format(pattern, entry.getKey()), entry.getValue());
			}
		}

		String regex = ".*\\{\\{(.*)\\}\\}.*";
		int i = 0;

		for (Map.Entry<String, Object> entry : endpoints.entrySet()) {
			if (!urls[i].toString().matches(regex)) {
				parsed.put(entry.getKey(), urls[i]);				
			}
			i++;
		}
		
		return parsed;
	}
}
