package com.waal.persistencia;

import com.waal.beans.Pessoa;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @Autor m159255
 * @Data 03/06/2019
 */
public class PessoaDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void inserir(Pessoa pessoa) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.save(pessoa);
        t.commit();
        sessao.close();
    }

    public static void alterar(Pessoa pessoa) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.update(pessoa);
        t.commit();
        sessao.close();
    }

    public static void excluir(Pessoa pessoa) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.delete(pessoa);
        t.commit();
        sessao.close();
    }

    public static List<Pessoa> listagem(String filtro) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta;
        if (filtro.trim().length() == 0) {
            consulta = sessao.createQuery("from Pessoa order by pes_id");
        } else {
            consulta = sessao.createQuery("from Pessoa " + "where pes_nome like :parametro order by pes_id");
            consulta.setString("parametro", "%" + filtro + "%");
        }
        List lista = consulta.list();
        sessao.close();
        return lista;
    }

    public static Pessoa pesqId(int valor) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta;
        consulta = sessao.createQuery("from Pessoa " + "where pes_id = :parametro");
        consulta.setInteger("parametro", valor);
        sessao.close();
        return (Pessoa) consulta.uniqueResult();
    }
}
