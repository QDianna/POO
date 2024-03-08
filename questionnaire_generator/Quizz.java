package com.example.project;

import java.io.*;

class Quizz {
    static int quizzId = 1;
    static int answerId = 1;
    static int submisionId = 1;
    String quizzName;
    String[] quizzQuestionIds;
    int questionsCount = 0;

    Quizz (String title) {
        quizzName = title;
        quizzQuestionIds = new String[10];
    }

    // comanda 5 'create-quizz'
    void createQuizz(String[] questions, String creatorUserName) {
        if (validateQuizz(quizzName)) {
            System.out.println("{'status':'error','message':'Quizz name already exists'}");
            return;
        }

        // verific daca intrebarile introduse de utilizator sunt valide
        if (!checkQuestions(questions))
            return;

        // retin in fisierul 'quizzes' informatii despre chestionar: id, numele utilizatorului care l-a creat, numele chestionarului, id-urile intrebarilor - id-urile raspunsurilor fiecarei intrebari
        try (FileWriter fw = new FileWriter("quizzes.csv", true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
            out.print(quizzId + "," + creatorUserName + "," +  quizzName);

            quizzId ++;

            for (int i = 0; i < questionsCount; i ++)
                out.print("," + quizzQuestionIds[i] + " " + printAnswersIds(quizzQuestionIds[i]));

            out.println();
            System.out.println("{'status':'ok','message':'Quizz added succesfully'}");

        } catch (IOException e) {
            System.out.println("createQuizz exception");
            return;
        }
    }

    // verific daca chestionarul cu numele 'title' exista deja in fisierul 'quizzes' (pentru comanda create-quizz)
    static boolean validateQuizz(String title) {
        try (BufferedReader br = new BufferedReader(new FileReader("quizzes.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] quizzInfo = line.split(",", -1);
                if (title.equals(quizzInfo[2]))
                    return true;
            }
        } catch (IOException e) {
            System.out.println("validateQuizz exception");
            return false;
        }

        return false;
    }

    // verifica daca intrebarile unui chestionar nou creat sunt valide (pentru comanda 'create-quizz')
    boolean checkQuestions(String[] questions) {
        if (questions.length > 10) {
            System.out.println("{'status':'error','message':'Quizz has more than 10 questions'}");
            return false;
        }

        for (int i = 0; i < questions.length; i++) {
            String qstId;
            String[] qstInfo = questions[i].split(" ", -1);

            if (qstInfo[0].equals("-question-" + (questionsCount + 1))) {
                qstId = qstInfo[1].substring(1, qstInfo[1].length() - 1);
                boolean ok = false;

                try (BufferedReader br = new BufferedReader(new FileReader("questions.csv"))) {
                    String line;

                    while ((line = br.readLine()) != null) {
                        String[] questionInfo = line.split(",", -1);

                        if (qstId.equals(questionInfo[0])) {
                            ok = true;
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("checkQuestions exception");
                    return false;
                }

                if (!ok) {
                    System.out.println("{'status':'error','message':'Question ID for question " + (questionsCount + 1) + " does not exist'}");
                    return false;
                }

                quizzQuestionIds[questionsCount] = qstId;
                questionsCount ++;
            } else
                return false;
        }

        return true;
    }

    // afiseaza id-urile si valoarea de adevar a raspunsurilor unei intrebari (pentru comanda 'create-quizz')
    static String printAnswersIds (String id) {
        String answers = "(";
        try (BufferedReader br = new BufferedReader(new FileReader("questions.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] questionInfo = line.split(",", -1);

                if (id.equals(questionInfo[0])) {
                    for (int i = 4; i < questionInfo.length; i += 2, answerId ++) {
                        if (i > 4)
                            answers += " ";
                        answers += answerId + "-" + questionInfo[i];
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("printAnswersIds exception");
            return null;
        }

        answers += ")";
        return answers;
    }

    // comanda 6 'get-quizz-by-name'
    static int getIdByTitle(String title) {
        if (!validateQuizz(title)) {
            return -1;
        }
        else {
            try (BufferedReader br = new BufferedReader(new FileReader("quizzes.csv"))) {
                String line;

                while ((line = br.readLine()) != null) {
                    String[] quizzInfo = line.split(",", -1);

                    if (title.equals(quizzInfo[2])) {
                        int id = Integer.parseInt(quizzInfo[0]);
                        return id;
                    }
                }
            } catch (IOException e) {
                System.out.println("getIdByTitle exception");
                return -1;
            }
        }

        return -1;
    }

    // returneaza numele chestionarului cu id-ul 'id'
    static String getTitleById(String id) {

        try (BufferedReader br = new BufferedReader(new FileReader("quizzes.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] quizzInfo = line.split(",", -1);

                if (id.equals(quizzInfo[0])) {
                    String title = quizzInfo[2];
                    return title;
                }
            }

        } catch (IOException e) {
            System.out.println("getTitleById exception");
            return null;
        }

        return null;
    }

    // comanda 7 'get-all-quizzes'
    static void getAllQuizzes(String userName) {
        try (BufferedReader br = new BufferedReader(new FileReader("quizzes.csv"))) {
            System.out.print("{'status':'ok','message':'[");

            String line;

            while ((line = br.readLine()) != null) {
                String[] quizzInfo = line.split(",", -1);

                if (quizzInfo[0].compareTo("1") > 0)
                    System.out.print(", ");

                System.out.print("{\"quizz_id\" : \"" + quizzInfo[0] + "\", \"quizz_name\" : \"" + quizzInfo[2] + "\"");

                try (BufferedReader br2 = new BufferedReader(new FileReader("submissions.csv"))) {
                    System.out.print(", \"is_completed\" : ");

                    String line2;
                    boolean find = false;

                    while ((line2 = br2.readLine()) != null) {
                        String[] submissionInfo = line.split(",", -1);

                        if (submissionInfo[0].equals(userName) && submissionInfo[1].equals(quizzInfo[2])) {
                            System.out.print("\"True\"}");
                            find = true;
                            break;
                        }
                    }

                    if (find == false)
                        System.out.print("\"False\"}");

                } catch (IOException e) {
                    System.out.println("getAllQuizzes exception");
                    return;
                }
            }

            System.out.print("]'}");

        } catch (IOException e) {
            System.out.println("getIdByTitle exception");
            return;
        }
    }

    // comanda 8 'get-quizz-details-by-id'
    static void getDetailsById(int id) {
        try (BufferedReader br = new BufferedReader(new FileReader("quizzes.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] quizzInfo = line.split(",", -1);

                if (id == Integer.parseInt(quizzInfo[0])) {
                    System.out.print("{'status':'ok','message':'[");

                    for (int i = 3; i < quizzInfo.length; i++) {
                        if (i > 3)
                            System.out.print(", ");
                        String[] questionInfo = quizzInfo[i].split(" ", -1);
                        Question.printQuestion(questionInfo[0]);
                    }

                    System.out.print("]'}");
                }
            }
        } catch (IOException e) {
            System.out.println("getDetailsById exception");
            return;
        }
    }

    // comanda 9 'submit-quizz'
    static void submitQuizz(int id, String userName, String[] answers) {
        if (!checkSubmission(id, userName))
            return;

        // retin in fisierul 'submission' date despre incarcarea unui chestionar: id-ul incarcarii, numele utilizatorului, id-ul chestionarului, numele chestionarului, scorul obtinut
        try (FileWriter fw = new FileWriter("submissions.csv", true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
            out.print(submisionId + "," + userName + "," + id + "," + getTitleById(id + ""));

            for (int i = 0; i < answers.length; i++) {
                String[] answrInfo = answers[i].split(" ", -1);
                answers[i] = answrInfo[1].substring(1, answrInfo[1].length() - 1);
            }

            // scorul total obtinut de utilizator pentru chestionar
            long score = Math.round(giveScore(id, answers));

            out.print("," + score);

            if (score < 0)
                score = 0;

            System.out.println("{'status':'ok','message':'" + score + " points'}");
            submisionId ++;

        } catch (IOException e) {
            System.out.println("submitQuizz exception");
            return;
        }
    }

    // verifica daca chestionarul cu id-ul 'id' poate fi compeletat de userul cu numele 'userName' (pentru comanda 'submit-quizz')
    static boolean checkSubmission (int id, String userName) {
        boolean find = false;

        try (BufferedReader br = new BufferedReader(new FileReader("quizzes.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] quizzInfo = line.split(",", -1);

                if (id == Integer.parseInt(quizzInfo[0])) {
                    if (userName.equals(quizzInfo[1])) {
                        System.out.print("{'status':'error','message':'You cannot answer your own quizz'}");
                        return false;
                    }

                    find = true;
                    break;
                }
            }

            if (!find) {
                System.out.print("{'status':'error','message':'No quiz was found'}");
                return false;
            }
        } catch (IOException e) {
            System.out.println("checkSubmission exception 1");
        }

        try (BufferedReader br = new BufferedReader(new FileReader("submissions.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] submitInfo = line.split(",", -1);
                if (userName.equals(submitInfo[1]) && id == Integer.parseInt(submitInfo[2])) {
                    System.out.println("{ “status” : “error”, “message” : “You already submitted this quizz”}");
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("checkSubmission exception 2");
            return false;
        }

        return true;
    }

    // calculez scorul obtinut pentru chestionarul cu id-ul 'qId' si raspunsurile 'answers' ale utilizatorului (pentru comanda 'submit-quizz')
    static double giveScore (int qId, String[] answers) {
        // scorul total pentru chestionar
        double score = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("quizzes.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] quizzInfo = line.split(",", -1);

                if (qId == Integer.parseInt(quizzInfo[0])) {
                    // valoarea unei singure intrebari
                    double questionScore = 100.0/(quizzInfo.length - 3);

                    for (int i = 3; i < quizzInfo.length; i++) {
                        // extrag intrebarile corecte, salvate anterior in fisierul 'quizz' sub forma "('id'-'truth value')"
                        String correctAnswrs = quizzInfo[i].substring(quizzInfo[i].indexOf("(") + 1, quizzInfo[i].indexOf(")"));
                        String[] correctA = correctAnswrs.split(" ", -1);

                        // numarul de intrebari cu valoarea de adevar 1
                        int totalT = correctAnswrs.split(("true"), -1).length - 1;
                        // numarul de intrebari cu valoarea de adevar 0
                        int totalF = correctAnswrs.split("false", -1).length - 1;

                        // valoarea unei intrebari corecte
                        double scoreT = 1.0 / totalT;
                        // valoarea (negativa) a unei intrebari gresite
                        double scoreF = -1 * (1.0 / totalF);

                        // punctele obtinute de utilizator pentru intrebarea curenta
                        double qPoints = 0;

                        // caut in array-ul de raspunsuri ale utilizatorului, 'answers', raspunsurile pentru intrebarea curenta si calculez punctajul obtinut
                        for (int j = 0; j < answers.length; j++) {
                            for (int k = 0; k < correctA.length; k++) {
                                String[] correctAInfo = correctA[k].split("-", -1);
                                if (correctAInfo[0].equals(answers[j])) {
                                    if (correctAInfo[1].equals("true"))
                                        qPoints += scoreT;
                                    else
                                        qPoints += scoreF;
                                }
                            }
                        }

                        // adaug la scorul total punctajul obtinut pe intrebarea curenta
                        score += questionScore * qPoints;

                    }
                }
            }
        } catch (IOException e) {
            System.out.println("giveScore exception");
            return 0;
        }

        return score;
    }

    // comanda 10 'delete-quizz-by-id'
    static void deleteQuizz(int id, String userName) {
        if (!checkDeleteQuizz(id, userName))
            return;

        File oldFile = new File ("quizzes.csv");
        File newFile = new File ("temp.csv");

        // retin id-ul liniei citite (care este acelasi cu id-ul chestionarului de pe liniea respectiva)
        int lineId = 0;

        try (FileWriter fw = new FileWriter("temp.csv", true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
            FileReader fr = new FileReader("quizzes.csv");
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                lineId ++;

                // copiez in fisierul temporar 'temp' doar liniile cu id diferit de id-ul chestionarului pe care vreau sa il sterg
                if (id != lineId)
                    out.println(line);
            }

            oldFile.delete();
            File t = new File("quizzes.csv");
            newFile.renameTo(t);

            System.out.println ("{'status':'ok','message':'Quizz deleted successfully'}");

        } catch (IOException e) {
            System.out.println("deleteQuizz exception");
            return;
        }
    }

    // verific daca chestionarul cu id 'id' poate fi sters de utilizatorul cu numele 'userName' (pentru comanda 'detele-quizz-by-id')
    static boolean checkDeleteQuizz (int id, String userName) {
        boolean find = false;

        try (BufferedReader br = new BufferedReader(new FileReader("quizzes.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {

                String[] quizzInfo = line.split(",", -1);

                if (id == Integer.parseInt(quizzInfo[0])) {
                    if (!userName.equals(quizzInfo[1])) {
                        System.out.print("{'status':'error','message':'This is not your quizz to delete'}");
                        return false;
                    }

                    find = true;
                    break;
                }
            }

            if (!find) {
                System.out.print("{'status':'error','message':'No quiz was found'}");
                return false;
            }
        } catch (IOException e) {
            System.out.println("checkDeleteQuizz exception1");
            return false;
        }

        return true;
    }


    // comanda 11 'get-my-solutions'
    static void getMySolutions(String userName) {
        try (BufferedReader br = new BufferedReader(new FileReader("submissions.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] submitInfo = line.split(",", -1);
                if (userName.equals(submitInfo[1])) {
                    System.out.println("{'status':'ok','message':'[{\"quiz-id\" : \"" + submitInfo [2] + "\", \"quiz-name\" : \"" + submitInfo[3] + "\", \"score\" : \"" + submitInfo[4] + "\", \"index_in_list\" : \"" + submitInfo[0] + "\"}]'}");
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("getMySolutions exception");
            return;
        }
    }
}
