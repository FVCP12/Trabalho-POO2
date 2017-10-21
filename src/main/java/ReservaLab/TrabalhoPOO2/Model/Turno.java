package ReservaLab.TrabalhoPOO2.Model;

import javax.persistence.*;

@Entity
@Table(name = "Turno")
public class Turno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long cod_turno;
    
    @Column
    String nomeTurno;
    
}
