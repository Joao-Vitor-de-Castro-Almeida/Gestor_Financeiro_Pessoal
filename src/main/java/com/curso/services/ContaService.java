package com.curso.services;

import com.curso.domains.Banco;
import com.curso.domains.Cliente;
import com.curso.domains.Conta;
import com.curso.domains.dtos.ContaDTO;
import com.curso.repositories.BancoRepository;
import com.curso.repositories.ClienteRepository;
import com.curso.repositories.ContaRepository;
import com.curso.repositories.LancamentoRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
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

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private BancoRepository bancoRepo;

    @Autowired
    private LancamentoRepository lancamentoRepo;

    public List<ContaDTO> findAll(){
        return  contaRepo.findAll().stream()
                .map(obj -> new ContaDTO(obj))
                .collect(Collectors.toList());
    }

    public Conta findById(Integer id){
        Optional<Conta> obj = contaRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Conta não encontrado! Id: "+ id));
    }

    private void validaConta(ContaDTO dto) {
        Optional<Conta> obj = contaRepo.findBynumero(dto.getNumero());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("numero já cadastrado!");
        }
    }

    public Conta create(ContaDTO dto){
        Conta obj = new Conta();

        obj.setNumero(dto.getNumero());
        obj.setTipoConta(dto.getTipoConta());
        obj.setAgencia(dto.getAgencia());
        obj.setSaldo(dto.getSaldo());
        obj.setLimite(dto.getLimite());
        obj.setDescricao(dto.getDescricao());

        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepo.findById(dto.getClienteId())
                    .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + dto.getClienteId()));
            obj.setTitular(cliente);
        }

        if (dto.getBancoId() != null) {
            Banco banco = bancoRepo.findById(dto.getBancoId())
                    .orElseThrow(() -> new ObjectNotFoundException("Banco não encontrado! Id: " + dto.getBancoId()));
            obj.setBanco(banco);
        }

        validaConta(dto);

        return contaRepo.save(obj);
    }

    public Conta update(Integer id, ContaDTO objDto){
        Conta oldObj = contaRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada! Id: " + id));

        oldObj.setNumero(objDto.getNumero());
        oldObj.setTipoConta(objDto.getTipoConta());
        oldObj.setAgencia(objDto.getAgencia());
        oldObj.setSaldo(objDto.getSaldo());
        oldObj.setLimite(objDto.getLimite());
        oldObj.setDescricao(objDto.getDescricao());

        if (objDto.getClienteId() != null) {
            Cliente cliente = clienteRepo.findById(objDto.getClienteId())
                    .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + objDto.getClienteId()));
            oldObj.setTitular(cliente);
        }

        if (objDto.getBancoId() != null) {
            Banco banco = bancoRepo.findById(objDto.getBancoId())
                    .orElseThrow(() -> new ObjectNotFoundException("Banco não encontrado! Id: " + objDto.getBancoId()));
            oldObj.setBanco(banco);
        }

        validaConta(objDto);
        return contaRepo.save(oldObj);
    }

    public Conta depositar(Integer contaId, double valor) {
        Conta conta = findById(contaId);
        conta.Depositar(valor);
        return contaRepo.save(conta);
    }

    public Conta sacar(Integer contaId, double valor) {
        Conta conta = findById(contaId);
        conta.Sacar(valor);
        return contaRepo.save(conta);
    }

    public void delete(Integer id){
        Conta obj = findById(id);
        if (!lancamentoRepo.findById(id).isEmpty()) {
            throw new DataIntegrityViolationException(
                    "Não é possível deletar: existem lançamentos vinculados a este Conta"
            );
        }
        contaRepo.deleteById(id);
    }
}
