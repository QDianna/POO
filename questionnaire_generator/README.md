# tema-1-QDianna
tema-1-QDianna created by GitHub Classroom

-- create-user --  
Instantiez un obiect 'User' cu nume si parola si apelez metoda 'createUser'.
Verific prin metoda 'validateUser' daca exista deja un cont de utilizator.
Retin informatiile despre user (nume, parola) in fisierul 'users'.

-- create-question --  
Instantiez un obiect 'Question' cu tip si text si apelez metoda 'createQuestion'.
Metoda primeste ca parametru un array cu raspunsurile intrebarii; verifica prin
metoda 'validateQuestion' daca exista deja aceasta intrebare; apoi verifica prin
metoda 'checkAnswers' daca raspunsurile intrebarii sunt valide.
Retin informatiile despre intrebare (id, text, tip, raspunsuri) in fisierul 'questions'.

-- get-question-id-by-text --  
Caut in fisierul 'Questions' intrebarea cu textul primit de metoda 'getIdByText' ca parametru
si returnez, daca intrebarea exista, id-ul acesteia.

-- get-all-questions --  
Afisez informatiile din fisierul 'Questions' in formatul dorit.

-- create-quizz --  
Instantiez un obiect 'Quizz' cu nume si apelez metoda 'createQuizz'.
Metoda primeste ca parametru intrebarile quizz-ului.
Verific prin metoda 'validateQuizz' daca exista deja un quizz cu numele primit ca parametru,
si prin metoda 'checkQuestions' daca intrebarile quizz-ului sunt valide.
Retin informatiile despre quizz (id, username-ul creatorului, titlu, intrebari + raspunsuri) in
fisierul 'quizzes'.

-- get-quizz-by-name --  
Caut in fisierul 'quizzes', chestionarul cu titlul primit de metoda 'getIdByTitle' ca parametru.
Returnez id-ul, daca acesta exista.

-- get-all-quizzes --  
Afisez toate chestionarele din fisierul 'quizzes' in formatul dorit, prin metoda 'getAllQuizzes'
Caut in fisierul 'submissions' daca chestionarul a fost completat de userul cu username-ul primit
de metoda ca parametru.

-- get-quizz-details-by-id --  
Afisez detaliile (intrebarile) chestionarului cu id-ul dat ca parametru functiei 'getDetailsById'.
Intrebarile sunt printate in formatul dorit prin metoda 'Question.printQuestion'. Aceasta primeste ca
parametru id-ul intrebarii din chestionar si printeaza informatiile (nume, tip, raspunsuri) din fisierul 'questions'.

-- submit-quizz --  
Prin metoda 'submitQuizz' retin informatiile (primite ca parametrii) despre raspunsurile 'answers',
ale utilizatorului cu numele 'userName', la chestionarul cu id 'id'.
Verific daca acest user poate completa chestionarul (daca nu l-a mai completat si daca nu este al lui).
Apoi scriu informatiile precizate in fisierul 'submissions', la care se adauga punctajul obtinut.
Punctajul este obtinut prin metoda 'giveScore' care primeste id-ul chestionarului si raspunsurile utilizatorului
si cauta in fisierul 'quizzes' informatiile despre chestionarul respectiv. 
Pentru fircare intrebare, retin id-urilor raspunsurilor si valoarea lor de adevar intr-un array 'correctA',
iar apoi verific raspunsurile utilizatorului, adunand un punctaj pozitiv 'scoreT' pentru cele corecte si
unul negativ 'scoreF' pentru cele gresite.

-- delete-quizz --  
Creez un fisier temporar 'temp' in care copiez toate chestionarele cu id diferit de parametrul 'id' primit
de metoda 'deleteQuizz', sterg fisierul original 'quizzes' si il redenumesc pe cel temporar.

-- get-my-solutions --  
Metoda 'getMySolutions' parcurge fisierul 'submissions' si afiseaza chestionarele la care utilizatorul cu numele 'userName'
a raspuns, precum si punctajul obtinut.

-- cleanup-all --  
Golesc toate fisierele deschise si resetez id-urile statice.


