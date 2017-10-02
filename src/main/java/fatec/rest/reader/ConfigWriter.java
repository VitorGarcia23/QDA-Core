package fatec.rest.reader;

import java.io.IOException;
import java.io.PrintWriter;

public abstract class ConfigWriter {

	public static String WriteFile(String fileJson) {
		try ( PrintWriter writer = new PrintWriter("config/apis.json", "UTF-8")){
		    writer.println(fileJson);
		    return "Success";
		} catch (IOException e) {
			return e.getMessage();
		}
	}
	
}
