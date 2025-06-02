package model;

// Classe que representa um Aluno na academia
public class Aluno {
    private int id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String telefone;
    private String email;

    // Construtor padrão (sem parâmetros)
    public Aluno() {}

    // Construtor com parâmetros (usado ao buscar no banco)
    public Aluno(int id, String nome, String cpf, String dataNascimento, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
    }

    // Métodos getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
