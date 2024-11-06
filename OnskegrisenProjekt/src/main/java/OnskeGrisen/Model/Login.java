package OnskeGrisen.Model;

public class Login {
    private String loginUserName;
    private String loginUserPassword;

    public Login(String loginUserName, String loginUserPassword) {
        this.loginUserName = loginUserName;
        this.loginUserPassword = loginUserPassword;
    }

    public String getLoginUserName() {
        return loginUserName;
    }
    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }
    public String getLoginUserPassword() {
        return loginUserPassword;
    }
    public void setLoginUserPassword(String loginUserPassword) {
        this.loginUserPassword = loginUserPassword;
    }
}
