package util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringDataParser {
    List<String> input;

    List<String> answer;
    public StringDataParser(String filename) {
        this.input = new ArrayList<>();
        this.answer = new ArrayList<>();

        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] arr = line.split(" ");
                input.add(arr[0]);
                answer.add(arr[1]);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getInput() {
        return input;
    }

    public List<String> getAnswer() {
        return answer;
    }
}
