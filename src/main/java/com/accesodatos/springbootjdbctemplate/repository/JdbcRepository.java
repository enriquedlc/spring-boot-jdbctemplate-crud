package com.accesodatos.springbootjdbctemplate.repository;

import java.util.List;

import com.accesodatos.springbootjdbctemplate.models.Actor;

public interface JdbcRepository {

    int save(Actor actor);

    int update(Actor actor);

    Actor findById(int actor_id);

    int deleteById(int actor_id);

    List<Actor> findAll();

    List<Actor> findActorByFirstName(String first_name);
}
