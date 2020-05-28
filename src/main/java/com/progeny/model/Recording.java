package com.progeny.model;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recordings")
public class Recording {
    // --------------------------------------------
    // -------------------BEAN---------------------
    // --------------------------------------------

    // ---------- TABLE SETUP -----------
    // 1. Create a primary key for the progeny_db table of recordings
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "recording")
    private String recordingUrl;

    @Column(nullable = false)
    private Timestamp timestamp;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, name = "group_id")
    private long groupId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="story_recordings",
            joinColumns={@JoinColumn(name="recordings_id")},
            inverseJoinColumns={@JoinColumn(name="stories_id")}
    )
    private List<Recording> recordingList;



    // ---------- CONSTRUCTOR METHOD(S) -----------

    // ----- MEMORY SPACE CONSTRUCTOR ----
    public Recording() {}


    // ----- COPY CONSTRUCTOR ----
    public Recording(Recording copy){
        id = copy.id;
        recordingUrl = copy.recordingUrl;
        timestamp = copy.timestamp;
        title = copy.title;
        groupId = copy.groupId;
    }
    //----------------------------


    // ----- WITH ID CONSTRUCTOR ----
    public Recording(long id, String recordingUrl, Timestamp timestamp, String title, User user, long groupId) {
        this.id = id;
        this.recordingUrl = recordingUrl;
        this.timestamp = timestamp;
        this.title = title;
        this.user =user;
        this.groupId = groupId;
    }


    // ----- WITHOUT ID CONSTRUCTOR ----
    public Recording(String recordingUrl, Timestamp timestamp, String title, User user, long groupId) {
        this.recordingUrl = recordingUrl;
        this.timestamp = timestamp;
        this.title = title;
        this.user = user;
        this.groupId = groupId;
    }

    // ----- WITHOUT ID & USER CONSTRUCTOR ----
    public Recording(String recordingUrl, Timestamp timestamp, String title, long groupId) {
        this.recordingUrl = recordingUrl;
        this.timestamp = timestamp;
        this.title = title;
        this.groupId = groupId;
    }

    // ---------- GET AND SET METHODS -----------


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecordingUrl() {
        return recordingUrl;
    }

    public void setRecordingUrl(String recordingUrl) {
        this.recordingUrl = recordingUrl;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
