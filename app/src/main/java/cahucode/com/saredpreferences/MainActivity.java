package cahucode.com.saredpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText txtuser, txtpass, txtpassVeri;
    Button btningresar;
    CheckBox checkRegistrar;
    SharePreference share;
    LinearLayout lnyPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtuser = findViewById(R.id.txtuser);
        txtpass = findViewById(R.id.txtpass);
        txtpassVeri = findViewById(R.id.txtpassVerifcar);
        btningresar = findViewById(R.id.btningresar);
        lnyPass = findViewById(R.id.lnyPassVeri);
        checkRegistrar = findViewById(R.id.checkboxId);
        share = new SharePreference(this);


        checkRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = checkRegistrar.isChecked();
                if (check) {
                    lnyPass.setVisibility(View.VISIBLE);
                    btningresar.setText("REGISTRARSE");
                    txtuser.setText("");
                    txtpass.setText("");
                    txtpassVeri.setText("");
                } else {
                    txtuser.setText("");
                    txtpass.setText("");
                    txtpassVeri.setText("");
                    lnyPass.setVisibility(View.GONE);
                    btningresar.setText("INGRESAR");
                }
            }
        });

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = checkRegistrar.isChecked();
                String user = txtuser.getText().toString().trim();
                String pass = txtpass.getText().toString().trim();
                if (check) {
                    String passs = txtpassVeri.getText().toString().trim();
                    verificarRegistro(user, pass, passs);
                } else {
                    verificarLogin(user, pass);
                }
            }
        });
    }

    private void verificarRegistro(String user, String pass, String passs) {
        if (user.length() == 0 || pass.length() == 0 || passs.length() == 0) {
            mostrarMensaje("Debe llenar todos los campos solicitaodos");
        } else {
            if (pass.equals(passs)) {
                mostrarMensaje("Usuario guardado correctamente");
                UserModel userModel = new UserModel(user, pass);
                share.saveUserModel(userModel);
                share.saveLogeado(true);
                checkRegistrar.setChecked(false);
                txtuser.setText("");
                txtpass.setText("");
                txtpassVeri.setText("");
                lnyPass.setVisibility(View.GONE);
            } else {
                mostrarMensaje("Su contrasenia no coincide con la ingresada para su verificacion");
            }
        }
    }

    private void verificarLogin(String user, String pass) {
        if (user.length() == 0 || pass.length() == 0) {
            mostrarMensaje("Debe llenar todos los campos solicitaodos");
        } else {
            boolean logeado = share.getLogeado();
            if (logeado) {
                UserModel userModel = share.getUserModel();
                if (userModel.user.equals(user) && userModel.pass.equals(pass)) {
                    share.saveLogeado(true);
                    startActivity(new Intent(MainActivity.this, SegundaPantalla.class));
                } else {
                    mostrarMensaje("Usuario o contraseña incorrectos.");
                }
            } else {
                mostrarMensaje("Estimado usuario, antes de ingresar debe registrarse");
            }
        }

    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}