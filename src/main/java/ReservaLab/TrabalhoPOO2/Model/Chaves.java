package ReservaLab.TrabalhoPOO2.Model;

import javax.persistence.*;

@Entity
@Table(name = "Chaves")
public class Chaves {//deve ser preenchida antes de tudo com 1 para chave na portaria e 2 para chave com o professor
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Cod_chaves;
    
    @Column
    private String descriChave;

    /**
     * @return the Cod_chaves
     */
    public long getCod_chaves() {
        return Cod_chaves;
    }

    /**
     * @param Cod_chaves the Cod_chaves to set
     */
    public void setCod_chaves(long Cod_chaves) {
        this.Cod_chaves = Cod_chaves;
    }

    /**
     * @return the descriChave
     */
    public String getDescriChave() {
        return descriChave;
    }

    /**
     * @param descriChave the descriChave to set
     */
    public void setDescriChave(String descriChave) {
        this.descriChave = descriChave;
    }
    
    
}
