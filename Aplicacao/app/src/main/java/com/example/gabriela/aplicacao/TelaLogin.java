package com.example.gabriela.aplicacao;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.Serializable;

import consumer.ResponsavelConsumer;
import pojo.Responsavel;

import static android.R.attr.id;
import static com.example.gabriela.aplicacao.R.id.viewPager;


public class TelaLogin extends AppCompatActivity implements
        View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = TelaLogin.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;

    private SignInButton btnSignIn;
    private Button btnSignOut, btnRevokeAccess, btnSalvar;
    private LinearLayout llProfileLayout;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail, txtId;

    private SharedPreferences spAutenticacao;
    private SharedPreferences.Editor editor;
    public static final String AUTENTICACAO = "autenticar";

    private ResponsavelConsumer responsavelConsumer;
    private Responsavel responsavel, resp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //GAB
        responsavel = new Responsavel();
        spAutenticacao = getApplicationContext().getSharedPreferences(AUTENTICACAO, MODE_APPEND);
        editor = spAutenticacao.edit();
        responsavelConsumer = new ResponsavelConsumer();

//        if (this.verificaSeJaLogou()) {
//            chamaTelaInicial();
//
//        } else {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
        btnSignOut = (Button) findViewById(R.id.btn_sign_out);
        btnRevokeAccess = (Button) findViewById(R.id.btn_revoke_access);
        llProfileLayout = (LinearLayout) findViewById(R.id.llProfile);
        imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
        txtName = (TextView) findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        btnSalvar = (Button) findViewById(R.id.btn_salvar);

        passaParametros();
//            btnSalvar.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    responsavel.setNomeResponsavel(txtName.getText().toString());
//                    responsavel.setEmailResponsavel(txtEmail.getText().toString());
//                    new HttpRequestTask().execute(responsavel);
//                    //  Log.i("DEBUG",((CustomSwip)viewPager.getAdapter()).getImagemCorrente()+"");
//                    // perfil.setMidia((CustomSwip) viewPager.getAdapter()).getImagemCorrente());
//
//                    responsavel = responsavelConsumer.validaLogin(responsavel);
//                    if (responsavel != null) {
//                        chamaTelaInicial();
//
//                        editor.putInt("idResponsavel", responsavel.getIdResponsavel());
//                    }
//
//                    Toast.makeText(TelaLogin.this, "Salvo", Toast.LENGTH_LONG).show();
//
//                }
//            });

//            super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);


            btnSignIn.setOnClickListener(this);
            btnSignOut.setOnClickListener(this);
            btnRevokeAccess.setOnClickListener(this);
            btnSalvar.setOnClickListener(this);

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();


            // Customizing G+ button
            btnSignIn.setSize(SignInButton.SIZE_STANDARD);
            btnSignIn.setScopes(gso.getScopeArray());
        }
//    }

//    private void chamaTelaInicial() {
//        Intent itTelaLogado = new Intent(this, TelaInicial.class);
//
//        String passaNome = txtName.getText().toString();
//        String passaEmail = txtEmail.getText().toString();
//        String passaFoto = imgProfilePic.toString();
//        Bundle bundle = new Bundle();
//
//        bundle.putString("nome", passaNome);
//        bundle.putString("email", passaEmail);
//        bundle.putString("photo", passaFoto);
//
//        bundle.putSerializable("responsavel", resp);
//        itTelaLogado.putExtras(bundle);
//
//        startActivity(itTelaLogado);
//    }

    private void passaParametros() {
        Intent passa = new Intent(this, MainActivity.class);

        String passaNome = txtName.getText().toString();
        String passaEmail = txtEmail.getText().toString();
        String passaFoto = imgProfilePic.toString();
        Bundle bundle = new Bundle();

        bundle.putString("nome", passaNome);
        bundle.putString("email", passaEmail);
        bundle.putString("photo", passaFoto);

        passa.putExtras(bundle);

        startActivity(passa);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("DEBUG", "FECHANDO ACTIVITY TELA LOGIN");
    }

    private boolean verificaSeJaLogou(){
        boolean logou = false;

        String login = this.spAutenticacao.getString("login", null);
        if (login!=null){
            logou  = true;
        }

        return logou;
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void chamaTelaCadastro(){
        Intent itTelaCadastro = new Intent(this, TelaCadastroCrianca.class);
        startActivity(itTelaCadastro);
        finish();
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String NomeResponsavel = acct.getDisplayName();
            Uri fotoResponsavel = acct.getPhotoUrl();

            //   String idResponsavel = acct.getId();
            String emailResponsavel = acct.getEmail();

            if (fotoResponsavel != null) {
                String personPhotoUrl = acct.getPhotoUrl().toString();
                Log.e(TAG, "Name: " + NomeResponsavel + ", email: " + emailResponsavel + ", Image: " + personPhotoUrl);
                Glide.with(getApplicationContext()).load(personPhotoUrl)
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgProfilePic);
            } else {
                imgProfilePic.setImageResource(android.R.color.transparent);
            }


            txtName.setText(NomeResponsavel);
            txtEmail.setText(emailResponsavel);


            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_sign_in:
                signIn();
                break;

            case R.id.btn_sign_out:
                signOut();
                break;

            case R.id.btn_revoke_access:
                revokeAccess();
                break;

            case R.id.btn_salvar:
                        Responsavel responsavel = new Responsavel();
                        responsavel.setNomeResponsavel(txtName.getText().toString());
                        responsavel.setEmailResponsavel((txtEmail.getText().toString()));
                        new HttpRequestTask().execute(responsavel);
                      //  Log.i("DEBUG",((CustomSwip)viewPager.getAdapter()).getImagemCorrente()+"");
                        // perfil.setMidia((CustomSwip) viewPager.getAdapter()).getImagemCorrente());
                        Toast.makeText(TelaLogin.this, "Salvo", Toast.LENGTH_LONG).show();

                chamaTelaCadastro();

                    break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            btnSignIn.setVisibility(View.GONE);
            btnSignOut.setVisibility(View.VISIBLE);
            btnRevokeAccess.setVisibility(View.VISIBLE);
            btnSalvar.setVisibility(View.VISIBLE);
            llProfileLayout.setVisibility(View.VISIBLE);
        } else {
            btnSignIn.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.GONE);
            btnRevokeAccess.setVisibility(View.GONE);
            btnSalvar.setVisibility(View.GONE);
            llProfileLayout.setVisibility(View.GONE);
        }
    }

    private class HttpRequestTask extends AsyncTask<Responsavel, Void, Responsavel> {

        // EXECUTA A TAREFA QUE DEVE SER REALIZADA

        @Override
        protected Responsavel doInBackground(Responsavel... params) {
            Log.i("DEBUG",params[0].getNomeResponsavel());
            Log.i("DEBUG", params[0].getEmailResponsavel());

            params[0] = responsavelConsumer.chamaCadastrar(params[0]);

            Log.i("DEBUG",params[0].getNomeResponsavel());
            Log.i("DEBUG",params[0].getEmailResponsavel());

            return params[0];

        }

        // é executado quando o webservice retorna
        @Override
        protected void onPostExecute(Responsavel responsavel) {
            super.onPostExecute(responsavel);
            Log.i("DEBUG",responsavel.getNomeResponsavel());
            Log.i("DEBUG", responsavel.getEmailResponsavel());
            resp = responsavel;
        }
    }

}

