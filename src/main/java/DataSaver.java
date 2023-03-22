import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class DataSaver {
    private int receivedReply;
    private boolean negativeReply;
    private int lastDateCheck;
    private int lastResultCheck;

    private Semaphore semaphore;

    public DataSaver() {
        semaphore = new Semaphore(1);

        File savedData = new File("SavedData.txt");
        try {
            FileReader fileReader = new FileReader(savedData);
            Scanner scanner = new Scanner(savedData);
            receivedReply = Integer.parseInt(scanner.nextLine());
            lastDateCheck = Integer.parseInt(scanner.nextLine());
            lastResultCheck = Integer.parseInt(scanner.nextLine());
            negativeReply = Boolean.parseBoolean(scanner.nextLine());

            fileReader.close();
        }
        catch (IOException e) {
            System.out.println("File wasn't read");
        }
    }

    public int getReceivedReply() {
        return receivedReply;
    }

    public void setReceivedReply(int receivedReply) {
        this.receivedReply = receivedReply;
        saveCurrentData();
    }

    public boolean isNegativeReply() {
        return negativeReply;
    }

    public void setNegativeReply(boolean negativeReply) {
        this.negativeReply = negativeReply;
        saveCurrentData();
    }

    public int getLastDateCheck() {
        return lastDateCheck;
    }

    public void setLastDateCheck(int lastDateCheck) {
        this.lastDateCheck = lastDateCheck;
        saveCurrentData();
    }

    public int getLastResultCheck() {
        return lastResultCheck;
    }

    public void setLastResultCheck(int lastResultCheck) {
        this.lastResultCheck = lastResultCheck;
        saveCurrentData();
    }

    public void saveCurrentData (){
        try {
            semaphore.acquire();
        }
        catch (Exception e) {
            System.out.println("Error");
        }

        File savedData = new File("SavedData.txt");
        try{
            FileWriter fileWriter = new FileWriter(savedData);
            fileWriter.write(Integer.toString(receivedReply));
            fileWriter.write("\n");
            fileWriter.write(Integer.toString(lastDateCheck));
            fileWriter.write("\n");
            fileWriter.write(Integer.toString(lastResultCheck));
            fileWriter.write("\n");
            fileWriter.write(Boolean.toString(negativeReply));
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println("File wasn't updated");
        }
        semaphore.release();
    }

}
