package com.walletbank.wbcontadigital.services;

import com.walletbank.wbcontadigital.dto.UserDTO;
import com.walletbank.wbcontadigital.entities.User;
import com.walletbank.wbcontadigital.repositories.UserRepository;
import com.walletbank.wbcontadigital.services.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

  @Transactional
    public List<UserDTO> findAll() {
        List<User> list = repository.findAll();
       return list.stream().map(x ->new UserDTO(x)). collect(Collectors.toList());

    }
    @Transactional
    public UserDTO findById(String id) {
      Optional<User> obj = repository.findById(id);
      User entity = obj.orElseThrow(() ->new EntityNotFoundException("Entity not found"));
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
}
