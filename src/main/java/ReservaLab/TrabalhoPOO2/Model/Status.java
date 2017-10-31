package ReservaLab.TrabalhoPOO2.Model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "StatusLab")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Cod_status;
    
    @Column
    private boolean situação; //0 para disponivel \ 1 para reservado

    @ManyToOne
    private Funcionarios professor;

    @ManyToOne
    private Chaves descricaoChave;

    @Temporal(TemporalType.DATE)
    private Date dataOperacao;//a data em que foi feita a reserva (data + hora)

    @Column
    private String horario;

    /**
     * @return the Cod_status
     */
    public long getCod_status() {
        return Cod_status;
    }

    /**
     * @param Cod_status the Cod_status to set
     */
    public void setCod_status(long Cod_status) {
        this.Cod_status = Cod_status;
    }

    /**
     * @return the professor
     */
    public Funcionarios getProfessor() {
        return professor;
    }

    /**
     * @param professor the professor to set
     */
    public void setProfessor(Funcionarios professor) {
        this.professor = professor;
    }

    /**
     * @return the descricaoChave
     */
    public Chaves getDescricaoChave() {
        return descricaoChave;
    }

    /**
     * @param descricaoChave the descricaoChave to set
     */
    public void setDescricaoChave(Chaves descricaoChave) {
        this.descricaoChave = descricaoChave;
    }

    /**
     * @return the dataOperacao
     */
    public Date getDataOperacao() {
        return dataOperacao;
    }

    /**
     * @param dataOperacao the dataOperacao to set
     */
    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    /**
     * @return the horario
     */
    public String getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * @return the situação
     */
    public boolean isSituação() {
        return situação;
    }

    /**
     * @param situação the situação to set
     */
    public void setSituação(boolean situação) {
        this.situação = situação;
    }
}
