package co.edu.uniandes.dse.parcial1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import java.util.List;
import java.util.Date;


@Repository
public interface ConciertoRepository extends JpaRepository<ConciertoEntity, Long> {
    List<ConciertoEntity> findByFechaConcierto(Date fechaConcierto);
    List<ConciertoEntity> findByCapacidadMaxima(Integer capacidadMaxima);
    List<ConciertoEntity> findByPresupuesto(Long presupuesto);
}
