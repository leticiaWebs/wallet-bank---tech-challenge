package com.walletbank.wbcontadigital.controller;

import com.walletbank.wbcontadigital.dto.UserRegisterDTO;
import com.walletbank.wbcontadigital.services.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value ="/users")
public class UserRegisterResource {

    @Autowired
    private UserRegisterService service;
    @GetMapping
    public ResponseEntity<Page<UserRegisterDTO>> findAll(
            @RequestParam(value ="page", defaultValue = "0") Integer page,
            @RequestParam(value ="linesPerPage", defaultValue = "12") Integer linesPerPager,
            @RequestParam(value ="direction", defaultValue = "DESC") String direction,
            @RequestParam(value ="orderBy", defaultValue = "nome") String orderBy)
    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPager, Sort.Direction.valueOf(direction), orderBy);

        Page<UserRegisterDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserRegisterDTO> findById(@PathVariable String id){
        UserRegisterDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<UserRegisterDTO> insert (@RequestBody UserRegisterDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @PutMapping (value = "/{id}")
    public ResponseEntity<UserRegisterDTO> insert (@PathVariable String id, @RequestBody UserRegisterDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping (value = "/{id}")
    public ResponseEntity<UserRegisterDTO> delete (@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
