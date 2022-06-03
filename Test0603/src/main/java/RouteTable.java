import java.util.ArrayList;
import java.util.List;

public class RouteTable {
    private int ID;
    private GateWay gateWay;
    List<Subnet> subnetList = new ArrayList<Subnet>();

    InternetGateWay internetGateWay = new InternetGateWay();
    NatGateWay natGateWay = new NatGateWay();

    public RouteTable() {}

    public void addSubnet(Subnet subnet) {
        subnetList.add(subnet);
    }

    public void setGateWay(GateWay gateWay) {
        this.gateWay = gateWay;
    }
}
