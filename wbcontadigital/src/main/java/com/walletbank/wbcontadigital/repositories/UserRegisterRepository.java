package com.walletbank.wbcontadigital.repositories;

import com.walletbank.wbcontadigital.domain.UserRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisterRepository extends JpaRepository<UserRegister, String> {

}
