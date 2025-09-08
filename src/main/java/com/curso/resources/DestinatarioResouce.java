package com.curso.resources;

import com.curso.domains.Destinatario;
import com.curso.domains.dtos.DestinatarioDTO;
import com.curso.services.DestinatarioService;
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
@RequestMapping(value = "/destinatario")
@Tag(name = "destinatarios", description="API para Gerenciamento de destinatarios")
public class DestinatarioResouce {

    @Autowired
    private DestinatarioService destinatarioService;

    @GetMapping
    @Operation(summary = "Listar todos os Destinatario",
            description = "Retorna uma lista com todos os Destinatario cadastrados")
    public ResponseEntity<List<DestinatarioDTO>> findAll(){
        return ResponseEntity.ok(destinatarioService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um Destinatario por id",
            description = "Realizar a busca de um Destinatario cadastrado por id")
    public ResponseEntity<DestinatarioDTO> findById(@PathVariable Integer id){
        Destinatario obj = this.destinatarioService.findById(id);
        return ResponseEntity.ok().body(new DestinatarioDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo Destinatario",
            description = "Criar um novo Destinatario com base nos dados fornecidos")
    public ResponseEntity<DestinatarioDTO> create(@Valid @RequestBody DestinatarioDTO dto){
        Destinatario produto = destinatarioService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um Destinatario",
            description = "Altera um Destinatario existente")
    public ResponseEntity<DestinatarioDTO> update(@PathVariable Integer id, @Valid @RequestBody DestinatarioDTO objDto){
        Destinatario Obj = destinatarioService.update(id, objDto);
        return ResponseEntity.ok().body(new DestinatarioDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um Destinatario",
            description = "Remove um Destinatario apatir do seu id")
    public ResponseEntity<DestinatarioDTO> delete(@PathVariable Integer id){
        destinatarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
