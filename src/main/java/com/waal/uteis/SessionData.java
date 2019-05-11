package com.waal.uteis;

import com.waal.persistencia.PessoaDAO;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Autor Alexandre Almeida
 * @Data 05/05/2019
 */
public class SessionData {

    public static String encriptarSenha(String senha) {
        String hash = null;
        try {
            MessageDigest algorithm = MessageDigest.getInstance("sha-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", 0xFF & b));
            }
            hash = hexString.toString();

            //System.out.println(hash);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            System.out.println("Erro ao encriptar senha:\n" + ex);
            return null;
        }
        return hash;
    }

    public static String getNomeUsuarioLogado() {
        try {
            Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //System.out.println("Usuário: " + user);
            if (!user.toString().equals("anonymousUser")) {
                String nomeUsuario = ((UserDetails) user).getUsername();
                return PessoaDAO.pesqNomeUsr(nomeUsuario).getNome();
            } else {
                return "Olá Visitante!";
            }
        } catch (Exception e) {
            System.out.println("Erro getNomeUsuarioLogado: " + e);
            return "Erro";
        }
    }

    public static boolean ehUserAdmin() {
        try {
            Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!user.toString().equals("anonymousUser")) {
                String userDetails = ((UserDetails) user).toString();
                return userDetails.contains("ROLE_ADMINISTRADOR");
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ehUserAdmin: " + e);
            return false;
        }
    }
}
