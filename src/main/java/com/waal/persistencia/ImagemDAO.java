package com.waal.persistencia;

import com.waal.beans.Imagem;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @Autor Winder Rezende
 * @Data 14/05/2019
 */
public class ImagemDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected static Session session;

    public static void save(Imagem f) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();
            session.save(f);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Imagem> listByProdutos(int ID) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            return session.createCriteria(Imagem.class, "f")
                    .createAlias("produto", "p")
                    .add(Restrictions.eq("p.id", ID)).list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return null;
    }
    
    @SuppressWarnings("unchecked")
    public static List<Imagem> listByServicos(int ID) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            return session.createCriteria(Imagem.class, "f")
                    .createAlias("servico", "s")
                    .add(Restrictions.eq("s.id", ID)).list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return null;
    }
}
