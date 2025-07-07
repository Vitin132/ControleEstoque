package view;

import DAO.ProdutoDAO;

import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class TelaEstoque extends JFrame {

    private JTextField txtNome, txtDescricao, txtPreco, txtQuantidade, txtId;
    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaEstoque() {
        setTitle("Controle de Estoque");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Campos
        JLabel lblId = new JLabel("ID:");
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblDescricao = new JLabel("Descrição:");
        JLabel lblPreco = new JLabel("Preço:");
        JLabel lblQuantidade = new JLabel("Quantidade:");

        txtId = new JTextField();
        txtNome = new JTextField();
        txtDescricao = new JTextField();
        txtPreco = new JTextField();
        txtQuantidade = new JTextField();

        lblId.setBounds(20, 20, 100, 25);
        txtId.setBounds(100, 20, 50, 25);
        lblNome.setBounds(20, 60, 100, 25);
        txtNome.setBounds(100, 60, 200, 25);
        lblDescricao.setBounds(20, 100, 100, 25);
        txtDescricao.setBounds(100, 100, 200, 25);
        lblPreco.setBounds(20, 140, 100, 25);
        txtPreco.setBounds(100, 140, 100, 25);
        lblQuantidade.setBounds(20, 180, 100, 25);
        txtQuantidade.setBounds(100, 180, 100, 25);

        add(lblId); add(txtId);
        add(lblNome); add(txtNome);
        add(lblDescricao); add(txtDescricao);
        add(lblPreco); add(txtPreco);
        add(lblQuantidade); add(txtQuantidade);

        // Botões
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnListar = new JButton("Listar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnDeletar = new JButton("Deletar");

        btnCadastrar.setBounds(320, 60, 120, 25);
        btnListar.setBounds(320, 100, 120, 25);
        btnAtualizar.setBounds(320, 140, 120, 25);
        btnDeletar.setBounds(320, 180, 120, 25);

        add(btnCadastrar);
        add(btnListar);
        add(btnAtualizar);
        add(btnDeletar);

        // Tabela
        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Descrição", "Preço", "Qtd"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 230, 740, 200);
        add(scroll);

        // Botão: Cadastrar
        btnCadastrar.addActionListener(e -> {
            Produto p = new Produto();
            p.setNome(txtNome.getText());
            p.setDescricao(txtDescricao.getText());
            p.setPreco(Double.parseDouble(txtPreco.getText()));
            p.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            new ProdutoDAO().inserir(p);
            limparCampos();
            listarProdutos();
        });

        // Botão: Listar
        btnListar.addActionListener(e -> listarProdutos());

        // Botão: Atualizar
        btnAtualizar.addActionListener(e -> {
            Produto p = new Produto();
            p.setId(Integer.parseInt(txtId.getText()));
            p.setNome(txtNome.getText());
            p.setDescricao(txtDescricao.getText());
            p.setPreco(Double.parseDouble(txtPreco.getText()));
            p.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            new ProdutoDAO().atualizar(p);
            limparCampos();
            listarProdutos();
        });

        // Botão: Deletar
        btnDeletar.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            new ProdutoDAO().deletar(id);
            limparCampos();
            listarProdutos();
        });

        // Evento de clique na tabela: preencher campos
        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.getSelectedRow();
                txtId.setText(tabela.getValueAt(linha, 0).toString());
                txtNome.setText(tabela.getValueAt(linha, 1).toString());
                txtDescricao.setText(tabela.getValueAt(linha, 2).toString());
                txtPreco.setText(tabela.getValueAt(linha, 3).toString());
                txtQuantidade.setText(tabela.getValueAt(linha, 4).toString());
            }
        });

        setVisible(true);
    }

    private void listarProdutos() {
        modelo.setRowCount(0); // limpa tabela
        List<Produto> lista = new ProdutoDAO().listar();
        for (Produto p : lista) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getDescricao(),
                p.getPreco(),
                p.getQuantidade()
            });
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtDescricao.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
    }

    public static void main(String[] args) {
        new TelaEstoque();
    }
}