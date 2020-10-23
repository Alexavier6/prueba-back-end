package desaweb.tareaback.repositorys;

import desaweb.tareaback.entitys.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

}
