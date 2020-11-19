package org.acme.repository;

import org.acme.Fruit;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface FruitRepository extends CrudRepository<Fruit, Long> {

    List<Fruit> findByColor(String color);
}
