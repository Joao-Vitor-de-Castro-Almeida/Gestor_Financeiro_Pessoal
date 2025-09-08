package com.curso.resources;

import com.curso.domains.Cliente;
import com.curso.domains.Conta;
import com.curso.domains.dtos.ClienteDTO;
import com.curso.services.ClienteService;
import com.curso.services.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
@Tag(name = "clientes", description="API para Gerenciamento de clientes")
public class ClienteResouce {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ContaService contaService;

    @GetMapping
    @Operation(summary = "Listar todos os Cliente",
            description = "Retorna uma lista com todos os Cliente cadastrados")
    public ResponseEntity<List<ClienteDTO>> findAll(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um Cliente por id",
            description = "Realizar a busca de um Cliente cadastrado por id")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        Cliente obj = this.clienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo Cliente",
            description = "Criar um novo Cliente com base nos dados fornecidos")
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO dto){
        Cliente produto = clienteService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um Cliente",
            description = "Altera um Cliente existente")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDto){
        Cliente Obj = clienteService.update(id, objDto);
        return ResponseEntity.ok().body(new ClienteDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um Cliente",
            description = "Remove um Cliente apatir do seu id")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
