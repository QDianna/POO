package main.java.org.example;

import main.exceptions.*;
import java.io.*;
import java.util.Date;

public class EntitateJuridica extends Utilizator{

    public EntitateJuridica(String nume, String reprezentant) {
        this.nume = nume;
        this.reprezentant = reprezentant;
    }

    @Override
    String scrieCerere(int tip) throws Ex_EntJur_0, Ex_EntJur_1, Ex_EntJur_2, Ex_EntJur_3, Ex_EntJur_6 {
        if (tip == 0)
            throw new Exceptie0EntJur("Utilizator de tip 'Entitate Juridica' nu poate inainta o cerere de tip 'inlocuire buletin'");

        if (tip == 1)
            throw new Exceptie1EntJur("Utilizator de tip 'Entitate Juridica' nu poate inainta o cerere de tip 'inregistrare venit salarial'");

        if (tip == 2)
            throw new Exceptie2EntJur("Utilizator de tip 'Entitate Juridica' nu poate inainta o cerere de tip 'inlocuire carnet de sofer'");

        if (tip == 3)
            throw new Exceptie3EntJur("Utilizator de tip 'Entitate Juridica' nu poate inainta o cerere de tip 'inlocuire carnet de elev'");

        if (tip == 6)
            throw new Exceptie6EntJur("Utilizator de tip 'Entitate Juridica' nu poate inainta o cerere de tip 'inregistrare cupoane de pensie'");

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
