package mx.edu.utez.Cursos.repository;

import mx.edu.utez.Cursos.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}