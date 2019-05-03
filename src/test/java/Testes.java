
import com.waal.uteis.BuscaCEP;
import com.waal.uteis.CepDTO;

/**
 * @Autor Alexandre Almeida
 * @Data  02/05/2019
 */
public class Testes {
    public static void main(String[] args) {
        CepDTO cepdto = new CepDTO();
        cepdto = BuscaCEP.Buscar("74305-290");
        
        System.out.println(cepdto.getBairro());
    }
}
