/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservaLab.TrabalhoPOO2.DAO;

import ReservaLab.TrabalhoPOO2.Model.Funcionarios;
import ReservaLab.TrabalhoPOO2.Model.Laboratorio;
import ReservaLab.TrabalhoPOO2.Model.Reservas;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author taynara
 */
@Repository
@Transactional
public class PorteiroDao extends Generica<Object>{

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }


   
    @SuppressWarnings("unchecked")
        public Laboratorio PegarTodos(){
           List<Laboratorio> func = getSession().createQuery("from Laboratorio" ).list();
        return null;
        }

}
