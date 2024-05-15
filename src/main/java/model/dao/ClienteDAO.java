/*
 * Click nbcs://nbhost/SystemFileSystem/Templates/Licenses/license-decault.txt to change this license
 * Click nbcs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;

/**
 *
 * @author MCVF2
 */
public class ClienteDAO {
     public void  create(Cliente c){
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO cliente(nome,CPF,celular,email, estado, cidade, endereco, CEP)VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1,c.getNome());
            stmt.setString(2,c.getCPF());
            stmt.setString(3,c.getCelular());
            stmt.setString(4,c.getEmail());
            stmt.setString(5,c.getEstado());
            stmt.setString(6,c.getCidade());
            stmt.setString(7,c.getEndereco());
            stmt.setString(8,c.getCEP());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Salvo com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar:" + ex);
        }finally{
            conexao.closeConnection(con, stmt);
        }
    }
     
      public void update(Cliente c) {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET nome= ? ,CPF = ?, celular = ?,email = ?,estado = ?,cidade = ?, endereco=?,CEP=? WHERE id = ?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCPF());
            stmt.setString(3, c.getCelular());
            stmt.setString(4, c.getEmail());
            stmt.setString(5, c.getEstado());
            stmt.setString(6, c.getCidade());
            stmt.setString(7, c.getEndereco());
            stmt.setString(8, c.getCEP());
            stmt.setInt(9, c.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            conexao.closeConnection(con, stmt);
        }

    }
      public void delete(Cliente c) {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE id = ?");
            stmt.setInt(1, c.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            conexao.closeConnection(con, stmt);
        }

    }
      
     public java.util.List <Cliente> read() {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        java.util.List <Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString(2));
                cliente.setCPF(rs.getString(3));
                cliente.setCelular(rs.getString(4));
                cliente.setEmail(rs.getString(5));
                cliente.setEstado(rs.getString(6));
                cliente.setCidade(rs.getString(7));
                cliente.setEndereco(rs.getString(8));
                cliente.setCEP(rs.getString(9));
                clientes.add(cliente);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           conexao.closeConnection(con, stmt, rs);
        }

        return clientes;

    }
      public java.util.List <Cliente> readForNome(String nome) {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        java.util.List <Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?");
            stmt.setString(1, "%"+nome+"%");

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt(1));
                cliente.setNome(rs.getString(2));
                cliente.setCPF(rs.getString(3));
                cliente.setCelular(rs.getString(4));
                cliente.setEmail(rs.getString(5));
                cliente.setEstado(rs.getString(6));
                cliente.setCidade(rs.getString(7));
                cliente.setEndereco(rs.getString(8));
                cliente.setCEP(rs.getString(9));
                clientes.add(cliente);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           conexao.closeConnection(con, stmt, rs);
        }

        return clientes;

    }
}
