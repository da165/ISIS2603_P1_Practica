package co.edu.uniandes.dse.parcial1.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.EstadioRepository;
import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;    
import java.util.*;

@DataJpaTest
@Transactional
@Import(EstadioEntity.class)
public class EstadioServiceTest {
    @Autowired
    private EstadioRepository estadioRepository;
    @Autowired
    private EstadioService estadioService;
    @Autowired
    private TestEntityManager entityManager;
    private EstadioEntity estadioEntity;
    private List<ConciertoEntity> listaConciertos;
    @BeforeEach
    void setUp(){     
        //Clear Data
        entityManager.getEntityManager().createQuery("delete from EstadioEntity").executeUpdate();
        //Insert Data
        estadioEntity= new EstadioEntity();
        estadioEntity.setAforoMaximoEstadio(85000);
        estadioEntity.setConciertos(listaConciertos);
        estadioEntity.setId(0L);
        estadioEntity.setNombre("Allianz Arena");
        estadioEntity.setNombreCiudadEstadio("Munich");
        estadioEntity.setPrecioAlquiler(100000L);
        entityManager.persist(estadioEntity);
    }
    
    @Test
    void testCrearEstadio() throws IllegalOperationException, EntityNotFoundException{
        EstadioEntity nuevoEstadio= new EstadioEntity();
        nuevoEstadio.setNombre("Sanchez Pizjuan");
        nuevoEstadio.setAforoMaximoEstadio(50000);
        nuevoEstadio.setNombreCiudadEstadio("Sevilla");
        nuevoEstadio.setPrecioAlquiler(75000L);
        nuevoEstadio.setConciertos(listaConciertos);
        nuevoEstadio.setId(0L);//contador que aumnetara el id
        EstadioEntity respuesta=estadioService.crearEstadio(nuevoEstadio);
        assertNotNull(respuesta);
        assertEquals(nuevoEstadio.getId(), respuesta.getId());
        assertEquals(nuevoEstadio.getAforoMaximoEstadio(), respuesta.getAforoMaximoEstadio());
        assertEquals(nuevoEstadio.getConciertos(), respuesta.getConciertos());
        assertEquals(nuevoEstadio.getNombre(), respuesta.getNombre());
        assertEquals(nuevoEstadio.getNombreCiudadEstadio(), respuesta.getNombreCiudadEstadio());
        assertEquals(nuevoEstadio.getPrecioAlquiler(), respuesta.getPrecioAlquiler());
        Optional<EstadioEntity> estadioAlmacenado= estadioRepository.findById(respuesta.getId());
        assertEquals(nuevoEstadio.getId(), estadioAlmacenado.get().getId());
    }
    @Test
    void testCrearEstadioExistente(){
        EstadioEntity nuevoEstadio= new EstadioEntity();
        nuevoEstadio.setNombre("Santiago Bernabeu");
        nuevoEstadio.setAforoMaximoEstadio(90000);
        nuevoEstadio.setConciertos(listaConciertos);
        nuevoEstadio.setNombreCiudadEstadio("Madrid");
        nuevoEstadio.setPrecioAlquiler(7500000L);
        nuevoEstadio.setId(0L);
        assertThrows(IllegalOperationException.class,()->{
            estadioService.crearEstadio(nuevoEstadio);
        } );
    }
}
