package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.empresa_parametrizacao.Empresa;
import com.quata.quatasafeguardbackend.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping(value = "get-empresa/{id}")
    public ResponseEntity<Empresa> getEmpresa(@PathVariable Long id) {
        Empresa empresa = empresaService.getEmpresaById(id);
        return ResponseEntity.ok().body(empresa);
    }

    @PostMapping(value = "create-empresa")
    public ResponseEntity<Object> createEmpresa(@RequestBody Empresa empresa) {
        Empresa empresa1 = empresaService.saveEmpresa(empresa);
        return ResponseEntity.ok().body(empresa1);
    }

    @PutMapping(value="update-empresa")
    public ResponseEntity<Object> updateEmpresa(@RequestBody Empresa empresa) {
        Empresa empresa1 = empresaService.atualizarEmpresa(empresa);
        return ResponseEntity.ok().body(empresa1);
    }

    @DeleteMapping(value = "delete-empresa/{cnpj}")
    public ResponseEntity<Object> deleteEmpresa(@PathVariable String cnpj) {
        if (empresaService.deleteEmpresa(cnpj)) {
            return ResponseEntity.ok().body("Empresa excluída com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Erro ao excluir empresa");
        }
    }
}
