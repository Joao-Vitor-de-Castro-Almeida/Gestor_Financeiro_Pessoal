package com.curso.services;

import com.curso.domains.Cliente;
import com.curso.domains.Conta;
import com.curso.domains.dtos.ClienteDTO;
import com.curso.domains.dtos.ContaDTO;
import com.curso.repositories.ClienteRepository;
import com.curso.repositories.ContaRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private ContaRepository contaRepo;

    public List<ClienteDTO> findAll(){
        return  clienteRepo.findAll().stream()
                .map(obj -> new ClienteDTO(obj))
                .collect(Collectors.toList());
    }

    public Cliente findById(Integer id){
        Optional<Cliente> obj = clienteRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: "+ id));
    }

    private void validaCliente(ClienteDTO dto) {
        Optional<Cliente> obj = clienteRepo.findBycpf(dto.getCpf());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("Cpf já cadastrado!");
        }
    }

    public Cliente create(ClienteDTO dto){
        dto.setId(null);
        validaCliente(dto);
        Cliente obj = new Cliente(dto);
        return clienteRepo.save(obj);
    }

    public Cliente update(Integer id, ClienteDTO objDto){
        objDto.setId(id);
        Cliente oldObj = findById(id);
        validaCliente(objDto);
        oldObj = new Cliente(objDto);
        return clienteRepo.save(oldObj);
    }

    public void delete(Integer id){
        Cliente obj = findById(id);
        if (!contaRepo.findById(id).isEmpty()) {
            throw new DataIntegrityViolationException(
                    "Não é possível deletar: existem contas vinculados a este Cliente"
            );
        }
        clienteRepo.deleteById(id);
    }
}
