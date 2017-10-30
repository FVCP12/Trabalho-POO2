package ReservaLab.TrabalhoPOO2.Model;

import javax.persistence.*;

@Entity
@Table(name = "Laboratorio")
public class Laboratorio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cod_labor;
    
    @Column
    private String nomeLab;
    
    @Column
    private String andar;
    
    @Column
    private String descricao;

    /**
     * @return the cod_labor
     */
    public long getCod_labor() {
        return cod_labor;
    }

    /**
     * @param cod_labor the cod_labor to set
     */
    public void setCod_labor(long cod_labor) {
        this.cod_labor = cod_labor;
    }

    /**
     * @return the nomeLab
     */
    public String getNomeLab() {
        return nomeLab;
    }

    /**
     * @param nomeLab the nomeLab to set
     */
    public void setNomeLab(String nomeLab) {
        this.nomeLab = nomeLab;
    }

    /**
     * @return the andar
     */
    public String getAndar() {
        return andar;
    }

    /**
     * @param andar the andar to set
     */
    public void setAndar(String andar) {
        this.andar = andar;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
       
}
