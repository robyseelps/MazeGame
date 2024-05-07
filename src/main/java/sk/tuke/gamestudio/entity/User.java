package sk.tuke.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@Table(name = "users")
@NamedQuery( name = "User.getUserFromLogin",
        query = "SELECT u FROM User u WHERE u.login=:login")
@NamedQuery( name = "User.getUserFromLoginAndPass",
        query = "SELECT u FROM User u WHERE u.login=:login and u.password =: password ")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private int ident;
    private String login;
    private String password;
    public User(){}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password'" + password + '\'';
    }
}
