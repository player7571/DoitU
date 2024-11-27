package com.example.DoitU.repository;

import com.example.DoitU.entity.Daily;
import com.example.DoitU.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalenderRepository extends JpaRepository<Daily, String> {

    Optional<Daily> findByDayAndUser(String day, User user);

}
