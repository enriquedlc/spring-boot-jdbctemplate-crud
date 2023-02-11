package com.accesodatos.springbootjdbctemplate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accesodatos.springbootjdbctemplate.models.Actor;
import com.accesodatos.springbootjdbctemplate.service.ActorService;

/**
 * This is a Spring RESTful web service controller class, responsible for
 * handling HTTP requests related to Actors.
 * 
 * The class is annotated with @RestController which is a combination
 * of @Controller and @ResponseBody and indicates that this class is a
 * controller and all its methods will return response data.
 * 
 * @RequestMapping("/api/v2") indicates that all the requests handled by this
 * controller will start with "/api/v2".
 * 
 * The class has the following methods:
 * 
 * 1. getAllActors
 * 2. createActor
 * 3. getActorById
 * 4. deleteActor
 * 5. updateActor
 * 6. getActorByFirstName
 * 
 * In each method, the class returns an instance of ResponseEntity with
 * appropriate HTTP status codes and response data.
 * 
 * The class also uses the ActorService class for retrieving and modifying Actor
 * information. It is autowired using the @Autowired annotation.
 */

@RestController
@RequestMapping("/api/v2")
public class ActorController {

    @Autowired
    ActorService actorService;

    /**
     * Handles a GET request to "/api/v2/actors" and returns a list of all Actors.
     * 
     * @return ResponseEntity with a list of all Actors and HTTP status code.
     */
    @GetMapping("/actors")
    public ResponseEntity<List<Actor>> getAllActors() {
        try {
            List<Actor> actors = new ArrayList<Actor>();
            actorService.findAllActors().forEach(actors::add);

            if (actors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(actors, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles a POST request to "/api/v2/actors" and creates a new Actor with the
     * information provided in the request body.
     * 
     * @param actor
     * @return ResponseEntity with a message and HTTP status code.
     */
    @PostMapping("/actors")
    public ResponseEntity<String> createActor(@RequestBody Actor actor) {
        try {
            actorService.createActor(
                    new Actor(
                            actor.getFirst_name(),
                            actor.getLast_name()));
            return new ResponseEntity<>("Actor created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Actor wasn't created", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles a GET request to "/api/v2/actors/{actor_id}" and returns an Actor
     * with the specified id.
     * 
     * @param actor_id
     * @return ResponseEntity with an Actor and HTTP status code.
     */
    @GetMapping("/actors/{actor_id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("actor_id") int actor_id) {
        try {
            Actor actor = actorService.findActorById(actor_id);
            if (actor != null) {
                return new ResponseEntity<>(actor, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles a DELETE request to "/api/v2/actors/{actor_id}" and deletes the
     * Actor with the specified id.
     * 
     * @param actor_id
     * @return ResponseEntity with a message and HTTP status code.
     */
    @DeleteMapping("/actors/{actor_id}")
    public ResponseEntity<String> deleteActor(@PathVariable("actor_id") int actor_id) {
        int result = actorService.deleteActorById(actor_id);
        if (result == 0) {
            return new ResponseEntity<>("Cannot find Actor with id = " + actor_id, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Actor deleted successfully", HttpStatus.OK);
        }
    }

    /**
     * Handles a PUT request to "/api/v2/actors/{actor_id}" and updates the Actor
     * with the specified id.
     * 
     * @param actor_id
     * @param actor
     * @return ResponseEntity with a message and HTTP status code.
     */
    @PutMapping("/actors/{actor_id}")
    public ResponseEntity<String> updateActor(@PathVariable("actor_id") int actor_id, @RequestBody Actor actor) {
        try {
            if (actorService.updateActor(
                    new Actor(
                            actor_id,
                            actor.getFirst_name(),
                            actor.getLast_name()))) {
                return new ResponseEntity<>("Cannot find Actor with id = " + actor_id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Actor updated succesfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Actor wasn't updated", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles a GET request to "/api/v2/actors/search" and returns a list of
     * Actors with the specified first name.
     * 
     * @param first_name
     * @return ResponseEntity with a list of Actors and HTTP status code.
     */
    @GetMapping("/actors/search")
    public ResponseEntity<List<Actor>> getActorByFirstName(@RequestParam("first_name") String first_name) {
        try {
            List<Actor> actors = actorService.findActorByFirstName(first_name);

            if (actors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(actors, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
