package main.java.org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cerere {
    String text;
    Date data;
    int prioritate;
    enum tipCerere {
        c0("inlocuire buletin"),
        c1("inregistrare venit salarial"),
        c2("inlocuire carnet de sofer"),
        c3("inlocuire carnet de elev"),
        c4("creare act constitutiv"),
        c5("reinnoire autorizatie"),
        c6("inregistrare cupoane de pensie");

        final String titlu;

        tipCerere (String titlu) {
            this.titlu = titlu;
        }
    }

    public Cerere(String text, Date data, int prioritate) {
        this.text = text;
        this.data = data;
        this.prioritate = prioritate;
    }

    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String d = formatter.format(data);
        return (d + " - " + text);
    }

}
