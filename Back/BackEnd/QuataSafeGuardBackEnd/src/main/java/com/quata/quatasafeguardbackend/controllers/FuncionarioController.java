import com.quata.quatasafeguardbackend.entities.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // Criar novo funcionário
    @PostMapping
    public ResponseEntity<Funcionario> salvarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = funcionarioService.salvarFuncionario(funcionario);
        return ResponseEntity.ok(novoFuncionario);
    }

    // Buscar funcionário pelo ID
    @GetMapping("/{idFuncionario}")
    public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Integer idFuncionario) {
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(idFuncionario);
        return ResponseEntity.ok(funcionario);
    }

    // Atualizar informações do funcionário
    @PutMapping("/{idFuncionario}")
    public ResponseEntity<Funcionario> atualizarFuncionario(
            @PathVariable Integer idFuncionario,
            @RequestBody Funcionario funcionarioAtualizado) {
        Funcionario funcionarioAtualizadoResposta = funcionarioService.atualizarFuncionario(idFuncionario, funcionarioAtualizado);
        return ResponseEntity.ok(funcionarioAtualizadoResposta);
    }

    // Listar todos os funcionários
    @GetMapping
    public ResponseEntity<List<Funcionario>> listarTodosFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listarTodosFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }

    // Deletar funcionário pelo ID
    @DeleteMapping("/{idFuncionario}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Integer idFuncionario) {
        funcionarioService.deletarFuncionario(idFuncionario);
        return ResponseEntity.noContent().build();
    }
}
