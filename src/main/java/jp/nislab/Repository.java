package jp.nislab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Repository {

    private static final String repoDir = "repositories/";

    public static void fetchAllRepoCommitMessages() {
        for (RepoEnum repo : RepoEnum.values()) {
            fetchCommitMessages(repo.name(), repo.repoID);
        }
    }

    private static void fetchCommitMessages(String repoID, String repoName) {
        String[] cmd = "git log --oneline --pretty=format:%s".split(" ");
        String commitMessages = CommandExecuter.run(cmd, repoDir + repoName);

        File outputDir = new File("commit_messages/");
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }

        try {
            File outputFile = new File(String.format("commit_messages/%s.txt", repoID));
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            FileWriter fw = new FileWriter(outputFile);
            fw.write(commitMessages);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
