package org.example;

import Model.Category;
import Model.Off;
import Model.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import Control.Controller;

import java.io.IOException;
import java.util.*;

import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ProductsPage {
    public static boolean calledFromOff = false;
    public ArrayList<Product> allProduct;
    private ArrayList<String> sellerTag = new ArrayList<>();
    private ArrayList<String> comanyTag = new ArrayList<>();
    public TableColumn name;
    public TableColumn id;
    public TableColumn price;
    public TableColumn existNumber;
    public TableView table;
    public ListView sortList;
    public TableColumn seenNumber;
    public TableColumn score;
    public Button reverseButton;
    public AnchorPane mainAnchorPane;
    public TableColumn tags;
    public TableColumn createDate;
    public TableColumn endTime;
    public TableColumn remainTime;
    public Slider slider;
    public TableColumn image;
    private CheckBox offCheckBox;
    private CheckBox available;

    private HashSet<String> filters = new HashSet<>();

    public void initialize() {
        allProduct = new ArrayList<>(Product.getAllProducts());
        allProduct.sort(Comparator.comparing(Product::getLocalDateTime));
        initializeTable();
        initializeSort();
        checkBoxForFilter();
        int max = 0;
        for (Product product : Product.getAllProducts()) {
            if(product.getCost() > max)
                max = product.getCost();
        }
        slider.setMax(max + 20);
        slider.setValue(max + 20);
        slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number > observable, Number oldValue, Number newValue)
                    {
                        fullFilter();
                    }
                });
        fullFilter();
    }

//    private ObservableList<Product> getInitialOffTableData() {
//        List list = new ArrayList();
//        list.addAll(Off.getAllOffs());
//        ObservableList<Product> data = FXCollections.observableArrayList(list);
//        return data;
//    }

    private void initializeSort() {
        ArrayList arrayList = new ArrayList<String>();
        arrayList.add("name");
        arrayList.add("score");
        arrayList.add("seen number");
        arrayList.add("price");
        arrayList.add("time");
        sortList.getItems().addAll(arrayList);
    }

    private void initializeTable() {
        name.setCellValueFactory(new PropertyValueFactory<>("NameOfProductNotBarcode"));
        id.setCellValueFactory(new PropertyValueFactory<>("Name"));
        price.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        existNumber.setCellValueFactory(new PropertyValueFactory<>("AmountOfExist"));
        seenNumber.setCellValueFactory(new PropertyValueFactory<>("Seen"));
        score.setCellValueFactory(new PropertyValueFactory<>("ScoreNo"));
        tags.setCellValueFactory(new PropertyValueFactory<>("Tags"));
        createDate.setCellValueFactory(new PropertyValueFactory<>("LocalDateTime"));
        endTime.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
        remainTime.setCellValueFactory(new PropertyValueFactory<>("RemainTime"));
        image.setCellValueFactory(new PropertyValueFactory<>("Image"));
        setTable();
    }

    private void checkBoxForFilter() {
        int i = 0;
        int y = 43;
        TagCheckBoxInitialize tagCheckBoxInitialize = new TagCheckBoxInitialize(i, y).invoke();
        y = tagCheckBoxInitialize.getY();
        HashSet tags = tagCheckBoxInitialize.getTags();
        i = 0;
        y += 40;
        categoryCheckBoxInitialize(i, y, tags);
        y += 50;
        i = 0;
        ArrayList<String> sellers = new ArrayList<>();
        for (Product product : Product.getAllProducts()) {
            for (String seller : product.getSellers()) {
                if(sellers.contains(seller))
                    continue;
                CheckBox checkBox = new CheckBox(seller);
                checkBox.setLayoutX(i);
                checkBox.setLayoutY(y);
                sellers.add(seller);
                mainAnchorPane.getChildren().add(checkBox);
                i += 75;
                checkBox.setOnAction(e -> sellerActivate(checkBox));
                if(i >= 300){
                    i = 0;
                    y += 20;
                }
            }
        }

        ArrayList<String> company = new ArrayList<>();
        for (Product product : Product.getAllProducts()) {
            if(company.contains(product.getCompany()))
                continue;
            CheckBox checkBox = new CheckBox(product.getCompany());
            checkBox.setLayoutX(i);
            checkBox.setLayoutY(y);
            company.add(product.getCompany());
            mainAnchorPane.getChildren().add(checkBox);
            i += 75;
            checkBox.setOnAction(e -> companyActivate(checkBox));
            if(i >= 300){
                i = 0;
                y += 20;
            }
        }

        CheckBox checkBox = new CheckBox("show offs");
        checkBox.setLayoutY(317);
        checkBox.setLayoutX(10);
        mainAnchorPane.getChildren().add(checkBox);
        checkBox.setOnAction(e -> activeOff());
        offCheckBox = checkBox;
        offCheckBox.setSelected(calledFromOff);
        CheckBox checkBox1 = new CheckBox("show available");
        available = checkBox1;
        checkBox1.setLayoutY(300);
        checkBox1.setLayoutX(10);
        mainAnchorPane.getChildren().add(checkBox1);
        checkBox1.setOnAction(e -> activeAvailable(checkBox1));
    }

    private void companyActivate(CheckBox checkBox) {
        if (checkBox.isSelected()) {
            if (!comanyTag.contains(checkBox.getText())) {
                comanyTag.add(checkBox.getText());
            }
        } else {
            comanyTag.remove(checkBox.getText());
        }
        fullFilter();
    }

    private void sellerActivate(CheckBox checkBox) {
        if (checkBox.isSelected()) {
            if (!sellerTag.contains(checkBox.getText())) {
                sellerTag.add(checkBox.getText());
            }
        } else {
            sellerTag.remove(checkBox.getText());
        }
        fullFilter();
    }

    private void activeAvailable( CheckBox checkBox) {
        if(checkBox.isSelected()){
            ArrayList temp = new ArrayList<>(allProduct);
            for (Product product : allProduct) {
                if(product.getAmountOfExist() <= 0){
                    temp.remove(product);
                }
            }
            allProduct = temp;
        }else{
            allProduct.clear();
            if(offCheckBox.isSelected()){
                allProduct = new ArrayList<>(getOffsProduct());
            }else{
                allProduct = new ArrayList<>(Product.getAllProducts());
            }
        }
        sortAction();
    }

    private void categoryCheckBoxInitialize(int i, int y, HashSet tags) {
        for (Product product : Product.getAllProducts()) {
            if (product.getCategory() == null) {
                continue;
            }
                if (tags.contains(product.getCategory())) {
                    continue;
                }
                Category category;
                CheckBox checkBox;
                if((category = Category.getCategoryByName(product.getCategory())) != null && product.getCategory() !=null){
                     checkBox = new CheckBox("catgory :" + product.getCategory() + "subcat" + category.getSubCategories() + "tags" + category.getTags());
                }else{
                    checkBox = new CheckBox("catgory :" + product.getCategory());
                }
                checkBox.setLayoutX(i);
                i += 500;
                checkBox.setLayoutY(y);
                mainAnchorPane.getChildren().add(checkBox);
                tags.add(product.getCategory());
                checkBox.setOnAction(e -> categoryActive(product.getCategory(), checkBox));
                if(i >= 300) {
                    i = 0;
                    y += 20;
                }
        }
    }

    private void categoryActive(String catName, CheckBox checkBox) {
        System.out.println(catName);
        Category category = Category.getCategoryByName(catName);
        if(category == null || category.getTags() == null){
            return;
        }
        if(checkBox.isSelected()){
            for (String categoryTag : category.getTags().substring(category.getTags().indexOf("[") +1, category.getTags().lastIndexOf("]")).split(",")) {
//                System.out.println(categoryTag);
//                categoryTag = categoryTag.substring(categoryTag.indexOf("\""), categoryTag.lastIndexOf("\""));
//                System.out.println(categoryTag);
                filters.add(categoryTag.trim());
            }
        }else{
            for (String categoryTag : category.getTags().substring(category.getTags().indexOf("[") +1, category.getTags().lastIndexOf("]")).split(",")) {
//                categoryTag = categoryTag.substring(categoryTag.indexOf("\""), categoryTag.lastIndexOf("\""));
                filters.remove(categoryTag.trim());
            }
        }
        fullFilter();
    }

    private void activeOff() {
        if (offCheckBox.isSelected()) {
            List list = new ArrayList();
            list.addAll(getOffsProduct());
            allProduct = getOffsProduct();
            ObservableList<Product> data = FXCollections.observableArrayList(list);
            table.setItems(data);
            table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }else{
            allProduct.clear();
            allProduct.addAll(Product.getAllProducts());
        }
        fullFilter();
    }

    private void tagsActivate(CheckBox checkBox) {
        if (checkBox.isSelected()) {
            if (!filters.contains(checkBox.getText())) {
                filters.add(checkBox.getText());
            }
        } else {
            filters.remove(checkBox.getText());
        }
        fullFilter();
    }

    private void setTable() {
        ObservableList<Product> data = getInitialTableData();
        table.setItems(data);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private ObservableList<Product> getInitialTableData() {
        List list = new ArrayList();
        list.addAll(allProduct);

        ObservableList<Product> data = FXCollections.observableArrayList(list);

        return data;
    }


    public void seeProduct(ActionEvent actionEvent) throws IOException {
        ProductPage.setProduct((Product) table.getSelectionModel().getSelectedItems().get(0));
        App.setRoot("product-page");
    }

    public void sortAction() {
        if (sortList.getSelectionModel().getSelectedItem() == null) {
            allProduct.sort(Comparator.comparing(Product::getLocalDateTime));
        } else {
            switch (sortList.getSelectionModel().getSelectedItem().toString()) {
                case "name":
                    allProduct.sort(Comparator.comparing(Product::getNameOfProductNotBarcode));
                    break;
                case "score":
                    allProduct.sort(Comparator.comparing(Product::getScoreNo));
                    break;
                case "seen number":
                    allProduct.sort(Comparator.comparing(Product::getSeen));
                    break;
                case "price":
                    allProduct.sort(Comparator.comparing(Product::getCost));
                    break;
                default:
                    allProduct.sort(Comparator.comparing(Product::getLocalDateTime));
                    break;
            }
        }
        if (reverseButton.getText().equals("decrease"))
            Collections.reverse(allProduct);
        setTable();
    }


    private ArrayList<Product> getOffsProduct() {
        ArrayList<Product> arrayList = new ArrayList<>();
        for (Off allOff : Off.getAllOffs()) {
            for (String product : allOff.getProducts()) {
                if (Product.getProductWithName(product) != null) {
                    Product.getProductWithName(product).setEndTime(allOff.getEndDate());
                    arrayList.add(Product.getProductWithName(product));
                }
            }
        }
        return arrayList;
    }

    public void reverse(ActionEvent actionEvent) {
        if (reverseButton.getText().equals("increase")) {
            reverseButton.setText("decrease");
        } else {
            reverseButton.setText("increase");
        }
        Collections.reverse(allProduct);
        setTable();
    }

    public void backToFirstMenu(ActionEvent actionEvent) {
        try {
            App.setRoot("main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToAccountPage(ActionEvent actionEvent) throws IOException {
        if (Controller.getOurController().getLoggedInAccount() == null) {
            LoginCreate.setBeforeRoot("main");
            App.setRoot("login-create");
        } else {
            switch (Controller.getOurController().getLoggedInAccount().getClass().toString()) {
                case "class Model.Manager":
                    App.setRoot("manager");
                    break;
                case "class Model.Customer":
                    App.setRoot("customer");
                    break;
                case "class Model.Seller":
                    App.setRoot("seller");
                    break;
            }
        }
    }

    private class TagCheckBoxInitialize {
        private int i;
        private int y;
        private HashSet tags;

        public TagCheckBoxInitialize(int i, int y) {
            this.i = i;
            this.y = y;
        }

        public int getY() {
            return y;
        }

        public HashSet getTags() {
            return tags;
        }

        public TagCheckBoxInitialize invoke() {
            tags = new HashSet();
            for (Product product : Product.getAllProducts()) {
                for (String tag : product.getTags()) {
                    if (tags.contains(tag)) {
                        continue;
                    }
                    CheckBox checkBox = new CheckBox(tag);
                    checkBox.setLayoutX(i);
                    i += 50;
                    checkBox.setLayoutY(y);
                    mainAnchorPane.getChildren().add(checkBox);
                    tags.add(tag);
                    checkBox.setOnAction(e -> tagsActivate(checkBox));
                    if(i >= 300){
                        i = 0;
                        y += 20;
                    }
                }
            }
            return this;
        }
    }

    private void fullFilter(){
        //reset all product
        if(offCheckBox.isSelected()){
            allProduct = new ArrayList<>(getOffsProduct());
        }else{
            allProduct = new ArrayList<>(Product.getAllProducts());
        }
        //cost check
        ArrayList temp = new ArrayList<>(allProduct);
        for (Product product : allProduct) {
            if(product.getCost() > slider.getValue()){
                temp.remove(product);
            }
        }
        allProduct = new ArrayList<>(temp);
        //tag check
        ArrayList temp1 = new ArrayList(allProduct);
        for (Product product : allProduct) {
            for (String filter : filters) {
                if(!product.getTags().contains(filter)){
                    temp1.remove(product);
                    break;
                }
            }
        }
        allProduct = new ArrayList<>(temp1);
        //available filter
        if(available.isSelected()){
            ArrayList temp2 = new ArrayList(allProduct);
            for (Product product : allProduct) {
                if(product.getAmountOfExist() <= 0)
                    temp2.remove(product);
            }
        }
        //seller filter
        ArrayList temp2 = new ArrayList<>(allProduct);
        for (String s : sellerTag) {
            for (Product product : allProduct) {
                if(!product.getSellers().contains(s))
                temp2.remove(product);
            }
        }
        allProduct = new ArrayList<>(temp2);

        //company tag
        ArrayList temp3 = new ArrayList<>(allProduct);
        for (String s : comanyTag) {
            for (Product product : allProduct) {
                if(!product.getCompany().equals(s))
                    temp3.remove(product);
            }
        }
        allProduct = new ArrayList<>(temp3);
        sortAction();
    }
}