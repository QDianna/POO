package com.example.project;

import java.io.FileOutputStream;
import java.io.IOException;

public class Tema1 {
	// verific daca exista argumentele 'nume' si 'parola' si daca exista contul de utilizator
	static boolean checkCredentials(String[] args) {
		boolean n = false, p = false;
		String name, pwd;

		// cauta argumentele
		for (int i = 0; i < args.length; i++) {
			if (args[i].substring(0, 2).equals("-u"))
				n = true;
			if (args[i].substring(0, 2).equals("-p"))
				p = true;
		}

		if (!(n && p)) {
			System.out.println("{'status':'error','message':'You need to be authenticated'}");
			return false;
		}

		name = args[1].substring(4, args[1].length() - 1);
		pwd = args[2].substring(4, args[2].length() - 1);

		// cauta contul de utilizator
		if(!User.validateUser(name, pwd)) {
			System.out.println("{'status':'error','message':'Login failed'}");
			return false;
		}

		return true;
	}

	public static void main(final String[] args) {
		if (args == null) {
			System.out.println("Hello world!");
			return;
		}

		String userName = null, userPassword = null;
		String text = null, type = null, title = null;
		int id = -1;

		switch (args[0]) {
			case "-create-user":
				for (int i = 0; i < args.length; i++) {
					if (args[i].substring(0, 2).equals("-u"))
						userName = args[i].substring(4, args[i].length() - 1);
					if (args[i].substring(0, 2).equals("-p"))
						userPassword = args[i].substring(4, args[i].length() - 1);
				}

				if (userName == null) {
					System.out.println("{'status':'error','message':'Please provide username'}");
					return;
				}

				if (userPassword == null) {
					System.out.println("{'status':'error','message':'Please provide password'}");
					return;
				}

				User newUser = new User(userName, userPassword);
				newUser.createUser();

				break;

			case "-create-question":
				if (!Tema1.checkCredentials(args))
					return;

				for (int i = 0; i < args.length; i++) {
					if (args[i].contains("-text"))
						text = args[i].substring(7, args[i].length() - 1);

					if (args[i].contains("-type"))
						type = args[i].substring(7, args[i].length() - 1);
				}

				if (text == null) {
					System.out.println("{'status':'error','message':'No question text provided'}");
					return;
				}

				if (type == null) {
					System.out.println("{'status':'error','message':'No question type provided'}");
					return;
				}

				String[] answers = new String [args.length - 5];
				System.arraycopy(args, 5, answers, 0, args.length - 5);

				Question newQuestion = new Question(type, text);
				newQuestion.createQuestion(answers);

				break;

			case "-get-question-id-by-text":
				if (!Tema1.checkCredentials(args))
					return;

				text = args[3].substring(7, args[3].length() - 1);
				id  = Question.getIdByText(text);

				if (id >= 1 )
					System.out.println("{'status':'ok','message':'" + id + "'}");
				else
					System.out.println("{'status':'error','message':'Question does not exist'}");

				break;

			case "-get-all-questions":
				if (!Tema1.checkCredentials(args))
					return;

				Question.getAllQuestions();

				break;

			case "-create-quizz":
				if (!Tema1.checkCredentials(args))
					return;

				title = args[3].substring(7, args[3].length() - 1);

				String[] questions = new String [args.length - 4];
				System.arraycopy(args, 4, questions, 0, args.length - 4);

				if (questions.length > 10) {
					System.out.println("{ “status” : “error”, “message” : “Quizz has more than 10 questions”}");
					return;
				}

				Quizz newQuizz = new Quizz(title);
				newQuizz.createQuizz(questions, args[1].substring(4, args[1].length() - 1));  // creator username

				break;

			case "-get-quizz-by-name":
				if (!Tema1.checkCredentials(args))
					return;

				title = args[3].substring(7, args[3].length() - 1);
				id  = Quizz.getIdByTitle(title);

				if (id >= 1 )
					System.out.println("{'status':'ok','message':'" + id + "'}");
				else
					System.out.println("{'status':'error','message':'Quizz does not exist'}");

				break;

			case "-get-all-quizzes":
				if (!Tema1.checkCredentials(args))
					return;

				Quizz.getAllQuizzes(args[1].substring(4, args[1].length() - 1));

				break;

			case "-get-quizz-details-by-id":
				if (!Tema1.checkCredentials(args))
					return;

				id = Integer.parseInt(args[3].substring(5, args[3].length() - 1));

				Quizz.getDetailsById(id);

				break;

			case "-submit-quizz":
				if (!Tema1.checkCredentials(args))
					return;

				for (int i = 0; i < args.length; i++)
					if (args[i].substring(0, 8).equals("-quiz-id"))
						id = Integer.parseInt(args[i].substring(10, args[i].length() - 1));

				if (id == -1) {
					System.out.println("{'status':'error','message':'No quizz identifier was provided'}");
					return;
				}

				String[] userAnswrs = new String [args.length - 4];
				System.arraycopy(args, 4, userAnswrs, 0, args.length - 4);
				Quizz.submitQuizz(id, args[1].substring(4, args[1].length() - 1), userAnswrs);

				break;

			case "-delete-quizz-by-id":
				if (!Tema1.checkCredentials(args))
					return;

				for (int i = 0; i < args.length; i++)
					if (args[i].substring(0, 3).equals("-id"))
						id = Integer.parseInt(args[i].substring(5, args[i].length() - 1));

				if (id == -1) {
					System.out.println("{'status':'error','message':'No quizz identifier was provided'}");
					return;
				}

				Quizz.deleteQuizz(id, args[1].substring(4, args[1].length() - 1));  // username

				break;

			case "-get-my-solutions":
				if (!Tema1.checkCredentials(args))
					return;

				Quizz.getMySolutions(args[1].substring(4, args[1].length() - 1));  //username

				break;

			case "-cleanup-all":
				try {
					new FileOutputStream("users.csv").close();
					new FileOutputStream("questions.csv").close();
					new FileOutputStream("quizzes.csv").close();
					new FileOutputStream("temp.csv").close();
					new FileOutputStream("submissions.csv").close();

					Question.questionId = 1;
					Question.answrId = 1;
					Quizz.quizzId = 1;
					Quizz.answerId = 1;
					Quizz.submisionId = 1;

				} catch (IOException e) {
					System.out.println("cleanup-all exception");
				}

				System.out.println("{ “status” : “ok”, “message” : “Cleanup finished successfully”}");

				break;

			default:
				System.out.println(args[0]);  // debugg
				break;
		}
	}
}
