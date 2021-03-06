package com.progeny.repositories;

import com.progeny.model.Friendship;
import com.progeny.model.FriendshipKey;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface FriendshipRepository extends CrudRepository<Friendship, FriendshipKey> { // < What we are dealing with, How it will be identified >

    List<Friendship> findFriendshipsByUserId(long userId);
    List<Friendship> findFriendshipsByUserIdOrFriendId(long userId, long friendId);
    List<Friendship> findFriendshipsByFriendId(long friendId);
    Friendship findFriendshipByUserIdAndFriendId(long userId, long friendId);
    long deleteByUserIdAndFriendId(long userId, long friendId);

}
