package agent.config;

/**
 * @author Yaroslav
 */

public class Configuration {

    private static Configuration INSTANCE;

    private Configuration() {
        // visibility
    }

    public static Configuration getInstance()
    {
        if (INSTANCE == null)
        {
            synchronized (Configuration.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = new Configuration();
                }
            }
        }
        return INSTANCE;
    }

    private String _dbUrl = null;
    private String _user = "root";
    private String _password = "root";

    public String getDbUrl() {
        return _dbUrl;
    }
    public String getDbUser() {
        return _user;
    }
    public String getDbPassword() {
        return _password;
    }
    public void setDbUrl(String host) {
        _dbUrl = "jdbc:mysql://" + host + "/systemagent?allowPublicKeyRetrieval=false&useSSL=false&serverTimezone=Asia/Novosibirsk";
    }
}
