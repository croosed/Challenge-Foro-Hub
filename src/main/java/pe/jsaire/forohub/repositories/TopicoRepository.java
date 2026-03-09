package pe.jsaire.forohub.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.jsaire.forohub.models.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
