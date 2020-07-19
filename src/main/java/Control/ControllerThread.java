package Control;

import Model.*;
import org.example.App;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ControllerThread extends Thread{
    private Socket socket;
    private Account loggedInAccount = null;
    private String token = null;
    Controller controller;
    ObjectOutputStream outObject;
    ObjectInputStream inObject;

    public ControllerThread(Socket socket) {
        this.socket = socket;
        controller = new Controller();
    }

    @Override
    public void run() {
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream;
        try {
            outObject = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            inObject = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        while(true){
            String input = dataInputStream.readUTF();
            String[] subString = input.split(" ");
            checkFunction(subString, dataOutputStream);
        }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  Controller getOurController() {
        return controller;
    }

    private void checkFunction(String[] subString, DataOutputStream dataOutputStream) throws IOException, ClassNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        if(subString[1].equals("getCartKeySet")){
            for (Object o : controller.getCart().keySet()) {
                stringBuilder.append(o + " ");
            }
            try {
                dataOutputStream.writeUTF(String.valueOf(stringBuilder));
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(subString[1].equalsIgnoreCase("setCurrentAccount")){
            try {
                getOurController().setCurrentAccount((Account) inObject.readObject());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (subString[1].equalsIgnoreCase("getProductWithBarcode")) {
            try {
                outObject.writeObject(Product.getProductWithBarcode(subString[2]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (subString[1].equalsIgnoreCase("getCart")) {
            try {
                outObject.writeObject(((Model.Customer)getOurController().getCurrentAccount()).getCart());
                outObject.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (subString[1].equalsIgnoreCase("getCurrentAccount")) {
            try {
                outObject.writeObject(((Model.Customer)getOurController().getCurrentAccount()));
                outObject.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (subString[1].equalsIgnoreCase("createCategory")) {
            try {
                String name = subString[2];
                ArrayList<String> subCategoriesArray = ((ArrayList<String>)inObject.readObject());
                Thread.currentThread().wait(100);
                ArrayList<String> tagsArray = ((ArrayList<String>)inObject.readObject());
                Thread.currentThread().wait(100);
                ArrayList<String> productsArray = ((ArrayList<String>)inObject.readObject());
                Thread.currentThread().wait(100);
                outObject.writeObject(getOurController().createCategory(name, subCategoriesArray, tagsArray, productsArray));
                outObject.flush();

            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        } else if (subString[1].equalsIgnoreCase("removeCategory")) {
            outObject.writeObject(getOurController().removeCategory(subString[2]));
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("showCategories")) {
            outObject.writeObject(getOurController().showCategories());
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("getCategoryByName")) {
            outObject.writeObject(Category.getCategoryByName(subString[2]));
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("setNameOfCategory")) {
            Category category = (Category) inObject.readObject();
            category.setName(subString[2]);
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setTagsOfCategory")) {
            Category category = (Category) inObject.readObject();
            category.setTags((ArrayList<String>) inObject.readObject());
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setProductsOfCategory")) {
            Category category = (Category) inObject.readObject();
            category.setProducts((ArrayList<String>) inObject.readObject());
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setSubCategoriesOfCategory")) {
            Category category = (Category) inObject.readObject();
            category.setSubCategories((ArrayList<String>) inObject.readObject());
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setCustomersField")) {
            getOurController().setCustomersField(subString[2], subString[3], subString[4], subString[5]);
        } else if (subString[1].equalsIgnoreCase("setCustomerPassWordAndAddress")) {
            getOurController().setCustomerPassWordAndAddress(subString[2], subString[3]);
            SaveAndLoad.getSaveAndLoad().writeJSON(getOurController().getCurrentAccount(), Customer.class.toString(), getOurController().getCurrentAccount().getUserName());
        } else if (subString[1].equalsIgnoreCase("logout")) {
            outObject.writeObject(getOurController().logout());
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("requestSalesHistoryInfoInSeller")) {
            outObject.writeObject(getOurController().requestSalesHistoryInfoInSeller());
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("addANewManager")) {
            Manager.addANewManager(subString[2], subString[3], inObject.readBoolean());
        } else if (subString[1].equalsIgnoreCase("controllerNewAccount")) {
            getOurController().controllerNewAccount(subString[3], subString[4], subString[5]);
        } else if (subString[1].equalsIgnoreCase("login")) {
            getOurController().controllerLogin(subString[3], subString[4]);
        } else if (subString[1].equalsIgnoreCase("changeFields")) {
            getOurController().changeFields(subString[2], subString[3], subString[4], subString[5], subString[6]);
            SaveAndLoad.getSaveAndLoad().writeJSON(getOurController().getCurrentAccount(), Model.Manager.class.toString(), getOurController().getCurrentAccount().getUserName());
        } else if (subString[1].equalsIgnoreCase("getAllProducts")) {
            outObject.writeObject(Product.getAllProducts());
            outObject.flush();
        } else if(subString[1].equalsIgnoreCase("controllerRemoveProduct")) {
            getOurController().controllerRemoveProduct(subString[2]);
        } else if (subString[1].equalsIgnoreCase("getAccountWithName")) {
            outObject.writeObject(Account.getAccountWithName(subString[2]));
            outObject.flush();
            dataOutputStream.writeUTF(Account.getAccountWithName(subString[2]).getClass().toString());
            dataOutputStream.flush();
        } else if (subString[1].equalsIgnoreCase("controllerCreateOffCode")) {
            int result = getOurController().controllerCreateOffCode(subString[2], subString[3], subString[4], subString[5], subString[6], (ArrayList<String>) inObject.readObject());
            outObject.writeObject(result);
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("setEndTimeForOffCode")) {
            CodedOff.getOffCodeWithName(subString[2]).setEndTime(subString[3]);
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setAmountForOffCode")) {
            CodedOff.getOffCodeWithName(subString[2]).setOffAmount(subString[3]);
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setUsageTimeForOffCode")) {
            CodedOff.getOffCodeWithName(subString[2]).setUsageTime(subString[3]);
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setPercentForOffCode")) {
            CodedOff.getOffCodeWithName(subString[2]).setPercent(subString[3]);
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("pay")) {

            outObject.writeObject(getOurController().pay(subString[2]));
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("requestAddProductToCart")) {
            getOurController().requestAddProductToCart(subString[2]);
        } else if (subString[1].equalsIgnoreCase("newComment")) {
            getOurController().newComment(subString[2], (Product) inObject.readObject(), subString[4]);
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setAverageScore")) {
            ((Product) inObject.readObject()).setAverageScore(Integer.parseInt(subString[2]));
        } else if (subString[1].equalsIgnoreCase("setSeen")) {
            ((Product) inObject.readObject()).setSeen(Integer.parseInt(subString[2]));
        } else if (subString[1].equalsIgnoreCase("setNameOfSellerOfProductAddedToCart")) {
            getOurController().setNameOfSellerOfProductAddedToCart(subString[2]);
        } else if (subString[1].equalsIgnoreCase("getAllOffs")) {
            outObject.writeObject(Off.getAllOffs());
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("getEditOffRequests")) {
            outObject.writeObject(Manager.getEditOffRequests());
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("getEditProductRequests")) {
            outObject.writeObject(Manager.getEditOffRequests());
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("getRegisterSellerAccountRequests")) {
            outObject.writeObject(Manager.getEditOffRequests());
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("RequestANewSellerAccountAccept")) {
            getOurController().acceptRequest((RequestANewSellerAccount)inObject.readObject());
        } else if (subString[1].equalsIgnoreCase("RequestProductAccountAccept")) {
            getOurController().acceptRequest((RequestProduct)inObject.readObject());
        } else if (subString[1].equalsIgnoreCase("RequestOffAccountAccept")) {
            getOurController().acceptRequest((RequestProduct)inObject.readObject());
        } else if (subString[1].equalsIgnoreCase("RequestANewSellerAccountDecline")) {
            getOurController().declineRequest((RequestANewSellerAccount)inObject.readObject());
        } else if (subString[1].equalsIgnoreCase("RequestProductAccountDecline")) {
            getOurController().declineRequest((RequestProduct)inObject.readObject());
        } else if (subString[1].equalsIgnoreCase("RequestOffAccountDecline")) {
            getOurController().declineRequest((RequestProduct)inObject.readObject());
        } else if (subString[1].equalsIgnoreCase("createOrEditOffRequest")) {
            getOurController().createOrEditOffRequest(inObject.readObject(), subString[2], subString[3], subString[4], subString[5]);
        } else if (subString[1].equalsIgnoreCase("createProductRequest")) {
            Controller.createProductRequest(subString[2],subString[3], Integer.parseInt(subString[4]), subString[5], subString[6], Integer.parseInt(subString[7]), (ArrayList<String>) inObject.readObject(), subString[8]);
        } else if (subString[1].equalsIgnoreCase("removeProductFromSellerProducts")) {
            getOurController().removeProductFromSellerProducts(subString[2]);
        } else if (subString[1].equalsIgnoreCase("controllerCreateNewManagerAccountFromManager")) {
            outObject.writeObject(getOurController().controllerCreateNewManagerAccountFromManager(subString[2], subString[3]));
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("getusers")) {
            outObject.writeObject(getOurController().getusers((Class) inObject.readObject()));
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("saveProduct")) {
            Product.getProductWithBarcode(subString[2]).setComment(((Product) inObject.readObject()).getComments());
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        }
    }
}
