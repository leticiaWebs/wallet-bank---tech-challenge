package com.walletbank.wbcontadigital.controller;

import com.walletbank.wbcontadigital.dto.AccountDTO;
import com.walletbank.wbcontadigital.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value ="/accounts")
public class AccountResource {
    @Autowired
    private AccountService service;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll(){
     List <AccountDTO> list = service.findAll();
     return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable String id){
        AccountDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<AccountDTO> insert (@RequestBody AccountDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getNumeroConta()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @PutMapping (value = "/{id}")
    public ResponseEntity<AccountDTO> insert (@PathVariable String id, @RequestBody AccountDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping (value = "/{id}")
    public ResponseEntity<AccountDTO> delete (@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
