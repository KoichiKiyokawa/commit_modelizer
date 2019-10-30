package jp.nislab;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CommandExecuter {
  /**
   * 指定したディレクトリに移動してから、コマンドを実行 Reference)
   * https://chat-messenger.com/blog/java/runtime-getruntime-exec
   *
   * @param args                            実行するコマンド
   * @param relativePathForWorkingDirectory 移動先のディレクトリ
   *
   * @return 実行結果の文字列
   *
   */
  public static String run(String[] args, String relativePathForWorkingDirectory) {
    String LINE_SEPA = "\n";
    ProcessBuilder pb = new ProcessBuilder(args);
    // getInputStream()で結果が帰ってこないことがあったので、標準出力と標準エラーを混ぜる
    // https://qiita.com/shintaness/items/6dd91260726e555c49e5
    pb.redirectErrorStream(true);
    pb.directory(new File(relativePathForWorkingDirectory));

    try {
      Process p = pb.start();
      InputStream in = null;
      BufferedReader br = null;
      in = p.getInputStream();
      StringBuffer out = new StringBuffer();
      br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null) {
        out.append(line + LINE_SEPA);
      }
      p.waitFor();
      return out.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }
}