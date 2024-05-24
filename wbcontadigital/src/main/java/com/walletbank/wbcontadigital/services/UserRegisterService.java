package com.walletbank.wbcontadigital.services;

import com.walletbank.wbcontadigital.domain.UserRegisterDTO;
import com.walletbank.wbcontadigital.domain.UserRegister;
import com.walletbank.wbcontadigital.repositories.UserRegisterRepository;
import com.walletbank.wbcontadigital.services.exceptions.DatabaseException;
import com.walletbank.wbcontadigital.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRegisterService {

    @Autowired
    private UserRegisterRepository repository;

  @Transactional
    public Page<UserRegisterDTO> findAllPaged(PageRequest pageRequest) {
    Page<UserRegister> list = repository.findAll(pageRequest);
       return list.map(x -> new UserRegisterDTO(x));

    }
    @Transactional
    public UserRegisterDTO findById(String id) {
      Optional<UserRegister> obj = repository.findById(id);
      UserRegister entity = obj.orElseThrow(() ->new ResourceNotFoundException("Entity not found"));
      return new UserRegisterDTO(entity);
    }

    @Transactional
    public UserRegisterDTO insert(UserRegisterDTO dto) {
      UserRegister entity = new UserRegister();
      entity.setId(dto.getId());
      entity.setNome(dto.getNome());
      entity.setTelefone(dto.getTelefone());
      entity.setEmail(dto.getEmail());
      repository.save(entity);
      return new UserRegisterDTO(entity);
    }

  @Transactional
    public UserRegisterDTO update(String id, UserRegisterDTO dto) {
    try {
      UserRegister entity = repository.getReferenceById(id);
      entity.setId(dto.getId());
      entity.setNome(dto.getNome());
      entity.setTelefone(dto.getTelefone());
      entity.setEmail(dto.getEmail());
      entity = repository.save(entity);
      return new UserRegisterDTO(entity);
    }
    catch(EntityNotFoundException e){
      throw new ResourceNotFoundException("Id not found" + id);
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
