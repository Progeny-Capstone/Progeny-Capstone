package com.progeny.model;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

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

    @Column(nullable = false, name = "user_id")
    private long userId;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, name = "group_id")
    private long groupId;



    // ---------- CONSTRUCTOR METHOD(S) -----------

    // ----- MEMORY SPACE CONSTRUCTOR ----
    public Recording() {}


    // ----- COPY CONSTRUCTOR ----
    public Recording(Recording copy){
        id = copy.id;
        recordingUrl = copy.recordingUrl;
        timestamp = copy.timestamp;
        userId = copy.userId;
        title = copy.title;
        groupId = copy.groupId;
    }
    //----------------------------


    // ----- WITH ID CONSTRUCTOR ----
    public Recording(long id, String recordingUrl, Timestamp timestamp, long userId, String title, long groupId) {
        this.id = id;
        this.recordingUrl = recordingUrl;
        this.timestamp = timestamp;
        this.userId = userId;
        this.title = title;
        this.groupId = groupId;
    }


    // ----- WITHOUT ID CONSTRUCTOR ----
    public Recording(String recordingUrl, Timestamp timestamp, long userId, String title, long groupId) {
        this.recordingUrl = recordingUrl;
        this.timestamp = timestamp;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
}