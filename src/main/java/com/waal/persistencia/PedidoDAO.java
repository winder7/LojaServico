package com.waal.persistencia;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.waal.beans.Pedido;

/**
 * @Autor Alexandre Almeida
 */
public class PedidoDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void inserir(Pedido pedido) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.save(pedido);
        t.commit();
        sessao.close();
    }

    public static void alterar(Pedido pedido) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.update(pedido);
        t.commit();
        sessao.close();
    }

    public static void excluir(Pedido pedido) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.delete(pedido);
        t.commit();
        sessao.close();
    }

    public static List<Pedido> listagem() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta;
        consulta = sessao.createQuery("from Pedido order by ped_data_emissao desc");
        List lista = consulta.list();
        sessao.close();
        return lista;
    }
    
    public static List<Pedido> listagemUsr(int pes_id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta;
        consulta = sessao.createQuery("from Pedido where fk_pes_id = :parametro order by ped_data_emissao desc");
        consulta.setInteger("parametro", pes_id);
        List lista = consulta.list();
        sessao.close();
        return lista;
    }
}
