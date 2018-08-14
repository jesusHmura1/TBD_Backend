package com.example.Controllers;

import com.example.Entities.Club;
import com.example.Entities.Message;
import com.example.Entities.MessageClubRelationship;
import com.example.Entities.User;
import com.example.Entities.dto.MessageDTO;
import com.example.Entities.dto.UserClubDTO;
import com.example.Entities.dto.UserMessagesDTO;
import com.example.Repositories.ClubRepository;
import com.example.Repositories.MessageClubRelationshipRepository;
import com.example.Repositories.MessageRepository;
import com.example.Repositories.UserRepository;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageClubRelationshipRepository messageClubRelationshipRepository;

    // retorna todos los club de la base de datos club, se llama con la ruta /club
    @RequestMapping(value = "influentialUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<UserMessagesDTO> getMostInfluentialUsers(@RequestParam(value = "followers", required = true) Integer followers,
                                           @RequestParam(value = "quantity", required = true) Integer quantity) throws IOException {
        return guardartopFollowers(followers,quantity);
    }

    @RequestMapping(value = "topUser", method = RequestMethod.GET)
    @ResponseBody
    public List<UserClubDTO> gettopUsersFront(@RequestParam(value = "cantidad",required = true) Integer cantidad) throws IOException {
        return getTop(cantidad);
    }

    public List<UserClubDTO> getTop(Integer sizeUsers) throws IOException{
       //List<User> top = userRepository.findAll()
        List<User> top = userRepository.findAll();
        List<UserClubDTO> userClub = new ArrayList<>();
        top = top.stream().sorted(Comparator.comparing(User::getFollowers).reversed()).limit(sizeUsers).collect(Collectors.toList());
        top.stream().forEach(x -> {
            UserClubDTO user = new UserClubDTO();
            user.setUser(x.getName());
            user.setUserName(x.getUsername());
            user.setFollowers(x.getFollowers());
            user.setClubMentions(clubRepository.getMentionedClubFromUserName(user.getUser()));
            userClub.add(user);
        });
       return userClub;
    }

    public List<UserMessagesDTO> guardartopFollowers(Integer minimunFollowers, Integer quantity) throws IOException {
        MongoClient mongoClient= new MongoClient("127.0.0.1",27017);
        DB db = mongoClient.getDB("twitter7");
        DBCollection collection = db.getCollection("twitter");
        DBCursor cursor = collection.find();
        Integer counter = 0;
        List<UserMessagesDTO> userMessages = new ArrayList<>();
        while (cursor.hasNext()) {
            DBObject tweet = cursor.next();
            BasicDBObject tw = (BasicDBObject) tweet;
            try {
                Integer followers = tw.getInt("followers");
                if (followers > minimunFollowers && counter < quantity) {
                    User loadedUser = userRepository.findUserByName(tw.getString("name").replaceAll("[^A-Za-z]",""));
                    if (loadedUser == null || loadedUser.getId()==null) {
                        User newUser = getUserFromTweetObject(tw);
                        userRepository.save(newUser);
                        UserMessagesDTO userMessage = new UserMessagesDTO();
                        userMessage.setFollowers(newUser.getFollowers());
                        userMessage.setUser(newUser.getName());
                        userMessage.setUserName(newUser.getUsername()); // ** :$
                        userMessage.setMessages(getMessageFromUser(newUser));
                        userMessages.add(userMessage);
                        System.out.println("Creando usuario " + newUser.getName() + " con " + newUser.getFollowers() + " followers");
                        counter++;
                    } else {
                        System.out.println(
                                "El usuario " + tw.getString("name") + " no ha sido guardado"
                        );
                    }
                }
            }catch(Exception e){
                System.out.println("Usuario que da problema con id "+tw.getString("id") + " Excepcion: "+e);
                return Collections.emptyList();
            }

        }
        System.out.println("Encontrados "+counter+" usuarios influyentes");

        return userMessages;
    }

    private  List<MessageDTO> getMessageFromUser(User user){
        MongoClient mongoClient= new MongoClient("127.0.0.1",27017);
        DB db = mongoClient.getDB("twitter7");
        DBCollection collection = db.getCollection("twitter");
        DBCursor cursor = collection.find();
        List<Club> loadedClubs = clubRepository.findAll();
        List<MessageDTO> messages = new ArrayList<>();
        while (cursor.hasNext()) {
            DBObject tweet = cursor.next();
            BasicDBObject tw = (BasicDBObject) tweet;
            if(tw.getString("name").replaceAll("[^A-Za-z]","").toUpperCase().equalsIgnoreCase(user.getName().toUpperCase())){
                Message message = new Message();
                String text = tw.getString("text").replaceAll("[^A-Za-z]"," ");
                message.setUser(user);
                message.setText(text);
                message.setRetweets(tw.getInt("retweet"));
                messageRepository.save(message);
                List<String> clubMentions = new ArrayList<>();
                loadedClubs.stream().forEach(club ->{
                    if(text.toUpperCase().contains(club.getName().toUpperCase())){
                        MessageClubRelationship rel = new MessageClubRelationship();
                        rel.setClub(club);
                        rel.setMessage(message);
                        messageClubRelationshipRepository.save(rel);
                        clubMentions.add(club.getName());
                    }
                });
                messages.add(messageToDTO(message,clubMentions));
            }
        }
        return messages;
    }

    private MessageDTO messageToDTO(Message message, List<String> clubMentions){
        MessageDTO msgDTO = new MessageDTO();
        msgDTO.setMessage(message.getText());
        msgDTO.setRetweets(message.getRetweets());
        msgDTO.setClubMentions(clubMentions);
        return msgDTO;
    }


    private User getUserFromTweetObject(BasicDBObject tweet){
        User user = new User();
        user.setFollowers(tweet.getInt("followers"));
        user.setName(tweet.getString("name").replaceAll("[^A-Za-z]",""));
        System.out.println("Creando objeto de usuario "+user.getName());
        String username = tweet.getString("screenName");
        user.setUsername(username!=null?username:null);
        return user;
    }
}
