package cahucode.com.saredpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SegundaPantalla extends AppCompatActivity {
    TextView txtuser, txtpass;
    SharePreference share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_pantalla);
        txtuser = findViewById(R.id.txtUsuarioRes);
        txtpass = findViewById(R.id.txtContraseniaRes);
        share = new SharePreference(this);
        UserModel usuario = share.getUserModel();
        if (usuario != null) {
            txtuser.setText(usuario.user);
            txtpass.setText(usuario.pass);
        }

    }
}