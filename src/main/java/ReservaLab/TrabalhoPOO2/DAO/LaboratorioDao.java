package ReservaLab.TrabalhoPOO2.DAO;

import java.util.*;
import ReservaLab.TrabalhoPOO2.Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LaboratorioDao extends Generica<Laboratorio>{

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @SuppressWarnings("unchecked")
        public Laboratorio pegarpordata(Date data){
        List<Laboratorio> Pegarpordata=getSession().createQuery(" from Laboratorio a Reservas b where not existis( select b.laboratorio where b.datareserva =:data)").setParameter("data",data).list();
        return Pegarpordata.get(0);
        }
        
}
