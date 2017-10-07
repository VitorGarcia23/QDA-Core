package fatec.rest.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class HttpHelper {

	public static String get(String urlName) throws IOException {
		String jsonResponse = "";
		HttpURLConnection connection = null;

		URL url = createURL(urlName);

		connection = openConnection(url, connection);
		int responseCode = getResponseCode(connection);
		jsonResponse = readResponse(responseCode, connection);

		return jsonResponse;
	}

	private static URL createURL(String urlName) throws MalformedURLException {
		if (urlName != null && !urlName.equals(""))
			return new URL(urlName);

		throw new RuntimeException("Invalid Url.");
	}

	private static int getResponseCode(HttpURLConnection connection) throws IOException {
		if (connection != null)
			return connection.getResponseCode();
		throw new RuntimeException("Error on get response status.");
	}

	private static HttpURLConnection openConnection(URL url, HttpURLConnection connection) throws IOException {
		connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-type", "application/json");
		connection.setDoOutput(true);

		return connection;
	}

	private static String readResponse(int responseCode, HttpURLConnection connection) throws IOException {
		if (responseCode == 200) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}

			reader.close();

			return response.toString();
		}

		throw new RuntimeException("Some errors occurred. \nStatus Code : " + responseCode);
	}
}
