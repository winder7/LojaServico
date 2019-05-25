package teste;

import java.io.File;
import java.io.IOException;

/**
 * @Autor Alexandre Almeida
 * @Data 25/05/2019
 */
public class TesteBoleto {

    private static void mostreBoletoNaTela(File arquivoBoleto) {

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

        try {
            desktop.open(arquivoBoleto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
