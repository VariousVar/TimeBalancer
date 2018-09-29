package ru.variousvar.timebalancer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.variousvar.timebalancer.TelegramBot;

/**
 * Configuration for telegram time balancer bot working environment.
 */
@Configuration
public class TelegramConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramConfiguration.class);

    @Value("${telegram.bot.username:}")
    private String botUsername;

    @Value("${telegram.bot.token:}")
    private String botToken;

    @Value("${telegram.bot.url:https://api.telegram.org/}")
    private String botBaseUrl;

    @Bean
    public TelegramBot telegramBot() {
        DefaultBotOptions options = new DefaultBotOptions();
        options.setBaseUrl(botBaseUrl);
        TelegramBot bot = new TelegramBot(options, botUsername, botToken);

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            LOGGER.error("Unable to start telegram bot '{}' : {}", botUsername, e.getMessage(), e);
        }

        return bot;
    }
}
