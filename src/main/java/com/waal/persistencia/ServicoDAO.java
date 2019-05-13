package com.waal.persistencia;

import java.io.Serializable;
import java.util.List;
import org.hibernate.*;
import com.waal.beans.Servico;

/**
 * @Autor m159255
 * @Data 27/02/2019
 */
public class ServicoDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void inserir(Servico servico) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.save(servico);
        t.commit();
        sessao.close();
    }

    public static void alterar(Servico servico) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.update(servico);
        t.commit();
        sessao.close();
    }

    public static void excluir(Servico servico) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.delete(servico);
        t.commit();
        sessao.close();
    }

    public static List<Servico> listagem(String filtro) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta;
        if (filtro.trim().length() == 0) {
            consulta = sessao.createQuery("from Servico order by ser_id");
        } else {
            consulta = sessao.createQuery("from Servico " + "where upper(ser_nome) like :parametro order by ser_id");
            consulta.setString("parametro", "%" + filtro.toUpperCase() + "%");
        }
        List lista = consulta.list();
        sessao.close();
        return lista;
    }
}
