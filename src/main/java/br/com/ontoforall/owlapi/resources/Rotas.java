package br.com.ontoforall.owlapi.resources;

import br.com.ontoforall.owlapi.model.OntologyExporter;
import br.com.ontoforall.owlapi.model.AxiomValidator;
import br.com.ontoforall.owlapi.model.Info;
import br.com.ontoforall.owlapi.model.Manager;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.json.JSONException;
import org.json.JSONObject;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

@Path("ontology")
public class Rotas {

	public Rotas() {

	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response funciona() {
		String resp = Boolean.TRUE.toString();
		return Response.status(Status.ACCEPTED).entity(resp).build();
	}

	@GET
	@Path("info")
	@Produces(MediaType.TEXT_PLAIN)
	public Response info() {
		Info info = new Info();

		return Response.status(Status.ACCEPTED).entity(info.getInfo()).build();
	}

	@GET
	@Path("getclasses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response pizzaQuantAxioms() throws OWLOntologyCreationException {
		Manager manager = new Manager();
		return Response.status(Status.ACCEPTED).entity(manager.getClasses()).build();
		
	}
	
	@POST
	@Path("valid")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response validacao(String ontologia) throws JSONException, Exception {
		System.out.print(ontologia);
		AxiomValidator validator = new AxiomValidator(new JSONObject(ontologia));
		String resp = validator.validAxioms();
//		return Response.status(Status.ACCEPTED).entity(resp).build();
		return Response.status(Status.ACCEPTED).header("Access-Control-Allow-Origin", "*").entity(resp).build();
	}

	@POST
	@Path("valid2")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response validacao2(String ontologia) throws Exception {
		String resp = Boolean.TRUE.toString();
		return Response.status(Status.ACCEPTED).entity(resp).build();
	}

	@POST
	@Path("valid3")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response validacao3(String txt) throws Exception {
		String resp = "Teste com o post ! " + txt;
		return Response.status(Status.OK).entity(resp).build();
	}
	
	@POST
	@Path("valid4")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response validacao4(String txt) {
		try {
			String resp = "Teste com o post ! " + txt;
			return Response.status(Status.ACCEPTED).entity(resp).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
		
	@POST
	@Path("format")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response formata(String ontology) throws JSONException, Exception {
		OntologyExporter ont = new OntologyExporter(new JSONObject(ontology));
		String resp = ont.exportOntology();
//		return Response.status(Status.ACCEPTED).entity(resp).build();
		return Response.status(Status.ACCEPTED).header("Access-Control-Allow-Origin", "*").entity(resp).build();
	}

	/*
	 * @POST
	 * 
	 * @Path("read")
	 * 
	 * @Produces(MediaType.TEXT_PLAIN)
	 * 
	 * @Consumes(MediaType.APPLICATION_XML) public Response readFromOWL(String
	 * ontologia) throws OWLOntologyCreationException { ElementosOWL elementos = new
	 * ElementosOWL(); String resp = elementos.readFromOWL(ontologia); return
	 * Response.status(Status.ACCEPTED) .entity(resp) .build(); }
	 */
}
