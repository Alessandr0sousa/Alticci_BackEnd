package br.com.alticci.controller;

import br.com.alticci.model.AlticciResponse;
import br.com.alticci.service.AlticciService;
import br.com.alticci.validation.AlticciIndice;
import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheManager;
import io.quarkus.cache.CacheResult;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/alticci")
@Produces(MediaType.APPLICATION_JSON)
public class AlticciController {

    @Inject
    AlticciService alticciService;

    @Inject
    CacheManager cacheManager;

    @GET
    @Path("/{n}")
    @CacheResult(cacheName = "get-alticci")
    public Response getValue(@Valid @PathParam("n") @AlticciIndice String n){
        System.out.println(n);
        return Response
                .ok(new AlticciResponse(alticciService.getValue(n)))
                .build();
    }

    @POST
    @Path("/cache/clear")
    public Response clearCache(){
        Optional<Cache> cache = cacheManager.getCache("get-alticci");
        if (cache.isPresent()) {
            return Response
                    .accepted(cache.get()
                            .invalidateAll()
                            .await()
                            .indefinitely())
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @GET
    @Path("/cache/Info")
    public Response getCache(){
        Optional<Cache> cache = cacheManager.getCache("get-alticci");
        return  Response.ok(cache).build();
    }

}
