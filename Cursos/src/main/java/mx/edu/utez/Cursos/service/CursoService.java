package mx.edu.utez.Cursos.service;

import mx.edu.utez.Cursos.model.Curso;
import mx.edu.utez.Cursos.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    private CursoRepository repo;

    public CursoService(CursoRepository repo){
        this.repo=repo;
    }

    public List<Curso> getAll(){
        return repo.findAll();
    }

    public Optional<Curso> findById(Long id){
        return repo.findById(id);
    }

    public Curso save(Curso curso){
        return repo.save(curso);
    }

    public Optional<Curso> update(Long id, Curso curso){
        Optional<Curso> optional = repo.findById(id);

        if(optional.isEmpty()){
            return Optional.empty();
        }
        Curso cursoDB = optional.get();

        cursoDB.setNombre(curso.getNombre());
        cursoDB.setDescripcion(curso.getDescripcion());
        cursoDB.setCreditos(curso.getCreditos());

        return Optional.of(repo.save(cursoDB));
    }

    public boolean delete(Long id){
        if (!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}