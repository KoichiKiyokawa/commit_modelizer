package jp.nislab;

import java.io.File;

public class App {
    public static void main(String[] args) {
        File commitMessageFile = new File(args[1]);
        Doc2Vec.learn(commitMessageFile);
    }
}
