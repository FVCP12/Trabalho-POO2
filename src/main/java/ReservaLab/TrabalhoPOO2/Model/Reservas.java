package ReservaLab.TrabalhoPOO2.Model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Reservas")
public class Reservas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long Cod_reservas;
    
    @Temporal(TemporalType.DATE)
    Date dataReserva;
    
    @Temporal(TemporalType.DATE)
    Date dataOperacao;//a data em que foi feita a reserva (data + hora)
    
    @ManyToOne
    Laboratorio laboratorio;
    
    @OneToOne
    Status statusHoraLabs;
 
    @OneToOne
    Chaves descricaoChave;
    
}
