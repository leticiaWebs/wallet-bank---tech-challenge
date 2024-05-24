package com.walletbank.wbcontadigital.services;

import com.walletbank.wbcontadigital.domain.AccountDTO;
import com.walletbank.wbcontadigital.domain.Account;
import com.walletbank.wbcontadigital.repositories.AccountRepository;
import com.walletbank.wbcontadigital.services.exceptions.DatabaseException;
import com.walletbank.wbcontadigital.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    @Transactional
    public List<AccountDTO> findAll(){
        List<Account> list = repository.findAll();
       return list.stream().map(AccountDTO::new).collect(Collectors.toList());

    }

    @Transactional
    public AccountDTO findById(String id) {
     Optional<Account> obj = repository.findById(id);
     Account entity = obj.orElseThrow(() ->new ResourceNotFoundException("Entity not found"));
     return new AccountDTO(entity);
    }

    @Transactional
    public AccountDTO insert(AccountDTO dto) {
        Account entity = new Account();
        entity.setId(dto.getId());
        entity.setNumeroConta(dto.getNumeroConta());
        entity.setAgencia(dto.getAgencia());
        entity.setTipoConta(dto.getTipoConta());
        repository.save(entity);
        return new AccountDTO(entity);
    }

    @Transactional
    public AccountDTO update(String numeroConta, AccountDTO dto) {
            try {
                Account entity = repository.getReferenceById(numeroConta);
               // entity.setNumeroConta(dto.getNumeroConta());
                entity.setAgencia(dto.getAgencia());
                entity.setTipoConta(dto.getTipoConta());
               entity = repository.save(entity);
                return new AccountDTO(entity);
            }
            catch(EntityNotFoundException e){
                throw new ResourceNotFoundException("Account number not found" + numeroConta);
            }
    }

    public void delete(String id) {
        try {
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Usuario não encontrado");
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
            }
        }

}


