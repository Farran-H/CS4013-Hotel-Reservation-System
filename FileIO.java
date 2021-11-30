import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
    ArrayList<Hotel> list = new ArrayList<>();
    static FileWriter csvWriter;

    public FileIO() {
    }
    public ArrayList<Hotel> getAll(){
        try {
            File file = new File("l4Hotels.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] arr;
            br.readLine();
            br.readLine();
            while((line = br.readLine()) != null) {
                arr = line.split(",");
                int integer[] = new int[7];
                for (int i = 0; i < 7; i++) {
                    integer[i] = Integer.parseInt(arr[i+5]);
                }
                list.add(new Hotel(arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), integer));
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return list;
    }

    public static void store(Reservation res){
        try {
            csvWriter = new FileWriter("reservations.csv", true);
            csvWriter.append(res.getReservationNumber()+","+res.getReservationName()+","+res.getReservationType()+","+res.getCheckIn()+","+res.getCheckOut()+","+res.getNumberRooms()+","+res.getRoomType()+","+res.getOccupancy()+","+res.getTotalCost()+"\n");
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
