package org.example;

import Model.Off;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;

import java.util.*;

import javafx.scene.layout.AnchorPane;

public class ProductsPage {
    public static boolean calledFromOff = false;
    public ArrayList<Product> allProduct;
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
    private CheckBox offCheckBox;

    private HashSet<String> filters = new HashSet<>();

    public void initialize() {
        allProduct = new ArrayList<>(Product.getAllProducts());
        allProduct.sort(Comparator.comparing(Product::getLocalDateTime));
        initializeTable();
        initializeSort();
        checkBoxForFilter();
    }

    private ObservableList<Product> getInitialOffTableData() {
        List list = new ArrayList();
        list.addAll(Off.getAllOffs());
        ObservableList<Product> data = FXCollections.observableArrayList(list);
        return data;
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
    }

    private void checkBoxForFilter() {
        int i = 0;
        HashSet tags = new HashSet();
        for (Product product : Product.getAllProducts()) {
            for (String tag : product.getTags()) {
                if (tags.contains(tag)) {
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
        CheckBox checkBox = new CheckBox("show offs");
        checkBox.setLayoutY(330);
        checkBox.setLayoutX(i);
        mainAnchorPane.getChildren().add(checkBox);
        checkBox.setOnAction(e -> activeOff());
        offCheckBox = checkBox;
        offCheckBox.setSelected(calledFromOff);

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
        sortAction();
    }

    private void tagsActivate(CheckBox checkBox) {
        if (checkBox.isSelected()) {
            if (!filters.contains(checkBox.getText())) {
                filters.add(checkBox.getText());
            }
        } else {
            filters.remove(checkBox.getText());
        }
        resetFilter();
    }

    private void resetFilter() {
        if (offCheckBox.isSelected()) {
            ArrayList<Product> temp = new ArrayList<>(getOffsProduct());
            getFilters(temp);
            return;
        }
        ArrayList<Product> temp = new ArrayList<>(Product.getAllProducts());
        getFilters(temp);
        setTable();
    }

    private void getFilters(ArrayList<Product> temp) {
        for (Product product : Product.getAllProducts()) {
            for (String filter : filters) {
                if (!product.getTags().contains(filter)) {
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
        resetFilter();
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
}