package com.crunch.crunch_server.domain.user.respository;

import java.util.List;

import com.crunch.crunch_server.domain.user.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByUserId(int userId);

}
