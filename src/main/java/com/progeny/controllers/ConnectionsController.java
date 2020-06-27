package com.progeny.controllers;

import com.progeny.model.Friendship;
import com.progeny.model.FriendshipKey;
import com.progeny.model.User;
import com.progeny.repositories.FriendshipRepository;
import com.progeny.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ConnectionsController {

    // --------- INITIALIZE ------------
    private UserRepository usersRepo;
    private FriendshipRepository friendshipRepo;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public ConnectionsController(UserRepository usersRepo, FriendshipRepository friendshipRepo) {
        this.usersRepo = usersRepo;
        this.friendshipRepo = friendshipRepo;
    }


    // --------- SHOW FRIENDS (GET) ------------
    @GetMapping("/profile/friends")
    public String showFriends(Model model) {

        // ------------- GET CURRENT USER FROM SESSION -------------
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user
        User currentUser = new User(user); // 2. set new user to user ^
        model.addAttribute("currentUser", currentUser);// 2. Show current user on form

        List<Friendship> friendRequests = friendshipRepo.findFriendshipsByFriendId(currentUser.getId());

        for(Friendship friendRequest: friendRequests){
            if (!friendRequest.isAccepted()){
                List<Friendship> friendRequestList = new ArrayList<>();
                friendRequestList.add(friendRequest);
                model.addAttribute("friendRequestList", friendRequestList);
            }
        }

        // ------------- SHOW USER'S FRIENDS -------------
        model.addAttribute("friendRequests", friendshipRepo.findFriendshipsByFriendId(currentUser.getId())); // List of users where the friend id is equal to the current user's id
        model.addAttribute("friends", friendshipRepo.findFriendshipsByUserIdOrFriendId(currentUser.getId(), currentUser.getId())); // List of all users in the friendship table


        return "users/connections";
    }

    // --------- ADD FRIEND (POST)------------
    @PostMapping("/profile/friends")
    public String addFriend(@RequestParam long friendId, Model model) {

        // ------------- GET CURRENT USER FROM SESSION -------------
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user
        User currentUser = new User(user); // 2. set new user to user ^
        model.addAttribute("currentUser", currentUser);// 3. Show current user on page

        // ------------- GET FRIEND FROM FORM SENT -------------
        User friend = usersRepo.getUserById(friendId);
        model.addAttribute("friend", friend);// 3. Show current user on page

        // --------- FRIENDSHIP KEY ------------
        FriendshipKey friendshipId = new FriendshipKey(currentUser.getId(), friend.getId());

        // --------- GET CURRENT FRIENDSHIPS ------------
        Friendship existingFriendshipId = friendshipRepo.findFriendshipByUserIdAndFriendId(currentUser.getId(), friendId);
        Friendship existingFriendshipIdReverse = friendshipRepo.findFriendshipByUserIdAndFriendId(friendId, currentUser.getId());

        // --------- CHECK FRIENDSHIPS ------------
        if (existingFriendshipId == null && existingFriendshipIdReverse == null) {

            // --------- NEW INSTANCE OF A FRIENDSHIP (FALSE UNTIL ACCEPTED) ------------
            Friendship userToFriend = new Friendship(friendshipId, currentUser, friend, false); // Not friends just yet (pending approval)

            // --------- SAVE TO DB -----------
            friendshipRepo.save(userToFriend); // 3. save the list of users to the current users information

        }


        // --------- DISPLAY INFORMATION VARIABLES -----------
        model.addAttribute("currentUser", currentUser);// 1. Show current user on form
        model.addAttribute("friends", friendshipRepo.findFriendshipsByUserId(currentUser.getId())); // 2. Show a list of users attached to current user
        model.addAttribute("users", usersRepo.findAll()); // 3. Show a list of users on Progeny


        return "users/connections";
    }

    // --------- CONFIRM FRIEND (POST)------------
    @PostMapping("/profile/friends/confirm-friend")
    public String confirmFriendship(@RequestParam long userId, @RequestParam long friendId) {

        // ------------- GET FRIENDSHIP -------------
        Friendship pendingFriendship = friendshipRepo.findFriendshipByUserIdAndFriendId(userId, friendId);

        // ------------- CHANGE FRIENDSHIP STATUS -------------
        pendingFriendship.setAccepted(true);

        // --------- SAVE TO DB -----------
        friendshipRepo.save(pendingFriendship); // 3. save the list of users to the current users information

        return "redirect:/profile/friends";
    }

    // --------- IGNORE FRIEND REQUEST (POST)------------
    @PostMapping("/profile/friends/ignore-friend")
    public String ignoreFriendship(@RequestParam long userId, @RequestParam long friendId) {

        // ------------- REMOVE FRIENDSHIP REQUEST -------------
        friendshipRepo.deleteByUserIdAndFriendId(userId, friendId);

        return "redirect:/profile/friends";
    }

    // --------- DELETE CONNECTION (POST) ------------
    @PostMapping("/profile/friends/delete")
    public String deleteFriendship(@RequestParam long userId, @RequestParam long friendId) {

        // ------------- REMOVE FRIENDSHIP -------------
        friendshipRepo.deleteByUserIdAndFriendId(userId, friendId);

        return "redirect:/profile/friends";
    }

    // --------- SEARCH PROGENY USERS (POST)------------
    @GetMapping("/profile/friends/search")
    public String searchUsers(@RequestParam String search, Model model) {


        // ------------- GET CURRENT USER FROM SESSION -------------
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user
        User currentUser = new User(user); // 2. set new user to user ^


        if (search.equals("all")) {

            // --------- SEARCH DB FOR ALL USERS -----------
            model.addAttribute("foundUsers", usersRepo.findAll()); // 1. Show a list of users on Progeny

        } else {

            // --------- SEARCH DB FOR A USER -----------
            List<User> foundUsers = usersRepo.findAllByFirstNameLikeOrLastNameLike(search, search);
            model.addAttribute("foundUsers", foundUsers);

            // ------------- GET CURRENT FRIENDS -------------
            Friendship currentFriends = friendshipRepo.findFriendshipByUserIdAndFriendId(currentUser.getId(), foundUsers.iterator().next().getId());
            model.addAttribute("foundFriends", currentFriends);

        }

        List<Friendship> friendRequests = friendshipRepo.findFriendshipsByFriendId(currentUser.getId());

        for(Friendship friendRequest: friendRequests){
            if (!friendRequest.isAccepted()){
                List<Friendship> friendRequestList = new ArrayList<>();
                friendRequestList.add(friendRequest);
                model.addAttribute("friendRequestList", friendRequestList);
            }
        }

        // --------- DISPLAY INFORMATION VARIABLES -----------
        model.addAttribute("currentUser", currentUser);// 1. Show current user on form
        model.addAttribute("friends", friendshipRepo.findFriendshipsByUserId(currentUser.getId())); // 2. Show a list of users attached to current user
        model.addAttribute("friendRequests", friendshipRepo.findFriendshipsByFriendId(currentUser.getId())); // List of users where the friend id is equal to the current user's id
        model.addAttribute("users", usersRepo.findAll()); // 3. Show a list of users on Progeny

        return "users/connections";
    }

}