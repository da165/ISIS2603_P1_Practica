package co.edu.uniandes.dse.parcial1.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.EstadioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class EstadioService {
    @Autowired
    private EstadioRepository estadioRepository;
    @Transactional
    public EstadioEntity crearEstadio(EstadioEntity nuevoEstadio) throws EntityNotFoundException, IllegalOperationException{
        log.info("Inicia el proceso de creacion de un Estadio");
        if(nuevoEstadio.getNombreCiudadEstadio().length()< 3){
            throw new IllegalOperationException("El nombre de la ciudad tiene menos de tres letras");
        }
        if(nuevoEstadio.getAforoMaximoEstadio() > 1000000){
            throw new IllegalOperationException("El aforo maximo del estadio se excedio del limite");
        }
        if(nuevoEstadio.getAforoMaximoEstadio()<1000){
            throw new IllegalOperationException("El aforo del estadio no es sufiente para crearlo");
        }
        if(nuevoEstadio.getPrecioAlquiler()<100000){
            throw new IllegalOperationException("El precio del alquiler no esta en los rangos minimos");
        }   
        log.info("Termina el proceso de creacion de un Estadio");     
        return estadioRepository.save(nuevoEstadio);
    }
}
