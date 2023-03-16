import java.time.DayOfWeek;
import java.time.LocalDateTime;
public class DateAndTimeCheckThread extends Thread{
   private DreamTeamRaidCheckBot dreamTeamRaidCheckBot;
   private int lastDateCheck;

   DateAndTimeCheckThread (DreamTeamRaidCheckBot dreamTeamRaidCheckBot){
    this.dreamTeamRaidCheckBot = dreamTeamRaidCheckBot;
   }

    public void run(){
        while(true) {
            try{
                Thread.sleep(500);

                LocalDateTime date = LocalDateTime.now();
                DayOfWeek day = date.getDayOfWeek();
                //System.out.println(date);

                if (day.getValue()==1 || day.getValue()==3 || day.getValue()==4 || day.getValue()==7){
                    if(date.getHour()==20){
                        if(lastDateCheck != day.getValue()){
                            dreamTeamRaidCheckBot.sendPoll();
                            lastDateCheck = day.getValue();
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
