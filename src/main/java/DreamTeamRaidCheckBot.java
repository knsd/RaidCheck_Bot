import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DreamTeamRaidCheckBot extends TelegramLongPollingBot {
    private final Long chatID1 = -1001516615641L;
    private final Long chatID2 = 1328572042L;

    private DataSaver dataSaver;

    DreamTeamRaidCheckBot (DataSaver dataSaver){
        this.dataSaver = dataSaver;
    }
    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText() && toReact(update.getMessage())) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());
            // System.out.println(update.getMessage().getChatId());

        } else if(update.hasPollAnswer()){
            PollAnswer pollAnswer = update.getPollAnswer();
            List <Integer> optionIds = pollAnswer.getOptionIds();
            if (optionIds.isEmpty()){
                dataSaver.setReceivedReply(dataSaver.getReceivedReply() - 1);
            } else {
                dataSaver.setReceivedReply(dataSaver.getReceivedReply() + 1);
                for (Integer option: optionIds) {
                    if (option == 1) {
                        dataSaver.setNegativeReply(true);
                        break;
                    }
                }
            }
            System.out.println(update.getPollAnswer().getUser().getFirstName());
        }
    }

    @Override
    public String getBotUsername() {
        return "DreamTeamRaidCheck_bot";
    }

    @Override
    public String getBotToken() {
        return "5657681076:AAFeyV7FL9D0k79gUfFr_aYMK0Lwz9hMRVE";
    }

    public boolean toReact(Message message){
        System.out.println(message.getChatId());
        if (Objects.equals(message.getChatId(), chatID1) || Objects.equals(message.getChatId(), chatID2)) {
            return true;
        }
        return false;
    }

    public void sendPoll(){
        List<String> options = new ArrayList<>();
        options.add("да");
        options.add("нет");

        SendPoll poll = new SendPoll();
        poll.setChatId(chatID1.toString());
        poll.setIsAnonymous(false);
        poll.setQuestion("Сбору быть или не быть, вот в чем вопрос");
        poll.setOptions(options);

        try {
            execute(poll); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendResult(){
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(chatID1.toString());
        if (dataSaver.getReceivedReply() == 4 & !dataSaver.isNegativeReply()) {
            message.setText("да будет сбор");
        } else {
            message.setText("Сбор отменен, трифекта сама себя не сделает, лентяи");
        }
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        dataSaver.setReceivedReply(0);
        dataSaver.setNegativeReply(false);
    }
}
