package com.curso.services;


import com.curso.domains.CentroCusto;
import com.curso.domains.Conta;
import com.curso.domains.Destinatario;
import com.curso.domains.Lancamento;
import com.curso.domains.dtos.LancamentoDTO;
import com.curso.repositories.CentroCustoRepository;
import com.curso.repositories.ContaRepository;
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
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepo;

    @Autowired
    private ContaRepository contaRepo;

    @Autowired
    private CentroCustoRepository centroCustoRepo;

    @Autowired
    private DestinatarioRepository destinatarioRepo;

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
        Conta conta = contaRepo.findById(dto.getContaId())
                .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada! Id: " + dto.getContaId()));
        CentroCusto centro = centroCustoRepo.findById(dto.getCentroCustoId())
                .orElseThrow(() -> new ObjectNotFoundException("Centro de Custo não encontrado! Id: " + dto.getCentroCustoId()));
        var destinatario = destinatarioRepo.findById(dto.getDestinatarioId())
                .orElseThrow(() -> new ObjectNotFoundException("Destinatário não encontrado! Id: " + dto.getDestinatarioId()));


        Lancamento obj = new Lancamento();
        obj.setConta(conta);
        obj.setCentroCusto(centro);
        obj.setDescricao(dto.getDescricao());
        obj.setParcela(dto.getParcela());
        obj.setDataLanca(dto.getDataLanca());
        obj.setDataVenci(dto.getDataVenci());
        obj.setDestinatario(destinatario);
        obj.setValorDocumento(dto.getValorDocumento());
        obj.setTipoLancamento(dto.getTipoLancamento());

        return lancamentoRepo.save(obj);
    }

    public Lancamento update(Integer id, LancamentoDTO objDto){
        Lancamento oldObj = findById(id);

        oldObj.setDescricao(objDto.getDescricao());
        oldObj.setParcela(objDto.getParcela());
        oldObj.setDataLanca(objDto.getDataLanca());
        oldObj.setDataVenci(objDto.getDataVenci());
        oldObj.setDataBaixa(objDto.getDataBaixa());
        oldObj.setValorDocumento(objDto.getValorDocumento());
        oldObj.setTipoLancamento(objDto.getTipoLancamento());
        oldObj.setSituacao(objDto.getSituacao());

        if (objDto.getContaId() != null) {
            Conta conta = contaRepo.findById(objDto.getContaId())
                    .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada"));
            oldObj.setConta(conta);
        }

        if (objDto.getCentroCustoId() != null) {
            CentroCusto cc = centroCustoRepo.findById(objDto.getCentroCustoId())
                    .orElseThrow(() -> new ObjectNotFoundException("Centro de Custo não encontrado"));
            oldObj.setCentroCusto(cc);
        }

        if (objDto.getDestinatarioId() != null) {
            Destinatario d = destinatarioRepo.findById(objDto.getDestinatarioId())
                    .orElseThrow(() -> new ObjectNotFoundException("Destinatario não encontrado"));
            oldObj.setDestinatario(d);
        }

        return lancamentoRepo.save(oldObj);
    }

    public Lancamento baixar(Integer id) {
        Lancamento lancamento = findById(id);
        lancamento.baixar();
        return lancamentoRepo.save(lancamento);
    }

    public void delete(Integer id){
        Lancamento obj = findById(id);
        if (!destinatarioRepo.findById(id).isEmpty()) {
            throw new DataIntegrityViolationException(
                    "Não é possível deletar: existem Destinatarios vinculados a este lancamento"
            );
        }
        lancamentoRepo.deleteById(id);
    }
}
