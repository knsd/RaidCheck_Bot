import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        DataSaver dataSaver = new DataSaver();
        DreamTeamRaidCheckBot dreamTeamRaidCheckBot = new DreamTeamRaidCheckBot(dataSaver);
        DateAndTimeCheckThread dateAndTimeCheckThread = new DateAndTimeCheckThread(dreamTeamRaidCheckBot, dataSaver);
        dateAndTimeCheckThread.start();

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(dreamTeamRaidCheckBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}