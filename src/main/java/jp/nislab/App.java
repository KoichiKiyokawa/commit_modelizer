package jp.nislab;

import java.io.File;

public class App {
    public static void main(String[] args) {
        String mode = args[0];

        switch (mode) {
        case "fetch":
            Repository.fetchAllRepoCommitMessages();
            break;
        case "learn":
            String project = args[1];
            File commitMessageFile = new File(String.format("commit_messages/%s.txt", project));
            Doc2Vec.learn(commitMessageFile);
            break;
        default:
            System.out.println("No mode match.");
        }
    }
}
