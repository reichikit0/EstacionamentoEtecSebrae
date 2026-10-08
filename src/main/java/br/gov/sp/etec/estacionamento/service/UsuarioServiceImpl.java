package br.gov.sp.etec.estacionamento.service;

import br.gov.sp.etec.estacionamento.entity.UsuarioEntity;
import br.gov.sp.etec.estacionamento.model.Usuario;
import br.gov.sp.etec.estacionamento.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    public String cadastrarUsuario(Usuario u) {
        UsuarioEntity e = new UsuarioEntity();
        e.setNome(u.getNome().trim());
        e.setCpf(u.getCpf().trim());
        e.setEmail(u.getEmail().trim().toLowerCase());
        e.setTelefone(u.getTelefone().trim());
        e.setDataDeNascimento(u.getDataDeNascimento());
        e.setSenha(u.getSenha());
        repository.save(e);
        return "Usuário cadastrado com sucesso!";
    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll().stream().map(this::toUsuario).toList();
    }

    public String atualizarUsuario(Usuario u) {
        if (u.getId() == null || !repository.existsById(u.getId())) {
            return "Usuário não encontrado.";
        }

        UsuarioEntity e = repository.findById(u.getId()).orElseThrow();
        e.setNome(u.getNome());
        e.setCpf(u.getCpf());
        e.setEmail(u.getEmail());
        e.setTelefone(u.getTelefone());
        e.setDataDeNascimento(u.getDataDeNascimento());
        e.setSenha(u.getSenha());
        repository.save(e);
        return "Usuário atualizado com sucesso!";
    }

    public String deletarUsuario(Long id) {
        if (!repository.existsById(id)) {
            return "Usuário não encontrado.";
        }

        repository.deleteById(id);
        return "Usuário deletado com sucesso!";
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        if (email == null || email.isBlank()) {
            return null;
        }

        UsuarioEntity e = repository.findByEmail(email.trim().toLowerCase());
        return e == null ? null : toUsuario(e);
    }

    private Usuario toUsuario(UsuarioEntity e) {
        Usuario u = new Usuario();
        u.setId(e.getId());
        u.setNome(e.getNome());
        u.setCpf(e.getCpf());
        u.setEmail(e.getEmail());
        u.setTelefone(e.getTelefone());
        u.setDataDeNascimento(e.getDataDeNascimento());
        u.setSenha(e.getSenha());
        return u;
    }
}
