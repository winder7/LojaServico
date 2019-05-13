package com.waal.persistencia;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.waal.beans.Produto;

/**
 * @Autor m159255
 * @Data 27/02/2019
 */
public class ProdutoDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void inserir(Produto produto) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.save(produto);
        t.commit();
        sessao.close();
    }

    public static void alterar(Produto produto) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.update(produto);
        t.commit();
        sessao.close();
    }

    public static void excluir(Produto produto) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.delete(produto);
        t.commit();
        sessao.close();
    }

    public static List<Produto> listagem(String filtro) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta;
        if (filtro.trim().length() == 0) {
            consulta = sessao.createQuery("from Produto order by pro_id");
        } else {
            consulta = sessao.createQuery("from Produto " + "where upper(pro_nome) like :parametro order by pro_id");
            consulta.setString("parametro", "%" + filtro.toUpperCase() + "%");
        }
        List lista = consulta.list();
        sessao.close();
        return lista;
    }
}
