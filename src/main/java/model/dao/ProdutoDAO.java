/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.conexao;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produto;

/**
 *
 * @author MCVF2
 */
public class ProdutoDAO {
    
   
    public void  create(Produto p){
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO produto(nomeProduto,categoriaProduto,qtdProduto,preco)VALUES(?,?,?,?)");
            stmt.setString(1,p.getNomeProduto());
            stmt.setString(2,p.getCategoriaProduto());
            stmt.setInt(3,p.getQtdProduto());
            stmt.setDouble(4,p.getPreco());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Salvo com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar:" + ex);
        }finally{
            conexao.closeConnection(con, stmt);
        }
    }
    
    public void update(Produto p) {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produto SET nomeProduto = ? , categoriaProduto = ?, qtdProduto = ?,preco = ? WHERE id = ?");
            stmt.setString(1, p.getNomeProduto());
            stmt.setString(2, p.getCategoriaProduto());
            stmt.setInt(3, p.getQtdProduto());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            conexao.closeConnection(con, stmt);
        }

    }
    
    public void delete(Produto p) {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            conexao.closeConnection(con, stmt);
        }

    }
    public java.util.List <Produto> read() {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        java.util.List <Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setNomeProduto(rs.getString(2));
                produto.setCategoriaProduto(rs.getString(3));
                produto.setQtdProduto(rs.getInt(4));
                produto.setPreco(rs.getDouble(5));
                produtos.add(produto);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           conexao.closeConnection(con, stmt, rs);
        }

        return produtos;

    }
    
    public java.util.List<Produto> readForDesc(String nomeProduto) {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        java.util.List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE nomeProduto LIKE ?");
            stmt.setString(1, "%"+nomeProduto+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getInt(1));
                produto.setNomeProduto(rs.getString(2));
                produto.setCategoriaProduto(rs.getString(3));
                produto.setQtdProduto(rs.getInt(4));
                produto.setPreco(rs.getDouble(5));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexao.closeConnection(con, stmt, rs);
        }

        return produtos;

    }
    
    
    
}
