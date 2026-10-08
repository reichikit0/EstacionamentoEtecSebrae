package br.gov.sp.etec.estacionamento.controller;

import br.gov.sp.etec.estacionamento.entity.VeiculoEntity;
import br.gov.sp.etec.estacionamento.model.Veiculo;
import br.gov.sp.etec.estacionamento.service.VeiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController {
    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @GetMapping({"/cadastrar", "/cadastrar-veiculo"})
    public String formulario() {
        return "cadastrar-veiculo";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Veiculo v, Model m) {
        service.cadastrarVeiculo(v);
        m.addAttribute("mensagem", "Entrada do veículo registrada com sucesso!");
        return "efetuar";
    }

    @GetMapping("/registrar-saida")
    public String registrarSaida(Model model) {
        List<VeiculoEntity> veiculos = service.listaVeiculos();
        model.addAttribute("veiculos", veiculos);
        return "registrar-saida";
    }

    @GetMapping("/registro/{id}")
    public String registroVeiculo(@PathVariable Long id, Model model) {
        model.addAttribute("veiculo", service.buscaVeiculoPorId(id));
        return "registro-veiculo";
    }

    @PostMapping("/confirmar-saida")
    public String confirmarSaida(@RequestParam Long id, Model model) {
        VeiculoEntity veiculo = service.buscaVeiculoPorId(id);
        model.addAttribute("veiculo", veiculo);
        model.addAttribute("horaSaida", LocalDateTime.now());
        return "confirmar-saida";
    }

    @PostMapping("/saida")
    public String removerVeiculo(@RequestParam Long id, Model model) {
        VeiculoEntity veiculo = service.buscaVeiculoPorId(id);
        LocalDateTime horaSaida = LocalDateTime.now();

        if (!service.deletarVeiculo(id)) {
            model.addAttribute("mensagem", "Não foi possível registrar a saída do veículo.");
            return "erro";
        }

        model.addAttribute("veiculo", veiculo);
        model.addAttribute("horaSaida", horaSaida);
        return "saida-confirmada";
    }
}
