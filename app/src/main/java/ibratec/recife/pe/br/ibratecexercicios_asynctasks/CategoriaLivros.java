package ibratec.recife.pe.br.ibratecexercicios_asynctasks;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Frederico on 30/10/2017.
 */

public class CategoriaLivros implements Serializable {

    private String categoria;
    private ArrayList<Livro> livros;

    public CategoriaLivros() {
    }

    public CategoriaLivros(String categoria, ArrayList<Livro> livros) {
        this.categoria = categoria;
        this.livros = livros;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }
}
