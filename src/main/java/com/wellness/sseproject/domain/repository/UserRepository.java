package com.wellness.sseproject.domain.repository;

import com.wellness.sseproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUserId(String userId);
    int countByUserId(String userId);

    @Query(value = "SELECT `user_password` FROM USER_TB WHERE `user_id` = (:userId)",nativeQuery = true)
    String findPasswordByUserId(@Param("userId") String userId);

    List<User> findAll();

    @Query(value = "SELECT * FROM USER_TB LIMIT :startCount, :pageCount", nativeQuery = true)
    List<User> getUsersByPage(@Param("startCount") int startCount, @Param("pageCount") int pageCount);
}
