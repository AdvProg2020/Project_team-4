package Model;

import java.io.File;
import java.util.ArrayList;

public class Request extends SaveAble{
    private static ArrayList<Request> allRequest;
    private RequestType requestType;
    private String requestId;

    public Request(String requestType, String requestId) {
        if(requestType.equalsIgnoreCase(String.valueOf(RequestType.ACCOUNT))) {
            this.requestType = RequestType.ACCOUNT;
            Manager.setRegisterSellerAccountRequest(this);
        } else if(requestType.equalsIgnoreCase(String.valueOf(RequestType.OFF))) {
            this.requestType = RequestType.OFF;
            Manager.setEditOffRequests(this);
        } else if(requestType.equalsIgnoreCase(String.valueOf(RequestType.PRODUCT))) {
            this.requestType = RequestType.PRODUCT;
            Manager.setEditProductsRequest(this);
        } else {
            return;
        }
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

    public RequestType getRequestType() {
        return requestType;
    }
}
