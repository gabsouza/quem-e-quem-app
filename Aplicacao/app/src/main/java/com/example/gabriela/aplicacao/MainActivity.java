package com.example.gabriela.aplicacao;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gabriela.aplicacao.Fragment.ContentFragment;
import com.example.gabriela.aplicacao.Interface.RecyclerViewOnClickListenerHack;
//import com.example.gabriela.aplicacao.Fragment.MiniJogoFragment;
//import com.example.gabriela.aplicacao.adapter.MiniJogoAdapter;

import java.util.ArrayList;
import java.util.List;
import pojo.MiniJogo;
public class MainActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ImageButton fotoPerfil;
    private ImageButton imagemselo1, imagemselo2, imagemselo3;
    private RecyclerView recyclerView;
    private MiniJogosAdapter adapter;
    private List<MiniJogo> miniJogoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaComponentes();

        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        miniJogoList = new ArrayList<>();
        adapter = new MiniJogosAdapter(this, miniJogoList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new MainActivity.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareMiniJogos();

//        try {
//            Glide.with(this).load(R.color.).into((ImageView) findViewById(R.id.backdrop));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.evolucao:
                        Toast.makeText(getApplicationContext(), "Evolução Selected", Toast.LENGTH_SHORT).show();
                        ContentFragment fragment = new ContentFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;

                    // For rest of the options we just show a toast on click

                    case R.id.configuracoes:
                        Toast.makeText(getApplicationContext(), "Configurações Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.sair:
                        Toast.makeText(getApplicationContext(), "Sair Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.dinamica:
                        Toast.makeText(getApplicationContext(), "Dinamica Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();



        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaConfiguracoesCrianca();
            }
        });

        imagemselo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaSelos();
            }
        });

        imagemselo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaSelos();
            }
        });

        imagemselo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaSelos();
            }
        });

//        Bundle extras = getIntent().getExtras();
//        Serializable telaLogin = null;
//            telaLogin = extras.getSerializable("email");
//            telaLogin = extras.getSerializable("nome");
//            telaLogin = extras.getSerializable("foto");

//        View hView = navigationView.inflateHeaderView(R.layout.header);
//        ImageView imgvw = (ImageView)hView.findViewById(R.id.profile_image);
//        TextView tvNome = (TextView)hView.findViewById(R.id.username);
//        TextView tvEmail = (TextView)hView.findViewById(R.id.email);
//        imgvw .setImageResource();
//        tvNome.settext(nome);
//        tvEmail.setText(foto);

//<<<<<<< HEAD
//        navigationView = (NavigationView) findViewById(R.id.navigation_view);
//        View header = navigationView.getHeaderView(0);
//        // PEGANDO OS DADOS DO LOGIN
//        Intent intent = getIntent();
//
//        Bundle bundle = intent.getExtras();
//
//        if (bundle != null) {
//            String nome = bundle.getString("nome");
//            String email = bundle.getString("email");
//            String foto = bundle.getString("photo");
//
//            TextView txtNome = (TextView) header.findViewById(R.id.username);
//            txtNome.setText(nome);
//
//            TextView txtEmail = (TextView) header.findViewById(R.id.email);
//            txtEmail.setText(email);
//
//            CircleImageView civFoto = (CircleImageView) header.findViewById(R.id.profile_image);
//            civFoto.setImageURI(Uri.parse(foto));
//        }

    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareMiniJogos() {
        int[] covers = new int[]{
                R.drawable.profissao,
                R.drawable.fantasma};

        com.example.gabriela.aplicacao.MiniJogo m = new com.example.gabriela.aplicacao.MiniJogo("Profissão", "Relacione as alternativas com as dicas", covers[0]);
        miniJogoList.add(m);

        m = new com.example.gabriela.aplicacao.MiniJogo("Fantasias", "Qual fantasia o personagem quer?", covers[1]);
        miniJogoList.add(m);

        adapter.notifyDataSetChanged();
    }

    private void chamaTelaConfiguracoesCrianca(){
        Intent itTelaConfiguracoesCrianca = new Intent(this, TelaConfiguracoesCrianca.class);
        startActivity(itTelaConfiguracoesCrianca);
        finish();
    }

    private void chamaTelaSelos(){
        Intent itTelaSelo = new Intent(this, TelaSelo.class);
        startActivity(itTelaSelo);
        finish();
    }

     public List<MiniJogo> getSetMiniJogoList(int qtd){
         String [] nomeMiniJogo = new String[]{"Teste1", "Teste2"};
         String [] introducao = new String[]{"algo legal 1", "algo legal 2"};
         int [] fotos = new int[]{R.drawable.profissao, R.drawable.profissao};
         List<MiniJogo> listAux = new ArrayList<>();

         for (int i = 0; i < qtd; i++){
             MiniJogo miniJogo = new MiniJogo(nomeMiniJogo[i % nomeMiniJogo.length], introducao[i % introducao.length], fotos[i % fotos.length]);
             listAux.add(miniJogo);
         }
          return (listAux);
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void inicializaComponentes() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        fotoPerfil = (ImageButton) findViewById(R.id.iv_foto_perfil);
        imagemselo1 = (ImageButton)findViewById(R.id.imagemselo1);
        imagemselo2 = (ImageButton)findViewById(R.id.imagemselo2);
        imagemselo3 = (ImageButton)findViewById(R.id.imagemselo3);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }
}