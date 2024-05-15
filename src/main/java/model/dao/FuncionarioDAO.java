/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import model.bean.Funcionario;


/**
 *
 * @author MCVF2
 */
public class FuncionarioDAO {
    public void  create(Funcionario f){
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO funcionario(nome,CPF,cargo,celular,email)VALUES(?,?,?,?,?)");
            stmt.setString(1,f.getNome());
             stmt.setString(2,f.getCPF());
            stmt.setString(3,f.getCargo());
            stmt.setString(4,f.getCelular());
            stmt.setString(5,f.getEmail());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Salvo com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar:" + ex);
        }finally{
            conexao.closeConnection(con, stmt);
        }
    }
    
      public void update(Funcionario f) {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE funcionario SET nome= ? , CPF = ?, cargo= ?,celular = ?, email =? WHERE id = ?");
            stmt.setString(1,f.getNome());
            stmt.setString(2,f.getCPF());
            stmt.setString(3,f.getCargo());
            stmt.setString(4,f.getCelular());
            stmt.setString(5,f.getEmail());
            stmt.setInt(6, f.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            conexao.closeConnection(con, stmt);
        }

    }
      
      public void delete(Funcionario f) {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM funcionario WHERE id = ?");
            stmt.setInt(1, f.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            conexao.closeConnection(con, stmt);
        }

    }
      
      
       public java.util.List <Funcionario> read() {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        java.util.List <Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString(2));
                funcionario.setCPF(rs.getString(3));
                funcionario.setCargo(rs.getString(4));
                funcionario.setCelular(rs.getString(5));
                funcionario.setEmail(rs.getString(6));
                funcionarios.add(funcionario);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           conexao.closeConnection(con, stmt, rs);
        }

        return funcionarios;

    }
       
       
       public java.util.List<Funcionario> readForNome(String nome) {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        java.util.List<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE nome LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getInt(1));
                funcionario.setNome(rs.getString(2));
                funcionario.setCPF(rs.getString(3));
                funcionario.setCargo(rs.getString(4));
                funcionario.setCelular(rs.getString(5));
                funcionario.setEmail(rs.getString(6));
                funcionarios.add(funcionario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexao.closeConnection(con, stmt, rs);
        }

        return funcionarios;

    }
}
