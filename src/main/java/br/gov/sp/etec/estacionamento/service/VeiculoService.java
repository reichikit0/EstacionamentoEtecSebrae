package br.gov.sp.etec.estacionamento.service;

import br.gov.sp.etec.estacionamento.entity.VeiculoEntity;
import br.gov.sp.etec.estacionamento.model.Veiculo;

import java.util.List;

public interface VeiculoService {

    void cadastrarVeiculo(Veiculo v);

    List<Veiculo> listarVeiculos();

    boolean deletarVeiculo(Long id);

    List<VeiculoEntity> listaVeiculos();

    VeiculoEntity buscaVeiculoPorId(Long id);
}
