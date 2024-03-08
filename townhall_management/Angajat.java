package main.java.org.example;

import main.exceptions.Exceptie3Angajat;
import main.exceptions.Exceptie4Angajat;
import main.exceptions.Exceptie5Angajat;
import main.exceptions.Exceptie6Angajat;

import java.io.*;
import java.util.Date;

public class Angajat extends Utilizator{
    public Angajat(String nume, String companie) {
        this.nume = nume;
        this.companie = companie;
    }

    @Override
    String scrieCerere(int tip) throws Exceptie3Angajat, Exceptie4Angajat, Exceptie5Angajat, Exceptie6Angajat {
        if (tip == 3)
            throw new Exceptie3Angajat("Utilizator Angajat nu poate accesa inlocuireCarnetDeElev");

        if (tip == 4)
            throw new Exceptie4Angajat("Utilizator Angajat nu poate accesa creareActConstitutiv");

        if (tip == 5)
            throw new ExceptieAngajat("Utilizatorul de tip angajat nu poate inainta o cerere de tip reinnoire autorizatie");

        if (tip == 6)
            throw new Exceptie6Angajat("Utilizator Angajat nu poate accesa inregistrareCupoaneDePensie");

        Cerere.tipCerere tipCerere;

        if (tip == 0)
            tipCerere = Cerere.tipCerere.c0;
        else if (tip == 1)
            tipCerere = Cerere.tipCerere.c1;
        else
            tipCerere = Cerere.tipCerere.c2;

        String text;
        text = "Subsemnatul " + this.nume + ", angajat la compania " + this.companie + ", va rog sa-mi aprobati urmatoarea solicitare: " + tipCerere.titlu;

        return text;
    }

    Cerere creareCerere(int tipCerere, int prioritate, Date data) throws Exceptie3Angajat, Exceptie4Angajat, Exceptie5Angajat, Exceptie6Angajat, IOException {
        String textNouaCerere = scrieCerere(tipCerere);
        Cerere nouaCerere = new Cerere(textNouaCerere, data, prioritate);

        this.cereriAsteptare[nrCereriAsteptare] = nouaCerere;
        nrCereriAsteptare ++;

        return nouaCerere;
    }




}
