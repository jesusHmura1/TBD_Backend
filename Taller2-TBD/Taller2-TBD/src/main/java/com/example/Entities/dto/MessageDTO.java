package com.example.Entities.dto;

import java.util.ArrayList;
import java.util.List;

public class MessageDTO {
    private String message;
    private Integer retweets;
    private List<String> clubMentions = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRetweets() {
        return retweets;
    }

    public void setRetweets(Integer retweets) {
        this.retweets = retweets;
    }

    public List<String> getClubMentions() {
        return clubMentions;
    }

    public void setClubMentions(List<String> clubMentions) {
        this.clubMentions = clubMentions;
    }
}
