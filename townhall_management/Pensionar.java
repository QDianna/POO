package main.java.org.example;

import main.exceptions.Exceptie1Pensionar;
import main.exceptions.Exceptie3Pensionar;
import main.exceptions.Exceptie4Pensionar;
import main.exceptions.Exceptie5Pensionar;

import java.util.Date;

public class Pensionar extends Utilizator{
    public Pensionar(String nume) {
        this.nume = nume;
    }

    @Override
    String scrieCerere(int tip) throws Exceptie1Pensionar, Exceptie3Pensionar, Exceptie4Pensionar, Exceptie5Pensionar{
        if (tip == 1)
            throw new Exceptie1Pensionar("Utilizator Pensionar nu poate accesa inregistrare venit salarial");

        if (tip == 3)
            throw new Exceptie3Pensionar("Utilizator Pensionar nu poate accesa inlocuire carnet de elev");

        if (tip == 4)
            throw new Exceptie4Pensionar("Utilizator Pensionar nu poate accesa creare act constitutiv");

        if (tip == 5)
            throw new Exceptie5Pensionar("Utilizator Pensionar nu poate accesa reinnoire autorizatie");

        Cerere.tipCerere tipCerere;

        if (tip == 0)
            tipCerere = Cerere.tipCerere.c0;
        else if (tip == 2)
            tipCerere = Cerere.tipCerere.c2;
        else
            tipCerere = Cerere.tipCerere.c6;

        String text;
        text = "Subsemnatul " + this.nume + ", va rog sa-mi aprobati urmatoarea solicitare: " + tipCerere.titlu;

        return text;
    }

    Cerere creareCerere(int tipCerere, int prioritate, Date data) throws Exceptie1Pensionar, Exceptie3Pensionar, Exceptie4Pensionar, Exceptie5Pensionar {
        String textnouaCerere = scrieCerere(tipCerere);
        Cerere nouaCerere = new Cerere(textnouaCerere, data, prioritate);
        return nouaCerere;
    }

}
