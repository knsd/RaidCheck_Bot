import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
    // You can use your own BotSession implementation if needed.
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new DreamTeamRaidCheckBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}