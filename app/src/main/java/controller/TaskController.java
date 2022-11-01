/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author iago
 */
public class TaskController {
    
    public void save(Task task) {
        String sql = "INSERT INTO tasks(idProject, name, description, notes, deadline, completed, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, task.getIdProject());
            stmt.setString(2, task.getName());
            stmt.setString(3, task.getDescription());
            stmt.setString(4, task.getNotes());
            stmt.setDate(5, new Date(task.getDeadline().getTime()));
            stmt.setBoolean(6, task.isCompleted());
            stmt.setDate(7, new Date(task.getCreatedAt().getTime()));
            stmt.setDate(8, new Date(task.getUpdatedAt().getTime()));

            //Executa a sql para inserção dos dados
            stmt.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa! " + ex.getMessage(), ex);
        } finally {
            //Fecha as conexões
           ConnectionFactory.closeConnection(conn, stmt);
        }

    }

    public void update(Task task) {
        String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?,"
                + " notes = ?, deadline = ?, completed = ?, createdAt = ?,"
                + " updatedAt = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            stmt = conn.prepareStatement(sql);

            //Setando valores da statement
            stmt.setInt     (1, task.getIdProject());
            stmt.setString  (2, task.getName());
            stmt.setString  (3, task.getDescription());
            stmt.setString  (4, task.getNotes());
            stmt.setDate    (5, new Date(task.getDeadline().getTime()));
            stmt.setBoolean (6, task.isCompleted());
            stmt.setDate    (7, new Date(task.getCreatedAt().getTime()));
            stmt.setDate    (8, new Date(task.getUpdatedAt().getTime()));
            stmt.setInt     (9, task.getId());

            //Executa a query
            stmt.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa", ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }

    public List<Task> getAll(int idProject) {
        
        String sql = "SELECT * FROM tasks WHERE idProject = ?";        

        Connection conn = null;
        PreparedStatement stmt = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;
        
        List<Task> tasks = new ArrayList<Task>();

        try {
            //Criação da conexão
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            
            //Setando o valor que corresponde ao filtro de busca
            stmt.setInt(1, idProject);

            //Valor retoranado pela execução da query
            rset = stmt.executeQuery();

            //Enquanto houverem valores a serem percorrido no meu rset
            while (rset.next()) {

                Task task = new Task();

                task.setId(rset.getInt("id"));
                task.setIdProject(rset.getInt("idProject"));
                task.setName(rset.getString("name"));
                task.setDescription(rset.getString("description"));
                task.setNotes(rset.getString("notes"));
                task.setDeadline(rset.getDate("deadline"));
                task.setIsCompleted(rset.getBoolean("completed"));
                task.setCreatedAt(rset.getDate("createdAt"));
                task.setCreatedAt(rset.getDate("updatedAt"));

               
                tasks.add(task);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir tarefas", ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rset);
        }
        //Lista de tarefas que criada e carregada do banco de dados
        return tasks;
    }

    public void removeById(int id) {

        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //Criação da conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            //Preparando a query
            stmt = conn.prepareStatement(sql);
            
            //Setando os valores
            stmt.setInt(1, id);
            
            //Executando a query
            stmt.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar a tarefa" + ex.getMessage(),ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
            
        }

    }
    
}
