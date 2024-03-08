package main.java.org.example;

import main.exceptions.*;

import java.util.Date;

public class Persoana extends Utilizator {
    public Persoana(String nume) {
        this.nume = nume;
    }
    private Cerere[] cereriAsteptare;
    private int nrCereriAsteptare;

    @Override
    String scrieCerere(int tip) throws Exceptie1Persoana, Exceptie3Persoana, Exceptie4Persoana, Exceptie5Persoana, Exceptie6Persoana {
        if (tip == 1)
            throw new Exceptie1Persoana("Utilizator Persoana nu poate accesa inregistrareVenitSalarial");

        if (tip == 3)
            throw new Exceptie3Persoana("Utilizator Persoana nu poate accesa inlocuireCarnetDeElev");

        if (tip == 4)
            throw new Exceptie4Persoana("Utilizator Persoana nu poate accesa creareActConstitutiv");

        if (tip == 5)
            throw new Exceptie5Persoana("Utilizator Persoana nu poate accesa reinnoireAutorizatie");

        if (tip == 6)
            throw new Exceptie6Persoana("Utilizator Persoana nu poate accesa inregistrareCupoaneDePensie");

        Cerere.tipCerere tipCerere;

        if (tip == 0)
            tipCerere = Cerere.tipCerere.c0;
        else
            tipCerere = Cerere.tipCerere.c2;

        String text;
        text = "Subsemnatul " + this.nume + ", va rog sa-mi aprobati urmatoarea solicitare: " + tipCerere.titlu;

        return text;
    }

    Cerere creareCerere(int tipCerere, int prioritate, Date data) throws Exceptie1Persoana, Exceptie3Persoana, Exceptie4Persoana, Exceptie5Persoana, Exceptie6Persoana {
        String textnouaCerere = scrieCerere(tipCerere);
        Cerere nouaCerere = new Cerere(textnouaCerere, data, prioritate);



        this.cereriAsteptare[nrCereriAsteptare] = nouaCerere;
        nrCereriAsteptare ++;

        return nouaCerere;
    }



}
