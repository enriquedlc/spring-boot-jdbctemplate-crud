package com.accesodatos.springbootjdbctemplate.models;

/**
 * The actor class is a model class that represents
 * the actor table in the database.
 * 
 * @author AccesoDatos
 * @version 1.0
 */

public class Actor {

    /**
     * The actor_id is the primary key of the actor table.
     * It is an auto-incremented value.
     * 
     * The first_name is the first name of the actor.
     * The last_name is the last name of the actor.
     * The last_update is the last time the actor was updated.
     */

    private int actor_id;
    private String first_name;
    private String last_name;
    private String last_update;

    /**
     * Constructs a new `Actor` object with specified values.
     */

    public Actor() {
    }

    /**
     * Constructs a new `Actor` object with the specified values.
     *
     * @param actor_id    The unique identifier of the actor.
     * @param first_name  The first name of the actor.
     * @param last_name   The last name of the actor.
     * @param last_update The date and time when the actor's information was last
     *                    updated.
     */

    public Actor(int actor_id, String first_name, String last_name, String last_update) {
        this.actor_id = actor_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.last_update = last_update;
    }

    /**
     * Constructs a new `Actor` object with the specified first and last name.
     *
     * @param first_name The first name of the actor.
     * @param last_name  The last name of the actor.
     */

    public Actor(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    /**
     * Constructs a new `Actor` object with the specified unique identifier, first,
     * and last name.
     *
     * @param actor_id   The unique identifier of the actor.
     * @param first_name The first name of the actor.
     * @param last_name  The last name of the actor.
     */

    public Actor(int actor_id, String first_name, String last_name) {
        this.actor_id = actor_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    /**
     * Returns the unique identifier of the actor.
     *
     * @return The unique identifier of the actor.
     */

    public int getActor_id() {
        return actor_id;
    }

    /**
     * Sets the unique identifier of the actor.
     *
     * @param actor_id The unique identifier of the actor.
     */

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    /**
     * Returns the first name of the actor.
     *
     * @return The first name of the actor.
     */

    public String getFirst_name() {
        return first_name;
    }

    /**
     * Sets the first name of the actor.
     *
     * @param first_name The first name of the actor.
     */

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Returns the last name of the actor.
     *
     * @return The last name of the actor.
     */

    public String getLast_name() {
        return last_name;
    }

    /**
     * Sets the last name of the actor.
     *
     * @param last_name The last name of the actor.
     */

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Returns the date and time when the actor's information was last updated.
     *
     * @return The date and time when the actor's information was last updated.
     */

    public String getLast_update() {
        return last_update;
    }

    /**
     * Sets the date and time when the actor's information was last updated.
     *
     * @param last_update The date and time when the actor's information was last
     *                    updated.
     */

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    /**
     * Returns a string representation of the actor.
     *
     * @return A string representation of the actor.
     */

    @Override
    public String toString() {
        return "Actor [ actor_id=" + actor_id + ", first_name=" + first_name + ", last_name=" + last_name
                + ", last_update=" + last_update + " ]";
    }
}
