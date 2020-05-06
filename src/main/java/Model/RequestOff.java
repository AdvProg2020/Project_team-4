package Model;

import java.util.ArrayList;

public class RequestOff extends Request {
    private Off off;
    public RequestOff(RequestType requestType, Off off) {
        super(requestType);
        this.off = off;
        Off.getAllOffs().remove(off);
        SaveAndLoad.getSaveAndLoad().writeJSON(Off.getAllOffs(), ArrayList.class, "allOffs");
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
}
