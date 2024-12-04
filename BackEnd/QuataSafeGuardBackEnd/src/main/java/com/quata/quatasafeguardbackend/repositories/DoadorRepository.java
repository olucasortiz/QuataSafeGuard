<<<<<<< HEAD
package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;



@Repository
public interface DoadorRepository extends JpaRepository<Doador, Integer> {
    Optional<Doador> findByCpf(String cpf);
}
=======
package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long> {
    Doador findByCpf(String cpf);
}
>>>>>>> branch-ortiz
