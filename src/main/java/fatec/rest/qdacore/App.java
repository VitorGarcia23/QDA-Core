package fatec.rest.qdacore;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fatec.rest.reader.Config;
import fatec.rest.reader.ConfigWriter;

@Path("/")
public class App {

	@Path("/config")
	@POST
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response config(String jsonText, @Context ServletContext ctx	) {
		String path = Config.getFilePath(ctx); 
		boolean result = ConfigWriter.WriteFile(jsonText,path);
		if(result)
			return Response.status(200).entity("").build();
		else
			return Response.status(500).entity("").build();
	}

	@Path("/result")
	@GET
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response result(@Context ServletContext ctx) {
		String path = Config.getFilePath(ctx); //path para passar ao reader
		//verificar se o arquivo existe antes de continuar; caso o arquivo não exista retornar uma mensagem de erro
		return Response.status(200).entity("").build();
	}
	
}
