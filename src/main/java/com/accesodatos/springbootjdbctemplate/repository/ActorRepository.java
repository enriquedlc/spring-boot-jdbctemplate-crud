package com.accesodatos.springbootjdbctemplate.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accesodatos.springbootjdbctemplate.models.Actor;

@Repository
public class ActorRepository implements JdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_ALL_ACTORS = "SELECT * FROM actor";
    private final String SQL_INSERT_ACTOR = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
    private final String SQL_FIND_ACTOR_BY_ID = "SELECT * FROM actor WHERE actor_id = ?";
    private final String SQL_UPDATE_ACTOR = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
    private final String SQL_DELETE_ACTOR = "DELETE FROM actor WHERE actor_id = ?";
    private final String SQL_FIND_ACTOR_BY_STARTING_FIRSTNAME = "SELECT * FROM actor WHERE first_name LIKE ?";

    @Override
    public int save(Actor actor) {
        return jdbcTemplate.update(SQL_INSERT_ACTOR,
                new Object[] {
                        actor.getFirst_name(),
                        actor.getLast_name()
                });
    }

    @Override
    public int update(Actor actor) {
        Actor existingActor = findById(actor.getActor_id());
        if (existingActor != null) {
            return jdbcTemplate.update(SQL_UPDATE_ACTOR,
                    new Object[] {
                            actor.getFirst_name(),
                            actor.getLast_name(),
                            actor.getActor_id()
                    });
        } else {
            return 0;
        }
    }

    @Override
    public Actor findById(int actor_id) {
        try {
            Actor actor = jdbcTemplate.queryForObject(SQL_FIND_ACTOR_BY_ID,
                    BeanPropertyRowMapper.newInstance(Actor.class),
                    actor_id);
            return actor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteById(int actor_id) {
        return jdbcTemplate.update(SQL_DELETE_ACTOR, actor_id);
    }

    @Override
    public List<Actor> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL_ACTORS,
                BeanPropertyRowMapper.newInstance(Actor.class));
    }

    @Override
    public List<Actor> findActorByFirstName(String first_name) {
        return jdbcTemplate.query(SQL_FIND_ACTOR_BY_STARTING_FIRSTNAME,
                BeanPropertyRowMapper.newInstance(Actor.class), first_name + "%");
    }

}
