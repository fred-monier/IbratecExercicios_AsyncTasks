package ibratec.recife.pe.br.ibratecexercicios_asynctasks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Frederico on 30/10/2017.
 */

public class PublicacoesNovatecHttp {

    private static final String LIVROS_URL_JASON =
            "https://raw.githubusercontent.com/nglauber/dominando_android/master/livros_novatec.json";

    public static boolean temConexaoWeb(Context ctx) {
        boolean res = false;

        ConnectivityManager con = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = con.getActiveNetworkInfo();

        if (net != null && net.isConnected()) {
            res = true;
        }

        return res;
    }

    public static PublicacoesNovatec carregarPublicacoesNovatecJSON() {

        PublicacoesNovatec res = null;

        try {

            HttpURLConnection conexao = conectar(LIVROS_URL_JASON);

            int resposta = conexao.getResponseCode();
            if (resposta == HttpURLConnection.HTTP_OK) {

                InputStream is = conexao.getInputStream();
                String str = bytesParaString(is);
                JSONObject jo = new JSONObject(str);

                Gson gson = new Gson();
                res = gson.fromJson(jo.toString(), PublicacoesNovatec.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;

    }

    private static HttpURLConnection conectar(String urlArquivo) throws IOException {

        final int MILI = 1000;

        URL url = new URL(urlArquivo);

        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setReadTimeout(10 * MILI);
        conexao.setConnectTimeout(15 + MILI);
        conexao.setRequestMethod("GET");
        conexao.setDoInput(true);
        conexao.setDoOutput(false);
        conexao.connect();

        return conexao;

    }

    private static String bytesParaString(InputStream is) throws IOException {

        byte[] buffer = new byte[1024];

        ByteArrayOutputStream buferzao = new ByteArrayOutputStream();

        int bytesLidos;

        while ((bytesLidos = is.read(buffer)) != -1) {
            buferzao.write(buffer, 0, bytesLidos);
        }

        return new String(buferzao.toByteArray(), "UTF-8");
    }

}
