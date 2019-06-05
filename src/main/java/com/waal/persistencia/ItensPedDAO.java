package com.waal.persistencia;

import java.io.Serializable;
import java.util.List;
import org.hibernate.*;
import com.waal.beans.ItensPed;

/**
 * @Autor m159255
 * @Data 27/02/2019
 */
public class ItensPedDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void inserir(ItensPed itensPed) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.save(itensPed);
        t.commit();
        sessao.close();
    }

    public static void alterar(ItensPed itensPed) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.update(itensPed);
        t.commit();
        sessao.close();
    }

    public static void excluir(ItensPed itensPed) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.delete(itensPed);
        t.commit();
        sessao.close();
    }

    public static List<ItensPed> listagem(String filtro) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta;
        if (filtro.trim().length() == 0) {
            consulta = sessao.createQuery("from ItensPed order by ser_id");
        } else {
            consulta = sessao.createQuery("from ItensPed " + "where upper(ser_nome) like :parametro order by ser_id");
            consulta.setString("parametro", "%" + filtro.toUpperCase() + "%");
        }
        List lista = consulta.list();
        sessao.close();
        return lista;
    }
    
    public static ItensPed pesqId(int valor) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta = sessao.createQuery("from Pessoa " + "where itp_id = :parametro");
        consulta.setInteger("parametro", valor);
        ItensPed itensPed = (ItensPed) consulta.uniqueResult();
        sessao.close();
        return itensPed;
    }
    
    public static List<ItensPed> listagemPed(int valor) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta;
        consulta = sessao.createQuery("from ItensPed where fk_ped_id = :parametro");
        consulta.setInteger("parametro", valor);
        List lista = consulta.list();
        sessao.close();
        return lista;
    }
}
