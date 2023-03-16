import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {

        DreamTeamRaidCheckBot dreamTeamRaidCheckBot = new DreamTeamRaidCheckBot();
        DateAndTimeCheckThread dateAndTimeCheckThread = new DateAndTimeCheckThread(dreamTeamRaidCheckBot);
        dateAndTimeCheckThread.start();

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(dreamTeamRaidCheckBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}