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
import model.bean.Cliente;
import model.bean.Usuario;
import model.bean.Usuario;


/**
 *
 * @author MCVF2
 */
public class UsuarioDAO {
      public boolean checkLogin(String usuario, String senha) {

        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {

            stmt = con.prepareStatement("SELECT * FROM usuario WHERE usuario = ? and senha = ?");
            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            if (rs.next()) {

                
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexao.closeConnection(con, stmt, rs);
        }

        return check;

    }
      public void  create(Usuario u){
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO usuario(usuario,senha)VALUES(?,?)");
            stmt.setString(1,u.getUsuario());
            stmt.setString(2,u.getSenha());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Salvo com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar:" + ex);
        }finally{
            conexao.closeConnection(con, stmt);
        }
    }
      
       public void  update(Usuario u){
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE usuario SET usuario= ? , senha = ?, WHERE id = ?");
            stmt.setString(1,u.getUsuario());
            stmt.setString(2,u.getSenha());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Salvo com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar:" + ex);
        }finally{
            conexao.closeConnection(con, stmt);
        }
    }
      public void delete(Usuario u) {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM usuario WHERE id = ?");
            stmt.setInt(1, u.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            conexao.closeConnection(con, stmt);
        }
    }
      public java.util.List <Usuario> read() {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        java.util.List <Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setUsuario(rs.getString(2));
                usuario.setSenha(rs.getString(3));
                usuarios.add(usuario);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           conexao.closeConnection(con, stmt, rs);
        }

        return usuarios;

    }
       public java.util.List <Usuario> readForUsuario(String usuario) {

        Connection con = conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        java.util.List <Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE nome LIKE ?");
            stmt.setString(1, "%"+usuario+"%");

            while (rs.next()) {

                Usuario usuario1 = new Usuario();

                usuario1.setId(rs.getInt("id"));
                usuario1.setUsuario(rs.getString(2));
                usuario1.setSenha(rs.getString(3));
                usuarios.add(usuario1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           conexao.closeConnection(con, stmt, rs);
        }

        return usuarios;

    }
}
