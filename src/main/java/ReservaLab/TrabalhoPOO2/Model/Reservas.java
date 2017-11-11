package ReservaLab.TrabalhoPOO2.Model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Reservas")
public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Cod_reservas;

    @Temporal(TemporalType.DATE)
    private Date dataReserva;
    
    public Boolean enabled;

    @ManyToOne
    private Laboratorio laboratorio;

    @ManyToMany
    @OrderColumn
    private StatusLab[] status = new StatusLab[6];//em ordem manha primeiro horario 1

    public Reservas(){
        for(int i = 0; i < this.status.length; i++){
            this.status[i] = new StatusLab();
        }
    }
    
    public long getCod_reservas() {
        return Cod_reservas;
    }

    /**
     * @param Cod_reservas the Cod_reservas to set
     */
    public void setCod_reservas(long Cod_reservas) {
        this.Cod_reservas = Cod_reservas;
    }

    /**
     * @return the dataReserva
     */
    public Date getDataReserva() {
        return dataReserva;
    }

    /**
     * @param dataReserva the dataReserva to set
     */
    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    /**
     * @return the laboratorio
     */
    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    /**
     * @param laboratorio the laboratorio to set
     */
    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    /**
     * @return the status
     */
    public StatusLab[] getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusLab[] status) {
        this.status = status;
    }

}
