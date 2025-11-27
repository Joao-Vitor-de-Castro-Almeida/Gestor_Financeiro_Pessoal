package com.curso.services;

import com.curso.domains.*;
import com.curso.domains.enums.Situacao;
import com.curso.domains.enums.TipoConta;
import com.curso.domains.enums.TipoLancamento;
import com.curso.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DBService {

    @Autowired
    private LancamentoRepository lancamentoRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private ContaRepository contaRepo;

    @Autowired
    private CentroCustoRepository centroCustoRepo;

    @Autowired
    private DestinatarioRepository destinatarioRepo;

    @Autowired
    private BancoRepository bancoRepo;

    @Autowired
    private PasswordEncoder encoder;


    public void initDB(){

        Banco banco1 = new Banco(null,"Mercenos");

        bancoRepo.save(banco1);

        CentroCusto centro1 = new CentroCusto(null,"compra de alimentos");

        centroCustoRepo.save(centro1);

        Cliente cliente1 = new Cliente(null,"João Santos","54783485","JoaoSantos@gmail.com",encoder.encode("123"),"99788524");

        clienteRepo.save(cliente1);

        Conta conta1 = new Conta(null,"4581256", TipoConta.ALIMENTACAO,"Municio Solsa",cliente1,banco1,20000,50000,"alguma coisa");

        contaRepo.save(conta1);

        Destinatario destinatario1 = new Destinatario(null,"Motos Velo");

        destinatarioRepo.save(destinatario1);

        Lancamento lancamento1 = new Lancamento(null,conta1,centro1,"temporario","Uma compra muito importante",LocalDate.of(2024,05,17),LocalDate.of(2027,03,10),
        LocalDate.of(2026,05,12),destinatario1,1000, TipoLancamento.CREDITO, Situacao.BAIXADO);

        lancamentoRepo.save(lancamento1);


    }
}
