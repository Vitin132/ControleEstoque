## Controle de Estoque

Esse Controle de Estoque usa Java Swing e o banco de dados MySQL com o objetivo de gerenciar um estoque de uma empresa. O sistema permite o cadastro, a edição, a remoção e uma consulta dos produtos.
As tecnologias utilizadas foram as seguintes:


  | Tecnologia | Descrição                                       |
  | ---------- | ----------------------------------------------- |
  | Java SE    | Linguagem de programação principal              |
  | Swing      | Biblioteca gráfica para interface com o usuário |
  | MySQL      | Banco de dados relacional                       |
  | JDBC       | Interface de conexão com banco de dados         |
  | NetBeans   | Ambiente de desenvolvimento integrado (IDE)     |


## O projeto contém a seguinte estrutura:
```sh     
src/
  
 └Conexao/Conexao.java
 └DAO/ProdutoDAO.java
 └model/Produto.java
 └view/TelaEstoque.java
```

 ## O sistema possui a seguinte interface:

 
<img width="786" height="493" alt="Captura de tela 2025-07-11 195637" src="https://github.com/user-attachments/assets/9ddd41c0-1828-494c-a1f5-0c515bcc4f4d" />

O sistema possui os botões:

Inserir: Para inserir produtos após adicionar as respectivas informações.

Listar: Lista na tabela abaixo todos os produtos cadastrados.

Atualizar: Botão para alterar as informações de um produto que foi selecionado da tabela ao clicar com o mouse.

Limpar: Limpa todos os campos de informações, caso tenha selecionado o produto errado.

Remover: Remove o produto selecionado da tabela e do banco de dados.

## INSTALAÇÃO

1. **Baixe o código:** Clone este repositório.
   ```sh
   git clone https://github.com/Vitin132/ControleEstoque

2. **Crie o Banco de dados**: Abaixo está o código para a criação do banco de dados.

   **MySQL:**
    
    ```sh
    CREATE DATABASE estoque_db;

    USE estoque_db;

    CREATE TABLE produtos (
   
        id INT AUTO_INCREMENT PRIMARY KEY,
   
        nome VARCHAR(100) NOT NULL,
   
        descricao TEXT,
   
        preco DECIMAL(10,2) NOT NULL,
   
        quantidade INT NOT NULL
   
    );
    ````
     
   3.**Execute o projeto:** Execute através da opção "run file" em TelaEstoque.java ou pelo atalho Shift + F6 no netbeans.
       
   



