package Model;


import java.time.LocalDateTime;
import java.util.*;

public class Off extends SaveAble {
    private static ArrayList<Off> allOffs = new ArrayList<>();
    private String offBarcode;
    private ArrayList<Product> products;
    private enum offStatus {
        MAKING, EDITING, APPROVED
    }

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int offAmount;

    public Off(LocalDateTime startDate, ArrayList<Product> products, LocalDateTime endDate, int offAmount) {
        this.offBarcode = givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect();
        for (Product product : products) {
            this.products.add(product);
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.offAmount = offAmount;
        for (Product product: products) {
            product.offTheCost(product.getCost() * offAmount / 100);
        }
        allOffs.add(this);
        SaveAndLoad.getSaveAndLoad().writeJSON(allOffs, ArrayList.class, "allOffs");
    }

    public String getOffBarcode() {
        return offBarcode;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public int getOffAmount() {
        return offAmount;
    }

    @Override
    public String toString() {
        return "Off{" +
                "offBarcode='" + offBarcode + '\'' +
                ", products=" + products +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", offAmount=" + offAmount +
                '}';
    }

    @Override
    protected String getName() {
        return offBarcode;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public static Off getOffByBarcode(String name) {
        for (Off off: allOffs) {
            if (off.getOffBarcode().equalsIgnoreCase(name)) {
                return off;
            }
        }
        return null;
    }

    public static ArrayList<Off> getAllOffs() {
        return allOffs;
    }

    public String givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Off off = (Off) o;
        return offBarcode.equalsIgnoreCase(off.offBarcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offBarcode) + 100;
    }
}
