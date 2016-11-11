package com.example.gabriela.aplicacao;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import pojo.*;
import pojo.MiniJogo;
import java.io.Serializable;
import de.hdodenhof.circleimageview.CircleImageView;
public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ImageButton fotoPerfil;
    private ImageButton imagemselo1, imagemselo2, imagemselo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaComponentes();

        setSupportActionBar(toolbar);

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
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View header = navigationView.getHeaderView(0);
        // PEGANDO OS DADOS DO LOGIN
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if (bundle != null) {


            String nome = bundle.getString("nome");
            String email = bundle.getString("email");
            String foto = bundle.getString("photo");

            TextView txtNome = (TextView) header.findViewById(R.id.username);
            txtNome.setText(nome);

            TextView txtEmail = (TextView) header.findViewById(R.id.email);
            txtEmail.setText(email);

            CircleImageView civFoto = (CircleImageView) header.findViewById(R.id.profile_image);
            civFoto.setImageURI(Uri.parse(foto));

        }
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
    public void inicializaComponentes() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        fotoPerfil = (ImageButton) findViewById(R.id.iv_foto_perfil);
        imagemselo1 = (ImageButton)findViewById(R.id.imagemselo1);
        imagemselo2 = (ImageButton)findViewById(R.id.imagemselo2);
        imagemselo3 = (ImageButton)findViewById(R.id.imagemselo3);
    }
}
