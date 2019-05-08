package teste;

import com.waal.persistencia.PessoaDAO;
import com.waal.uteis.SessionData;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Autor Alexandre Almeida
 * @Data 02/05/2019
 */
public class Testes {

    public static void main(String[] args) {
        System.out.println(PessoaDAO.pesqNomeUsr("windergt@gmail.com").getNome()); 
    }
}
