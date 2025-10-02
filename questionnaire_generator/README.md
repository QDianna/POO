# Questionnaire Generator
Proiect realizat pentru cursul **Programare Orientată pe Obiect (POO)**.  
Aplicația permite crearea, gestionarea și completarea de chestionare (quizzes) cu întrebări și răspunsuri stocate în fișiere text.  

## Funcționalități implementate

- **Gestionare utilizatori**
  - `create-user`: creează un utilizator nou (nume + parolă) și îl salvează în `users`
  - `validateUser`: verifică dacă există deja contul

- **Gestionare întrebări**
  - `create-question`: adaugă întrebare nouă (id, text, tip, răspunsuri) și o salvează în `questions`
  - `validateQuestion`: verifică existența întrebării
  - `checkAnswers`: validează răspunsurile întrebării
  - `get-question-id-by-text`: returnează id-ul unei întrebări pe baza textului
  - `get-all-questions`: afișează toate întrebările din `questions`

- **Gestionare chestionare (quizzes)**
  - `create-quizz`: creează un chestionar nou (titlu, autor, întrebări + răspunsuri) și îl salvează în `quizzes`
  - `validateQuizz`: verifică dacă există deja un chestionar cu același nume
  - `checkQuestions`: verifică validitatea întrebărilor
  - `get-quizz-by-name`: caută chestionarul după titlu
  - `get-all-quizzes`: afișează toate chestionarele și verifică dacă au fost completate de utilizator
  - `get-quizz-details-by-id`: afișează detaliile întrebărilor dintr-un chestionar

- **Completare și evaluare chestionare**
  - `submit-quizz`: permite unui utilizator să răspundă la întrebările unui chestionar  
    - verifică dacă utilizatorul nu și-a completat deja propriul chestionar  
    - calculează scorul folosind `giveScore`  
    - salvează răspunsurile și punctajul în `submissions`
  - `get-my-solutions`: afișează chestionarele completate de un utilizator și punctajele obținute

- **Ștergere și administrare**
  - `delete-quizz`: șterge un chestionar pe baza id-ului
  - `cleanup-all`: golește toate fișierele și resetează id-urile statice

## Structura fișierelor

- `users` – informații despre utilizatori (username, parolă)  
- `questions` – întrebări (id, text, tip, răspunsuri)  
- `quizzes` – chestionare (id, autor, titlu, întrebări, răspunsuri)  
- `submissions` – soluții trimise de utilizatori + scoruri  

## Setup și rulare
1. Compilează și rulează proiectul cu Java (sau IDE-ul folosit).  
2. Operațiile se fac prin apelarea metodelor clasei corespunzătoare (`User`, `Question`, `Quizz`).  
3. Datele se persistă automat în fișiere text (`users`, `questions`, `quizzes`, `submissions`).  

## Concluzie
Aplicația demonstrează concepte de **POO** precum:  
- clase și obiecte,  
- metode de validare,  
- gestionarea datelor în fișiere,  
- separarea logicii pe entități (User, Question, Quizz).  
