package com.curso.resoucers;

import com.curso.domains.Conta;
import com.curso.domains.dtos.ContaDTO;
import com.curso.services.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/conta")
public class ContaResouce {

    @Autowired
    private ContaService contaService;

    @GetMapping
    @Operation(summary = "Listar todos as Conta",
            description = "Retorna uma lista com todas as Conta cadastrados")
    public ResponseEntity<List<ContaDTO>> findAll(){
        return ResponseEntity.ok(contaService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar uma Conta por id",
            description = "Realizar a busca de uma Conta cadastrado por id")
    public ResponseEntity<ContaDTO> findById(@PathVariable Integer id){
        Conta obj = this.contaService.findById(id);
        return ResponseEntity.ok().body(new ContaDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um nova Conta",
            description = "Criar um nova Conta com base nos dados fornecidos")
    public ResponseEntity<ContaDTO> create(@Valid @RequestBody ContaDTO dto){
        Conta produto = contaService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera uma Conta",
            description = "Altera uma Conta existente")
    public ResponseEntity<ContaDTO> update(@PathVariable Integer id, @Valid @RequestBody ContaDTO objDto){
        Conta Obj = contaService.update(id, objDto);
        return ResponseEntity.ok().body(new ContaDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar uma Conta",
            description = "Remove uma Conta apatir do seu id")
    public ResponseEntity<ContaDTO> delete(@PathVariable Integer id){
        contaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
