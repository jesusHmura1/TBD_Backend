package com.example.Repositories;

import com.example.Entities.MessageClubRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageClubRelationshipRepository extends JpaRepository<MessageClubRelationship,Long> {

    MessageClubRelationship findUserClubRelationshipById(Long id);

}
