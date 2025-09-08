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
    private TechnicianRepository techRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;


    public void initDB(){

        Banco banco1 = new Banco(null,"Mercenos");

        bancoRepo.save(banco1);

        CentroCusto centro1 = new CentroCusto(null,"compra de alimentos");

        centroCustoRepo.save(centro1);

        Cliente cliente1 = new Cliente(null,"Maneizes Santos","54783485","manezes@gmail.com","123","99788524");

        clienteRepo.save(cliente1);

        Conta conta1 = new Conta(null,"4581256", TipoConta.ALIMENTACAO,"Municio Solsa",cliente1,banco1,20000,50000,"alguma coisa");

        contaRepo.save(conta1);

        Lancamento lancamento1 = new Lancamento(null,conta1,centro1,"temporario","Umam compra muito importante",LocalDate.of(2024,05,17),LocalDate.of(2027,03,10),
        LocalDate.of(2026,05,12),1000, TipoLancamento.CREDITO, Situacao.ABERTO);

        lancamentoRepo.save(lancamento1);

        Destinatario destinatario1 = new Destinatario(null,"Motos Velo",lancamento1,200,LocalDate.of(2025,07,20));

        destinatarioRepo.save(destinatario1);

        Technician tec1 = new
                Technician(null,"Matias","Fernandes",
                "79548964000","Matias@gmail.com",encoder.encode("123"));

        User user01 = new User(null, "Maneizes", "Santos",
                "5478348599", "manezes@gmail.com",encoder.encode("123"));

        techRepo.save(tec1);
        userRepo.save(user01);

    }
}
