package co.edu.uniandes.dse.parcial1.entities;
import java.util.*;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class EstadioEntity extends BaseEntity {

    private String nombre;
    private Long precioAlquiler;
    private String nombreCiudadEstadio;
    private Integer aforoMaximoEstadio;
    //Relacion de un estadio que tiene muchos conciertos
    @PodamExclude
    @OneToMany(mappedBy = "Estadio")
    private List<ConciertoEntity> conciertos= new ArrayList<>();
}
