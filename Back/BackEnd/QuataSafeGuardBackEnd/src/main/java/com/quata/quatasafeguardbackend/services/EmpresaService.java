package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.dto.empresa.DetalhesEmpresaDTO;
import com.quata.quatasafeguardbackend.dto.empresa.VerificaParametrizacaoDTO;
import com.quata.quatasafeguardbackend.entities.empresa_parametrizacao.Empresa;
import com.quata.quatasafeguardbackend.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa getEmpresaById(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("A empresa não foi encontrada"));
    }

    public Optional<Empresa> getEmpresaByCnpj(String cnpj) {
        return empresaRepository.findByCnpj(cnpj);
    }

    public Empresa saveEmpresa(Empresa empresa) {
        if (empresaRepository.existsAny()) {
            throw new IllegalStateException("A parametrização já foi realizada.");
        }
        if (!Empresa.isCNPJ(empresa.getCnpj())) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }
        Optional<Empresa> empresaExistente = getEmpresaByCnpj(empresa.getCnpj());
        if (empresaExistente.isPresent()) {
            throw new IllegalStateException("Uma empresa com este CNPJ já existe: " + Empresa.imprimeCNPJ(empresa.getCnpj()));
        }
        empresa.setDataCriacao(LocalDateTime.now());
        return empresaRepository.save(empresa);
    }

    public boolean deleteEmpresa(String cnpj) {
        Optional<Empresa> empresa = getEmpresaByCnpj(cnpj);

        if (empresa.isEmpty()) {
            return false;
        }

        try {
            empresaRepository.deleteById(empresa.get().getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Empresa atualizarEmpresa(Empresa empresa) {
        if (!Empresa.isCNPJ(empresa.getCnpj())) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }

        Empresa empresaExistente = empresaRepository.findByCnpj(empresa.getCnpj())
                .orElseThrow(() -> new NoSuchElementException("A empresa não foi encontrada."));

        empresaExistente.setRazaoSocial(empresa.getRazaoSocial());
        empresaExistente.setNomeFantasia(empresa.getNomeFantasia());
        empresaExistente.setCnpj(empresa.getCnpj());
        empresaExistente.setLogoPequeno(empresa.getLogoPequeno());
        empresaExistente.setLogoGrande(empresa.getLogoGrande());
        empresaExistente.setEndereco(empresa.getEndereco());
        empresaExistente.setBairro(empresa.getBairro());
        empresaExistente.setCidade(empresa.getCidade());
        empresaExistente.setUf(empresa.getUf());
        empresaExistente.setTelefone(empresa.getTelefone());
        empresaExistente.setSite(empresa.getSite());

        return empresaRepository.save(empresaExistente);
    }

    public DetalhesEmpresaDTO getDetalhesEmpresa() {
        Empresa empresa = empresaRepository.findFirstByOrderByIdDesc();
        if (empresa == null) {
            throw new NoSuchElementException("Nenhuma empresa encontrada.");
        }

        return new DetalhesEmpresaDTO(
                empresa.getId(),
                empresa.getNomeFantasia(),
                empresa.getRazaoSocial(),
                empresa.getSite(),
                empresa.getEmail(),
                empresa.getCnpj(),
                empresa.getEndereco(),
                empresa.getBairro(),
                empresa.getCidade(),
                empresa.getUf(),
                empresa.getCep(),
                empresa.getTelefone(),
                empresa.getLogoGrande(),
                empresa.getLogoPequeno(),
                empresa.getDataCriacao() != null ? empresa.getDataCriacao().toString() : null
        );
    }


    public VerificaParametrizacaoDTO verificarParametrizacao() {
        boolean existe = empresaRepository.existsAny();
        return new VerificaParametrizacaoDTO(existe);
    }
}