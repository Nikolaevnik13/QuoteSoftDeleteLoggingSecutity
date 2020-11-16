package com.quote.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quote.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

}
