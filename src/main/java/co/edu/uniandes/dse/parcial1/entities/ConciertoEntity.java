package co.edu.uniandes.dse.parcial1.entities;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ConciertoEntity extends BaseEntity {

    private String nombre;
    private Long presupuesto;
    private LocalDateTime fechaConcierto;
    private Integer capacidadMaxima;
    //Relacion de muchos conciertos a un solo estadio
    @ManyToOne
    private EstadioEntity estadio;
}
