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

@RestController
@RequestMapping("/api/v2")
public class ActorController {

    @Autowired
    ActorService actorService;

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

    @DeleteMapping("/actors/{actor_id}")
    public ResponseEntity<String> deleteActorById(@PathVariable("actor_id") int actor_id) {
        try {
            if (actorService.deleteActorById(actor_id)) {
                return new ResponseEntity<>("Cannot find Actor with id = " + actor_id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Actor deleted succesfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Actor wasn't deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
