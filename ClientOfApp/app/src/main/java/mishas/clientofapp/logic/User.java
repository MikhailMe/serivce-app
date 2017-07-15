package mishas.clientofapp.logic;

import java.io.Serializable;

public final class User implements Serializable{

    private Long id;
    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private int age;
    private String telephone;
    private boolean hasCard;
    private boolean inSystem;

    public boolean isInSystem() {
        return inSystem;
    }

    public void setInSystem(boolean inSystem) {
        this.inSystem = inSystem;
    }

    public User(){
        this.id = null;
        this.login = null;
        this.password = null;
        this.email = null;
        this.name = null;
        this.surname = null;
        this.age = -1;
        this.telephone = null;
        this.hasCard = false;
        this.inSystem = false;

    }

    public User(Long id, String login, String password, String email, String name, String surname, int age, String telephone, boolean hasCard) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.telephone = telephone;
        this.hasCard = hasCard;

    }

    public boolean HasCard() {
        return hasCard;
    }

    public void setHasCard(boolean hasCard) {
        this.hasCard = hasCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}