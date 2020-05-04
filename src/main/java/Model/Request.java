package Model;

import java.util.ArrayList;
import java.util.Random;

public class Request extends SaveAble {
    private RequestType requestType;
    protected String requestId;
    Random rand = new Random();

    public Request(String requestType) {
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
        this.requestId = rand.nextInt()+"";
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
        return requestId + "";
    }

    public RequestType getRequestType() {
        return requestType;
    }
}
