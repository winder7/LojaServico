
import com.waal.uteis.BuscaCEP;
import com.waal.uteis.CepDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @Autor Alexandre Almeida
 * @Data 02/05/2019
 */
public class Testes {

    public static void main(String[] args) {
//        CepDTO cepdto = new CepDTO();
//        cepdto = BuscaCEP.Buscar("74305-290");
//        
//        System.out.println(cepdto.getBairro());

        String caminhoOrigen = "postgresql-42.2.5.jre7.jar";
        String caminhoDestino = "target/LojaServico-1.0-SNAPSHOT/WEB-INF/lib/postgresql-42.2.5.jre7.jar";
        copiarArquivo(caminhoOrigen, caminhoDestino);
    }

    public static void copiarArquivo(String caminhoOrigen, String caminhoDestino) {
        try {
            InputStream file = new FileInputStream(new File(caminhoOrigen));
            Path path = Paths.get(caminhoDestino);
            Files.copy(file, path, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Sucesso Arquivo Copiado!!!");
        } catch (Exception ex) {
            System.out.println("Erro ao salvar arquivo! \n" + ex);
        }
    }
}
