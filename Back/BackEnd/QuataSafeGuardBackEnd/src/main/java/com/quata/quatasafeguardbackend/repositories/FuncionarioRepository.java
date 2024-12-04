package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}