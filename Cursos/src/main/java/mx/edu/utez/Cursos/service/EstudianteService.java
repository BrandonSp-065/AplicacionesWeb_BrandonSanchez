package mx.edu.utez.Cursos.service;

import mx.edu.utez.Cursos.model.Estudiante;
import mx.edu.utez.Cursos.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {
    private EstudianteRepository repo;

    public EstudianteService(EstudianteRepository repo){
        this.repo=repo;
    }

    public List<Estudiante> getAll(){
        return repo.findAll();
    }

    public Optional<Estudiante> findById(Long id){
        return repo.findById(id);
    }

    public Estudiante save(Estudiante estudiante){
        return repo.save(estudiante);
    }

    public Optional<Estudiante> update(Long id, Estudiante estudiante){
        Optional<Estudiante> optional = repo.findById(id);

        if(optional.isEmpty()){
            return Optional.empty();
        }
        Estudiante estudianteDB = optional.get();

        estudianteDB.setNombre(estudiante.getNombre());
        estudianteDB.setMatricula(estudiante.getMatricula());
        estudianteDB.setSemestre(estudiante.getSemestre());

        return Optional.of(repo.save(estudianteDB));
    }

    public boolean delete(Long id){
        if (!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}