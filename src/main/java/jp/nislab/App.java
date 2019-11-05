package jp.nislab;

public class App {
    public static void main(String[] args) {
        String mode = args[0];

        switch (mode) {
        case "fetch":
            Repository.fetchAllRepoCommitMessages();
            return;
        case "learn":
            try {
                String repoID = args[1];
                if (repoID.equals("all")) {
                    Doc2Vec.learnAllRepo();
                } else {
                    Doc2Vec.learn(repoID);
                }
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("No repoID");
                return;
            }
        default:
            System.out.println("No mode match.");
        }
    }
}
