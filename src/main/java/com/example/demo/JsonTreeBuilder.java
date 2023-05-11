package com.example.demo;

import com.example.demo.Document.MentionedUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonTreeBuilder {
    private static Gson gson = new GsonBuilder()
    .excludeFieldsWithoutExposeAnnotation()
    .create();

    public static Document findDocument(String rootID, List<Document> documents) {
        for (Document document : documents) {
            String documentID = document.getId();
            if (documentID.equals(rootID)) {
                return document;
            }
        }

        return new Document();
    }

    public static JsonObject buildJsonTree(String rootID, List<Document> documents) {
        Document rootDocument = findDocument(rootID, documents);

        //JsonObject arr = buildChildren(rootDocument, documents, new HashSet<>());
        JsonObject result = buildChildren(rootDocument, documents, new HashSet<>());
        System.out.println(gson.toJson(result));
        return result;
        
    }

    private static JsonObject buildChildren(Document rootDocument, List<Document> documents, Set<String> addedDocuments) {
        JsonObject tree = new JsonObject();

        tree.addProperty("id", rootDocument.getId());
        tree.addProperty("story", rootDocument.getStory());

        JsonArray childrenArray = new JsonArray();
        
        if (documents != null) {
            for (Document document : documents) {
                // Check if the document has already been visited
                if (addedDocuments.contains(document.getId())) {
                    continue;
                }
        
                List<Document.MentionedUser> mentionedUsers = document.getMentioned();
                if (mentionedUsers != null) {
                    for (Document.MentionedUser mentionedUser : mentionedUsers) {
                        if (rootDocument.getAuthor().getId().equals(mentionedUser.getId())) {
                            addedDocuments.add(document.getId());
                            JsonObject childObject = buildChildren(document, documents, addedDocuments);
                            childrenArray.add(childObject);
                        }
                    }
                }
            }
        }
    
        if (childrenArray.size() > 0) {
            tree.add("children", childrenArray);
        }
    
        return tree;
    }
}
