package Twitch;

import java.util.Arrays;

public class StreamerMain {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(StreamerManager.solution(
                new String[]{"Ninja", "100000", "Fortnite", "Pokimane", "40000", "Valorant"},
                new String[]{"StreamerOnline", "AOC", "75000", "Just Chatting",
                            "UpdateViews", "Ninja", "120000", "Fortnite",
                            "UpdateCategory", "Ninja", "Fortnite", "Warzone",
                            "StreamerOffline", "Ninja", "Fortnite",
                            "TopStreamer"})));
    }
}
