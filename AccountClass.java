public class AccountClass {

    private String username;
    private String password;
    private String mailServer;

    public AccountClass(String username, String password, String mailServer) {
        this.username = username;
        this.password = password;
        this.mailServer = mailServer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailServer() {
        return mailServer;
    }

    public void setMailServer(String mailServer) {
        this.mailServer = mailServer;
    }
}

