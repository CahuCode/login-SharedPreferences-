package cahucode.com.saredpreferences;

/**
 * Creado por Carlos_Code el 02/04/2022.
 * carlos.japon.code@gmail.com
 */

class UserModel {
    String user = "";
    String pass = "";

    public UserModel() {
    }

    public UserModel(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "UserLoginModel{" +
                "user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
