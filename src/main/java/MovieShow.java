public class MovieShow {
    private final int showID;
    private final int showTime;
    private final int showDate;
    private final int roomID;
    private final String movieID;

    public MovieShow(int pShowID, int pShowTime, int pShowDate, int pRoomID, String pMovieID) {
        this.showID = pShowID;
        this.showTime = pShowTime;
        this.showDate = pShowDate;
        this.roomID = pRoomID;
        this.movieID = pMovieID;
    }
    public int getShowID() {
        return showID;
    }
    public int getShowTime() {
        return showTime;
    }
    public int getShowDate() {
        return showDate;
    }
    public int getRoomID() {
        return roomID;
    }
    public String getMovieID() {
        return movieID;
    }
}

