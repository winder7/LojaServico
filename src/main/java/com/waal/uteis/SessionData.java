package com.waal.uteis;

import com.waal.persistencia.PessoaDAO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Autor Alexandre Almeida
 * @Data 05/05/2019
 */
public class SessionData {

    public static String getNomeUsuarioLogado() {
        String nome = "";
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String detalhes = user.toString();
            if (detalhes.length() > 0) {
                String[] allDetails = detalhes.split(";");
                String[] nomeUsr = allDetails[0].split(":");
                String nomeUsuario = nomeUsr[2].trim();
                nome = PessoaDAO.pesqNomeUsr(nomeUsuario).getNome();
            }
        } catch (Exception e) {
            System.out.println("Erro getNomeUsuarioLogado: Nenhum usúario logado! " + e);
            return "Olá Visitante!";
        }
        return nome;
    }

    public static boolean ehUserAdmin() {
        boolean role = false;
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String detalhes = user.toString();
            System.out.println(user);
            if (detalhes.length() > 0) {
                String[] allDetails = detalhes.split(":");
                role = "ROLE_ADMINISTRADOR".equals(allDetails[allDetails.length-1].trim());
                System.out.println("ehUserAdmin " + role);
            }
        } catch (Exception e) {
            System.out.println("Erro getRole: Nenhum usúario logado! " + e);
        }
        return role;
    }
}
