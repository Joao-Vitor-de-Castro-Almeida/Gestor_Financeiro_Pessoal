package com.curso.services;

import com.curso.domains.CentroCusto;
import com.curso.domains.dtos.CentroCustoDTO;
import com.curso.repositories.CentroCustoRepository;
import com.curso.repositories.LancamentoRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CentroCustoService {

    @Autowired
    private CentroCustoRepository centroCustoRepo;

    @Autowired
    private LancamentoRepository lancamentoRepo;


    public List<CentroCustoDTO> findAll(){
        return  centroCustoRepo.findAll().stream()
                .map(obj -> new CentroCustoDTO(obj))
                .collect(Collectors.toList());
    }

    public CentroCusto findById(Integer id){
        Optional<CentroCusto> obj = centroCustoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("CentroCusto não encontrado! Id: "+ id));
    }

    public CentroCusto create(CentroCustoDTO dto){
        dto.setId(null);
        CentroCusto obj = new CentroCusto(dto);
        return centroCustoRepo.save(obj);
    }

    public CentroCusto update(Integer id, CentroCustoDTO objDto){
        objDto.setId(id);
        CentroCusto oldObj = findById(id);
        oldObj = new CentroCusto(objDto);
        return centroCustoRepo.save(oldObj);
    }

    public void delete(Integer id){
        CentroCusto obj = findById(id);
        if (!lancamentoRepo.findById(id).isEmpty()) {
            throw new DataIntegrityViolationException(
                    "Não é possível deletar: existem lançamentos vinculados a este Centro de Custo"
            );
        }
        centroCustoRepo.deleteById(id);
    }
}
