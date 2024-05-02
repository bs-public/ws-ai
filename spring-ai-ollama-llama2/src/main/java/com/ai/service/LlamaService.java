package com.ai.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;
import com.ai.dto.LlamaResponse;

@Service
public class LlamaService {

  private final ChatClient chatClient;

  public LlamaService(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  public LlamaResponse generateMessage(String promptMessage) {
    String llamaMessage = chatClient.call(promptMessage);

    LlamaResponse response = new LlamaResponse();
    response.setMessage(llamaMessage);
    return response;
  }

}
