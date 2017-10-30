package ibratec.recife.pe.br.ibratecexercicios_asynctasks;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Frederico on 30/10/2017.
 */

public class CategoriaLivros implements Serializable {

    public String categoria;
    private List<Livro> livros;

    public CategoriaLivros() {
    }

    public CategoriaLivros(String categoria, List<Livro> livros) {
        this.categoria = categoria;
        this.livros = livros;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
