package sapc.sapcbackend.db.entities.empresa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "parametrizacao")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "site")
    private String site;

    @Column(name = "email")
    private String email;

    @Column(name = "cnpj", unique = true)
    private String cnpj;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "uf")
    private String uf;

    @Column(name = "cep")
    private String cep;

    @Column(name = "tel")
    private String telefone;

    @Column(name = "logo_grande")
    private String logoGrande;

    @Column(name = "logo_pequeno")
    private String logoPequeno;

    @Column(name = "data_criacao", nullable = true)
    private LocalDateTime dataCriacao;
}
