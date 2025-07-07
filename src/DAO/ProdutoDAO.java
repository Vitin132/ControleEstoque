package DAO;

//import Conexao.Conexao;
import Conexao.Conexao;
import java.sql.Connection;
import model.Produto;

import java.sql.*;
import java.util.*;
import model.Produto;

public class ProdutoDAO {

    // INSERIR produto
    public void inserir(Produto p) {
        String sql = "INSERT INTO produtos (nome, descricao, preco, quantidade) VALUES (?, ?, ?, ?)";
        try (Connection connection = (Connection) Conexao.getConn(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getQuantidade());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // LISTAR todos os produtos
    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection con = (Connection) Conexao.getConn(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidade(rs.getInt("quantidade"));
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
        try (Connection con = (Connection) Conexao.getConn(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getQuantidade());
            stmt.setInt(5, p.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETAR produto por ID
    public void deletar(int id) {
        String sql = "DELETE FROM produtos WHERE id=?";
        try (Connection con = (Connection) Conexao.getConn(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
