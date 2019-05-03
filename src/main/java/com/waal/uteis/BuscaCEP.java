package com.waal.uteis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.json.XML;

/**
 * @Autor Alexandre Almeida
 * @Data 02/05/2019
 */
public class BuscaCEP {

    public static CepDTO Buscar(String cep) {
        try {
            System.out.println("Cep antes: " + cep);
            String cepFormatado = cep.replaceAll("[^0-9]","");
            System.out.println("Cep Formatado " + cepFormatado);
            URL url = new URL("http://viacep.com.br/ws/" + cepFormatado + "/xml");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), HTTP.UTF_8));

            String strResponse = "";
            String output;
            while ((output = br.readLine()) != null) {
                strResponse += output;
            }
            br.close();
            conn.disconnect();

            JSONObject respJson = XML.toJSONObject(strResponse);
            JSONObject time_entries = respJson.getJSONObject("xmlcep");

            CepDTO cepdto = new CepDTO(
                    time_entries.getString("logradouro"),
                    time_entries.getString("bairro"),
                    time_entries.getString("localidade"),
                    time_entries.getString("uf")
            );

            return cepdto;
        } catch (IOException | RuntimeException e) {
            return null;
        }
    }
}
