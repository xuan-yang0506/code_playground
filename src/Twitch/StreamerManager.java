package Twitch;

import java.util.*;

class StreamerManager {
    private static Set<String> streamerOnline;
    // category -> Set<Streamer>
    private static Map<String, Set<String>> categoryMap;
    // streamer -> category
    private static Map<String, String> streamerCategory;
    private static Map<String, Integer> viewsMap;

    public static String[] solution(String[] streamerInformation, String[] commands) {
        // constructor
        streamerOnline = new HashSet<>();
        categoryMap = new HashMap<>();
        streamerCategory = new HashMap<>();
        viewsMap = new HashMap<>();

        for (int i = 0; i < streamerInformation.length; i = i + 3) {
            String name = streamerInformation[i];
            int views = Integer.parseInt(streamerInformation[i + 1]);
            String category = streamerInformation[i + 2];
            addStreamer(name, views, category);
        }

        return processCmd(commands);
    }

    private static String[] processCmd(String[] commands) {
        List<String> list = new ArrayList<>();

        int index = 0;
        while (index < commands.length) {
            List<String> input = new ArrayList<>();
            if (commands[index].equals("StreamerOnline")) {
                index++;
                for (int i = 0; i < 3; i++) {
                    input.add(commands[index]);
                    index++;
                }
                streamerOnline(input);
            } else if (commands[index].equals("UpdateViews")) {
                index++;
                for (int i = 0; i < 3; i++) {
                    input.add(commands[index]);
                    index++;
                }
                updateViews(input);
            } else if (commands[index].equals("UpdateCategory")) {
                index++;
                for (int i = 0; i < 3; i++) {
                    input.add(commands[index]);
                    index++;
                }
                updateCategory(input);
            } else if (commands[index].equals("StreamerOffline")) {
                index++;
                for (int i = 0; i < 2; i++) {
                    input.add(commands[index]);
                    index++;
                }
                streamerOffline(input);
            } else if (commands[index].equals("ViewsInCategory")) {
                index++;
                for (int i = 0; i < 1; i++) {
                    input.add(commands[index]);
                    index++;
                }
                list.add("" + viewsInCategory(input));
            } else if (commands[index].equals("TopStreamerInCategory")) {
                index++;
                for (int i = 0; i < 1; i++) {
                    input.add(commands[index]);
                    index++;
                }
                list.add(topStreamerInCategory(input));
            } else if (commands[index].equals("TopStreamer")) {
                index++;
                list.add(topStreamer());
            }
        }

        String[] output = new String[list.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = list.get(i);
        }
        return output;
    }

    private static void streamerOnline(List<String> input) {
        String name = input.get(0);
        int views = Integer.parseInt(input.get(1));
        String category = input.get(2);
        addStreamer(name, views, category);
    }

    private static void updateViews(List<String> input) {
        String name = input.get(0);
        int views = Integer.parseInt(input.get(1));
        String category = input.get(2);

        if (streamerCategory.containsKey(name) && streamerCategory.get(name).equals(category)) {
            addStreamer(name, views, category);
        }
    }

    private static void updateCategory(List<String> input) {
        String name = input.get(0);
        String category = input.get(1);
        String newCategory = input.get(2);

        if (streamerCategory.containsKey(name) && streamerCategory.get(name).equals(category)) {
            categoryMap.get(category).remove(name);
            addStreamer(name, viewsMap.get(name), newCategory);
        }
    }

    private static void streamerOffline(List<String> input) {
        String name = input.get(0);
        String category = input.get(1);

        if (streamerCategory.containsKey(name) && streamerCategory.get(name).equals(category)) {
            streamerOnline.remove(name);
            categoryMap.get(category).remove(name);
            streamerCategory.remove(name);
            viewsMap.remove(name);
        }
    }

    private static int viewsInCategory(List<String> input) {
        String category = input.get(0);
        int count = 0;
        if (categoryMap.containsKey(category)) {
            Set<String> set = categoryMap.get(category);
            for (String streamer : set) {
                count += viewsMap.get(streamer);
            }
        }
        return count;
    }

    private static String topStreamerInCategory(List<String> input) {
        String category = input.get(0);
        if (!categoryMap.containsKey(category) || categoryMap.get(category).isEmpty()) {
            return null;
        } else {
            Set<String> set = categoryMap.get(category);
            String output = null;
            int max = -1;
            for (String streamer : set) {
                if (viewsMap.get(streamer) > max) {
                    max = viewsMap.get(streamer);
                    output = streamer;
                }
            }
            return output;
        }
    }

    private static String topStreamer() {
        int max = -1;
        String output = null;
        for (String streamer : streamerOnline) {
            if (viewsMap.get(streamer) > max) {
                max = viewsMap.get(streamer);
                output = streamer;
            }
        }
        return output;
    }


    private static void addStreamer(String streamer, int views, String category) {
        streamerOnline.add(streamer);

        if (!categoryMap.containsKey(category)) {
            categoryMap.put(category, new HashSet<>());
        }
        categoryMap.get(category).add(streamer);

        streamerCategory.put(streamer, category);

        viewsMap.put(streamer, views);
    }
}