# Gestor_Financeiro_Pessoal - Sistema para Gestão Financeira Pessoal

## Descrição Geral
O Gestor_Financeiro_Pessoal é um sistema backend desenvolvido em Java com Spring Boot para auxiliar no gerenciamento de finanças pessoais. Ele oferece funcionalidades como:

- Cadastro de clientes
- Criação de contas bancárias
- Registro de lançamentos (receitas e despesas)
- Alteração de situação de lançamentos (ex.: aberto → baixado)
- Gerenciamento de bancos, centros de custo e destinatários
- Exposição de API REST documentada com Swagger
- Sistema de usuários (`User`) e técnicos (`Technician`) com herança da classe base `Person`
- Segurança com **Spring Security** e **JWT**

O projeto aplica boas práticas de organização e separação em camadas, utilizando **DTOs, Services, Repositories e Controllers**.

---

## Funcionalidades

- Criar e gerenciar clientes.
- Criar contas bancárias vinculadas a clientes.
- Cadastrar bancos e centros de custo.
- Gerenciar destinatários para os lançamentos.
- Registrar lançamentos financeiros, definindo valor, tipo e situação.
- Alterar situação de lançamentos (**ABERTO, BAIXADO**).
- Consultar dados por ID ou listar todos os registros.
- Atualizar ou excluir entidades cadastradas.
- Depositar e sacar valores em contas bancárias, respeitando saldo e limite.

---

## Regras de Negócio dos Lançamentos

- Todo lançamento é criado inicialmente com a situação **ABERTO**.
- Um lançamento aberto pode ser:
    - **Baixado**, quando quitado/pago.
- Um lançamento **baixado não pode ser alterado**.
- O controle das transições de estado segue a lógica encapsulada no enum `Situacao`.

---

## Estrutura do Projeto

- **Domains** → entidades principais (Cliente, Conta, Banco, Lançamento, CentroCusto, Destinatario, Person, User, Technician).
- **DTOs** → objetos de transferência de dados.
- **Repositories** → persistência com Spring Data JPA.
- **Services** → regras de negócio.
- **Controllers** → endpoints REST.
- **Security** → autenticação e autorização
  - `SecurityConfig` → configuração do Spring Security
  - `JWTUtils` → geração e validação de tokens JWT
  - `JWTAuthenticationFilter` → filtro de autenticação via JWT
  - `UserSS` → implementação do UserDetails para Spring Security
  - `UserDetailsServiceImpl` → serviço que carrega usuário do banco
  - `CredenciaisDTO` → DTO para login (email + senha)
  - `TokenDTO` → DTO com token JWT retornado após login

---

## Persistência de Dados

- Banco de dados **H2** utilizado para testes.
- Suporte a bancos relacionais como **PostgreSQL** e **MySQL** para produção.
- Entidades mapeadas com anotações **JPA**.

## Endpoints
| Método     | Rota                          | Ação                             |
|------------|-------------------------------|----------------------------------|
| POST       | `/Banco`                      | Criar um novo Banco              |
| GET        | `/Banco`                      | Listar todos os Bancos           |
| GET        | `/Banco/{id}`                 | Buscar Banco por ID              |
| Post       | `/Banco`                      | cria um novo Banco               |
| Put        | `/Banco/{id}`                 | atualiza um Banco                |
| Delete     | `/Banco/{id} `                | deleta um Banco                  |
| ---------- | ----------------------------- | -------------------------------- |
| POST       | `/CentroCusto`                | Criar um novo CentroCusto        |
| GET        | `/CentroCusto`                | Listar todos os CentroCustos     |
| GET        | `/CentroCusto/{id}`           | Buscar CentroCusto por ID        |
| Post       | `/CentroCusto`                | cria um novo CentroCusto         |
| Put        | `/CentroCusto/{id}`           | atualiza um CentroCusto          |
| Delete     | `/CentroCusto/{id} `          | deleta um CentroCusto            |
| ---------- | ---------------------------   | -------------------------------- |
| POST       | `/Cliente`                    | Criar um novo Cliente            |
| GET        | `/Cliente`                    | Listar todos os Clientes         |
| GET        | `/Cliente/{id}`               | Buscar Cliente por ID            |
| Post       | `/Cliente`                    | cria um novo Cliente             |
| Put        | `/Cliente/{id}`               | atualiza um Cliente              |
| Delete     | `/Cliente/{id} `              | deleta um Cliente                |
| PATCH      | `/Conta/{id}/depositar`       | Depositar um valor na conta      |
| PATCH      | `/Conta/{id}/sacar`           | Sacar um valor da conta          |
| ---------- | ---------------------------   | -------------------------------- |
| POST       | `/Conta`                      | Criar uma nova Conta             |
| GET        | `/Conta`                      | Listar todas as Contas           |
| GET        | `/Conta/{id}`                 | Buscar Conta por ID              |
| Post       | `/Conta`                      | cria uma nova Conta              |
| Put        | `/Conta/{id}`                 | atualiza uma Conta               |
| Delete     | `/Conta/{id} `                | deleta uma Conta                 |
| ---------- | ---------------------------   | -------------------------------- |
| POST       | `/Destinatario`               | Criar um novo Destinatario       |
| GET        | `/Destinatario`               | Listar todos os Destinatarios    |
| GET        | `/Destinatario/{id}`          | Buscar Destinatario por ID       |
| Post       | `/Destinatario`               | cria um novo Destinatario        |
| Put        | `/Destinatario/{id}`          | atualiza um Destinatario         |
| Delete     | `/Destinatario/{id} `         | deleta um Destinatario           |
| ---------- | ---------------------------   | -------------------------------- |
| POST       | `/Lancamento`                 | Criar um novo Lancamento         |
| GET        | `/Lancamento`                 | Listar todos os Lancamen         |
| GET        | `/Lancamento/{id}`            | Buscar Lancamento por ID         |
| Post       | `/Lancamento`                 | cria um novo Lancamento          |
| Put        | `/Lancamento/{id}`            | atualiza um Lancamento           |
| PATCH      | /Lancamento/{id}/baixar       | Baixar um lançamento             |
| Delete     | `/Lancamento/{id} `           | deleta um Lancamento             |
| --------   | ---------------------------   | ------------------------------   |