package com.walletbank.wbcontadigital.repositories;

import com.walletbank.wbcontadigital.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
