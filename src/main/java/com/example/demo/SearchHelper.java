package com.example.demo;

import java.util.List;

public class SearchHelper {
    public static String findStories(String searchTerm, List<Document> documents) {
        String htmlParagraphs = "";

        for (Document document : documents) {
            String story = document.getStory();
            //Lower case check
            if (story.toLowerCase().contains(searchTerm.toLowerCase())) {
                htmlParagraphs = htmlParagraphs + "\n" + "<p>" + story + "<p>";
            }
        }

        return htmlParagraphs;
    }
}
