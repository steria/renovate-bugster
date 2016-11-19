package no.soprasteria.bugster.application.controller;

import com.google.gson.Gson;
import no.soprasteria.bugster.application.server.AppConfig;
import no.soprasteria.bugster.application.server.ReloadableAppConfigFile;
import no.soprasteria.bugster.business.match.domain.FootballMatch;
import no.soprasteria.bugster.business.match.service.MatchService;
import no.soprasteria.bugster.infrastructure.db.repository.MatchRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/match")
public class MatchController {

    private MatchService matchService;
    private Gson gson = new Gson();

    public MatchController() {
        AppConfig instance = ReloadableAppConfigFile.getInstance();
        matchService = new MatchService(new MatchRepository(instance.getDatabase()));
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    public Response all() {
        List<FootballMatch> list = matchService.findAll();
        return Response.status(200).entity(gson.toJson(list)).build();
    }

    @GET
    @Produces("application/json")
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") int id) {
        Optional<FootballMatch> footballMatch = matchService.findById(id);
        if(footballMatch.isPresent()) {
            return Response.status(200).entity(gson.toJson(footballMatch)).build();
        }
        return Response.status(400).build();
    }

    @GET
    @Produces("application/json")
    @Path("/search/name/{id}")
    public Response findByName(@PathParam("id") String id) {
        List<FootballMatch> byName = matchService.findAllByName(id);
        return Response.status(200).entity(gson.toJson(byName)).build();
    }

    @GET
    @Produces("application/json")
    @Path("/search/date/{id}")
    public Response findByDate(@PathParam("id") String date) {
        List<FootballMatch> byName = matchService.findAllByDate(date);
        return Response.status(200).entity(gson.toJson(byName)).build();
    }
}