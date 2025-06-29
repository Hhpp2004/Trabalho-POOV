# Sistema de Controle de Doação de Sangue

## 📋 Descrição do Projeto

Sistema desenvolvido em Java para gerenciamento de doações de sangue, implementando conceitos de Programação Orientada a Objetos (POO). O sistema permite o cadastro e controle de doadores, receptores e doações, facilitando a gestão de bancos de sangue.

## 🎯 Objetivos

- Aplicar conceitos fundamentais de POO (Encapsulamento, Herança, Polimorfismo)
- Gerenciar dados de doadores e receptores de sangue
- Controlar o estoque de sangue por tipo sanguíneo
- Facilitar o processo de compatibilidade entre doadores e receptores
- Gerar relatórios e estatísticas do sistema

## 🏗️ Arquitetura do Sistema

### Principais Classes

- **Pessoa** (classe abstrata): Classe base com atributos comuns
- **Doador**: Herda de Pessoa, representa doadores de sangue
- **Receptor**: Herda de Pessoa, representa receptores de sangue
- **Doacao**: Gerencia informações sobre as doações realizadas
- **SistemaBanco**: Classe principal que coordena todas as operações
- **TipoSanguineo**: Enum para tipos sanguíneos (A+, A-, B+, B-, AB+, AB-, O+, O-)

### Funcionalidades Principais

- ✅ Cadastro de doadores e receptores
- ✅ Registro de doações
- ✅ Verificação de compatibilidade sanguínea
- ✅ Controle de estoque por tipo sanguíneo
- ✅ Geração de relatórios
- ✅ Busca e listagem de dados

## 🚀 Como Executar

### Pré-requisitos

- Java JDK 8 ou superior
- PostgreSQL
- IDE Java (Eclipse, IntelliJ IDEA, VS Code, etc.)

### Configuração do Banco de Dados

1. Instale o PostgreSQL
2. Crie um banco de dados para o projeto
3. Configure as credenciais de conexão no arquivo de configuração

### Passos para Execução

1. Clone ou baixe o projeto
2. Importe o projeto na sua IDE Java
3. Configure a conexão com o banco de dados
4. Compile todas as classes
5. Execute a classe principal `Main`

```bash
# Compilar
javac -cp "lib/*:src" src/Main.java

# Executar
java -cp "lib/*:bin" Main
```

## 💻 Estrutura do Projeto

```
src/
├── Main.java (classe principal)
├── Model/
│   ├── DAO/
│   │   ├── Core/
│   │   │   ├── DAOFactory.java
│   │   │   ├── GenericJDBCDAO.java
│   │   │   └── ...
│   │   ├── ConexaoFactoryPostgresSQL.java
│   │   ├── DaodorDAO.java
│   │   └── ...
│   └── Doador.java
├── Menu/
│   ├── MenuDoador.java
│   └── MenuDoacao.java
└── ...
lib/
├── postgresql-driver.jar
└── ...
```

## 📊 Funcionalidades Detalhadas

### Gestão de Doadores

- Cadastro completo de doadores
- Pesquisa por código, nome ou outros critérios
- Atualização de dados
- Controle de histórico de doações

### Gestão de Doações

- Registro de novas doações
- Controle de tipos sanguíneos
- Verificação de compatibilidade
- Histórico de doações

### Relatórios Disponíveis

- Lista de doadores por tipo sanguíneo
- Estoque atual de sangue
- Histórico de doações
- Estatísticas gerais do sistema

## 🔧 Tecnologias Utilizadas

- **Linguagem**: Java
- **Banco de Dados**: PostgreSQL
- **Paradigma**: Programação Orientada a Objetos
- **Padrões**: DAO (Data Access Object), Factory
- **JDBC**: Para conexão com banco de dados

## 🎓 Conceitos de POO Aplicados

### Herança

- Hierarquia de classes para diferentes tipos de entidades

### Encapsulamento

- Atributos privados com métodos getters e setters
- Validações nos métodos de acesso

### Polimorfismo

- Métodos sobrescritos nas classes filhas
- Uso de interfaces para diferentes comportamentos

### Abstração

- Classes abstratas e interfaces
- Padrão DAO para abstração de acesso a dados

## 📈 Possíveis Melhorias

- [ ] Interface gráfica (JavaFX/Swing)
- [ ] Sistema de autenticação
- [ ] API REST
- [ ] Sistema de notificações
- [ ] Agendamento de doações
- [ ] Relatórios em PDF
- [ ] Testes automatizados

## 🗄️ Configuração do Banco de Dados

### Criação das Tabelas

```sql
-- Exemplo de estrutura da tabela doador
CREATE TABLE doador (
    codigo SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    contato VARCHAR(20),
    tipo_rh VARCHAR(10),
    rh VARCHAR(3),
    tiposanguineo VARCHAR(5),
    situacao VARCHAR(20)
);
```

## 👥 Desenvolvimento

Este projeto foi desenvolvido como trabalho acadêmico para a disciplina de Programação Orientada a Objetos (POOV) do IFTM, demonstrando a aplicação prática dos conceitos fundamentais de POO em um sistema real de gerenciamento.

## 📄 Licença

Projeto acadêmico desenvolvido para fins educacionais.

---

**Nota**: Este é um sistema educacional e não deve ser utilizado em ambiente de produção sem as devidas adaptações e validações necessárias para sistemas críticos de saúde.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
