package com.curso.resoucers;

import com.curso.domains.CentroCusto;
import com.curso.domains.dtos.CentroCustoDTO;
import com.curso.services.CentroCustoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/centroCusto")
public class CentroCustoResouce {

    @Autowired
    private CentroCustoService centroCustoService;

    @GetMapping
    @Operation(summary = "Listar todos os CentroCusto",
            description = "Retorna uma lista com todos os CentroCusto cadastrados")
    public ResponseEntity<List<CentroCustoDTO>> findAll(){
        return ResponseEntity.ok(centroCustoService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um CentroCusto por id",
            description = "Realizar a busca de um CentroCusto cadastrado por id")
    public ResponseEntity<CentroCustoDTO> findById(@PathVariable Integer id){
        CentroCusto obj = this.centroCustoService.findById(id);
        return ResponseEntity.ok().body(new CentroCustoDTO(obj));
    }


    @PostMapping
    @Operation(summary = "Criar um novo CentroCusto",
            description = "Criar um novo CentroCusto com base nos dados fornecidos")
    public ResponseEntity<CentroCustoDTO> create(@Valid @RequestBody CentroCustoDTO dto){
        CentroCusto produto = centroCustoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um CentroCusto",
            description = "Altera um CentroCusto existente")
    public ResponseEntity<CentroCustoDTO> update(@PathVariable Integer id, @Valid @RequestBody CentroCustoDTO objDto){
        CentroCusto Obj = centroCustoService.update(id, objDto);
        return ResponseEntity.ok().body(new CentroCustoDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um CentroCusto",
            description = "Remove um CentroCusto apatir do seu id")
    public ResponseEntity<CentroCustoDTO> delete(@PathVariable Integer id){
        centroCustoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
