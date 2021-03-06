package com.progeny.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Friendship implements Serializable {

    // --------------------------------------------
    // ----------------JOIN TABLE------------------
    // --------------------------------------------

    // ----------  TABLE SETUP -----------
    @EmbeddedId // mark the primary key, which is an instance of the FriendshipKey class
    FriendshipKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("friend_id")
    @JoinColumn(name = "friend_id")
    User friend;

    private boolean isAccepted;


    // ---------- CONSTRUCTOR METHOD(S) -----------
    // ----- MEMORY SPACE CONSTRUCTOR ----
    public Friendship() { }

    // ----- WITH FRIENDSHIPKEY ID CONSTRUCTOR ----
    public Friendship(FriendshipKey id, User user, User friend, boolean isAccepted) {
        this.id = id;
        this.user = user;
        this.friend = friend;
        this.isAccepted = isAccepted;
    }


    // ---------- GET AND SET METHODS -----------
    public FriendshipKey getId() {
        return id;
    }

    public void setId(FriendshipKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
