# Tema 2 - Townhall Management System

In aceasta tema am impementat un sistem digital de management pentru o primarie, pentru a facilita desfasurarea activitatilor precum înregistrarea unei afaceri,
schimbarea actelor de identitate sau înregistrarea legitimației de student. Cum majoritatea cetățenilor necesita cereri pentru schimbarea de documente sau anumite task-uri
administrative, se dorește gestionarea cât mai eficientă a acestora.

In primul rand este necesara inregistrarea utilizatorilor in baza de date a primariei; asadar am creat o colectie care va retine obiecte de tip 'Utilizator' - clasa abstracta
ce va fi explicata in continuare. Apoi parsez comenzile primite de sistem si le execut sau le refuz daca actiunea utilizatorului incalca anumite reguli. Pe scurt,
dacă un tip de cerere nu se pretează unui anumit utilizator (de exemplu, un Pensionar solicita înlocuirea carnetului de elev), actiunea va genera o exceptie pe care o tratez
in mod corespunzator.

Sistemul admite diferite tipuri de utilizatori (elev, pensionar, entitate juridica, angajat, persoana), fiecare avand un anumit set de functionalitati disponibile.
Fiecare utilizator trebuie să îndeplinească anumite caracteristici, modelate printr-o clasă abstractă Utilizator; aceasta clasa contine, de asemenea, metode abstracte
ce expun anumite functionalitati si vor fi implementate ulterior, in clase speficice fiecarui tip de utilizator (creare cerere, scriere cerere)
dar si metode comune tuturor utilizatorilor (afisare cereri).

