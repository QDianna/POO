package com.example.project;

import java.io.*;

class User {
    String userName;
    String userPassword;

    User(String name, String password) {
        this.userName = name;
        this.userPassword = password;
    }

    static boolean validateUser(String name, String pwd) {
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userAcc = line.split(",", -1);
                if (name.equals(userAcc[0]) && pwd.equals(userAcc[1]))
                    return true;
            }
        } catch (IOException e) {
            System.out.println("validateUser exception");
            return false;
        }

        return false;
    }

    // comanda 1 'create-user'
    void createUser() {
        // verific daca exista deja utilizatorul cu numele 'name' si parola 'pwd' in fisierul 'users'
        if (validateUser(userName, userPassword)) {
            System.out.println("{'status':'error','message':'User already exists'}");
            return;
        }

        // retin in fisierul 'users' numele si parola userului
        try (FileWriter fw = new FileWriter("users.csv", true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
            out.println(userName + "," + userPassword);

            System.out.println("{'status':'ok','message':'User created successfully'}");

        } catch (IOException e) {
            System.out.println("createUser exception");
            return;
        }
    }

}
