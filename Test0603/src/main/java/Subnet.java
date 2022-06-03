public class Subnet {
    private int ID;
    private String region;
    private String IP;
    private RouteTable routeTable;

    public Subnet() {}

    public Subnet(int ID, String IP, String region) {
        this.ID = ID;
        this.IP = IP;
        this.region = region;
    }

    public String transfer(String msg) {
        return msg;
    }
}
