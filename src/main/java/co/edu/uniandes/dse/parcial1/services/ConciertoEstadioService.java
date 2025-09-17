package co.edu.uniandes.dse.parcial1.services;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.EstadioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConciertoEstadioService {
    @Autowired
    private EstadioRepository estadioRepository;    
    @Transactional
    public EstadioEntity agregarConciertoaEstadio(ConciertoEntity concierto, EstadioEntity estadio, ConciertoEntity concierto2)throws IllegalOperationException, EntityNotFoundException{
        log.info("Inicia el proceso de agregar un concierto a un estadio");
        if(estadio.getAforoMaximoEstadio()!=concierto.getCapacidadMaxima()){
            throw new IllegalOperationException("Los aforos no son compatibles");
        }
        if(estadio.getPrecioAlquiler()!=concierto.getPresupuesto()){
            throw new IllegalOperationException("El presupuesto es incongruente");
        }
        Duration duracionEntreConciertos=Duration.between(concierto2.getFechaConcierto(), concierto.getFechaConcierto());
        Duration diasDiferencia= Duration.ofDays(2);
        if(duracionEntreConciertos.compareTo(diasDiferencia)<2){
            throw new IllegalOperationException("No se cumple los dias de diferencia");
        }
        return estadioRepository.save(estadio);
    }

}
