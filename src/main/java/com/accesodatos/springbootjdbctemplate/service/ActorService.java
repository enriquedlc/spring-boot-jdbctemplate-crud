package com.accesodatos.springbootjdbctemplate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accesodatos.springbootjdbctemplate.models.Actor;
import com.accesodatos.springbootjdbctemplate.repository.ActorRepository;

@Service
public class ActorService {

    @Autowired
    ActorRepository actorRepository;

    public List<Actor> findAllActors() {
        return actorRepository.findAll();
    }

    public int createActor(Actor actor) {
        return actorRepository.save(
                new Actor(
                        actor.getFirst_name(),
                        actor.getLast_name()));
    }

    public Actor findActorById(int actor_id) {
        return actorRepository.findById(actor_id);
    }

    public Boolean deleteActorById(int actor_id) {
        return actorRepository.deleteById(actor_id) == 0;
    }

    public Boolean updateActor(Actor actor) {
        return actorRepository.update(actor) == 0;
    }

    public List<Actor> findActorByFirstName(String first_name) {
        return actorRepository.findActorByFirstName(first_name);
    }
}
