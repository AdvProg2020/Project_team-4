package Model;

import java.io.File;
import java.util.ArrayList;

public class Request extends SaveAble{
    private static ArrayList<Request> allRequest;
    private String requestType;
    private String requestId;

    public Request(String requestType, String requestId) {
        this.requestType = requestType;
        this.requestId = requestId;
        allRequest.add(this);
    }



    public String getRequestId() {
        return requestId;
    }

    public static Request getRequestByName(String name) {
        for (Request request : allRequest) {
            if (request.getRequestId().equalsIgnoreCase(name)) {
                return request;
            }
        }
        return null;
    }

    @Override
    protected String getName() {
        return requestId;
    }
}
