package Model;

import java.util.ArrayList;
import java.util.Objects;

public class RequestOff extends Request {
    private Off off;
    public RequestOff(RequestType requestType, Off off) {
        super(requestType);
        this.off = off;
        //Off.getAllOffs().remove(off);
//        Manager.getEditOffRequests().add(this);
        // for duplicate creating off
        SaveAndLoad.getSaveAndLoad().writeJSON(Manager.getEditOffRequests(), ArrayList.class.toString(), "editOffRequests");
    }

    public void setOff(Off off) {
        this.off = off;
    }



    public String getStartDate() {
        return off.getStartDate();
    }
    public String getEndDate() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestOff that = (RequestOff) o;
        return off.equals(that.off);
    }

    @Override
    public int hashCode() {
        return Objects.hash(off);
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
