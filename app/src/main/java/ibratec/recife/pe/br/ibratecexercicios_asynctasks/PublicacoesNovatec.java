package ibratec.recife.pe.br.ibratecexercicios_asynctasks;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Frederico on 30/10/2017.
 */

public class PublicacoesNovatec implements Serializable {

    private ArrayList<CategoriaLivros> novatec;

    public PublicacoesNovatec() {
    }

    public PublicacoesNovatec(ArrayList<CategoriaLivros> novatec) {
        this.novatec = novatec;
    }

    public ArrayList<CategoriaLivros> getNovatec() {
        return novatec;
    }

    public void setNovatec(ArrayList<CategoriaLivros> novatec) {
        this.novatec = novatec;
    }
}
