package main.java.org.example;

import main.exceptions.*;
import java.io.*;
import java.util.Date;

public class Angajat extends Utilizator{
    public Angajat(String nume, String companie) {
        this.nume = nume;
        this.companie = companie;
    }

    @Override
    String scrieCerere(int tip) throws Ex_Ang_3, Ex_Ang_4, Ex_Ang_5, Ex_Ang_6 {
        if (tip == 3)
            throw new Exceptie3Angajat("Utilizator de tip 'Angajat' nu poate inainta o cerere de tip 'inlocuire carnet de elev'");

        if (tip == 4)
            throw new Exceptie4Angajat("Utilizator de tip 'Angajat'  nu poate inainta o cerere de tip 'creare act constitutiv'");

        if (tip == 5)
            throw new ExceptieAngajat("Utilizatorul de tip 'Angajat' nu poate inainta o cerere de tip 'reinnoire autorizatie'");

        if (tip == 6)
            throw new Exceptie6Angajat("Utilizator de tip 'Angajat' nu poate inainta o cerere de tip  'inregistrare cupoane de pensie'");

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
