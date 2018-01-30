package main;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Game {

    private int rightAnswers = 0;
    private HashMap signMap;
    private Kana kanaRound;
    private LinkedList<Kana> roundQueue;



    public Game(int KANA){
        switch (KANA) {
            case Kana.HIRAGANA:
                trainerController.setButtonTexts(Kana.hiraganaSigns); break;
            case Kana.KATAKANA:
                trainerController.setButtonTexts(Kana.katakanaSigns); break;
            default:
                System.out.print("ERROR IN SETTING BUTTONTEXTS");
                trainerController.setButtonTexts(Kana.hiraganaSigns); break;
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
}
