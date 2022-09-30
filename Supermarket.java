

public class Supermarket {

    private String name;
    private String email;
    private String password;
    private Memberships memberships;

    public Supermarket(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.memberships = new Memberships();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Memberships getMemberships() {
        return memberships;
    }

    public void setMemberships(Memberships memberships) {
        this.memberships = memberships;
    }
}

