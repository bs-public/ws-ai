package com.ai.controller;

import java.util.Map;
import org.springframework.ai.chat.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAIController {

  private final ChatClient chatClient;

  public OpenAIController(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  @GetMapping("/v1/chat/completions")
  public ResponseEntity<Map<String, String>> getCompletion(@RequestParam(value = "message",
      defaultValue = "Tell me something Spring AI Project") String message) {
    try {
      String response = chatClient.call(message);
      return ResponseEntity.ok(Map.of("generation", response));
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(Map.of("error", "Failed to process the request: " + e.getMessage()));
    }
  }

}
