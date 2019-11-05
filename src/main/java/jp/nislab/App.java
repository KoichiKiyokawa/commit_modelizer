package jp.nislab;

public class App {
    public static void main(String[] args) {
        String mode = args[0];

        switch (mode) {
        case "fetch":
            Repository.fetchAllRepoCommitMessages();
            break;
        case "learn":
            String repoID = args[1];
            if (repoID == null) {
                System.out.println("No repoID.");
                break;
            } else if (repoID.isEmpty()) {
                System.out.println("No repoID.");
                break;
            } else if (repoID.equals("all")) {
                Doc2Vec.learnAllRepo();
                break;
            }
            Doc2Vec.learn(repoID);
            break;
        default:
            System.out.println("No mode match.");
        }
    }
}
