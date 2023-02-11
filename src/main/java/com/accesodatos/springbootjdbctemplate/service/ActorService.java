package com.accesodatos.springbootjdbctemplate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accesodatos.springbootjdbctemplate.models.Actor;
import com.accesodatos.springbootjdbctemplate.repository.ActorRepository;

/**
 * The `ActorService` class provides methods for performing
 * CRUD operations on actors.
 * 
 * The @Service annotation is used to indicate that this
 * class is a service class and it uses the @Autowired annotation
 * to inject an instance of the `ActorRepository` class.
 *
 * @author AccesoDatos
 * @version 1.0
 */
@Service
public class ActorService {

    /**
     * An instance of `ActorRepository` for performing database operations on
     * actors.
     */
    @Autowired
    ActorRepository actorRepository;

    /**
     * Retrieves a list of all actors from the database.
     *
     * @return A list of all actors.
     */
    public List<Actor> findAllActors() {
        return actorRepository.findAll();
    }

    /**
     * Creates a new actor in the database.
     *
     * @param actor The actor to be created.
     * @return The id of the created actor.
     */
    public int createActor(Actor actor) {
        return actorRepository.save(
                new Actor(
                        actor.getFirst_name(),
                        actor.getLast_name()));
    }

    /**
     * Retrieves an actor with a specific id from the database.
     *
     * @param actor_id The id of the actor to be retrieved.
     * @return The actor with the specified id.
     */
    public Actor findActorById(int actor_id) {
        return actorRepository.findById(actor_id);
    }

    /**
     * Deletes an actor with a specific id from the database.
     *
     * @param actor_id The id of the actor to be deleted.
     * @return The number of deleted rows.
     */
    public int deleteActorById(int actor_id) {
        return actorRepository.deleteById(actor_id);
    }

    /**
     * Updates an existing actor in the database.
     *
     * @param actor The updated actor.
     * @return `true` if the update was successful, `false` otherwise.
     */
    public Boolean updateActor(Actor actor) {
        return actorRepository.update(actor) == 0;
    }

    /**
     * Retrieves a list of actors with a specific first name from the database.
     *
     * @param first_name The first name of the actors to be retrieved.
     * @return A list of actors with the specified first name.
     */
    public List<Actor> findActorByFirstName(String first_name) {
        return actorRepository.findActorByFirstName(first_name);
    }
}