package edu.eci.arep.controller;

import edu.eci.arep.model.Post;
import edu.eci.arep.services.StreamService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/v1/stream")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StreamController {

    @Inject
    StreamService streamService;

    @GET
    public Response getPosts() {
        return Response.ok(streamService.getStream()).build();
    }

    @POST
    public Response createPost(Post post) {
        return Response.ok(streamService.addPost(post)).build();
    }

}
