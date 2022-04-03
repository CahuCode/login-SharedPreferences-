package cahucode.com.saredpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Creado por Carlos_Code el 16/3/2020.
 * carlos.japon.code@gmail.com
 */

public class SharePreference {

    private static final String SHARE = "prueba";
    private static final String USER_LOGIN = "usuario_login";
    private static final String LOGEADO = "logeado";

    private Context context;

    public SharePreference(Context context) {
        this.context = context;
    }

    // Guardar, obtener el Modelo Usuario
    public void saveUserModel(UserModel login) {
        SharedPreferences mPrefs = context.getSharedPreferences(SHARE, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(login);
        prefsEditor.putString(USER_LOGIN, json);
        prefsEditor.apply();
    }

    public UserModel getUserModel() {
        SharedPreferences mPrefs = context.getSharedPreferences(SHARE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(USER_LOGIN, null);
        UserModel userModel = gson.fromJson(json, UserModel.class);
        if (userModel == null) return new UserModel();
        return userModel;
    }

    public void clearUserModel() {
        SharedPreferences mPrefs = context.getSharedPreferences(SHARE, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.clear().apply();
    }

    // Guardar, obtener el usuario como Strings
    public void saveLogeado(boolean log) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGEADO, log);
        editor.commit();
    }

    public boolean getLogeado() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE, Context.MODE_PRIVATE);
        boolean logeado = sharedPreferences.getBoolean(LOGEADO, false);
        return logeado;
    }
}
