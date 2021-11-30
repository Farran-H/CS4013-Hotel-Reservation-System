import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Menu {
    static int choice;
    static ArrayList<Hotel> list;
    static Scanner scanner = new Scanner(System.in);
    static String name = "";
    static LocalDateTime checkIn;
    static LocalDateTime checkOut;
    static String days[] = {"Mon",	"Tues",	"Wed", "Thurs",	"Fri", "Sat", "Sun"};

    static {
        list = new FileIO().getAll();
        for (Hotel hotel: list) {
            System.out.println(hotel);
        }
    }

    public static void display() {
        do {
            System.out.println("Menu");
            System.out.println("1) Standard Reservation");
            System.out.println("2) Advance Reservation");
            System.out.println("0) Exit");
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
            }catch (Exception e){
                scanner.next();
                System.out.println("Enter Valid Input.");
                display();
            }

            switch (choice) {
                case 1:
                    System.out.println("Standard Reservation");
                    System.out.print("Enter Name: ");
                    scanner.next();
                    name = scanner.nextLine();
                    standardReservation();
                    break;
                case 2:
                    System.out.println("Advance Reservation");
                    System.out.print("Enter Name: ");
                    scanner.next();
                    name = scanner.nextLine();
                    advanceReservation();
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid input...");
            }
        } while (choice != 0);
    }

    public static int getHotelType() {
        System.out.println(list.size());
        int rch = 0;
        if (!(rch <= 10 && rch >=1)){
            System.out.println("Select Hotel Type");
            int i = 1;
            for (Hotel hotel: list) {
                System.out.println(i+") "+hotel.getRoomType());
                i+=1;
            }
            System.out.println("0) Exit");
            try {
                System.out.print("Your Choice:");
                rch = scanner.nextInt();
            }catch (Exception e){
                scanner.next();
                System.out.println("Enter valid 'Integer' input.");
                getHotelType();
            }
            System.out.println(rch);

        }else {
            System.out.println("Invalid Input...");
            getHotelType();
        }
        return rch;
    }

    public static int getNumberOfRooms(int num){
        int no = 0;
        if (!(no <= num && no > 0)){
            try {
                System.out.println("Select Number of Rooms: ");
                no = scanner.nextInt();
                System.out.println(no);
            }catch (Exception e){
                scanner.next();
                System.out.println("Enter valid 'Integer' input.");
                getNumberOfRooms(num);
            }
        }else {
            System.out.println("Invalid Input...");
            getNumberOfRooms(num);
        }
        return no;
    }

    public static int getNumberOfDays(int num){
        int no = 0;
        if (!(no <= num && no > 0)){
            System.out.println("Select Day: ");
            int i = 1;
            for (String day: days) {
                System.out.println(i+") "+day);
                i+=1;
            }
            try{
                System.out.println("Enter Choice: ");
                no = scanner.nextInt();
                System.out.println(no);
            }catch (Exception e){
                scanner.next();
                System.out.println("Enter valid 'Integer' input.");
                getNumberOfDays(num);
            }
        }else {
            System.out.println("Invalid Input...");
            getNumberOfRooms(num);
        }
        return no;
    }

    public static int getOccupancy(int min, int max){
        int selected = 0;
        if (!(selected >= min && selected <= max)){
            System.out.println("Enter number of Occupancy"+min+"-"+max);
            selected = scanner.nextInt();
        }else {
            scanner.next();
            System.out.println("Invalid Input...");
            getOccupancy(min, max);
        }
        return selected;
    }

    public static void standardReservation() {
        int ch = getHotelType();
        int num = 0, req=0, total = 0;
        if (ch>0){
            Hotel hotel = list.get(ch-1);
            System.out.println(hotel);
            num = getNumberOfRooms(hotel.getNumberOfRooms());
            if (num > 0){
                req = getNumberOfDays(num);
                int arr[] = hotel.getWeekDay();
                total = num * arr[req-1];
                int occ = getOccupancy(hotel.getOccupancyMin(), hotel.getOccupancyMax());
                System.out.println("Hotel room reserved.");
                System.out.println("Your total bill is: "+total);
                checkIn = LocalDateTime.now();
                checkOut = LocalDateTime.now().plusDays(1);
                list.get(ch).setNumberOfRooms(hotel.getNumberOfRooms()-req);
                Reservation reservation = new Reservation(name, "S", checkIn, checkOut, req, hotel.getRoomType(), occ, total);
                FileIO.store(reservation);
            }else {
                System.out.println("No Rooms Are Available...");
                display();
            }
        }else {
            display();
        }
    }

    public static void advanceReservation() {
        int ch = getHotelType();
        int num = 0, req=0, total = 0;
        if (ch>0){
            Hotel hotel = list.get(ch-1);
            System.out.println(hotel);
            num = getNumberOfRooms(hotel.getNumberOfRooms());
            if (num > 0){
                req = getNumberOfDays(num);
                int arr[] = hotel.getWeekDay();
                total = num * arr[req-1];
                int percent = (int)(total*0.05);
                total = total-percent;
                int occ = getOccupancy(hotel.getOccupancyMin(), hotel.getOccupancyMax());
                System.out.println("Hotel room reserved.");
                System.out.println("Your total bill is: "+total);
                checkIn = LocalDateTime.now();
                checkOut = LocalDateTime.now().plusDays(1);
                list.get(ch).setNumberOfRooms(hotel.getNumberOfRooms()-req);
                Reservation reservation = new Reservation(name, "A", checkIn, checkOut, req, hotel.getRoomType(), occ, total);
                FileIO.store(reservation);
            }else {
                System.out.println("No Rooms Are Available...");
                display();
            }
        }else {
            display();
        }
    }
}