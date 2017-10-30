package ibratec.recife.pe.br.ibratecexercicios_asynctasks;

import java.io.Serializable;

public class Livro implements Serializable {

    public String titulo;
    public String autor;
    public int ano;
    public int paginas;
    public String capa;

    public Livro() {
    }

    public Livro(String titulo, String autor, int ano, int paginas, String capa) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.paginas = paginas;
        this.capa = capa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    @Override
    public String toString() {
        return this.titulo;
    }
}
