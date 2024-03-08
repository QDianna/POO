package main.java.org.example;

import main.exceptions.*;

import java.util.Date;

public class Elev extends Utilizator{
    public Elev(String nume, String scoala) {
        this.nume = nume;
        this.scoala = scoala;
    }

    @Override
    String scrieCerere(int tip) throws Exceptie1Elev, Exceptie2Elev, Exceptie4Elev, Exceptie5Elev, Exceptie6Elev {
        if (tip == 1)
            throw new Exceptie1Elev("Utilizator Elev nu poate accesa inregistrare venit salarial");

        if (tip == 2)
            throw new Exceptie2Elev("Utilizator Elev nu poate accesa inlocuire carnet de sofer");

        if (tip == 4)
            throw new Exceptie4Elev("Utilizator Elev nu poate accesa creare act constitutiv");

        if (tip == 5)
            throw new Exceptie5Elev("Utilizator Elev nu poate accesa reinnoire autorizatie");

        if (tip == 6)
            throw new Exceptie6Elev("Utilizator Elev nu poate accesa inregistrare cupoane de pensie");

        Cerere.tipCerere tipCerere;

        if (tip == 0)
            tipCerere = Cerere.tipCerere.c0;
        else
            tipCerere = Cerere.tipCerere.c3;

        String text;
        text = "Subsemnatul " + this.nume + ", elev la scoala " + this.scoala + ", va rog sa-mi aprobati urmatoarea solicitare: " + tipCerere.titlu;

        return text;
    }

    Cerere creareCerere(int tipCerere, int prioritate, Date data) throws Exceptie1Elev, Exceptie2Elev, Exceptie4Elev, Exceptie5Elev, Exceptie6Elev {
        String textnouaCerere = scrieCerere(tipCerere);
        Cerere nouaCerere = new Cerere(textnouaCerere, data, prioritate);
        return nouaCerere;
    }


}
