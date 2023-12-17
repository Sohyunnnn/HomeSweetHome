package HomeSweetHome;

public class WishlistItem {
    private String productImg;
    private String productName;
    private int productPrice;
    private int productID;

    // 생성자, 게터, 세터 등을 정의

    public WishlistItem(String productImg, String productName, int productPrice,int productID) {
        this.productImg = productImg;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productID=productID;
    }

    public String getProductImg() {
        return productImg;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }
    
    public int getProductID() {
        return productID;
    }
}