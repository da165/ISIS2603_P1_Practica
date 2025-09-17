package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Optional;

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
import co.edu.uniandes.dse.parcial1.repositories.ConciertoRepository;
import jakarta.transaction.Transactional;

@DataJpaTest
@Transactional
@Import(ConciertoService.class)
public class ConciertoServiceTest {
    @Autowired
    private ConciertoRepository conciertoRepository;
    @Autowired
    private ConciertoService conciertoService;
    @Autowired
    TestEntityManager entityManager;
    private ConciertoEntity conciertoEntity;
    private EstadioEntity estadio;
    @BeforeEach
    void setUp(){
        //Clear Data
        entityManager.getEntityManager().createQuery("delete from ConciertoEntity").executeUpdate();
        //Insert Data
        conciertoEntity= new ConciertoEntity();
        conciertoEntity.setCapacidadMaxima(50000);
        conciertoEntity.setEstadio(estadio);
        conciertoEntity.setFechaConcierto(LocalDateTime.now());
        conciertoEntity.setId(0L);
        conciertoEntity.setNombre("MTV Unplugged Musica de Fondo-Zoe");
        conciertoEntity.setPresupuesto(1000000L);
        entityManager.persist(conciertoEntity);
    }
    @Test
    void testCrearConcierto() throws IllegalOperationException, EntityNotFoundException{
        ConciertoEntity conciertoNuevo= new ConciertoEntity();
        conciertoNuevo.setCapacidadMaxima(75000);
        conciertoNuevo.setEstadio(estadio);
        conciertoNuevo.setFechaConcierto(LocalDateTime.now());
        conciertoNuevo.setId(0L);
        conciertoNuevo.setNombre("Live at Royal Hall-Artic Monkeys");
        conciertoNuevo.setPresupuesto(80000L);
        ConciertoEntity respuesta=conciertoService.crearConcierto(conciertoNuevo);
        assertNotNull(respuesta);
        assertEquals(conciertoNuevo, respuesta);
        Optional<ConciertoEntity> conciertoAlmacenado=conciertoRepository.findById(respuesta.getId());
        assertEquals(conciertoNuevo.getId(), conciertoAlmacenado.get().getId());
    }
    @Test
    void testCrearConciertoExistente(){
        ConciertoEntity nuevoConcierto= new ConciertoEntity();
        nuevoConcierto.setCapacidadMaxima(90000);
        nuevoConcierto.setEstadio(estadio);
        nuevoConcierto.setFechaConcierto(LocalDateTime.now());
        nuevoConcierto.setId(0L);
        nuevoConcierto.setNombre("Septimo Día-Soda Estereo");
        nuevoConcierto.setPresupuesto(85000L);
    }
}
