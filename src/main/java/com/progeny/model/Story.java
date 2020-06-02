package com.progeny.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "stories")
public class Story {
    // --------------------------------------------
    // -------------------BEAN---------------------
    // --------------------------------------------

    // ---------- TABLE SETUP -----------


    // 1. Create a primary key for the progeny_db table of recordings
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 45, name = "author_first_name")
    private String authorFirstName;

    @Column(nullable = false, length = 45, name = "author_last_name")
    private String authorLastName;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, name = "story_file")
    private String storyFile;

    @Column(nullable = false, columnDefinition = "text")
    private String summary;

    @Column(nullable = false, name = "published_year")
    private int publishedYear;

    @Column(nullable = false, length = 45)
    private String publisher;

    @Column(name = "cover_image", columnDefinition = "text")
    private String coverImageUrl;

    @ManyToMany(mappedBy = "recordingList")
    private List<Recording> recordings;

    // ---------- CONSTRUCTOR METHOD(S) -----------

    // ----- MEMORY SPACE CONSTRUCTOR ----
    public Story(){}


    // ----- COPY CONSTRUCTOR ----
    public Story(Story copy){
        id = copy.id;
        authorFirstName = copy.authorFirstName;
        authorLastName = copy.authorLastName;
        title = copy.title;
        storyFile = copy.storyFile;
        summary = copy.summary;
        publishedYear = copy.publishedYear;
        publisher = copy.publisher;
        coverImageUrl = copy.coverImageUrl;
    }


    // ----- WITH ID CONSTRUCTOR ----
    public Story(long id, String authorFirstName, String authorLastName, String title, String storyFile, String summary, int publishedYear, String publisher, String coverImageUrl) {
        this.id = id;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.title = title;
        this.storyFile = storyFile;
        this.summary = summary;
        this.publishedYear = publishedYear;
        this.publisher = publisher;
        this.coverImageUrl = coverImageUrl;
    }


    // ----- WITHOUT ID CONSTRUCTOR ----
    public Story(String authorFirstName, String authorLastName, String title, String storyFile, String summary, int publishedYear, String publisher, String coverImageUrl) {
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.title = title;
        this.storyFile = storyFile;
        this.summary = summary;
        this.publishedYear = publishedYear;
        this.publisher = publisher;
        this.coverImageUrl = coverImageUrl;
    }

    // ---------- GET AND SET METHODS -----------


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStoryFile() {
        return storyFile;
    }

    public void setStoryFile(String storyFile) {
        this.storyFile = storyFile;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}
