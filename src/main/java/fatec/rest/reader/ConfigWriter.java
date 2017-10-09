package fatec.rest.reader;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ws.rs.core.Response;

public abstract class ConfigWriter {

	public static boolean WriteFile(String fileJson, String path) {
		try (PrintWriter writer = new PrintWriter(path, "UTF-8")) {
			writer.println(fileJson);
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	public static boolean VerifyPath(String jsonText) {
		if(jsonText == null || jsonText.equals("")) {
			return false;
		}
		return true;
	}
}
