package fatec.rest.qdacore;

import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fatec.rest.services.HttpService;

@Path("/")
public class App {

	@Path("/config")
	@POST
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response config(String config) {
		System.out.println(config);

		return Response.status(200).entity("").build();
	}

	@Path("/result")
	@GET
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response result(@Context UriInfo uri) {
		Map<String, String> params = new TreeMap<String, String>();
		
		uri.getQueryParameters().forEach((key, value) -> {
			params.put(key, value.get(value.size() - 1));
		});
		
		String json = HttpService.proccess(params).toString();

		return Response.status(200).entity(json).build();
	}
}
