package br.gov.sp.etec.estacionamento.model;

import java.time.LocalDateTime;

public class Veiculo {

    private Long id;
    private String placa;
    private String modelo;
    private String cor;
    private String observacoes;
    private LocalDateTime horaEntrada;

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        id = v;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String v) {
        placa = v;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String v) {
        modelo = v;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String v) {
        cor = v;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String v) {
        observacoes = v;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime v) {
        horaEntrada = v;
    }
}
