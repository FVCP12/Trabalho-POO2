package ReservaLab.TrabalhoPOO2.Model;

import javax.persistence.*;

@Entity
@Table(name = "Chaves")
public class Chaves {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long Cod_chaves;
    
    @Column
    String descricaoChave;
    
    
}
