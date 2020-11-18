package org.mengka.endpoint;

import com.google.gson.Gson;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.mengka.model.Cfops;
import org.mengka.model.CfopsResposta;
import org.mengka.service.GreetingService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {

    @Inject
    GreetingService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws Exception{

        long start = System.currentTimeMillis();
        String result = "Success!";
        long endTime = System.currentTimeMillis();
        Cfops cfopsDO = new Cfops();
        cfopsDO.setId(1001L);
        cfopsDO.setNome(result+" "+(endTime - start)+"ms");
        cfopsDO.setTime((endTime - start)+"ms");

        CfopsResposta cfopsResposta = new CfopsResposta();
        cfopsResposta.cfops.add(cfopsDO);

        return new Gson().toJson(cfopsResposta);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(@PathParam String name) {
        return service.greeting(name);
    }
}