package com.progeny.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


// NOTE:
// The composite primary key class must be public,
// contains a no-argument constructor,
// defines both equals() and hashCode() methods,
// and implements the Serializable interface.

@Embeddable
public class FriendshipKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "friend_id")
    private Long friendId;


    public FriendshipKey() {
    }

    public FriendshipKey(Long userId, Long friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipKey friendshipKey = (FriendshipKey) o;
        return friendId.equals(friendshipKey.friendId) &&
                userId.equals(friendshipKey.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, friendId);
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }
}
