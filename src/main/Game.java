package main;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Game {

    private Thread clock = new Thread(){
      @Override
      public void run(){
          try {
              for (int i = 0; i < 1000; i++){
                  sleep(1000);
                  wastedTime++;
              }
          }
          catch (InterruptedException e){
              System.out.println("Clock interrupted");
          }
      }
    };
    private int rightAnswers = 0;
    private HashMap signMap;
    private Kana kanaRound;
    private LinkedList<Kana> roundQueue;
    private int wastedTime = 0;

    public void start(){
        clock.start();
    }

    public void stop(){
        clock.interrupt();
    }

    public double getProgressBarStep(){
        double step = (1/(double)Kana.KANA_READINGS.length);
        return step;
    }

    public Game(int KANA){
        switch (KANA) {
            case Kana.HIRAGANA:
                TrainerController.setButtonTexts(Kana.HIRAGANA_SIGNS); break;
            case Kana.KATAKANA:
                TrainerController.setButtonTexts(Kana.KATAKANA_SIGNS); break;
            default:
                System.out.print("ERROR IN SETTING BUTTONTEXTS");
                TrainerController.setButtonTexts(Kana.HIRAGANA_SIGNS); break;
        }

        signMap = Kana.getKanaMap(KANA);
        roundQueue = Kana.getKanaList(KANA);
        long seed = System.nanoTime();
        Collections.shuffle(roundQueue, new Random(seed));
        kanaRound = roundQueue.poll();
    }

    public String getRoundReading() {
        return kanaRound.getReading();
    }

    public int nextRound(String txt){
        if (signMap.get(txt) == kanaRound.getReading()){
            rightAnswers++;
            if(roundQueue.isEmpty()){
                return 0;
            }
            kanaRound = roundQueue.poll();
            return 2;
        }
        if(roundQueue.isEmpty()){
            return 0;
        }
        kanaRound = roundQueue.poll();
        return 1;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }


    public int getWastedTime() {
        return wastedTime;
    }
}
