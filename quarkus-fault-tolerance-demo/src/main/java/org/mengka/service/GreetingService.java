package org.mengka.service;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author mengka
 * @version 2020/11/18
 * @since
 */
@ApplicationScoped
public class GreetingService {

    public String greeting(String name) {
        return "hello " + name;
    }
}
