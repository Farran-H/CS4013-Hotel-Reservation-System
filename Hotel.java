import java.util.Arrays;

public class Hotel {
    String roomType;
    int numberOfRooms;
    int occupancyMin;
    int occupancyMax;
    int weekDay[];

    public Hotel() {
    }

    public Hotel(String roomType, int numberOfRooms, int occupancyMin, int occupancyMax, int[] weekDay) {
        this.roomType = roomType;
        this.numberOfRooms = numberOfRooms;
        this.occupancyMin = occupancyMin;
        this.occupancyMax = occupancyMax;
        this.weekDay = weekDay;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getOccupancyMin() {
        return occupancyMin;
    }

    public void setOccupancyMin(int occupancyMin) {
        this.occupancyMin = occupancyMin;
    }

    public int getOccupancyMax() {
        return occupancyMax;
    }

    public void setOccupancyMax(int occupancyMax) {
        this.occupancyMax = occupancyMax;
    }

    public int[] getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int[] weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "roomType='" + roomType + '\'' +
                ", numberOfRooms=" + numberOfRooms +
                ", occupancyMin=" + occupancyMin +
                ", occupancyMax=" + occupancyMax +
                ", weekDay=" + Arrays.toString(weekDay) +
                '}';
    }
}
