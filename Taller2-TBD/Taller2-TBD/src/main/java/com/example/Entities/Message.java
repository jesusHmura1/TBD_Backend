package com.example.Entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", unique = true)
    private Long id;

    @Column(name= "text" ,nullable = false)
    private String text;

    @Column(name= "retweets")
    private Integer retweets;

    @OneToMany(mappedBy = "message",fetch = FetchType.LAZY)
    private Set<MessageClubRelationship> messageClubRelationships;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name ="id_user")
    private User user;

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<MessageClubRelationship> getMessageClubRelationships() {
        return messageClubRelationships;
    }

    public void setMessageClubRelationships(Set<MessageClubRelationship> messageClubRelationships) {
        this.messageClubRelationships = messageClubRelationships;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRetweets() {
        return retweets;
    }

    public void setRetweets(Integer retweets) {
        this.retweets = retweets;
    }
}
