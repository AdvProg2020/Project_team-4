package org.example;

import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;

import java.util.*;

import javafx.scene.layout.AnchorPane;

public class ProductsPage {
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
    public ArrayList<Product> allProduct;
    public TableColumn tags;
    public TableColumn createDate;
    private HashSet<String> filters = new HashSet<>();

    public void initialize(){
        allProduct = new ArrayList<>(Product.getAllProducts());
        allProduct.sort(Comparator.comparing(Product::getLocalDateTime));
        initializeTable();
        initializeSort();
        checkBoxForFilter();
    }

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
        setTable();
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void checkBoxForFilter() {
        int i = 0;
        HashSet tags = new HashSet();
        for (Product product : Product.getAllProducts()) {
            for (String tag : product.getTags()) {
                if(tags.contains(tag)){
                    continue;
                }
                CheckBox checkBox = new CheckBox(tag);
                checkBox.setLayoutX(i);
                i += 50;
                checkBox.setLayoutY(330);
                mainAnchorPane.getChildren().add(checkBox);
                tags.add(tag);
                checkBox.setOnAction(e -> tagsActivate(checkBox));
            }
        }
    }

    private void tagsActivate(CheckBox checkBox) {
        if(checkBox.isSelected()){
            if(!filters.contains(checkBox.getText())){
                filters.add(checkBox.getText());
            }
        }else{
            filters.remove(checkBox.getText());
        }
        resetFilter();
    }

    private void resetFilter() {
        ArrayList<Product> temp = new ArrayList<>(allProduct);
        for (Product product : Product.getAllProducts()) {
            for (String filter : filters) {
                if(!product.getTags().contains(filter)){
                    temp.remove(product);
                }
            }
        }
        allProduct.clear();
        allProduct.addAll(temp);
        setTable();
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


    public void seeProduct(ActionEvent actionEvent) {
        //here we should call productpage with Product
        System.out.println(table.getSelectionModel().getSelectedItem());
    }

    public void sortAction() {
        allProduct.clear();
        allProduct.addAll(Product.getAllProducts());
        switch (sortList.getSelectionModel().getSelectedItem().toString()){
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
            case "time":
                allProduct.sort(Comparator.comparing(Product::getLocalDateTime));
                break;
        }
        if(reverseButton.getText().equals("decrease"))
            Collections.reverse(allProduct);
        resetFilter();
    }


    public void reverse(ActionEvent actionEvent) {
        if(reverseButton.getText().equals("increase")){
            reverseButton.setText("decrease");
        }else {
            reverseButton.setText("increase");
        }
        Collections.reverse(allProduct);
        setTable();
    }
}