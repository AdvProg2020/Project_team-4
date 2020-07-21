package Control;

import Model.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

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
        DataOutputStream dataOutputStream = null;
        try {
//            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
//            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(os);
            dataInputStream = new DataInputStream(is);
            outObject = new ObjectOutputStream(dataOutputStream);
            inObject = new ObjectInputStream(dataInputStream);
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
        System.out.println(Arrays.toString(subString));
        if(subString[1].equals("getCartKeySet")){
            System.out.println("get cart server");
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
            System.out.println("set current Account server");
            try {
                getOurController().setCurrentAccount((Account) inObject.readObject());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (subString[1].equalsIgnoreCase("getProductWithBarcode")) {
            System.out.println("get product with barcode server");
            try {
                outObject.writeObject(Product.getProductWithBarcode(subString[2]));
                outObject.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (subString[1].equalsIgnoreCase("getCart")) {
            System.out.println("get cart server");
            try {
                outObject.writeObject(((Customer)getOurController().getCurrentAccount()).getCart());
                outObject.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (subString[1].equalsIgnoreCase("getCurrentAccount")) {
            System.out.println("get current Account server");
            System.out.println(getOurController().getCurrentAccount());
            try {
                dataOutputStream.writeUTF(getAccounttype());
                dataOutputStream.flush();
                outObject.writeObject((getOurController().getCurrentAccount()));
                outObject.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (subString[1].equalsIgnoreCase("createCategory")) {
            System.out.println("create category server");
            try {
                String name = subString[2];
                ArrayList<String> subCategoriesArray = ((ArrayList<String>)inObject.readObject());
                ArrayList<String> tagsArray = ((ArrayList<String>)inObject.readObject());
                ArrayList<String> productsArray = ((ArrayList<String>)inObject.readObject());
                outObject.writeObject(getOurController().createCategory(name, subCategoriesArray, tagsArray, productsArray));
                outObject.flush();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (subString[1].equalsIgnoreCase("removeCategory")) {
            System.out.println("remove category server");
            outObject.writeObject(getOurController().removeCategory(subString[2]));
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("showCategories")) {
            System.out.println("show categories server");
            outObject.writeObject(getOurController().showCategories());
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("getCategoryByName")) {
            System.out.println("get category by name server");
            outObject.writeObject(Category.getCategoryByName(subString[2]));
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("setNameOfCategory")) {
            System.out.println("set name of category server");
            Category category = (Category) inObject.readObject();
            category.setName(subString[2]);
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setTagsOfCategory")) {
            System.out.println("set tegs of category server");
            Category category = (Category) inObject.readObject();
            category.setTags((ArrayList<String>) inObject.readObject());
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setProductsOfCategory")) {
            System.out.println("set product of category server");
            Category category = (Category) inObject.readObject();
            category.setProducts((ArrayList<String>) inObject.readObject());
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setSubCategoriesOfCategory")) {
            System.out.println("set subCategories of category server");
            Category category = (Category) inObject.readObject();
            category.setSubCategories((ArrayList<String>) inObject.readObject());
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setCustomersField")) {
            System.out.println("set customers field server");
            getOurController().setCustomersField(subString[2], subString[3], subString[4], subString[5]);
        } else if (subString[1].equalsIgnoreCase("setCustomerPassWordAndAddress")) {
            System.out.println("set customer pass word and address server");
            getOurController().setCustomerPassWordAndAddress(subString[2], subString[3]);
            SaveAndLoad.getSaveAndLoad().writeJSON(getOurController().getCurrentAccount(), Customer.class.toString(), getOurController().getCurrentAccount().getUserName());
        } else if (subString[1].equalsIgnoreCase("logout")) {
            System.out.println("logout server");
            outObject.writeObject(getOurController().logout());
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("requestSalesHistoryInfoInSeller")) {
            System.out.println("request sales history info in seller on server");
            outObject.writeObject(getOurController().requestSalesHistoryInfoInSeller());
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("addANewManager")) {
            System.out.println("add new manager server");
            Manager.addANewManager(subString[2], subString[3], (Boolean) inObject.readObject());
        } else if (subString[1].equalsIgnoreCase("controllerNewAccount")) {
            System.out.println("new Account server");
            int result = getOurController().controllerNewAccount(subString[2], subString[3], subString[4]);
            outObject.writeObject(result);
        } else if (subString[1].equalsIgnoreCase("login")) {
            System.out.println("login server");
            int result = getOurController().controllerLogin(subString[2], subString[3]);
            outObject.writeObject(result);
        } else if (subString[1].equalsIgnoreCase("changeFields")) {
            System.out.println("change fields server");
            getOurController().changeFields(subString[2], subString[3], subString[4], subString[5], subString[6]);
            SaveAndLoad.getSaveAndLoad().writeJSON(getOurController().getCurrentAccount(), Model.Manager.class.toString(), getOurController().getCurrentAccount().getUserName());
        } else if (subString[1].equalsIgnoreCase("getAllProducts")) {
            System.out.println("get all products server");
            outObject.writeObject(Product.getAllProducts());
            outObject.flush();
        } else if(subString[1].equalsIgnoreCase("controllerRemoveProduct")) {
            System.out.println("remove product server");
            getOurController().controllerRemoveProduct(subString[2]);
        } else if (subString[1].equalsIgnoreCase("getAccountWithName")) {
            System.out.println("get Account with name server");
            outObject.writeObject(Account.getAccountWithName(subString[2]));
            outObject.flush();
            dataOutputStream.writeUTF(Account.getAccountWithName(subString[2]).getClass().toString());
            dataOutputStream.flush();
        } else if (subString[1].equalsIgnoreCase("controllerCreateOffCode")) {
            System.out.println("create off code server");
            int result = getOurController().controllerCreateOffCode(subString[2], subString[3], subString[4], subString[5], subString[6], (ArrayList<String>) inObject.readObject());
            outObject.writeObject(result);
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("setEndTimeForOffCode")) {
            System.out.println("set end time for off code server");
            CodedOff.getOffCodeWithName(subString[2]).setEndTime(subString[3]);
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setAmountForOffCode")) {
            System.out.println("set Amount for off code server");
            CodedOff.getOffCodeWithName(subString[2]).setOffAmount(subString[3]);
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setUsageTimeForOffCode")) {
            System.out.println("set usageTime for off code server");
            CodedOff.getOffCodeWithName(subString[2]).setUsageTime(subString[3]);
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setPercentForOffCode")) {
            System.out.println("set percent for off code server");
            CodedOff.getOffCodeWithName(subString[2]).setPercent(subString[3]);
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("pay")) {
            System.out.println("pay server");

            outObject.writeObject(getOurController().pay(subString[2]));
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("requestAddProductToCart")) {
            System.out.println("request add product to cart server");
            getOurController().requestAddProductToCart(subString[2]);
        } else if (subString[1].equalsIgnoreCase("newComment")) {
            System.out.println("new comment server");
            getOurController().newComment(subString[2], (Product) inObject.readObject(), subString[4]);
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setAverageScore")) {
            System.out.println("set average score server");
            ((Product) inObject.readObject()).setAverageScore(Integer.parseInt(subString[2]));
        } else if (subString[1].equalsIgnoreCase("setSeen")) {
            System.out.println("set seen server");
            ((Product) inObject.readObject()).setSeen(Integer.parseInt(subString[2]));
        } else if (subString[1].equalsIgnoreCase("setNameOfSellerOfProductAddedToCart")) {
            System.out.println("set name of seller of product added to cart server");
            getOurController().setNameOfSellerOfProductAddedToCart(subString[2]);
        } else if (subString[1].equalsIgnoreCase("getAllOffs")) {
            System.out.println("get all offs server");
            outObject.writeObject(Off.getAllOffs());
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("getEditOffRequests")) {
            System.out.println("get edit off request server");
            outObject.writeObject(Manager.getEditOffRequests());
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("getEditProductRequests")) {
            System.out.println("get edit prodcut request server");
            outObject.writeObject(Manager.getEditOffRequests());
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("getRegisterSellerAccountRequests")) {
            System.out.println("get register seller Account server");
            outObject.writeObject(Manager.getEditOffRequests());
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("RequestANewSellerAccountAccept")) {
            System.out.println("request a new seller Account Accept server");
            getOurController().acceptRequest((RequestANewSellerAccount)inObject.readObject());
        } else if (subString[1].equalsIgnoreCase("RequestProductAccountAccept")) {
            System.out.println("Request Product Account accept server");
            getOurController().acceptRequest((RequestProduct)inObject.readObject());
        } else if (subString[1].equalsIgnoreCase("RequestOffAccountAccept")) {
            System.out.println("Request off Accouunt Acccept server");
            getOurController().acceptRequest((RequestProduct)inObject.readObject());
        } else if (subString[1].equalsIgnoreCase("RequestANewSellerAccountDecline")) {
            System.out.println("Request a new seller Account decline server");
            getOurController().declineRequest((RequestANewSellerAccount)inObject.readObject());
        } else if (subString[1].equalsIgnoreCase("RequestProductAccountDecline")) {
            System.out.println("Request product Account Decline server");
            getOurController().declineRequest((RequestProduct)inObject.readObject());
        } else if (subString[1].equalsIgnoreCase("RequestOffAccountDecline")) {
            System.out.println("Request off Account decline server");
            getOurController().declineRequest((RequestProduct)inObject.readObject());
        } else if (subString[1].equalsIgnoreCase("createOrEditOffRequest")) {
            System.out.println("create or edit off request server");
            getOurController().createOrEditOffRequest((ArrayList<String>) inObject.readObject(), subString[2], subString[3], subString[4], subString[5]);
        } else if (subString[1].equalsIgnoreCase("createProductRequest")) {
            System.out.println("create product request server");
            Controller.createProductRequest(subString[2],subString[3], Integer.parseInt(subString[4]), subString[5], subString[6], Integer.parseInt(subString[7]), (ArrayList<String>) inObject.readObject(), subString[8]);
        } else if (subString[1].equalsIgnoreCase("removeProductFromSellerProducts")) {
            System.out.println("remove product from seller product server");
            getOurController().removeProductFromSellerProducts(subString[2]);
        } else if (subString[1].equalsIgnoreCase("controllerCreateNewManagerAccountFromManager")) {
            System.out.println("creat new Manager from manager server");
            outObject.writeObject(getOurController().controllerCreateNewManagerAccountFromManager(subString[2], subString[3]));
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("getusers")) {
            System.out.println("get users server");
            outObject.writeObject(getOurController().getusers((Class) inObject.readObject()));
            outObject.flush();
        } else if (subString[1].equalsIgnoreCase("saveProduct")) {
            System.out.println("save product server");
            Product.getProductWithBarcode(subString[2]).setComment(((Product) inObject.readObject()).getComments());
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        }else if(subString[1].equalsIgnoreCase("firstManExist")){
            boolean hasMan = hasMan();
            System.out.println("first man" + hasMan);
            dataOutputStream.writeUTF(String.valueOf(hasMan));
        } else if (subString[1].equalsIgnoreCase("changeFieldsOffSeller")) {
            getOurController().changeFields(subString[2], subString[3], subString[4], subString[5], subString[6]);SaveAndLoad.getSaveAndLoad().writeJSON(getOurController().getCurrentAccount(), Model.Seller.class.toString(), getOurController().getCurrentAccount().getUserName());
        } else if (subString[1].equalsIgnoreCase("changeCompanyName")) {
            getOurController().changeCompanyName(subString[2]);
            SaveAndLoad.getSaveAndLoad().writeJSON(getOurController().getCurrentAccount(), Model.Seller.class.toString(), getOurController().getCurrentAccount().getUserName());
        } else if (subString[1].equalsIgnoreCase("setSellersOfProduct")) {
            Product.getProductWithBarcode(subString[2]).setSellers(subString[3]);
            SaveAndLoad.getSaveAndLoad().saveGenerally();
        } else if (subString[1].equalsIgnoreCase("setProductsOfASeller")) {
            ((Seller)Account.getAccountWithName(subString[2])).setProducts(subString[3]);
        } else if (subString[1].equalsIgnoreCase("setProductEndTime")) {
            Product.getProductWithBarcode(subString[2]).setEndTime(subString[3]);
        }
    }
    private String getAccounttype() {
        String type;
        if(getOurController().getCurrentAccount().getClass().equals(Manager.class)){
            type = "class Model.Manager";
        }else if(getOurController().getCurrentAccount().getClass().equals(Seller.class)){
            type = "class Model.Seller";
        }else{
            type = "class Model.Customer";
        } return type;
    }

    public boolean hasMan(){
        File directory = new File(System.getProperty("user.dir") + "\\" + "class Model.Manager");
        boolean isFirstManagerCreatedOrNot = false;
        if (directory.isDirectory()) {
            String[] files = directory.list();
            if (files.length > 0) {
//                System.out.println(System.getProperty("user.dir") + "\\" + Manager.class);
                isFirstManagerCreatedOrNot = true;
            }
        }
        return isFirstManagerCreatedOrNot;
    }
}
