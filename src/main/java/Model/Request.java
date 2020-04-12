package Model;

import java.util.ArrayList;

public class Request {
    private static ArrayList<Request> allRequest;
    private String requestType;

    public Request(String requestType) {
        this.requestType = requestType;
        allRequest.add(this);
    }
}
