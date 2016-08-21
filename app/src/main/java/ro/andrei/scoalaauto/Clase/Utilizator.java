package ro.andrei.scoalaauto.Clase;

/**
 * Created by Adrian on 1/4/2016.
 */
public class Utilizator {
    private String nume;
    private String email;
    private String username;
    private String parola;
    private int testePicate;
    private int testeTrecute;

    public Utilizator(String nume, String email, String username, String parola) {
        this.nume = nume;
        this.email = email;
        this.username = username;
        this.parola = parola;
        this.testePicate=0;
        this.testeTrecute=0;
    }

    public Utilizator(String nume, String email, String parola, String username, int testePicate, int testeTrecute) {
        this.nume = nume;
        this.email = email;
        this.parola = parola;
        this.username = username;
        this.testePicate = testePicate;
        this.testeTrecute = testeTrecute;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public int getTestePicate() {
        return testePicate;
    }

    public void setTestePicate(int testePicate) {
        this.testePicate = testePicate;
    }

    public int getTesteTrecute() {
        return testeTrecute;
    }

    public void setTesteTrecute(int testeTrecute) {
        this.testeTrecute = testeTrecute;
    }
}
