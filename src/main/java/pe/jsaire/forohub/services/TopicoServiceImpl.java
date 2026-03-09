package pe.jsaire.forohub.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.jsaire.forohub.models.Topico;
import pe.jsaire.forohub.models.dto.TopicoDTO;
import pe.jsaire.forohub.repositories.TopicoRepository;
import pe.jsaire.forohub.utils.SortType;
import pe.jsaire.forohub.utils.exceptions.TopicoNotFoundException;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class TopicoServiceImpl implements ITopicoService {

    private final TopicoRepository repository;


    @Override
    @Transactional
    public Page<Topico> findAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest = null;

        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
        }
        return repository.findAll(pageRequest);
    }

    @Override
    public Topico findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TopicoNotFoundException("No existe este topico con id " + id));
    }

    @Override
    @Transactional
    public Topico save(TopicoDTO topico) {
        var topicoNuevo = new Topico(null
                ,topico.titulo()
                ,topico.mensaje()
                ,topico.fechaCreacion()
                ,topico.autor()
                ,topico.curso());

        return repository.save(topicoNuevo);
    }

    @Override
    @Transactional
    public Topico update(Long id, TopicoDTO topico) {

        var topicoUpdate = repository.findById(id).
                orElseThrow(() -> new TopicoNotFoundException("No existe este topico con id " + id));

        topicoUpdate.setAutor(topico.autor());
        topicoUpdate.setTitulo(topico.titulo());
        topicoUpdate.setCurso(topico.curso());
        topicoUpdate.setMensaje(topico.mensaje());
        return repository.save(topicoUpdate);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new TopicoNotFoundException("No existe este topico " + id);
        }
        repository.deleteById(id);
    }
}
