package MySSM.DATA;

public class administrator {
    private String administrator_id;
    private String password;

    public administrator() {

    }
    public administrator(String administrator_id, String password) {
        this.administrator_id = administrator_id;
        this.password = password;
    }

    public String getAdministrator_id() {
        return administrator_id;
    }

    public void setAdministrator_id(String administrator_id) {
        this.administrator_id = administrator_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
