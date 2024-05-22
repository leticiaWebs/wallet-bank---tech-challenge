package com.walletbank.wbcontadigital.repositories;

import com.walletbank.wbcontadigital.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

}
