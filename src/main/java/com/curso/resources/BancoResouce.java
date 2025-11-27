package com.curso.resources;

import com.curso.domains.Banco;
import com.curso.domains.dtos.BancoDTO;
import com.curso.services.BancoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/banco")
@Tag(name = "bancos", description="API para Gerenciamento de bancos")
public class BancoResouce {

    @Autowired
    private BancoService bancoService;

    @GetMapping
    @Operation(summary = "Listar todos os Banco",
            description = "Retorna uma lista com todos os Banco cadastrados")
    public ResponseEntity<List<BancoDTO>> findAll(){
        return ResponseEntity.ok(bancoService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um Banco por id",
            description = "Realizar a busca de um Banco cadastrado por id")
    public ResponseEntity<BancoDTO> findById(@PathVariable Integer id){
        Banco obj = this.bancoService.findById(id);
        return ResponseEntity.ok().body(new BancoDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo Banco",
            description = "Criar um novo Banco com base nos dados fornecidos")
    public ResponseEntity<BancoDTO> create(@Valid @RequestBody BancoDTO dto){
        Banco produto = bancoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um Banco",
            description = "Altera um Banco existente")
    public ResponseEntity<BancoDTO> update(@PathVariable Integer id, @Valid @RequestBody BancoDTO objDto){
        Banco Obj = bancoService.update(id, objDto);
        return ResponseEntity.ok().body(new BancoDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um Banco",
            description = "Remove um Banco apatir do seu id")
    public ResponseEntity<BancoDTO> delete(@PathVariable Integer id){
        bancoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
