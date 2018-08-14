package com.example.Repositories;

import com.example.Entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserById(Long id);
    @Query("SELECT u FROM User u WHERE u.followers > :followers")
    List<User> findUserWithMoreFollowers(@Param("followers")Integer followers);
    @Query("SELECT u FROM User u WHERE u.name like :name")
    User findUserByName(@Param("name")String name);
    @Query("SELECT u FROM User u")
    List<User> findTopUserFollowers();

    // esta sera utilizada mas adelante
    User findUserByUsername(String username);
}
