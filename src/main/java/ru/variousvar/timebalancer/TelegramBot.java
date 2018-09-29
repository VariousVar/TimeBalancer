package ru.variousvar.timebalancer;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Telegram bot to process user messages.
 *
 * @author Anatoly Sibiryatko
 */
public class TelegramBot extends TelegramLongPollingBot {

    private String username;
    private String token;

    public TelegramBot(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public TelegramBot(DefaultBotOptions options, String username, String token) {
        super(options);
        this.username = username;
        this.token = token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // todo to be implemented
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
