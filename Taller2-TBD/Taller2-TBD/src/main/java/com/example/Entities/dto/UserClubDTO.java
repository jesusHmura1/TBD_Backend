package com.example.Entities.dto;

import java.util.ArrayList;
import java.util.List;

public class UserClubDTO extends UserDataDTO{
    List<String> clubMentions = new ArrayList<>();

    public List<String> getClubMentions() {
        return clubMentions;
    }

    public void setClubMentions(List<String> clubMentions) {
        this.clubMentions = clubMentions;
    }
}
