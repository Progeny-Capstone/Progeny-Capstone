package com.progeny.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FriendshipKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "friend_id")
    Long friendId;


    public FriendshipKey() {}

    public FriendshipKey(Long userId, Long friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendshipKey)) return false;
        FriendshipKey that = (FriendshipKey) o;
        return userId.equals(that.userId) &&
                friendId.equals(that.friendId);
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
