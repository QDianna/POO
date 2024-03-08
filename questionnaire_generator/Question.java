package com.example.project;

import java.io.*;

class Question {
    static int questionId = 1;
    static int answrId = 1;
    int questionType;
    // 1 for single 2 for multiple
    int answersCount = 0;
    String questionText;
    Answer[] questionAnswers;

    Question(String type, String text) {
        if (type.equals("single"))
            questionType = 1;
        else
            questionType = 2;

        questionText = text;
        questionAnswers = new Answer[5];
    }

    // comanda 2 'create-question'
    void createQuestion(String[] answers) {
        if (validateQuestion(questionText)) {
            System.out.println("{'status':'error','message':'Question already exists'}");
            return;
        }

        // verific daca raspunsurile introduse de utilizator sunt valide
        if(!checkAnswers(answers))
            return;

        // retin in fisierul 'questions' detalii despre intrebare: id, text, type, answers - answers truth value
        try (FileWriter fw = new FileWriter("questions.csv", true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
            out.print(questionId + "," + questionText + ",");

            if (questionType == 1)
                out.print("single");
            else
                out.print("multiple");

            for (int i = 0; i < answersCount; i++)
                out.print("," + questionAnswers[i].toString());

            out.println();

            questionId ++;

            System.out.println("{'status':'ok','message':'Question added successfully'}");

        } catch (IOException e) {
            System.out.println("createQuestion exception");
            return;
        }
    }

    // verific daca intrebarea cu textul 'text' exista deja in fisierul 'questions' (pentru comanda 'create-question')
    static boolean validateQuestion(String text) {
        try (BufferedReader br = new BufferedReader(new FileReader("questions.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] questionInfo = line.split(",", -1);
                if (text.equals(questionInfo[1]))
                    return true;
            }
        } catch (IOException e) {
            System.out.println("validateQuestion exception");
            return false;
        }

        return false;
    }

    // verifica daca raspunsurile unei intrebari nou create sunt valide (pentru comanda 'create-question')
    boolean checkAnswers(String[] answers) {
        if (answers.length == 0) {
            System.out.println("{'status':'error','message':'No answer provided'}");
            return false;
        }

        if (answers.length <= 2) {
            System.out.println("{'status':'error','message':'Only one answer provided'}");
            return false;
        }

        if (answers.length > 10) {
            System.out.println("{'status':'error','message':'More than 5 answers were submitted'}");
            return false;
        }

        // retin cate raspunsuri corecte are o intrebare
        int findTrue = 0;

        for (int i = 0; i < answers.length;) {
            String answr;
            boolean truth;
            String[] answerInfo = answers[i].split(" ", -1);

            if (answerInfo[0].equals("-answer-" + (answersCount + 1))) {
                answr = answerInfo[1].substring(1, answerInfo[1].length() - 1);

                if (findAnswer(answr) && answersCount >= 1) {
                    System.out.println("{'status':'error','message':'Same answer provided more than once'}");
                    return false;
                }

                i ++;

            } else {
                System.out.println("{'status':'error','message':'Answer " + (answersCount + 1) + " has no answer description'}");
                return false;
            }

            String[] answersTruth = answers[i].split(" ", -1);

            if (answersTruth[0].equals("-answer-" + (answersCount + 1) + "-is-correct")) {
                if (answersTruth[1].equals("'0'"))
                    truth = false;
                else {
                    truth = true;
                    findTrue ++;
                }

                if (findTrue > 1 && questionType == 1) {
                    System.out.println("{'status':'error','message':'Single correct answer question has more than one correct answer'}");
                    return false;
                }

                i ++;
            } else {
                System.out.println("{'status':'error','message':'Answer " + (answersCount + 1) + " has no answer correct flag'}");
                return false;
            }

            Answer newAnswer = new Answer(answr, truth);
            addAnswer(newAnswer);
        }

        return true;
    }

    // verific daca raspunsul cu textul 'answr' exista deja in cadrul intrebarii
    boolean findAnswer(String answr) {
        for (int i = 0; i < answersCount; i++)
            if (questionAnswers[i].answer.equals(answr))
                return true;

        return false;
    }
    void addAnswer(Answer a) {
        questionAnswers[answersCount] = a;
        answersCount ++;
    }

    // comanda 3 'get-question-id-by-text'
    static int getIdByText(String text) {
        if (!validateQuestion(text))
            return -1;

        else {
            try (BufferedReader br = new BufferedReader(new FileReader("questions.csv"))) {
                String line;

                while ((line = br.readLine()) != null) {
                    String[] questionInfo = line.split(",", -1);

                    if (text.equals(questionInfo[1])) {
                        int id = Integer.parseInt(questionInfo[0]);
                        return id;
                    }
                }
            } catch (IOException e) {
                System.out.println("getIdByText exception");
                return -1;
            }
        }

        return -1;
    }

    // comanda 4 'get-all-questions'
    static void getAllQuestions() {
        try (BufferedReader br = new BufferedReader(new FileReader("questions.csv"))) {
            System.out.print("{'status':'ok','message':'[");

            String line;

            while ((line = br.readLine()) != null) {
                String[] questionInfo = line.split(",", -1);

                if (questionInfo[0].compareTo("1") > 0)
                    System.out.print(", ");

                System.out.print("{\"question_id\" : \"" + questionInfo[0] + "\", \"question_name\" : \"" + questionInfo[1] + "\"}");
            }

            System.out.print("]'}");

        } catch (IOException e) {
            System.out.println("getAllQuestions exception");
            return;
        }
    }

    // afiseaza detaliile unei intrebari (in formatul dorit pentru comanda 'get-quizz-details-by-id')
    static void printQuestion(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader("questions.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] questionInfo = line.split(",", -1);

                if (questionInfo[0].equals(id)) {
                    System.out.print("{\"question-name\":\"" + questionInfo[1] + "\", \"question_index\":\"" + questionInfo[0] + "\", \"question_type\":\"" + questionInfo[2] + "\", \"answers\":\"[");

                    for (int i = 3; i < questionInfo.length; i += 2, answrId ++) {
                        if (i > 3)
                            System.out.print(", ");

                        System.out.print("{\"answer_name\":\"" + questionInfo[i] +"\", \"answer_id\":\"" + answrId +"\"}");
                    }

                    System.out.print("]\"}");
                }
            }
        } catch (IOException e) {
            System.out.println("printQuestion exception");
            return;
        }
    }

}