package fatec.rest.qdacore;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	public Response result() {

		return Response.status(200).entity("").build();
	}
}
