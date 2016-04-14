package patricia.hackaton3;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Patricia on 11/04/2016.
 */
public class RegisterActivity extends AppCompatActivity {
    private EditText mCpfView;
    private EditText mPasswordView;
    private Toolbar mToolbar;
    private HashMap<String, String> vetorPessoa = new HashMap<>();
    private int i;
    private Button mHomeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register); //set xml
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //padrão portrait

        //toolbar
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Registro");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //setinha para voltar a home

        //cpf
        mCpfView = (EditText) findViewById(R.id.cpf);
        mCpfView.addTextChangedListener(Mask.insert("###.###.###-##", mCpfView)); //campo cpf formatado

        mPasswordView = (EditText) findViewById(R.id.password);

        mHomeButton = (Button) findViewById(R.id.voltarHome);
        //caso o botao entrar seja clicado
        mHomeButton.setOnClickListener(new View.OnClickListener() {
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
                    addPessoa(); //adiciona a pessoa na hash
                    Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        }); //final do onClickListener

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    public void addPessoa(){
        for (i=0; i<1000 ; i++) {
            vetorPessoa.put(mCpfView.getText().toString(),mPasswordView.getText().toString() );
        }
    }

}
