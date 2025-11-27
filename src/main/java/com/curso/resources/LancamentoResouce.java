package com.curso.resources;

import com.curso.domains.Lancamento;
import com.curso.domains.dtos.LancamentoDTO;
import com.curso.services.LancamentoService;
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
@RequestMapping(value = "/lancamento")
@Tag(name = "lancamentos", description="API para Gerenciamento de lancamentos")
public class LancamentoResouce {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    @Operation(summary = "Listar todos os Lancamento",
            description = "Retorna uma lista com todos os Lancamento cadastrados")
    public ResponseEntity<List<LancamentoDTO>> findAll(){
        return ResponseEntity.ok(lancamentoService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um Banco por id",
            description = "Realizar a busca de um Banco cadastrado por id")
    public ResponseEntity<LancamentoDTO> findById(@PathVariable Integer id){
        Lancamento obj = this.lancamentoService.findById(id);
        return ResponseEntity.ok().body(new LancamentoDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo Lancamento",
            description = "Criar um novo Lancamento com base nos dados fornecidos")
    public ResponseEntity<LancamentoDTO> create(@Valid @RequestBody LancamentoDTO dto){
        Lancamento produto = lancamentoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um Lancamento",
            description = "Altera um Lancamento existente")
    public ResponseEntity<LancamentoDTO> update(@PathVariable Integer id, @Valid @RequestBody LancamentoDTO objDto){
        Lancamento Obj = lancamentoService.update(id, objDto);
        return ResponseEntity.ok().body(new LancamentoDTO(Obj));
    }

    @PatchMapping("/{id}/baixar")
    @Operation(summary = "Baixar um Lançamento",
            description = "Altera a situação de um lançamento de ABERTO para BAIXADO")
    public ResponseEntity<Void> baixar(@PathVariable Integer id) {
        lancamentoService.baixar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um Lancamento",
            description = "Remove um Lancamento apatir do seu id")
    public ResponseEntity<LancamentoDTO> delete(@PathVariable Integer id){
        lancamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
