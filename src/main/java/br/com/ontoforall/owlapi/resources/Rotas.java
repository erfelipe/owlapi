package br.com.ontoforall.owlapi.resources;

import br.com.ontoforall.owlapi.model.ElementosOWL;
import br.com.ontoforall.owlapi.model.Info;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

@Path("ontology")
public class Rotas {

	public Rotas() {

	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String funciona() {
		return Boolean.TRUE.toString();
	}

	@GET
	@Path("info")
	@Produces(MediaType.TEXT_PLAIN)
	public Response info() {
		Info info = new Info();

		return Response.status(Status.ACCEPTED).entity(info.getInfo()).build();
	}

	@POST
	@Path("valid")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validacao(String ontologia) {
		ElementosOWL elementos = new ElementosOWL(new JSONObject(ontologia));
		String resp = elementos.validaOWL();
		return Response.status(Status.ACCEPTED).entity(resp).build();
	}

	@POST
	@Path("format")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response formata(String ontologia) {
		ElementosOWL elementos = new ElementosOWL(new JSONObject(ontologia));
		String resp = elementos.formataOWL();
		return Response.status(Status.ACCEPTED).entity(resp).build();
	}

	@POST
	@Path("read")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_XML)
	public Response readFromOWL(String ontologia) throws OWLOntologyCreationException {
		ElementosOWL elementos = new ElementosOWL(new JSONObject(ontologia));
		String resp = elementos.readFromOWL(ontologia);
		return Response.status(Status.ACCEPTED)
						.entity(resp)
						.build();
	}
}
