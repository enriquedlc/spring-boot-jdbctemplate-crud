package com.accesodatos.springbootjdbctemplate.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accesodatos.springbootjdbctemplate.models.Actor;

/**
 * A class that implements the {@link JdbcRepository} interface
 * and provides methods for performing CRUD operations
 * on the actor data stored in a database using the
 * Spring JDBC template.
 * 
 * This class uses the Spring JDBC template to interact with the
 * database and perform CRUD operations.
 * 
 * The class is annotated with `@Repository` to indicate that
 * it is a repository class and it uses the `@Autowired`
 * annotation to inject an instance of the `JdbcTemplate` class. The SQL
 * statements for performing the CRUD operations are defined as constant
 * strings in the class.
 * 
 * @author AccesoDatos
 * 
 * @see JdbcRepository
 * @see Actor
 * @see JdbcTemplate
 * @see Autowired
 * @see Repository
 */

@Repository
public class ActorRepository implements JdbcRepository {

    /**
     * An instance of the {@link JdbcTemplate} class provided by the Spring JDBC
     * framework, used for executing
     * SQL statements and mapping the results to the {@link Actor} model class.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * A constant string representing the SQL statement for finding all actors in
     * the database.
     */
    private final String SQL_FIND_ALL_ACTORS = "SELECT * FROM actor";

    /**
     * A constant string representing the SQL statement for inserting an actor in
     * the database.
     */
    private final String SQL_INSERT_ACTOR = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";

    /**
     * A constant string representing the SQL statement for finding an actor by its
     * ID in the database.
     */
    private final String SQL_FIND_ACTOR_BY_ID = "SELECT * FROM actor WHERE actor_id = ?";

    /**
     * A constant string representing the SQL statement for updating an actor in the
     * database.
     */
    private final String SQL_UPDATE_ACTOR = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";

    /**
     * A constant string representing the SQL statement for deleting an actor from
     * the database.
     */
    private final String SQL_DELETE_ACTOR = "DELETE FROM actor WHERE actor_id = ?";

    /**
     * A constant string representing the SQL statement for finding actors by their
     * first name in the database.
     */
    private final String SQL_FIND_ACTOR_BY_STARTING_FIRSTNAME = "SELECT * FROM actor WHERE first_name LIKE ?";

    /**
     * Implementation of the save method from JdbcRepository to insert an actor
     * into the actor table in the database.
     * 
     * @param actor the actor to be inserted into the database
     * @return the number of rows affected by the database operation
     */
    @Override
    public int save(Actor actor) {
        return jdbcTemplate.update(SQL_INSERT_ACTOR,
                new Object[] {
                        actor.getFirst_name(),
                        actor.getLast_name()
                });
    }

    /**
     * Implementation of the update method from JdbcRepository to update an actor
     * in the actor table in the database.
     * 
     * @param actor the actor to be updated in the database
     * @return the number of rows affected by the database operation
     */
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

    /**
     * Implementation of the findById method from JdbcRepository to find an actor
     * in the actor table in the database by its ID.
     * 
     * @param actor_id the ID of the actor to be found in the database
     * @return the actor with the specified ID, or null if no actor with that ID
     */
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

    /**
     * Implementation of the deleteById method from JdbcRepository to delete an
     * actor from the actor table in the database by its ID.
     * 
     * @param actor_id the ID of the actor to be deleted from the database
     * @return the number of rows affected by the database operation
     */
    @Override
    public int deleteById(int actor_id) {
        return jdbcTemplate.update(SQL_DELETE_ACTOR, actor_id);
    }

    /**
     * Implementation of the findAll method from JdbcRepository to find all actors
     * in the actor table in the database.
     * 
     * @return a list of all actors in the database
     */
    @Override
    public List<Actor> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL_ACTORS,
                BeanPropertyRowMapper.newInstance(Actor.class));
    }

    /**
     * Implementation of the findActorByFirstName method from JdbcRepository to
     * find all actors in the actor table in the database whose first name starts
     * with the specified string.
     * 
     * @param first_name the first name of the actors to be found in the database
     * @return a list of all actors in the database whose first name starts with
     *         the specified string
     */
    @Override
    public List<Actor> findActorByFirstName(String first_name) {
        return jdbcTemplate.query(SQL_FIND_ACTOR_BY_STARTING_FIRSTNAME,
                BeanPropertyRowMapper.newInstance(Actor.class), first_name + "%");
    }

}
