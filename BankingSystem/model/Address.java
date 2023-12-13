package BankingSystem.model;

public class Address {
    String city;
    String state;
    String pincode;
    String address;

    @Override
    public String toString() {
        return
                "\ncity : " + city + "\n" +
                "state : " + state + "\n" +
                "pincode : " + pincode + "\n" +
                "address : " + address + "\n" ;
    }

    public Address(String city, String state, String pincode, String address) {
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPincode() {
        return pincode;
    }

    public String getAddress() {
        return address;
    }
}
