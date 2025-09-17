package co.edu.uniandes.dse.parcial1.services;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.ConciertoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConciertoService {

    @Autowired
    private ConciertoRepository conciertoRepository;
    @Transactional
    public ConciertoEntity crearConcierto(ConciertoEntity nuevoConcierto) throws EntityNotFoundException, IllegalOperationException{
        log.info("Inicia el proceso de crear un Concierto");
        LocalDateTime fechaActual=LocalDateTime.now();
        if(nuevoConcierto.getFechaConcierto().isBefore(fechaActual)){
            throw new IllegalOperationException("La fecha es imposible de asignar");
        }
        if(nuevoConcierto.getCapacidadMaxima()<10){
            throw new IllegalOperationException("La capacidad no es suficiente para el concierto");
        }
        if(nuevoConcierto.getPresupuesto()<1000){
            throw new IllegalOperationException("El presupuesto es insuficiente");
        }
        log.info("Termina el proceso de crear un concierto");
        return conciertoRepository.save(nuevoConcierto);
    }
}
