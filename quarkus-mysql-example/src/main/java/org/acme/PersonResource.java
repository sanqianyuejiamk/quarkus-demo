package org.acme;

import org.acme.repository.FruitRepository;
import org.acme.repository.PersonRepository;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/person")
public class PersonResource {

    @Inject
    PersonRepository personRepository;

    @Inject
    FruitRepository fruitRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPersons() {
        return Person.listAll();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonByName(@PathParam String name) {
        return Person.findByName(name);
    }

    @GET
    @Path("/foo")
    @Produces(MediaType.APPLICATION_JSON)
    public Person foo() {
        return personRepository.findByName("mengka");
    }

    @GET
    @Path("/fruit")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> fruit() {
        return fruitRepository.findByColor("Green");
    }
}