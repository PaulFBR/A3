package dao;

import model.Treino;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Classe responsável por todas as operações no banco de dados relacionadas a Treinos
public class TreinoDAO {

    // Insere um novo treino para um aluno
    public void cadastrarTreino(Treino treino) {
        String sql = "INSERT INTO treinos (aluno_id, tipo_treino, descricao, duracao_minutos, data_inicio) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, treino.getAlunoId());
            stmt.setString(2, treino.getTipoTreino());
            stmt.setString(3, treino.getDescricao());
            stmt.setInt(4, treino.getDuracaoMinutos());
            stmt.setString(5, treino.getDataInicio());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar treino: " + e.getMessage());
        }
    }

    // Lista todos os treinos de um aluno específico
    public List<Treino> listarTreinosDoAluno(int alunoId) {
        List<Treino> lista = new ArrayList<>();
        String sql = "SELECT * FROM treinos WHERE aluno_id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            // Cria objetos Treino e adiciona à lista
            while (rs.next()) {
                Treino treino = new Treino(
                    rs.getInt("id"),
                    rs.getInt("aluno_id"),
                    rs.getString("tipo_treino"),
                    rs.getString("descricao"),
                    rs.getInt("duracao_minutos"),
                    rs.getString("data_inicio")
                );
                lista.add(treino);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar treinos: " + e.getMessage());
        }

        return lista;
    }

    // Atualiza as informações de um treino
    public void atualizarTreino(Treino treino) {
        String sql = "UPDATE treinos SET tipo_treino=?, descricao=?, duracao_minutos=?, data_inicio=? WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, treino.getTipoTreino());
            stmt.setString(2, treino.getDescricao());
            stmt.setInt(3, treino.getDuracaoMinutos());
            stmt.setString(4, treino.getDataInicio());
            stmt.setInt(5, treino.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar treino: " + e.getMessage());
        }
    }

    // Exclui um treino do banco de dados
    public void excluirTreino(int id) {
        String sql = "DELETE FROM treinos WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir treino: " + e.getMessage());
        }
    }
}
