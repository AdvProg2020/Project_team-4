package Model;

public class RequestOff extends Request{
    private Off off;
    public RequestOff(String requestType, String requestId, Off off) {
        super(requestType, requestId);
        this.off = off;
    }

    public Off getOff() {
        return off;
    }
}
