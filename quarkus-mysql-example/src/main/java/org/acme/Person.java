package org.acme;

import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.List;

@Entity
public class Person extends PanacheEntity {
    public String name;
    public int age;

    public static Person findByName(String name){
        return find("name", name).firstResult();
    }

    public static List<Person> findByAge(Integer age){
        return list("age", age);
    }

    public static void deleteStefs(){
        delete("name", "Stef");
    }
}