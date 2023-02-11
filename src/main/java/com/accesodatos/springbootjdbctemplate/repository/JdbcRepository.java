package com.accesodatos.springbootjdbctemplate.repository;

import java.util.List;

import com.accesodatos.springbootjdbctemplate.models.Actor;

/**
 * This interface defines the methods for a JDBC repository
 * to perform CRUD operations on the Actor object.
 *
 * @author AccesoDatos
 * @version 1.0
 */
public interface JdbcRepository {

    /**
     * Saves the given {@link Actor} object to the database.
     *
     * @param actor The actor to be saved.
     * @return The number of rows affected.
     */
    int save(Actor actor);

    /**
     * Updates the given {@link Actor} object in the database.
     *
     * @param actor The actor to be updated.
     * @return The number of rows affected.
     */
    int update(Actor actor);

    /**
     * Retrieves the {@link Actor} object with the given actor ID from the database.
     *
     * @param actor_id The ID of the actor to retrieve.
     * @return The retrieved actor, or {@code null} if no such actor was found.
     */
    Actor findById(int actor_id);

    /**
     * Deletes the {@link Actor} object with the given actor ID from the database.
     *
     * @param actor_id The ID of the actor to delete.
     * @return The number of rows affected.
     */
    int deleteById(int actor_id);

    /**
     * Retrieves a list of all {@link Actor} objects stored in the database.
     *
     * @return A list of actors.
     */
    List<Actor> findAll();

    /**
     * Retrieves a list of {@link Actor} objects with the given first name from the
     * database.
     *
     * @param first_name The first name to search for.
     * @return A list of actors with the given first name.
     */
    List<Actor> findActorByFirstName(String first_name);
}
