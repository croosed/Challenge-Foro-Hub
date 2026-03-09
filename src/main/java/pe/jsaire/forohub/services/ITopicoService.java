package pe.jsaire.forohub.services;

import org.springframework.data.domain.Page;
import pe.jsaire.forohub.models.Topico;
import pe.jsaire.forohub.models.dto.TopicoDTO;
import pe.jsaire.forohub.utils.SortType;

public interface ITopicoService {

    String FIELD_BY_SORT = "fechaCreacion";

    Page<Topico> findAll(Integer page, Integer size, SortType sortType);

    Topico findById(Long id);


    Topico save(TopicoDTO topico);

    Topico update(Long id, TopicoDTO topico);


    void deleteById(Long id);

}
