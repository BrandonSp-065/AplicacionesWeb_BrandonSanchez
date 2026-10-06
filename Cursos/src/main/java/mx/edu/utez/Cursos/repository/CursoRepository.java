package mx.edu.utez.Cursos.repository;

import mx.edu.utez.Cursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}