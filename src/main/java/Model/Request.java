package Model;

import java.util.ArrayList;
import java.util.Random;

public abstract class Request extends SaveAble {
    private RequestType requestType;
    //protected String requestId;
    //Random rand = new Random();

    public Request(RequestType requestType) {
        this.requestType = requestType ;
        if(requestType == RequestType.ACCOUNT) {
            Manager.setRegisterSellerAccountRequest(this);
        } else if(requestType == RequestType.PRODUCT) {
            Manager.setEditProductsRequest(this);
        } else if(requestType == RequestType.OFF) {
            Manager.setEditOffRequests(this);
        }
        //this.requestId = rand.nextInt()+"";
    }



    //public String getRequestId() {
    //    return requestId;
    //}


    public RequestType getRequestType() {
        return requestType;
    }
}
