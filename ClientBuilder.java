package ro.uvt.fi.dp;

public class ClientBuilder {

    private String clientId;
    private String name;

    private String address;

    public ClientBuilder(String clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public ClientBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Client build() {
        return new Client(clientId, name, address);
    }
}