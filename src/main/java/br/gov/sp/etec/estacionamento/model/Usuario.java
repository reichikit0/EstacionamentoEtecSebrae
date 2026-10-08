package br.gov.sp.etec.estacionamento.model;

import java.time.LocalDate;

public class Usuario {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
    private LocalDate dataDeNascimento;

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        id = v;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String v) {
        nome = v;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String v) {
        cpf = v;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String v) {
        email = v;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String v) {
        telefone = v;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate v) {
        dataDeNascimento = v;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String v) {
        senha = v;
    }
}
