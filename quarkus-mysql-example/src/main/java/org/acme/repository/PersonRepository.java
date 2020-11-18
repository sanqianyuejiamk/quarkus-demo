package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.Person;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

/**
 * @author mengka
 * @version 2020/11/18
 * @since
 */
@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public Person findByName(String name){
        return find("name", name).firstResult();
    }

    public List<Person> findAlive(){
        return list("status", 1);
    }

    public void deleteStefs(){
        delete("name", "Stef");
    }
}
