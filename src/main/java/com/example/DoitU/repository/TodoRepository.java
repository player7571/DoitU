package com.example.DoitU.repository;

import com.example.DoitU.entity.Todo;
import com.example.DoitU.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUserOrderByCreatedTimeDesc(User user);
    List<Todo> findByUserAndDoneOrderByCreatedTimeDesc(User user, Boolean done);

}
