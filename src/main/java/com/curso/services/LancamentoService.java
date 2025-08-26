package com.curso.services;


import com.curso.domains.Lancamento;
import com.curso.domains.dtos.LancamentoDTO;
import com.curso.repositories.LancamentoRepository;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepo;


    public List<LancamentoDTO> findAll(){
        return  lancamentoRepo.findAll().stream()
                .map(obj -> new LancamentoDTO(obj))
                .collect(Collectors.toList());
    }

    public Lancamento findById(Integer id){
        Optional<Lancamento> obj = lancamentoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Lancamento não encontrado! Id: "+ id));
    }

    public Lancamento create(LancamentoDTO dto){
        dto.setId(null);
        Lancamento obj = new Lancamento(dto);
        return lancamentoRepo.save(obj);
    }

    public Lancamento update(Integer id, LancamentoDTO objDto){
        objDto.setId(id);
        Lancamento oldObj = findById(id);
        oldObj = new Lancamento(objDto);
        return lancamentoRepo.save(oldObj);
    }

    public void delete(Integer id){
        Lancamento obj = findById(id);
        lancamentoRepo.deleteById(id);
    }
}
