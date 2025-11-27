package com.curso.services;



import com.curso.domains.Destinatario;
import com.curso.domains.Lancamento;
import com.curso.domains.dtos.CentroCustoDTO;
import com.curso.domains.dtos.DestinatarioDTO;
import com.curso.repositories.DestinatarioRepository;
import com.curso.repositories.LancamentoRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DestinatarioService {

    @Autowired
    private DestinatarioRepository destinatarioRepo;

    @Autowired
    private LancamentoRepository lancamentoRepo;

    public List<DestinatarioDTO> findAll(){
        return  destinatarioRepo.findAll().stream()
                .map(obj -> new DestinatarioDTO(obj))
                .collect(Collectors.toList());
    }

    public Destinatario findById(Integer id){
        Optional<Destinatario> obj = destinatarioRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Destinatario não encontrado! Id: "+ id));
    }

    public Destinatario create(DestinatarioDTO dto){
        dto.setId(null);
        Destinatario obj = new Destinatario(dto);
        return  destinatarioRepo.save(obj);
    }

    public Destinatario update(Integer id, DestinatarioDTO objDto){
        objDto.setId(id);
        Destinatario oldObj = findById(id);
        oldObj = new Destinatario(objDto);
        return destinatarioRepo.save(oldObj);
    }

    public void delete(Integer id){
        Destinatario obj = findById(id);
        if (!lancamentoRepo.findById(id).isEmpty()) {
            throw new DataIntegrityViolationException(
                    "Não é possível deletar: existem lançamentos vinculados a este Destinatario"
            );
        }
        destinatarioRepo.deleteById(id);
    }
}

