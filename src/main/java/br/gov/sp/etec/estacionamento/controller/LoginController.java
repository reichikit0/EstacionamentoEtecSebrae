package br.gov.sp.etec.estacionamento.controller;

import br.gov.sp.etec.estacionamento.model.Usuario;
import br.gov.sp.etec.estacionamento.service.UsuarioService;
import br.gov.sp.etec.estacionamento.service.VeiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;
    private final VeiculoService veiculoService;

    public LoginController(UsuarioService u, VeiculoService v) {
        usuarioService = u;
        veiculoService = v;
    }

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @PostMapping("/efetuar_cadastro")
    public String efetuarCadastro(
            Usuario u,
            @RequestParam(required = false) String confirmarSenha,
            Model m) {
        if (u.getSenha() == null || !u.getSenha().equals(confirmarSenha)) {
            m.addAttribute("mensagem", "As senhas não coincidem.");
            return "erro";
        }

        if (usuarioService.buscarUsuarioPorEmail(u.getEmail()) != null) {
            m.addAttribute("mensagem", "Já existe uma conta com esse e-mail.");
            return "erro";
        }

        usuarioService.cadastrarUsuario(u);
        m.addAttribute("mensagem", "Cadastro realizado com sucesso!");
        return "efetuar";
    }

    @PostMapping("/autenticar")
    public String autenticar(
            @RequestParam String email,
            @RequestParam String senha,
            Model m) {
        Usuario u = usuarioService.buscarUsuarioPorEmail(email);

        if (u == null || u.getSenha() == null || !u.getSenha().equals(senha)) {
            m.addAttribute("mensagem", "E-mail ou senha inválidos.");
            return "erro";
        }

        m.addAttribute("nome", u.getNome());
        m.addAttribute("veiculos", veiculoService.listarVeiculos());
        return "painel";
    }

    @GetMapping("/painel")
    public String painel(Model m) {
        m.addAttribute("veiculos", veiculoService.listarVeiculos());
        return "painel";
    }
}
