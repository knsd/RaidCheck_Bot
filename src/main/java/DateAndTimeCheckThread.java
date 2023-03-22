import java.time.DayOfWeek;
import java.time.LocalDateTime;
public class DateAndTimeCheckThread extends Thread{
   private DreamTeamRaidCheckBot dreamTeamRaidCheckBot;
   private DataSaver dataSaver;
   DateAndTimeCheckThread (DreamTeamRaidCheckBot dreamTeamRaidCheckBot, DataSaver dataSaver){
    this.dreamTeamRaidCheckBot = dreamTeamRaidCheckBot;
    this.dataSaver = dataSaver;
   }

    public void run(){
        while(true) {
            try{
                Thread.sleep(500);

                LocalDateTime date = LocalDateTime.now();
                DayOfWeek day = date.getDayOfWeek();
                //System.out.println(date);

                if (day.getValue()==1 || day.getValue()==3 || day.getValue()==4 || day.getValue()==7){
                    if(date.getHour()==12){
                        if(dataSaver.getLastDateCheck() != day.getValue()){
                            dreamTeamRaidCheckBot.sendPoll();
                            dataSaver.setLastDateCheck(day.getValue());
                        }
                    }
                    if(date.getHour()==18){
                        if(dataSaver.getLastResultCheck() != day.getValue()){
                            dreamTeamRaidCheckBot.sendResult();;
                            dataSaver.setLastResultCheck(day.getValue());
                        }
                    }
                }
            }
            catch(InterruptedException e){
                System.out.println("Thread has been interrupted");
            }
        }
    }
}
