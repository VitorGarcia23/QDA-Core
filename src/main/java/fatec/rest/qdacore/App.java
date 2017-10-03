package fatec.rest.qdacore;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fatec.rest.reader.Config;
import fatec.rest.reader.ConfigWriter;
import fatec.rest.services.HttpService;

@Path("/")
public class App {

	@Path("/config")
	@POST
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response config(String jsonText, @Context ServletContext ctx	) {
		String path = Config.getFilePath(ctx); 
		boolean result = ConfigWriter.WriteFile(jsonText,path);
    
		if(result)
			return Response.status(201).build();
		else
			return Response.status(500).build();
	}

	@Path("/result")
	@GET
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response result(@Context UriInfo uri, @Context ServletContext ctx) {
		String path = Config.getFilePath(ctx);

		Map<String, String> params = new TreeMap<String, String>();

		uri.getQueryParameters().forEach((key, value) -> {
			params.put(key, value.get(value.size() - 1));
		});

		String json = HttpService.proccess(params, path).toString();

		return Response.status(200).entity(json).build();
	}
}
