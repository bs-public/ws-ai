package com.ai;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.ai.controller.OpenAIController;

@WebMvcTest(controllers = OpenAIController.class)
class OpenAIControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ChatClient chatClient;

  @Test
  public void testGetCompletion_success() throws Exception {
    String testMessage = "Hello, world!";
    String expectedResponse = "Hello, Spring AI!";

    when(chatClient.call(testMessage)).thenReturn(expectedResponse);

    mockMvc.perform(get("/v1/chat/completions") //
        .param("message", testMessage) //
        .accept(MediaType.APPLICATION_JSON)) //
        .andExpect(status().isOk()) //
        .andExpect(jsonPath("$.generation").value(expectedResponse));
  }

  @Test
  public void testGetCompletion_failure() throws Exception {
    String testMessage = "Hello, world!";
    String errorMessage = "Failed to connect";

    when(chatClient.call(testMessage)).thenThrow(new RuntimeException(errorMessage));

    mockMvc.perform(get("/v1/chat/completions") //
        .param("message", testMessage) //
        .accept(MediaType.APPLICATION_JSON)) //
        .andExpect(status().isInternalServerError()) //
        .andExpect(jsonPath("$.error").value("Failed to process the request: " + errorMessage));
  }

}
