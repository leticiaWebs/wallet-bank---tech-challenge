package com.walletbank.wbcontadigital.services;

import com.walletbank.wbcontadigital.dto.UserDTO;
import com.walletbank.wbcontadigital.entities.User;
import com.walletbank.wbcontadigital.repositories.UserRepository;
import com.walletbank.wbcontadigital.services.exceptions.DatabaseException;
import com.walletbank.wbcontadigital.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

  @Transactional
    public Page<UserDTO> findAllPaged(PageRequest pageRequest) {
    Page<User> list = repository.findAll(pageRequest);
       return list.map(x -> new UserDTO(x));

    }
    @Transactional
    public UserDTO findById(String id) {
      Optional<User> obj = repository.findById(id);
      User entity = obj.orElseThrow(() ->new ResourceNotFoundException("Entity not found"));
      return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
      User entity = new User();
      entity.setId(dto.getId());
      entity.setNome(dto.getNome());
      entity.setTelefone(dto.getTelefone());
      entity.setEmail(dto.getEmail());
      repository.save(entity);
      return new UserDTO(entity);
    }

  @Transactional
    public UserDTO update(String id, UserDTO dto) {
    try {
      User entity = repository.getReferenceById(id);
      entity.setId(dto.getId());
      entity.setNome(dto.getNome());
      entity.setTelefone(dto.getTelefone());
      entity.setEmail(dto.getEmail());
      entity = repository.save(entity);
      return new UserDTO(entity);
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
