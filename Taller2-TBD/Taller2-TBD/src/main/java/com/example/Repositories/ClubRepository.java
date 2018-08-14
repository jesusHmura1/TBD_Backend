package com.example.Repositories;

import com.example.Entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club,Long> {

    Club findClubById(Long id);

    @Query("SELECT DISTINCT c.name FROM Club c" +
            " INNER JOIN c.messageClubRelationships cr" +
            " INNER JOIN cr.message m" +
            " INNER JOIN m.user u" +
            " WHERE u.name = :name"
    )
    List<String> getMentionedClubFromUserName(@Param("name")String name);
}
