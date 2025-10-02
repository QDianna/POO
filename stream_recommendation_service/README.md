# Stream Recommendation Service

Proiect realizat pentru cursul **Programare Orientată pe Obiect / Sisteme de Recomandare**.  
Implementarea constă într-un sistem smart de recomandări pentru stream-uri audio, cu două tipuri de utilizatori și mai multe tipuri de conținut.

## Descriere
Aplicația gestionează:
- **Utilizatori**: ascultători și creatori
- **Stream-uri**: 3 tipuri (muzică, podcast, audiobook)
- **Creatori**: specializați în funcție de tipul de stream

Recomandările se fac atât pe baza informațiilor inițiale (din `users.txt`), cât și pe baza interacțiunilor din timpul rularii aplicației (istoric de ascultare, număr de ascultări per stream).

Datele se actualizează dinamic:  
- Dacă un utilizator ascultă un stream nou → istoricul și numărul de ascultări al stream-ului se modifică.  
- Dacă un streamer adaugă/șterge conținut → lista de streamuri se actualizează.  

## Funcționalități

### Comenzi pentru **streameri**
- `addStream` – adaugă stream nou
- `listStreams` – afișează streamurile existente
- `deleteStream` – șterge un stream

### Comenzi pentru **ascultători**
- `listHistory` – afișează istoricul utilizatorului
- `listenStream` – marchează ascultarea unui stream
- `recommendPreferred` – recomandă 5 streamuri neascultate din preferințele utilizatorului
- `recommendSurprise` – recomandă 3 streamuri „surpriză” din creatori neascultați

## Algoritmi de recomandare

### Recomandări după preferințe
1. Se identifică streamerii ascultați de utilizator.  
2. Din lista lor de streamuri se aleg **top 5 streamuri neascultate** cu cele mai multe ascultări.  
3. Se filtrează după tipul de stream cerut (SONG, PODCAST, AUDIOBOOK).  

### Recomandări „surpriză”
1. Se selectează streamerii pe care utilizatorul **nu** i-a ascultat.  
2. Din streamurile lor se aleg **cele mai recente 3**.  
   - Dacă au aceeași dată de adăugare → se alege streamul cu cele mai multe ascultări.  
3. Se filtrează după tipul de stream cerut.  

## Structura fișierelor
- `users.txt` – informații utilizatori și istoricul ascultărilor  
- `streams.txt` – detalii streamuri (tip, creator, număr ascultări, dată adăugare)  
- codul sursă Java/Python/C++ (în funcție de implementare)  

## Setup și rulare
1. Compilează proiectul (Java/C++/Python).  
2. Asigură-te că fișierele `users.txt` și `streams.txt` sunt în directorul proiectului.  
3. Rulează programul și introdu comenzile dorite (streamer/ascultător).  

## Concluzie
Proiectul demonstrează implementarea unui **sistem de recomandare** pentru aplicații de streaming audio, combinând:  
- gestionarea utilizatorilor și conținutului,  
- actualizarea dinamică a datelor,  
- recomandări bazate pe preferințe și explorare (surpriză).  
