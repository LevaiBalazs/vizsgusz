package com.exam.RestAPI.Repo;

import com.exam.RestAPI.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <User, Long> {
}
