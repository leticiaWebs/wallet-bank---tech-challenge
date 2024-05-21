package com.walletbank.wbcontadigital.controller;

import com.walletbank.wbcontadigital.dto.UserDTO;
import com.walletbank.wbcontadigital.entities.User;
import com.walletbank.wbcontadigital.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value ="/users")
public class UserResource {

    @Autowired
    private UserService service;
    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(
            @RequestParam(value ="page", defaultValue = "0") Integer page,
            @RequestParam(value ="linesPerPage", defaultValue = "12") Integer linesPerPager,
            @RequestParam(value ="direction", defaultValue = "DESC") String direction,
            @RequestParam(value ="orderBy", defaultValue = "nome") String orderBy)
    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPager, Sort.Direction.valueOf(direction), orderBy);

        Page<UserDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        UserDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<UserDTO> insert (@RequestBody UserDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @PutMapping (value = "/{id}")
    public ResponseEntity<UserDTO> insert (@PathVariable String id, @RequestBody UserDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping (value = "/{id}")
    public ResponseEntity<UserDTO> delete (@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
