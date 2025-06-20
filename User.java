import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Ride> rideHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.rideHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void addRide(Ride ride) {
        rideHistory.add(ride);
    }

    public List<Ride> getRideHistory() {
        return rideHistory;
    }
}