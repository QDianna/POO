package main.java.org.example;

import main.exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ManagementPrimarie {

    Utilizator[] colectieUtilizatori = new Utilizator[100];
    static int numarUtilizatori;

    void adaugaUtilizator(String tip, String nume, String extraInfo) {
        Utilizator nouUtilizator;

        if(tip.equals("persoana")) {
            nouUtilizator = new Persoana(nume);
        } else if(tip.equals("angajat")) {
            nouUtilizator = new Angajat(nume, extraInfo);
        } else if(tip.equals("pensionar")) {
            nouUtilizator = new Pensionar(nume);
        } else if(tip.equals("elev")) {
            nouUtilizator = new Elev(nume, extraInfo);
        } else {
            nouUtilizator = new EntitateJuridica(nume, extraInfo);
        }

        colectieUtilizatori[numarUtilizatori] = nouUtilizator;
        numarUtilizatori ++;
    }

    public static void main(String[] args) throws IOException, ParseException,
            Exceptie1Persoana, Exceptie3Persoana, Exceptie4Persoana, Exceptie5Persoana, Exceptie6Persoana,
            Exceptie3Angajat, Exceptie4Angajat, Exceptie5Angajat, Exceptie6Angajat,
            Exceptie1Pensionar, Exceptie3Pensionar, Exceptie4Pensionar, Exceptie5Pensionar,
            Exceptie1Elev, Exceptie2Elev, Exceptie4Elev, Exceptie5Elev, Exceptie6Elev,
            Exceptie0EntJur, Exceptie1EntJur, Exceptie2EntJur, Exceptie3EntJur, Exceptie6EntJur {

        ManagementPrimarie primarie = new ManagementPrimarie();

        String outputPathname = "src/main/resources/output/" + args[0];
        File file = new File(outputPathname);
        file.createNewFile();

        try {
            String inputPathname = "src/main/resources/input/" + args[0];

            File f = new File(inputPathname);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] data = line.split(";", -1);

                if(data[0].equals("adauga_utilizator")) {

                    data[1] = data[1].substring(1);
                    data[2] = data[2].substring(1);
                    data[3] = data[3].substring(1);
                    primarie.adaugaUtilizator(data[1], data[2], data[3]);

                }


                else if (data[0].equals("cerere_noua")) {
                    data[1] = data[1].substring(1);
                    data[2] = data[2].substring(1);
                    data[3] = data[3].substring(1);
                    data[4] = data[4].substring(1);

                    String nume = data[1];
                    for (int i = 0; i < numarUtilizatori; i++) {
                        if (nume.equals(primarie.colectieUtilizatori[i].nume)) {

                            for (Cerere.tipCerere t : Cerere.tipCerere.values()) {

                                if (t.titlu.equals(data[2])) {

                                    int p = Integer.parseInt(data[4]);

                                    try {
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                                        Date d = formatter.parse(data[3]);
                                        String dd = formatter.format(d);

                                            FileWriter myWriter = new FileWriter("filename.txt");
                                            if (primarie.colectieUtilizatori[i] instanceof Angajat) {
                                                Angajat a = (Angajat) primarie.colectieUtilizatori[i];
                                                myWriter.write(a.nume + a.companie);
                                                myWriter.close();
                                            }


                                        primarie.colectieUtilizatori[i].creareCerere(t.ordinal(), p, d);

                                    } catch (ParseException e) {
                                        System.out.println("Parsing error");
                                    }
                                }
                            }
                        }
                    }
                }

                else if (data[0].equals("afiseaza_cereri_in_asteptare")) {
                    data[1] = data[1].substring(1);
                    String nume = data[1];
                    for (int i = 0; i < numarUtilizatori; i++) {
                        if (nume.equals(primarie.colectieUtilizatori[i].nume)) {
                            primarie.colectieUtilizatori[i].afisareCereriAsteptare(outputPathname);
                        }
                    }
                }
                System.out.println(line);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file " + args[0]);
        }
    }
}