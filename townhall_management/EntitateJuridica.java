package main.java.org.example;

import main.exceptions.*;

import java.util.Date;

public class EntitateJuridica extends Utilizator{

    public EntitateJuridica(String nume, String reprezentant) {
        this.nume = nume;
        this.reprezentant = reprezentant;
    }

    @Override
    String scrieCerere(int tip) throws Exceptie0EntJur, Exceptie1EntJur, Exceptie2EntJur, Exceptie3EntJur, Exceptie6EntJur {
        if (tip == 0)
            throw new Exceptie0EntJur("Utilizator EntitateJuridica nu poate accesa inlocuire buletin");

        if (tip == 1)
            throw new Exceptie1EntJur("Utilizator EntitateJuridica nu poate accesa inregistrare venit salarial");

        if (tip == 2)
            throw new Exceptie2EntJur("Utilizator EntitateJuridica nu poate accesa inlocuire carnet de sofer");

        if (tip == 3)
            throw new Exceptie3EntJur("Utilizator EntitateJuridica nu poate accesa inlocuire carnet de elev");

        if (tip == 6)
            throw new Exceptie6EntJur("Utilizator EntitateJuridica nu poate accesa inregistrare cupoane de pensie");

        Cerere.tipCerere tipCerere;

        if (tip == 4)
            tipCerere = Cerere.tipCerere.c4;
        else
            tipCerere = Cerere.tipCerere.c5;

        String text;
        text = "Subsemnatul " + this.reprezentant + ", reprezentant legal al companiei " + this.companie + ", va rog sa-mi aprobati urmatoarea solicitare: " + tipCerere.titlu;

        return text;
    }

    Cerere creareCerere(int tipCerere, int prioritate, Date data) throws Exceptie0EntJur, Exceptie1EntJur, Exceptie2EntJur, Exceptie3EntJur, Exceptie6EntJur {
        String textnouaCerere = scrieCerere(tipCerere);
        Cerere nouaCerere = new Cerere(textnouaCerere, data, prioritate);
        return nouaCerere;
    }


}
