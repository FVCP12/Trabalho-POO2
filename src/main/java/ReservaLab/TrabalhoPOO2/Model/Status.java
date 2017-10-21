package ReservaLab.TrabalhoPOO2.Model;

import javax.persistence.*;

@Entity
@Table(name = "StatusLab")
public class Status {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long Cod_status;
    
    @Column
    String nomeStatus;
    
}
