package com.example.demo;

import java.util.List;

import com.google.gson.JsonObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    
	private RestConsumer restConsumer = new RestConsumer();

	@GetMapping("/search")
	public String search(@RequestParam String searchTerm) {

		List<Document> response = restConsumer.getPosts();
		String stories = SearchHelper.findStories(searchTerm, response);

		return stories;
	}

	@GetMapping("/mentionedtree")
	public String mentionedtree(@RequestParam String authorID) {

		List<Document> response = restConsumer.getPosts();
		JsonObject tree = JsonTreeBuilder.buildJsonTree(authorID, response);

		return tree.toString();
	}

}
