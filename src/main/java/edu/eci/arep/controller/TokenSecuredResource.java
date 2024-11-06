package edu.eci.arep.controller;

import org.eclipse.microprofile.jwt.JsonWebToken;

import edu.eci.arep.model.User;
import edu.eci.arep.security.jwt.GenerateToken;
import edu.eci.arep.security.jwt.GenerateToken.TokenDto;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/secured")
@RequestScoped
public class TokenSecuredResource {

    @Inject
    JsonWebToken jwt;
    @Inject
    GenerateToken tokenService;

    @POST
    @Path("login")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        TokenDto token = tokenService.createToken(user);
        if (token != null) {
            return Response.ok(token).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
