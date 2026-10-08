package br.gov.sp.etec.estacionamento.service;

import br.gov.sp.etec.estacionamento.entity.VeiculoEntity;
import br.gov.sp.etec.estacionamento.model.Veiculo;
import br.gov.sp.etec.estacionamento.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoServiceImpl(VeiculoRepository repository) {
        this.repository = repository;
    }

    public void cadastrarVeiculo(Veiculo v) {
        VeiculoEntity e = new VeiculoEntity();
        e.setPlaca(v.getPlaca().trim().toUpperCase());
        e.setModelo(v.getModelo().trim());
        e.setCor(v.getCor().trim());
        e.setObservacoes(v.getObservacoes() == null ? "" : v.getObservacoes().trim());
        e.setHoraEntrada(LocalDateTime.now());
        repository.save(e);
    }

    public List<Veiculo> listarVeiculos() {
        return repository.findAll().stream().map(this::toVeiculo).toList();
    }

    public boolean deletarVeiculo(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public List<VeiculoEntity> listaVeiculos() {
        return repository.findAll();
    }

    @Override
    public VeiculoEntity buscaVeiculoPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    private Veiculo toVeiculo(VeiculoEntity e) {
        Veiculo v = new Veiculo();
        v.setId(e.getId());
        v.setPlaca(e.getPlaca());
        v.setModelo(e.getModelo());
        v.setCor(e.getCor());
        v.setObservacoes(e.getObservacoes());
        v.setHoraEntrada(e.getHoraEntrada());
        return v;
    }
}
