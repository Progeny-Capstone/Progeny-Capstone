package com.progeny.controllers;

import com.progeny.model.User;
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
    private PasswordEncoder passwordEncoder;


    // ------------ CONSTRUCTOR METHOD ---------------
    // --------- AKA DEPENDENCY INJECTION ------------
    public ConnectionsController(UserRepository usersRepo, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
    }


    // --------- SHOW (GET) ------------
    @GetMapping("/profile/friends")
    public String showFriends(Model model) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user
        model.addAttribute("currentUser", currentUser);// 2. Show current user on form

        model.addAttribute("friends", currentUser.getFriends()); // 2. Show a list of users attached to current user

        return "users/connections";
    }

    // --------- ADD FRIEND (POST)------------
    @PostMapping("/profile/friends")
    public String addFriend(@RequestParam long friendId, Model model) {

        // 1. User selects friend to add
            // a. Grab the current user
            // b. Grab the friend
            // c. Set the pending friendship list
        // 2. Friend handles request
            // a. Confirm
                // 1. Add users
            // b. Deny
                // 1. Delete request

        // ------------- GET CURRENT USER FROM SESSION -------------
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user
        User currentUser = new User(user); // 2. set new user to user ^
        model.addAttribute("currentUser", currentUser);// 3. Show current user on page

        // ------------- GET FRIEND FROM FORM SENT -------------
        User friend = usersRepo.getUserById(friendId);
        model.addAttribute("friend", friend);// 3. Show current user on page

        // ------------- CHECK FOR FRIEND LIST(s) -------------
        if (friend.getPendingFriends() == null) { // if there is no friends list -->

            List<User> newFriends = new ArrayList<>(); // 1. Make a new friends list
            friend.setPendingFriends(newFriends); // 2. give the new friends list to User

        }
        if (currentUser.getPendingFriends() == null) { // if there is no friends list -->

            List<User> newFriends = new ArrayList<>(); // 1. Make a new friends list
            currentUser.setPendingFriends(newFriends); // 2. give the new friends list to User

        }

        // --------- ADD USERS TO EACH OTHERS PENDING FRIENDS LIST------------
        friend.getPendingFriends().add(currentUser);
        friend.setPendingFriends(friend.getPendingFriends());

        currentUser.getPendingFriends().add(friend);
        currentUser.setPendingFriends(currentUser.getPendingFriends());

        // --------- SAVE TO DB -----------
        usersRepo.save(currentUser); // 3. save the list of users to the current users information
        usersRepo.save(friend); // 3. save the list of users to the current users information

        // --------- REDISPLAY THE GET FRIEND INFORMATION AFTER POST -----------
        model.addAttribute("currentUser", currentUser);// 2. Show current user on form
        model.addAttribute("friends", currentUser.getFriends()); // 2. Show a list of users attached to current user
        model.addAttribute("users", usersRepo.findAll()); // 1. Show a list of users on Progeny


        return "users/connections";
    }


    // --------- DELETE CONNECTION (POST) ------------
    @PostMapping("/profile/friends/delete")
    public String postDelete(@RequestParam long deleteId, Model model) {

        // ------------- GET CURRENT USER FROM SESSION -------------
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user
        User currentUser = new User(user); // 2. set new user to user ^
        model.addAttribute("currentUser", currentUser);// 3. Show current user on page

        // 1. Find the user in the user Repo by the id being passed in
        User deleteUser = usersRepo.getOne(deleteId);

        // 2. Delete the connection to the user in the friends joining table
        List<User> friendsList = deleteUser.getFriends(); // 1. get list of friends
        for (int i = 0; i < friendsList.size(); i++) { // 2. iterate through friends list

            if (friendsList.get(i).getId() == currentUser.getId()) {

                deleteUser.getFriends().remove(i);

            }
        }
        deleteUser.setFriends(deleteUser.getFriends());


        List<User> userFriendList = currentUser.getFriends(); // 1. get list of friends
        for (int i = 0; i < userFriendList.size(); i++) { // 2. iterate through friends list

            if (userFriendList.get(i).getId() == deleteUser.getId()) {

                currentUser.getFriends().remove(i);

            }
        }
        currentUser.setFriends(currentUser.getFriends());

        // 3. Save the changes
        usersRepo.save(currentUser); // 3. save the removal of friend to the current users information
        usersRepo.save(deleteUser); // 3. save the removal of friend to the current users information

        return "redirect:/profile/friends";
    }


    // --------- SEARCH FOR FRIEND (GET)------------
    @GetMapping("/profile/friends/search")
    public String showUsers(Model model) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user
        model.addAttribute("currentUser", currentUser);// 2. Show current user on form

        model.addAttribute("friends", currentUser.getFriends()); // 2. Show a list of users attached to current user

        return "users/connections";
    }

    // --------- SEARCH FOR FRIEND (POST)------------
    @PostMapping("/profile/friends/search")
    public String searchUsers(@RequestParam String search, Model model) {

        // ------------- GET CURRENT USER FROM SESSION -------------
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user
        User currentUser = new User(user); // 2. set new user to user ^
        model.addAttribute("currentUser", currentUser);// 3. Show current user on page
        model.addAttribute("friends", currentUser.getFriends()); // 4. Show a list of users attached to current user

        if (search.equals("all")) {

            // --------- SEARCH DB FOR ALL USERS -----------
            model.addAttribute("foundUsers", usersRepo.findAll()); // 1. Show a list of users on Progeny

        } else {

            // --------- SEARCH DB FOR A USER -----------
            List<User> foundUsers = usersRepo.findAllByFirstNameLikeOrLastNameLike(search, search);
            model.addAttribute("foundUsers", foundUsers);

        }


        return "users/connections";
    }

}