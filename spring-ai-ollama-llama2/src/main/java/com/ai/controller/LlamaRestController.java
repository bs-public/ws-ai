package com.ai.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ai.dto.LlamaResponse;
import com.ai.service.LlamaService;

@RestController
public class LlamaRestController {

  private final LlamaService llamaService;

  public LlamaRestController(LlamaService llamaService) {
    this.llamaService = llamaService;
  }

  @GetMapping("/v1/chat/generate")
  public ResponseEntity<LlamaResponse> generate(@RequestParam(value = "promptMessage",
      defaultValue = "Tell me something about Java?") String promptMessage) {
    final LlamaResponse response = llamaService.generateMessage(promptMessage);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }


}
