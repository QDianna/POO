package main.java.org.example;

import main.exceptions.*;
import java.io.*;
import java.util.Date;

public class Elev extends Utilizator{
    public Elev(String nume, String scoala) {
        this.nume = nume;
        this.scoala = scoala;
    }

    @Override
    String scrieCerere(int tip) throws Ex_Elev_1, Ex_Elev_2, Ex_Elev_4, Ex_Elev_5, Ex_Elev_6 {
        if (tip == 1)
            throw new Exceptie1Elev("Utilizator de tip 'Elev' nu poate inainta o cerere de tip 'inregistrare venit salarial'");

        if (tip == 2)
            throw new Exceptie2Elev("Utilizator de tip 'Elev' nu poate inainta o cerere de tip 'inlocuire carnet de sofer'");

        if (tip == 4)
            throw new Exceptie4Elev("Utilizator de tip 'Elev' nu poate inainta o cerere de tip 'creare act constitutiv'");

        if (tip == 5)
            throw new Exceptie5Elev("Utilizator de tip 'Elev' nu poate inainta o cerere de tip 'reinnoire autorizatie'");

        if (tip == 6)
            throw new Exceptie6Elev("Utilizator de tip 'Elev' nu poate inainta o cerere de tip 'inregistrare cupoane de pensie'");

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
