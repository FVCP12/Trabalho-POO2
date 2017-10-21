package ReservaLab.TrabalhoPOO2.Model;

import javax.persistence.*;

@Entity
@Table(name = "TipoLaboratorio")
public class TipoLab {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long cod_TipoLab;
    
    @Column
    String nomeTipLab;
    
    
}
