package DAO;

//import Conexao.Conexao;
import Controller.Conexao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import model.Produto;

public class ProdutoDAO {

    // INSERIR produto
    public void inserir(Produto p) throws SQLException {
        String sql = "INSERT INTO produtos (nome, descricao, preco, quantidade) VALUES (?, ?, ?, ?)";

        BigDecimal preco;
        try {
            preco = new BigDecimal(p.getPreco());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Informações inválidas! Digite informações válidas.");
            return;
        }

        try (Connection connection = (Connection) Conexao.getConn().abrirConexao(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setString(3, p.getPreco());
            stmt.setString(4, p.getQuantidade());

            if (p.getPreco().isEmpty()) {
                stmt.setNull(3, java.sql.Types.DECIMAL);
            } else {
                stmt.setBigDecimal(3, new BigDecimal(p.getPreco()));
            }
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (p.getNome().isEmpty() || p.getDescricao().isEmpty() || p.getDescricao().isEmpty() || p.getPreco().isEmpty() || p.getQuantidade().isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Insira todas as informações do produto.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

    }

    // LISTAR todos os produtos
    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection con = (Connection) Conexao.getConn().abrirConexao(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getString("id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setPreco(rs.getString("preco"));
                p.setQuantidade(rs.getString("quantidade"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ATUALIZAR produto
    public void atualizar(Produto p) {
        String sql = "UPDATE produtos SET nome=?, descricao=?, preco=?, quantidade=? WHERE id=?";
        try (Connection con = (Connection) Conexao.getConn().abrirConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setString(3, p.getPreco());
            stmt.setString(4, p.getQuantidade());
            stmt.setString(5, p.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (p.getNome().isEmpty() && p.getDescricao().isEmpty() && p.getDescricao().isEmpty() && p.getPreco().isEmpty() && p.getQuantidade().isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Preencha todos os campos antes de continuar.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
    }

    // DELETAR produto por ID
    public void deletar(String id) {
        String sql = "DELETE FROM produtos WHERE id=?";

        try (Connection con = (Connection) Conexao.getConn().abrirConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        if(id.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Selecione o produto a ser removido.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

    }
}
