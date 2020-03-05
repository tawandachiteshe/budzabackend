package com.dickensdev.budzabackend.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class NewsFeed {
    private UUID storyId;
    private String heading;
    private String body;
    private String category;
    public NewsFeed(){

    }

    public NewsFeed(@JsonProperty("story_id") UUID storyId, @JsonProperty("heading") String heading, @JsonProperty("body") String body, @JsonProperty("category") String category) {
        this.storyId = storyId;
        this.heading = heading;
        this.body = body;
        this.category = category;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public UUID getStoryId() {
        return storyId;
    }

    public void setStoryId(UUID storyId) {
        this.storyId = storyId;
    }
}