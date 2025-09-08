package com.curso.services;


import com.curso.domains.Destinatario;
import com.curso.domains.Lancamento;
import com.curso.domains.dtos.DestinatarioDTO;
import com.curso.repositories.DestinatarioRepository;
import com.curso.repositories.LancamentoRepository;
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
        Destinatario obj = new Destinatario();

        obj.setRazaoSocial(dto.getRazaoSocial());
        obj.setValor(dto.getValor());
        obj.setDataRecibi(dto.getDataRecibi());

        if (dto.getLancamentoId() != null) {
            Lancamento lancamento = lancamentoRepo.findById(dto.getLancamentoId())
                    .orElseThrow(() -> new ObjectNotFoundException("Lançamento não encontrado! Id: " + dto.getLancamentoId()));
            obj.setLancamento(lancamento);
        }

        return destinatarioRepo.save(obj);
    }

    public Destinatario update(Integer id, DestinatarioDTO objDto){
        Destinatario oldObj = destinatarioRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Destinatário não encontrado! Id: " + id));

        oldObj.setRazaoSocial(objDto.getRazaoSocial());
        oldObj.setValor(objDto.getValor());
        oldObj.setDataRecibi(objDto.getDataRecibi());

        if (objDto.getLancamentoId() != null) {
            Lancamento lancamento = lancamentoRepo.findById(objDto.getLancamentoId())
                    .orElseThrow(() -> new ObjectNotFoundException("Lançamento não encontrado! Id: " + objDto.getLancamentoId()));
            oldObj.setLancamento(lancamento);
        }

        return destinatarioRepo.save(oldObj);
    }

    public void delete(Integer id){
        Destinatario obj = findById(id);
        destinatarioRepo.deleteById(id);
    }
}
