public class Movie {

    private final int movieID;
    private final String movieName;
    private final String releaseDate;
    private final String movieGenre;

    public Movie(int pMovieID, String pMovieName, String pReleaseDate, String pMovieGenre) {
        this.movieID  = pMovieID;
        this.movieName = pMovieName;
        this.releaseDate = pReleaseDate;
        this.movieGenre = pMovieGenre;
    }

    public int getMovieID() {
        return movieID;
    }
    public String getMovieName() {
        return movieName;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public String getMovieGenre() {
        return movieGenre;
    }
}
