package Integracion;

import java.sql.*;

public class ConectionDao {
    private String url;
    private String user;
    private String password;
    private Connection connection;
    private Statement statement;


    public ConectionDao(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        try {
            connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());
            statement = connection.createStatement();
        } catch (
                SQLException e) {
            System.out.println("FALLO CONEXIÓN DB " + e.getMessage());
        }

    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
