package org.ankit.demo.simplechat.chatmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;

class ChatControllerTest {

    @Mock
    private OllamaChatModel ollamaChatModel;

    @InjectMocks
    private ChatController chatController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testChat_ShouldReturnResponseFromOllamaModel() {
        // Arrange
        String promptText = "Hello, AI!";
        String expectedResponse = "Hello, human! How can I assist you today?";

        // Mock the ChatResponse and the method chain
        ChatResponse mockResponse = mock(ChatResponse.class, RETURNS_DEEP_STUBS);
        when(mockResponse.getResult().getOutput().getText()).thenReturn(expectedResponse);
        when(ollamaChatModel.call(any(Prompt.class))).thenReturn(mockResponse);

        // Act
        String actualResponse = chatController.chat(promptText);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        verify(ollamaChatModel).call(any(Prompt.class));
    }

    @Test
    void testChat_ShouldUseCorrectModelAndOptions() {
        // Arrange
        String promptText = "Test prompt";
        String expectedResponse = "Test response";

        // Mock the ChatResponse and the method chain
        ChatResponse mockResponse = mock(ChatResponse.class, RETURNS_DEEP_STUBS);
        when(mockResponse.getResult().getOutput().getText()).thenReturn(expectedResponse);

        // Verify the prompt has the correct content and options
        when(ollamaChatModel.call(any(Prompt.class))).thenAnswer(invocation -> {
            Prompt prompt = invocation.getArgument(0);

            // Verify prompt content
            assertEquals(promptText, prompt.getContents());

            // Verify options
            OllamaOptions options = (OllamaOptions) prompt.getOptions();
            // The actual model name at runtime is "llama3.2" even though we use OllamaModel.LLAMA3_2
            assertEquals("llama3", options.getModel());
            assertEquals(0.4, options.getTemperature());

            return mockResponse;
        });

        // Act
        String actualResponse = chatController.chat(promptText);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }
}
