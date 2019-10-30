package jp.nislab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Repository {

  private static final String repoDir = "repository";

  private static Map<String, String> ID2project = new HashMap<>();
  static {
    ID2project.put("closure", "closure-compiler.git");
    ID2project.put("lang", "commons-lang.git");
    ID2project.put("math", "commons-math.git");
    ID2project.put("jfreechart", "jfreechart");
    ID2project.put("time", "joda-time.git");
    ID2project.put("mockito", "mockito.git");
  };

  public static void fetchAllRepoCommitMessages() {
    for (String repo : ID2project.values()) {
      fetchCommitMessages(repo);
    }
  }

  private static void fetchCommitMessages(String repoName) {
    String[] cmd = "git log --oneline --pretty=format:'%s'".split(" ");
    String commitMessages = CommandExecuter.run(cmd, repoDir);

    File outputDir = new File("commit_messages/");
    if (!outputDir.exists()) {
      outputDir.mkdir();
    }

    try {
      File outputFile = new File(repoName);
      FileWriter fw = new FileWriter(outputFile);
      fw.write(commitMessages);
      fw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
