
* Funcionalidades

- Cadastro de alunos com:
  - Nome
  - CPF (somente números)
  - Data de nascimento
  - Telefone ((xx)-xxxxx-xxxx)
  - E-mail

- Cadastro de treinos vinculados ao aluno:
  - Tipo do treino
  - Descrição
  - Duração (em minutos)
  - Data de início

- Consultas:
  - Listar todos os alunos
  - Buscar aluno por CPF
  - Listar todos os treinos de um aluno

- Atualização e exclusão de alunos e treinos

/**/

* Tecnologias

- Java 17+
- MySQL
- JDBC (MySQL Connector)

/**/


* Pré-requisitos

- [JDK 17 ou superior]
- [MySQL Server]
- Driver JDBC: `mysql-connector-j-9.3.0.jar`

/**/

* Configurando o Banco

1. Acesse o MySQL.

/* COMANDO PARA CRIAR A BASE DE DADOS */

CREATE DATABASE A3;

/*ESSE COMANDO FAZ COM QUE OS PROXIMOS COMANDOS UTILIZEM A BASE DE ADOS CRIADA ANTERIORMENTE*/

USE A3;

/* COMANDO PARA CRIAR A TABELA ALUNOS */

CREATE TABLE ALUNOS (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NOME VARCHAR(100),
    CPF VARCHAR(11) NOT NULL UNIQUE,
    DATA_NASCIMENTO DATE,
    TELEFONE VARCHAR(20),
    EMAIL VARCHAR(100));

/* COMANDO PARA CRIAR A TABELA TREINOS*/
/*FOI FEITO UMA LIGAÇÃO COM A TABELA ALUNOS, PELO ID DO ALUNO, E UM CASCADE PARA APAGAR OS TREINOS CASO O ALUNO FOR APAGADO*/

CREATE TABLE TREINOS (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ALUNO_ID INT,
    TIPO_TREINO VARCHAR(50),
    DESCRICAO TEXT,
    DURACAO_MINUTOS INT,
    DATA_INICIO DATE,
    FOREIGN KEY (ALUNO_ID) REFERENCES ALUNOS(ID) ON DELETE CASCADE);

2. Verifique se o usuário e senha no arquivo `Conexao.java` estão corretos:

/**/

private static final String USUARIO = "root";
private static final String SENHA = "123456";

/**/

* Compilando o Projeto

Abra o terminal na pasta raiz do projeto e execute:

/**/

javac -cp lib/mysql-connector-j-9.3.0.jar -d bin src/model/*.java src/dao/*.java src/main/*.java

/**/

/**/

* Executando o Sistema

/**/

java -cp "bin;lib/mysql-connector-j-9.3.0.jar" main.Main

/**/

/**/

* Validações no Sistema

- CPF: obrigatório no formato somente números (11 dígitos).
- Telefone: obrigatório no formato (xx)-xxxxx-xxxx.
- Data deve ser informada como YYYY-MM-DD (ex: 2024-05-25).

