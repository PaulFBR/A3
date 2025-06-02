package model;

// Classe que representa um Treino na academia
public class Treino {
    private int id;
    private int alunoId;          // ID do aluno dono do treino
    private String tipoTreino;    // Ex: Musculação, Aeróbico
    private String descricao;     // Detalhes do treino
    private int duracaoMinutos;   // Duração do treino
    private String dataInicio;    // Data de início

    // Construtor padrão
    public Treino() {}

    // Construtor com parâmetros
    public Treino(int id, int alunoId, String tipoTreino, String descricao, int duracaoMinutos, String dataInicio) {
        this.id = id;
        this.alunoId = alunoId;
        this.tipoTreino = tipoTreino;
        this.descricao = descricao;
        this.duracaoMinutos = duracaoMinutos;
        this.dataInicio = dataInicio;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAlunoId() { return alunoId; }
    public void setAlunoId(int alunoId) { this.alunoId = alunoId; }

    public String getTipoTreino() { return tipoTreino; }
    public void setTipoTreino(String tipoTreino) { this.tipoTreino = tipoTreino; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(int duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }

    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }
}
