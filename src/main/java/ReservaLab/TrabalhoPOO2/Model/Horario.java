package ReservaLab.TrabalhoPOO2.Model;

import javax.persistence.*;

@Entity
@Table(name = "Horario")
public class Horario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long cod_horario;
    
    @Column
    String nomeHorar;
    
    
    
}
