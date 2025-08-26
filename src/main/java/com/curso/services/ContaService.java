package com.curso.services;

import com.curso.domains.Conta;
import com.curso.domains.dtos.ContaDTO;
import com.curso.repositories.ContaRepository;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepo;


    public List<ContaDTO> findAll(){
        return  contaRepo.findAll().stream()
                .map(obj -> new ContaDTO(obj))
                .collect(Collectors.toList());
    }

    public Conta findById(Integer id){
        Optional<Conta> obj = contaRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Conta não encontrado! Id: "+ id));
    }

    public Conta create(ContaDTO dto){
        dto.setId(null);
        Conta obj = new Conta(dto);
        return contaRepo.save(obj);
    }

    public Conta update(Integer id, ContaDTO objDto){
        objDto.setId(id);
        Conta oldObj = findById(id);
        oldObj = new Conta(objDto);
        return contaRepo.save(oldObj);
    }

    public void delete(Integer id){
        Conta obj = findById(id);
        contaRepo.deleteById(id);
    }
}
