package co.edu.uniandes.dse.parcial1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import java.util.List;


@Repository
public interface EstadioRepository extends JpaRepository<EstadioEntity, Long> {
    List<EstadioEntity> findByNombreCiudadEstadio(String nombreCiudadEstadio);
    List<EstadioEntity> findByAforoMaximoEstadio(Integer aforoMaximoEstadio);
    List<EstadioEntity> findByPrecioAlquiler(Long precioAlquiler);
}
