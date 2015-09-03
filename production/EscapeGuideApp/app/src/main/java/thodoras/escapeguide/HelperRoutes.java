package thodoras.escapeguide;

/**
 * Created by thodoras on 9/25/14.
 */
public class HelperRoutes {


    private String image;
    private String title;
    private String topPlaces;
    private String days;
    private String distance;
    private String destination;
    private String pointsOfInterest;
    private String charge;
    private String description;

    public HelperRoutes(){

    }

    public String getImage() {
        return image;
    }

    public String getCharge() {
        return charge;
    }

    public String getPointsOfInterest() {
        return pointsOfInterest;
    }

    public void setPointsOfInterest(String pointsOfInterest) {
        this.pointsOfInterest = pointsOfInterest;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopPlaces() {
        return topPlaces;
    }

    public void setTopPlaces(String topPlaces) {
        this.topPlaces = topPlaces;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
