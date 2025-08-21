import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.io.CharArrayWriter;

import java.time.LocalDateTime;

public class WordFrequencyGame {
    public String getResult(String sentence){


        if (sentence.split("\\s+").length==1) {
            return sentence + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");

                List<Input> wordsList = new ArrayList<>();
                for (String s : words) {
                    Input input = new Input(s, 1);
                    wordsList.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> wordsGroup =getListMap(wordsList);

                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : wordsGroup.entrySet()){
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                wordsList = list;

                wordsList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Input w : wordsList) {
                    String s = w.getValue() + " " +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            }

            else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }


}