 package ReservaLab.TrabalhoPOO2.DAO;

import java.util.*;
import ReservaLab.TrabalhoPOO2.Model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ReservasDao extends Generica<Reservas> {

    
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @SuppressWarnings("unchecked")
    public boolean verificarExiste(Laboratorio l,Date d){
        
        List<Laboratorio> labs =getSession().createQuery(" from Reservas r where r.laboratorio = :l and "
                + "dataReserva = :d").setParameter("l", l).setParameter("d", d).list();
        
        boolean resultado = false;
        
        if(labs.isEmpty()){
            resultado = true;
        }
        
        return resultado;
    }
    
    @SuppressWarnings("unchecked")
    public List<Reservas> buscaPorLaboratorio(Laboratorio l){
        
        List<Reservas> labs = getSession().createQuery(" from Reservas r where r.laboratorio = :l ").
                setParameter("l", l).list();
        
        return labs;
    }
    
    
    @SuppressWarnings("unchecked")
    public List<Reservas> buscaPorMes(Date inicio,Date fim){
    
        List<Reservas> reservas = getSession().createQuery("from Reservas r "
                + " where "
                + "r.dataReserva >= :inicio and r.dataReserva < :fim order by r.laboratorio,r.dataReserva").
                setParameter("inicio",inicio).setParameter("fim", fim).list();
        
        //left join fetch r.status s
      return reservas;
    }
    
        @SuppressWarnings("unchecked")
    public List<Reservas> buscaPorDia(Date dia){
    
        List<Reservas> reservas = getSession().createQuery("from Reservas r "
                + " where "
                + "r.dataReserva =:dia order by r.laboratorio,r.dataReserva").
                setParameter("dia",dia).list();
        
      return reservas;
    }
    
     @SuppressWarnings("unchecked")
    public List<Reservas> buscaPorLab_Periodo(Laboratorio lab,Date inicio,Date fim){
    
        List<Reservas> reservas = getSession().createQuery("from Reservas r "
                + " where "
                + "r.dataReserva >= :inicio and r.dataReserva <= :fim and r.laboratorio = :lab order by r.laboratorio,r.dataReserva").
                setParameter("inicio",inicio).setParameter("fim", fim).setParameter("lab", lab).list();
        
        //left join fetch r.status s
      return reservas;
    }
    
    
   /* @SuppressWarnings("unchecked")
    public List<Reservas> buscaPorMeseprof(Date inicio,Date fim, Funcionarios prof){
    
       
        List<Reservas> reservas = getSession().createQuery("from Reservas r "
                + " where "
                + "r.dataReserva >= :inicio and r.dataReserva < :fim and (r.status[0].professor=:prof or r.status[1].professor=:prof or r.status[2].professor=:prof or r.status[3].professor=:prof or r.status[4].professor=:prof or r.status[5].professor=:prof) order by r.laboratorio,r.dataReserva)").setParameter("inicio",inicio).setParameter("fim", fim).setParameter("prof", prof).list();
        
        //left join fetch r.status s
      return reservas;
    }*/
 
    
}
