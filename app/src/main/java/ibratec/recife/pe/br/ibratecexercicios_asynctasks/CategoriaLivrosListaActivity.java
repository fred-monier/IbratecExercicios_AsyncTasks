package ibratec.recife.pe.br.ibratecexercicios_asynctasks;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriaLivrosListaActivity extends AppCompatActivity {

    private static final String LISTA_LIVROS = "ListaLivros";

    private TextView txtCategoria;

    private ArrayList<Livro> listaLivros;
    private LivrosAdapter listaLivrosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_livros_lista);

        CategoriaLivros catLivro = (CategoriaLivros) getIntent().
                getSerializableExtra(PublicacoesNovatecListaActivity.CATEGORIA_LIVROS);

        String categ = "Livros da categoria: " + catLivro.getCategoria();
        txtCategoria = (TextView) findViewById(R.id.txtCategoria);
        txtCategoria.setText(categ);

        if (savedInstanceState != null) {
            listaLivros = (ArrayList<Livro>) savedInstanceState.getSerializable(LISTA_LIVROS);
        } else {
            listaLivros = catLivro.getLivros();
        }

        this.montarLista();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(LISTA_LIVROS, listaLivros);
    }

    private void montarLista() {

        listaLivrosAdapter = new LivrosAdapter(listaLivros, this);

        ListView listaLivrosView = (ListView) findViewById(R.id.listview);
        listaLivrosView.setAdapter(listaLivrosAdapter);
    }

    private class LivrosAdapter extends BaseAdapter {

        private ArrayList<Livro> dataSet;
        private Context mContext;

        public LivrosAdapter(ArrayList<Livro> dataSet, Context mContext) {
            this.dataSet = dataSet;
            this.mContext = mContext;
        }

        public int getCount() {
            return dataSet.size();
        }

        public Livro getItem(int position) {
            return dataSet.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;

            //carregando layout
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).
                        inflate(R.layout.categoria_livros_row_item, parent, false);

                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //caregando item atual
            Livro currentItem = getItem(position);

            //carregando componentes do layout
            viewHolder.itemTitulo.setText(currentItem.getTitulo());
            viewHolder.itemAutor.setText(currentItem.getAutor());

            //retornando a view
            return convertView;
        }

        private class ViewHolder {
            TextView itemTitulo;
            TextView itemAutor;

            public ViewHolder(View view) {
                itemTitulo = (TextView) view.findViewById(R.id.txtTitulo);
                itemAutor = (TextView) view.findViewById(R.id.txtAutor);
            }
        }


    }
}
