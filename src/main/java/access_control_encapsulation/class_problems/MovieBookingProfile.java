package access_control_encapsulation;

public class MovieBookingProfile {

    private String name;
    private boolean confirmed;
    private String otp; // Write-only field

    public MovieBookingProfile() {
        this.name = "Guest";
        this.confirmed = false;
        this.otp = null;
    }

    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}