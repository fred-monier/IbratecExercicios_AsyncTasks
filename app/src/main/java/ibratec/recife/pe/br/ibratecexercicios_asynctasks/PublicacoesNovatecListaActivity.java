package ibratec.recife.pe.br.ibratecexercicios_asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class PublicacoesNovatecListaActivity extends AppCompatActivity {

    private static final String LISTA_CATEGORIA_LIVROS = "ListaCategoriaLivros";

    private ProgressBar progressBar;
    private TextView txtEmpty;

    private CategoriaLivros categoriaLivrosWork;

    private ArrayList<CategoriaLivros> listaCategoriaLivros;
    private CategoriaLivrosAdapter listaCategoriaLivrosAdapter;

    private PublicacoesNovatecTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacoes_novatec_lista);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtEmpty = (TextView) findViewById(R.id.txtEmpty);

        if (savedInstanceState != null) {
            listaCategoriaLivros = (ArrayList<CategoriaLivros>) savedInstanceState.getSerializable(LISTA_CATEGORIA_LIVROS);
        } else {
            listaCategoriaLivros = new ArrayList<CategoriaLivros>();
        }

        this.montarLista();

        Button btPesquisarModelo = (Button) findViewById(R.id.btCallTask);
        btPesquisarModelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarTarefa();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(LISTA_CATEGORIA_LIVROS, listaCategoriaLivros);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        this.getMenuInflater().inflate(R.menu.categoria_livros_opcoes, menu);

        MenuItem itemExcluir = menu.getItem(0);
        itemExcluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //************************
                //VISUALIZAR
                //************************

                return true;
            }
        });

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        this.categoriaLivrosWork = listaCategoriaLivrosAdapter.getItem(info.position);

    }

    private void montarLista() {

        listaCategoriaLivrosAdapter = new CategoriaLivrosAdapter(listaCategoriaLivros, this);

        ListView listaCategoriaLivrosView = (ListView) findViewById(R.id.listview);
        listaCategoriaLivrosView.setEmptyView(txtEmpty);
        listaCategoriaLivrosView.setAdapter(listaCategoriaLivrosAdapter);

        registerForContextMenu(listaCategoriaLivrosView);
    }

    private void chamarTarefa() {

        if (task == null) {
            if (PublicacoesNovatecHttp.temConexaoWeb(this)) {

                iniciarChamada();
            } else {

                txtEmpty.setText("Sem conexão com a internet");
            }

        } else if (task.getStatus() == AsyncTask.Status.RUNNING) {

            exibirProgresso(true);
        } else {

            iniciarChamada();
        }

    }

    private void iniciarChamada() {
        if (task == null || task.getStatus() != AsyncTask.Status.RUNNING) {

            listaCategoriaLivros.clear();
            listaCategoriaLivrosAdapter.notifyDataSetChanged();

            task = new PublicacoesNovatecTask();
            task.execute();
        }
    }

    private void exibirProgresso(boolean exibir) {
        if (exibir) {
            txtEmpty.setText("Baixando publicações...");
        }
        txtEmpty.setVisibility(exibir ? View.VISIBLE : View.GONE);
        progressBar.setVisibility(exibir ? View.VISIBLE : View.GONE);

    }

    private class CategoriaLivrosAdapter extends BaseAdapter {

        private ArrayList<CategoriaLivros> dataSet;
        private Context mContext;

        public CategoriaLivrosAdapter(ArrayList<CategoriaLivros> dataSet, Context mContext) {
            this.dataSet = dataSet;
            this.mContext = mContext;
        }

        public int getCount() {
            return dataSet.size();
        }

        public CategoriaLivros getItem(int position) {
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
                        inflate(R.layout.publicacoes_novatec_row_item, parent, false);

                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //caregando item atual
            CategoriaLivros currentItem = (CategoriaLivros) getItem(position);

            //carregando componentes do layout
            String categoria = "Categoria: " + currentItem.getCategoria();
            String livros = "Livros disponíveis: " + currentItem.getLivros().size();
            viewHolder.itemCategoria.setText(categoria);
            viewHolder.itemLivros.setText(livros);

            //retornando a view
            return convertView;
        }

        private class ViewHolder {
            TextView itemCategoria;
            TextView itemLivros;

            public ViewHolder(View view) {
                itemCategoria = (TextView) view.findViewById(R.id.txtCategoria);
                itemLivros = (TextView) view.findViewById(R.id.txtLivros);
            }
        }


    }

    class PublicacoesNovatecTask extends AsyncTask<String, Void, PublicacoesNovatec> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exibirProgresso(true);
        }

        @Override
        protected PublicacoesNovatec doInBackground(String... params) {
            return PublicacoesNovatecHttp.carregarPublicacoesNovatecJSON();
        }

        @Override
        protected void onPostExecute(PublicacoesNovatec pub) {
            super.onPostExecute(pub);
            exibirProgresso(false);

            if (pub != null) {
                
                System.out.println("Sucesso");
                System.out.println(pub);

                ArrayList<CategoriaLivros> catLivro = new ArrayList<CategoriaLivros>();
                for (int i = 0; i < pub.getNovatec().length; i++) {
                    catLivro.add(pub.getNovatec()[i]);
                }

                listaCategoriaLivros.clear();
                listaCategoriaLivros.addAll(catLivro);
                listaCategoriaLivrosAdapter.notifyDataSetChanged();


            } else {
                txtEmpty.setText("Falha ao obter publicações");
            }
        }

    }

}
