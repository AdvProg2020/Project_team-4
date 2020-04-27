package Model;

public class RequestOff extends Request {
    private Off off;
    public RequestOff(String requestType, Off off) {
        super(requestType);
        this.off = off;
    }

    public Off getOff() {
        return off;
    }
}
