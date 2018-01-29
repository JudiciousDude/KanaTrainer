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
                Controller.buttonTexts = Kana.hiraganaSigns; break;
            case Kana.KATAKANA:
                Controller.buttonTexts = Kana.katakanaSigns; break;
            default:
                Controller.buttonTexts = Kana.hiraganaSigns; break;
        }

        signMap = Kana.getKanaMap(KANA);
        roundQueue = Kana.getKanaList(KANA);
        long seed = System.nanoTime();
        Collections.shuffle(roundQueue, new Random(seed));
        kanaRound = roundQueue.poll();
    }

    public String getRoundLabel() {
        return kanaRound.getReading();
    }

    public boolean nextRound(String txt){
        if(roundQueue.isEmpty()){
            System.out.print("Right answers count: " + rightAnswers);
            return false;
        }
        if (signMap.get(txt) == kanaRound.getReading()){
            kanaRound = roundQueue.poll();
            rightAnswers++;
            System.out.print('+');
            return true;
        }
        kanaRound = roundQueue.poll();
        return false;
    }

}
