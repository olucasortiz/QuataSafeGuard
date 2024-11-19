package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
