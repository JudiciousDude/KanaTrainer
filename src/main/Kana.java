package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Kana {
    private String sign;
    private String reading;

    public final static int HIRAGANA = 196;
    public final static int KATAKANA = 197;

    public static final String[] HIRAGANA_SIGNS = {"あ", "い", "う", "え", "お", "か", "き", "く", "け", "こ", "さ", "し", "す", "せ", "そ",
            "た", "ち", "つ", "て", "と", "な", "に", "ぬ", "ね", "の", "は", "ひ", "ふ", "へ", "ほ", "ま", "み", "む", "め",
            "も", "ら", "り", "る", "れ", "ろ", "や", "ゆ", "よ", "わ", "を", "ん"};

    public static final String[] KATAKANA_SIGNS = {"ア", "イ", "ウ", "エ", "オ", "カ", "キ", "ク", "ケ", "コ", "サ", "シ", "ス", "セ", "ソ",
            "タ", "チ", "ツ", "テ", "ト", "ナ", "ニ", "ヌ", "ネ", "ノ","ハ", "ヒ", "フ", "ヘ", "ホ", "マ", "ミ", "ム", "メ",
            "モ", "ラ", "リ", "ル", "レ", "ロ", "ヤ" , "ユ", "ヨ", "ワ", "ヲ", "ン"};

    public static final String[] KANA_READINGS = {"a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko", "sa", "shi", "su", "se", "so",
            "ta", "chi", "tsu", "te", "to", "na", "ni", "nu", "ne", "no", "ha", "hi", "fu", "he", "ho", "ma", "mi", "mu", "me",
            "mo", "ra", "ri", "ru", "re", "ro", "ya", "yu", "yo", "wa", "wo", "n"};

    public Kana(String sign, String reading){
        this.sign = sign;
        this.reading = reading;
    }

    private void setSign(String sign){this.sign = sign;}

    private void setReading(String reading){this.reading = reading;}

    public String getSign(){return sign;}

    public String getReading() {
        return reading;
    }

    public static LinkedList<Kana> getKanaList(int KANA){
        LinkedList<Kana> kana = new LinkedList<>();
        int i = 0;

        switch (KANA){
            case HIRAGANA:
                for (String s : KANA_READINGS) {
                    kana.add(new Kana(HIRAGANA_SIGNS[i], s));
                    i++;
                }
                break;
            case KATAKANA:
                for (String s : KANA_READINGS) {
                    kana.add(new Kana(KATAKANA_SIGNS[i], s));
                    i++;
                }
                break;
            default:
                for (String s : KANA_READINGS) {
                    kana.add(new Kana(HIRAGANA_SIGNS[i], s));
                    i++;
                }
                break;
        }

        return kana;
    }

    public static Map<String, String> getKanaMap(int KANA){
        Map<String, String> map = new HashMap<>();
        int i = 0;

        switch (KANA){
            case HIRAGANA:
                for (String s : KANA_READINGS) {
                    map.put(HIRAGANA_SIGNS[i], s);
                    i++;
                }
                break;
            case KATAKANA:
                for (String s : KANA_READINGS) {
                    map.put(KATAKANA_SIGNS[i], s);
                    i++;
                }
                break;
            default:
                for (String s : KANA_READINGS) {
                    map.put(HIRAGANA_SIGNS[i], s);
                    i++;
                }
                break;
        }

        return map;
    }
}
