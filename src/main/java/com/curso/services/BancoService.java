package com.curso.services;

import com.curso.domains.Banco;
import com.curso.domains.dtos.BancoDTO;
import com.curso.repositories.BancoRepository;
import com.curso.repositories.ContaRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepo;

    @Autowired
    private ContaRepository contaRepo;

    public List<BancoDTO> findAll(){
        return  bancoRepo.findAll().stream()
                .map(obj -> new BancoDTO(obj))
                .collect(Collectors.toList());
    }

    public Banco findById(Integer id){
        Optional<Banco> obj = bancoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Banco não encontrado! Id: "+ id));
    }


    public Banco create(BancoDTO dto){
        dto.setId(null);
        Banco obj = new Banco(dto);
        return bancoRepo.save(obj);
    }

    public Banco update(Integer id, BancoDTO objDto){
        objDto.setId(id);
        Banco oldObj = findById(id);
        oldObj = new Banco(objDto);
        return bancoRepo.save(oldObj);
    }

    public void delete(Integer id){
        Banco obj = findById(id);
        if (!contaRepo.findById(id).isEmpty()) {
            throw new DataIntegrityViolationException(
                    "Não é possível deletar: existem Contas vinculados a este Banco"
            );
        }
        bancoRepo.deleteById(id);
    }
}
