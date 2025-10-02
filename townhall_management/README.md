# Townhall Management System

Proiect realizat pentru cursul **Programare Orientată pe Obiect (POO)**.  
Scopul aplicației este de a simula un sistem digital de management pentru o primărie, care să faciliteze activitățile administrative precum:  
- înregistrarea unei afaceri,  
- schimbarea actelor de identitate,  
- înregistrarea legitimației de student.  

---

## Descriere generală
- Cetățenii interacționează cu sistemul prin trimiterea de **cereri**.  
- Fiecare cerere este validată în funcție de **tipul utilizatorului** (ex: un pensionar nu poate solicita înlocuirea carnetului de elev).  
- Dacă o cerere nu este compatibilă cu utilizatorul, se generează o **excepție**, care este tratată corespunzător.  

---

## Modelul obiectual

### Clasa abstractă `Utilizator`
- Reține informații comune tuturor utilizatorilor.  
- Expune metode abstracte pentru:  
  - crearea de cereri,  
  - scrierea cererilor,  
- Include și metode comune tuturor (ex. afișare cereri).  

### Tipuri de utilizatori
- **Elev**  
- **Pensionar**  
- **Entitate juridică**  
- **Angajat**  
- **Persoană fizică**  

Fiecare tip de utilizator are funcționalități specifice, implementate în clase derivate.  

---

## Funcționalități cheie
- Înregistrare utilizatori în baza de date a primăriei.  
- Parsarea și execuția comenzilor trimise de utilizatori.  
- Validarea cererilor în funcție de reguli.  
- Gestionarea și afișarea cererilor pentru fiecare utilizator.  
- Tratarea excepțiilor atunci când un utilizator încearcă o acțiune nevalidă.  

---

## Structura proiectului
- `Utilizator.java` – clasă abstractă, baza ierarhiei.  
- `Elev.java`, `Pensionar.java`, `EntitateJuridica.java`, `Angajat.java`, `Persoana.java` – implementări concrete.  
- `Cerere.java` – model pentru cererile administrative.  
- `Main.java` – punctul de intrare, unde se parsează comenzile și se rulează logica aplicației.  

---

## Concluzie
Aplicația demonstrează folosirea conceptelor de **moștenire, clase abstracte și polimorfism** pentru a modela un sistem administrativ real.  
