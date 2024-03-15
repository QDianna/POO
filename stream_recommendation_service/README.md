# Proiect - Stream Recommendation Service

In acest proiect am implementat un sistem smart de recomandari de stream-uri audio pentru utilizatorii unei aplicatii de streamning,
Aplicatiea permite accesul pentru 2 tipuri de utilizatori: ascultatori si creatori; stream-urile sunt de 3 feluri: muzica, podcast si audiobook;
creatorii sunt, de asemenea de 3 feluri in functie de ce stream produc. Recomandarile se fac atat pe baza informatiilor din sistem despre utilizatori,
cat si pe baza informatiilor acumulate de-a lungul rularii aplicatiei.

Sistemul este capabil sa modifice datele existente atunci cand se rulează comenzi de către ascultatori sau streameri (ex: dacă un utilizator asculta un nou
stream, diferit de cele ascultate pana la momentul citirii din fișierul users.txt, atunci istoricul utilizatorului cat și numărul de ascultari al stream-ului
respectiv se vor schimba).

Comenzi streameri: adauga stream, listeaza streamuri, sterge stream.
Comenzi ascultatori: listeaza history, asculta stream, recomanda 5 streamuri dupa preferinta, recomanda 3 streamuri surpriza.

Astfel, recomandarile dupa preferinta se bazeaza urmatoarea logica:
- Din lista de streamers ascultați de utilizator se aleg top 5 stream-uri (neascultate) cu cele mai multe ascultari
- Recomandarea va fi făcută pentru tipul de stream pasat ca parametru (SONG, PODCAST sau AUDIOBOOK)

Recomandarile 'surpriza' se fac dupa urmatorul algoritm:
- Din lista de streamers din aplicație, ce nu au fost ascultați de utilizator se aleg 3 stream-uri ce au fost adaugate
cel mai recent. Dacă au fost adaugate in acceasi zi, atunci se alege stream-ul cu cele mai multe ascultari.
- Recomandarea va fi făcută pentru tipul de stream pasat ca parametru (SONG, PODCAST sau AUDIOBOOK)
