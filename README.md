# Projeto: Folha de Pagamento – POO com Java

Este projeto foi desenvolvido como parte da disciplina de Programação Orientada a Objetos (POO).  
O objetivo principal é simular o processo de geração de folhas de pagamento de funcionários, considerando dependentes, descontos de INSS e IR, e persistência de dados em banco de dados PostgreSQL.

## 💻 Tecnologias e ferramentas utilizadas

- Java 17  
- JDBC (acesso a banco de dados)  
- PostgreSQL  
- Eclipse IDE  
- Lucidchart (para o diagrama UML)  

## 🧩 Estrutura do Projeto

- `classes`: contém as classes principais como `Funcionario`, `Dependente`, `FolhaPagamento` e a superclasse `Pessoa`.
- `banco`: classes de acesso a banco de dados (DAO), como `FuncionarioDAO`, `DependenteDAO` e `FolhaPagamentoDAO`.
- `io`: manipulação de arquivos CSV para entrada e saída de dados.
- `exceptions`: definição de exceção customizada (`DependenteException`).
- `enums`: enumeração `Parentesco`.

## 📄 Funcionalidades

- Leitura de um arquivo CSV contendo dados dos funcionários e seus dependentes
- Cálculo automático de descontos de INSS e IR com base em regras estabelecidas
- Armazenamento das informações em um banco de dados PostgreSQL
- Geração de um novo arquivo CSV com os dados da folha de pagamento
- Validação de dados (ex: CPF único para dependentes, idade < 18)

## 📊 Diagrama UML

O diagrama de classes UML está disponível neste repositório no arquivo:  
📄 `diagrama-uml.pdf`

## 🗂 Como executar

1. Certifique-se de ter o PostgreSQL rodando e com o banco configurado
2. Importe o projeto no Eclipse
3. Configure o caminho do arquivo CSV no console
4. Execute a classe principal
5. Veja os dados persistidos no banco e o arquivo de saída gerado

---

Desenvolvido por [Anna May Duarte] ✨
