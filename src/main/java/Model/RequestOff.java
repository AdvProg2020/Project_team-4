package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RequestOff extends Request {
    private Off off;
    public RequestOff(RequestType requestType, Off off) {
        super(requestType);
        this.off = off;
        //Off.getAllOffs().remove(off);
        Manager.getEditOffRequests().add(this);
        SaveAndLoad.getSaveAndLoad().writeJSON(Manager.getEditOffRequests(), ArrayList.class, "editOffRequests");
    }
    public LocalDateTime getStartDate() {
        return off.getStartDate();
    }
    public LocalDateTime getEndDate() {
        return off.getEndDate();
    }
    public ArrayList<String> getProducts() {
        return off.getProducts();
    }
    public int getOffAmount() {
        return off.getOffAmount();
    }

    public String getOffName() {
        return off.getName();
    }

    public Off getOff() {
        return off;
    }

    @Override
    protected String getName() {
        return off.getName();
    }

    @Override
    public String toString() {
        return "RequestOff{" +
                "off=" + off +
                '}';
    }
}
