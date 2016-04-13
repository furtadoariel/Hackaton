package patricia.hackaton3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //variaveis
    private EditText mCpfView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mSignInButton;
    private Button mRegisterButton;

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //cpf
        mCpfView = (EditText) findViewById(R.id.cpf);
        mCpfView.addTextChangedListener(Mask.insert("###.###.###-##", mCpfView)); //campo cpf formatado


        mPasswordView = (EditText) findViewById(R.id.password);


        //campo_telefone.addTextChangedListener(Mask.insert("(##)####-####", campo_telefone));
//-----------------------------------------------------------------------------------
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

//-----------------------------------------------------------------------------------

        //botes clicaveis

       /* mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FeedActivity.class);
                startActivity(i);
            }
        }); //fim click listener
        */


        //seta o xml do botao
        mSignInButton = (Button) findViewById(R.id.sign_in_button);
        //caso o botao entrar seja clicado
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Validator.validateNotNull(mPasswordView,"Preencha o campo senha");
                Validator.validateNotNull(mCpfView,"Preencha o campo CPF");
                boolean cpf_valido = Validator.validateCPF(mCpfView.getText().toString());
                boolean senha_valida = Validator.validatePassword(mPasswordView.getText().toString());
                //caso o cpf seja invalido mostra o erro

                if(!cpf_valido) {
                    mCpfView.setError("CPF inválido.");
                    mCpfView.setFocusable(true);
                    mCpfView.requestFocus();
                }else if (!senha_valida) {
                        mPasswordView.setError("Senha inválida.\nSua senha deve conter mais de 4 caracteres.");
                        mPasswordView.setFocusable(true);
                        mPasswordView.requestFocus();
                } else {
                    //caso o cpf seja valido segue para a tela de feed
                    Intent i = new Intent(MainActivity.this, FeedActivity.class);
                    startActivity(i);
                }
            }
        }); //final do onClickListener


        mRegisterButton = (Button) findViewById(R.id.register__button);
        //caso o botao registrar seja clicado
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
        } else if (id == R.id.feed) {
            Intent i = new Intent(MainActivity.this, FeedActivity.class);
            startActivity(i);
        } else if (id == R.id.register) {
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(i);
        } else if (id == R.id.sobre) {
            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
