package main.java.org.example;

import main.exceptions.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public abstract class Utilizator {
    String nume;
    String companie;
    String scoala;
    String reprezentant;

    Cerere[] cereriAsteptare = new Cerere[100];

    int nrCereriAsteptare;
    abstract String scrieCerere(int tipCerere) throws Exceptie1Persoana, Exceptie3Persoana, Exceptie4Persoana, Exceptie5Persoana, Exceptie6Persoana,
            Exceptie3Angajat, Exceptie4Angajat, Exceptie5Angajat, Exceptie6Angajat,
            Exceptie1Pensionar, Exceptie3Pensionar, Exceptie4Pensionar, Exceptie5Pensionar,
            Exceptie1Elev, Exceptie2Elev, Exceptie4Elev, Exceptie5Elev, Exceptie6Elev,
            Exceptie0EntJur, Exceptie1EntJur, Exceptie2EntJur, Exceptie3EntJur, Exceptie6EntJur;

    abstract Cerere creareCerere(int tipCerere, int prioritate, Date data) throws IOException, Exceptie1Persoana, Exceptie3Persoana, Exceptie4Persoana, Exceptie5Persoana, Exceptie6Persoana,
            Exceptie3Angajat, Exceptie4Angajat, Exceptie5Angajat, Exceptie6Angajat,
            Exceptie1Pensionar, Exceptie3Pensionar, Exceptie4Pensionar, Exceptie5Pensionar,
            Exceptie1Elev, Exceptie2Elev, Exceptie4Elev, Exceptie5Elev, Exceptie6Elev,
            Exceptie0EntJur, Exceptie1EntJur, Exceptie2EntJur, Exceptie3EntJur, Exceptie6EntJur;

    //abstract Cerere retrageCerere();
    //
    //abstract void afisareCereriSolutionate();

    void afisareCereriAsteptare(String fileName) throws IOException {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter(fileName, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.println(nume + " - cereri in asteptare:");

            for (int i = 0; i < nrCereriAsteptare - 1; i++)
                for (int j = i + 1; j < nrCereriAsteptare; j++)
                    if (cereriAsteptare[i].data.compareTo(cereriAsteptare[j].data) > 0) {
                        Cerere aux = cereriAsteptare[i];
                        cereriAsteptare[i] = cereriAsteptare[j];
                        cereriAsteptare[j] = aux;
                    }

            for (int i = 0; i < nrCereriAsteptare; i++)
                pw.println(cereriAsteptare[i].toString());

            pw.flush();

        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException io) {
                System.out.println("afisare cereri asteptare exceptie");
            }
        }
    }
}
