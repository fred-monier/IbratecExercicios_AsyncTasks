package ibratec.recife.pe.br.ibratecexercicios_asynctasks;

import java.io.Serializable;

/**
 * Created by Frederico on 30/10/2017.
 */

public class PublicacoesNovatec implements Serializable {

    private CategoriaLivros[] novatec;

    public PublicacoesNovatec() {
    }

    public PublicacoesNovatec(CategoriaLivros[] novatec) {
        this.novatec = novatec;
    }

    public CategoriaLivros[] getNovatec() {
        return novatec;
    }

    public void setNovatec(CategoriaLivros[] novatec) {
        this.novatec = novatec;
    }
}
