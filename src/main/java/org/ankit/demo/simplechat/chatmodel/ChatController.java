package org.ankit.demo.simplechat.chatmodel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final OllamaChatModel ollamaChatModel;

    @Autowired
    public ChatController(OllamaChatModel ollamaChatModel) {
        this.ollamaChatModel = ollamaChatModel;
    }

    private final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @GetMapping("/chat/{prompt}")
    public String chat(@PathVariable("prompt") String prompt) {

        logger.debug("Received request for chat prompt '{}'", prompt);

        ChatResponse response = ollamaChatModel.call(
                new Prompt(
                        prompt,
                        OllamaOptions.builder()
                                .model(OllamaModel.LLAMA3)
                                .temperature(0.4)
                                .build()
                ));

        String string = response.getResult().getOutput().getText();

        logger.debug("Received response for chat prompt '{}'", string);

        return string;
    }

}
