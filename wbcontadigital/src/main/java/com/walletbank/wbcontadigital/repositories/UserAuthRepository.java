package com.walletbank.wbcontadigital.repositories;

import com.walletbank.wbcontadigital.domain.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserAuthRepository extends JpaRepository<UserAuth, String> {
   UserDetails findByLogin(String login);
}
