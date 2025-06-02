package dao;

import model.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Classe responsável por todas as operações no banco de dados relacionadas a Alunos
public class AlunoDAO {

    // Insere um novo aluno no banco de dados
    public void cadastrarAluno(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, cpf, data_nascimento, telefone, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getDataNascimento());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getEmail());

            stmt.executeUpdate();  // Executa o INSERT
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    // Retorna uma lista com todos os alunos cadastrados
    public List<Aluno> listarAlunos() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alunos";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Enquanto houver resultados, cria objetos Aluno e adiciona à lista
            while (rs.next()) {
                Aluno aluno = new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("data_nascimento"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
                lista.add(aluno);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }

        return lista;
    }

    // Busca um aluno específico pelo CPF
    public Aluno buscarPorCPF(String cpf) {
        String sql = "SELECT * FROM alunos WHERE cpf = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("data_nascimento"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
        }

        return null;
    }

    // Atualiza as informações de um aluno existente
    public void atualizarAluno(Aluno aluno) {
        String sql = "UPDATE alunos SET nome=?, cpf=?, data_nascimento=?, telefone=?, email=? WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getDataNascimento());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getEmail());
            stmt.setInt(6, aluno.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    // Exclui um aluno do banco de dados
    public void excluirAluno(int id) {
        String sql = "DELETE FROM alunos WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir aluno: " + e.getMessage());
        }
    }
}
