package com.example.demo;

import java.util.List;

public class Document {
    private String id;
    private String story;
    private Author author;
    private List<MentionedUser> mentioned;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public List<MentionedUser> getMentioned() {
        return mentioned;
    }

    public void setMentioned(List<MentionedUser> mentioned) {
        this.mentioned = mentioned;
    }


    public static class Author {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class MentionedUser {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}


