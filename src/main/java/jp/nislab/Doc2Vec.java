package jp.nislab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.paragraphvectors.ParagraphVectors;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.ops.transforms.Transforms;

public class Doc2Vec {

  /**
   *
   * @param commitMessageFile File that saves commit messages.
   */
  public static void learn(File commitMessageFile) {
    TokenizerFactory t = new DefaultTokenizerFactory();
    t.setTokenPreProcessor(new CommonPreprocessor());

    try {
      SentenceIterator iterator = new BasicLineIterator(commitMessageFile);
      System.out.println("Builging model...");
      ParagraphVectors vec = new ParagraphVectors.Builder().batchSize(1000).epochs(1).trainWordVectors(true)
          .minWordFrequency(1)
          // .useAdaGrad(false)
          .layerSize(100).iterations(5) // バッチサイズから自動的に決まる？ ref) https://qiita.com/kenta1984/items/bad75a37d552510e4682
          .seed(1).learningRate(0.025).minLearningRate(1e-3).sampling(0)
          // .negativeSample(0)
          .iterate(iterator).tokenizerFactory(t)
          // .workers(6)
          .windowSize(5)
          // .labelsSource(new LabelsSource(Arrays.asList("negative",
          // "neutral","positive")))
          // .stopWords(new ArrayList<String>())
          .build();

      System.out.println("Learning...");
      vec.fit();
      System.out.println("Saving model...");
      WordVectorSerializer.writeParagraphVectors(vec, commitMessageFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

  public static void calcSim(File commitMessageFile, String strA, String strB) {
    System.out.println("Loading model...");
    try {
      ParagraphVectors vec = WordVectorSerializer.readParagraphVectors(commitMessageFile);
      TokenizerFactory t = new DefaultTokenizerFactory();
      t.setTokenPreProcessor(new CommonPreprocessor());
      vec.setTokenizerFactory(t);
      // コサイン類似度を測定
      // caution! モデルの中に存在していない文章は無理
      // double sim = vec.similarity(strA, strB); 方法1
      INDArray vecA = vec.inferVector(strA);
      INDArray vecB = vec.inferVector(strB);
      double sim = Transforms.cosineSim(vecA, vecB);
      System.out.println(String.format("the distance between %s and %s is %f", strA, strB, sim));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}