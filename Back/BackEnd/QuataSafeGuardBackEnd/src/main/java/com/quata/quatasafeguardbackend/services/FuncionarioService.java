import com.quata.quatasafeguardbackend.entities.Funcionario;
import com.quata.quatasafeguardbackend.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Criar novo funcionário
    public Funcionario salvarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    // Buscar funcionário pelo ID
    public Funcionario buscarFuncionarioPorId(Integer idFuncionario) {
        return funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado para o ID: " + idFuncionario));
    }

    // Atualizar informações do funcionário
    public Funcionario atualizarFuncionario(Integer idFuncionario, Funcionario funcionarioAtualizado) {
        Funcionario funcionarioExistente = buscarFuncionarioPorId(idFuncionario);

        funcionarioExistente.setNome(funcionarioAtualizado.getNome());
        funcionarioExistente.setCpf(funcionarioAtualizado.getCpf());
        funcionarioExistente.setCargo(funcionarioAtualizado.getCargo());
        funcionarioExistente.setSalario(funcionarioAtualizado.getSalario());
        funcionarioExistente.setPermissoes(funcionarioAtualizado.getPermissoes());
        funcionarioExistente.setLogin(funcionarioAtualizado.getLogin());
        funcionarioExistente.setSenha(funcionarioAtualizado.getSenha());

        return funcionarioRepository.save(funcionarioExistente);
    }

    // Listar todos os funcionários
    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    // Deletar funcionário pelo ID
    public void deletarFuncionario(Integer idFuncionario) {
        if (funcionarioRepository.existsById(idFuncionario)) {
            funcionarioRepository.deleteById(idFuncionario);
        } else {
            throw new RuntimeException("Funcionário não encontrado para o ID: " + idFuncionario);
        }
    }
}