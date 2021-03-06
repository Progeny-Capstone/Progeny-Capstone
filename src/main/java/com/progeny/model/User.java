package com.progeny.model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    // --------------------------------------------
    // -------------------BEAN---------------------
    // --------------------------------------------

    // ---------- TABLE SETUP -----------

    // 1. Create a primary key for the progeny_db table of users
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 2. A username that is unique and is required with a character limit of 50.
    @Column(nullable = false, length = 50, unique = true)
    private String username;

    // 3. A user first name the is required with a character limit of 45
    @Column(nullable = false, length = 45)
    private String firstName;

    // 4. A user last name the is required with a character limit of 45
    @Column(nullable = false, length = 45)
    private String lastName;

    // 5. An email that is required and unique with a character limit of 60 (MAYBE HAVE NO CHARACTER LIMIT HERE?)
    @Column(nullable = false, length = 60, unique = true)
    private String email;

    // 6. A password column that is required with a character limit of 60.
    @Column(nullable = false, length = 60)
    private String password;

    // 7. A user profile picture url (FILE STACK)
    @Column
    private String profileImageUrl;

    // 8. A user location (STRETCH: USE LOCATION DATA TYPE)
    @Column
    private String location;

    // 9. A user bio with a character length of 120 characters
    @Column(length = 120)
    private String bio;

    // 10. Determine if the user has admin privileges (true or false, 0 or 1)
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isAdmin;

    // 11. A list of recordings mapped by the user
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Recording> recordings;

    // --------------- FRIENDS ----------------

    //    We configured the relationships to the Student and Course classes as @ManyToOne.
    //    We could do this because with the new entity we structurally decomposed the many-to-many relationship to two many-to-one relationships.
    @OneToMany(mappedBy = "friend")
    List<Friendship> friendAcceptedList;

    @OneToMany(mappedBy = "user")
    List<Friendship> userAcceptedList;


    // --------------- GROUPS ----------------
    // 13. A user can have many groups and a group can have many users
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_groups",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")}
    )
    private List<Group> groupList;


    // ---------- CONSTRUCTOR METHOD(S) -----------
    // ----- MEMORY SPACE CONSTRUCTOR ----
    public User() {
    }

    // ----- COPY CONSTRUCTOR ----
    public User(User copy) { //  It is used as an alternative to cloning an object
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        username = copy.username;
        firstName = copy.firstName;
        lastName = copy.lastName;
        email = copy.email;
        password = copy.password;
        profileImageUrl = copy.profileImageUrl;
        location = copy.location;
        bio = copy.bio;
        isAdmin = copy.isAdmin;
    }
    //----------------------------

    // ----- WITH ID CONSTRUCTOR ----
    public User(long id, String username, String firstName, String lastName, String email, String password, String profileImageUrl, String location, String bio, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.location = location;
        this.bio = bio;
        this.isAdmin = isAdmin;
    }

    // ----- WITHOUT ID CONSTRUCTOR ----
    public User(String username, String firstName, String lastName, String email, String password, String profileImageUrl, String location, String bio, boolean isAdmin) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.location = location;
        this.bio = bio;
        this.isAdmin = isAdmin;
    }

    // ----- THE WHOLE SHEBANG ----
    public User(long id, String username, String firstName, String lastName, String email, String password, String profileImageUrl, String location, String bio, boolean isAdmin, List<Recording> recordings, List<Friendship> friendAcceptedList, List<Friendship> userAcceptedList, List<Group> groupList) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.location = location;
        this.bio = bio;
        this.isAdmin = isAdmin;
        this.recordings = recordings;
        this.friendAcceptedList = friendAcceptedList;
        this.userAcceptedList = userAcceptedList;
        this.groupList = groupList;
    }


    // ----- THE WHOLE SHEBANG  WITHOUT !D----
    public User(String username, String firstName, String lastName, String email, String password, String profileImageUrl, String location, String bio, boolean isAdmin, List<Recording> recordings, List<Friendship> friendAcceptedList, List<Friendship> userAcceptedList, List<Group> groupList) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.location = location;
        this.bio = bio;
        this.isAdmin = isAdmin;
        this.recordings = recordings;
        this.friendAcceptedList = friendAcceptedList;
        this.userAcceptedList = userAcceptedList;
        this.groupList = groupList;
    }


    // ---------- GET AND SET METHODS -----------
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Recording> getRecordings() {
        return recordings;
    }

    public void setRecordings(List<Recording> recordings) {
        this.recordings = recordings;
    }

    public List<Friendship> getFriendAcceptedList() {
        return friendAcceptedList;
    }

    public void setFriendAcceptedList(List<Friendship> friendAcceptedList) {
        this.friendAcceptedList = friendAcceptedList;
    }

    public List<Friendship> getUserAcceptedList() {
        return userAcceptedList;
    }

    public void setUserAcceptedList(List<Friendship> userAcceptedList) {
        this.userAcceptedList = userAcceptedList;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }
}