public class Product {
    private String name;
    private double price;
    private String productGroup;
    private String[] allProducts;
    private boolean isCampaignProduct;
    private String campaignDescription;
    private int quantity;


    public Product(String name, String productGroup) {
        this.name = name;
        this.productGroup = productGroup;
    }

    public Product(String name, String productGroup, double price) {
        this.name = name;
        this.productGroup = productGroup;
        this.price = price;
    }

    public Product(String name, String productGroup, String[] allProducts) {
        this.name = name;
        this.productGroup = productGroup;
        this.allProducts = allProducts;
    }

    public Product(String name, String productGroup, String[] allProducts, double price) {
        this.name = name;
        this.productGroup = productGroup;
        this.allProducts = allProducts;
        this.price = price;
    }

    public Product(String name, String productGroup, double price, boolean isCampaignProduct, String campaignDescription) {
        this.name = name;
        this.productGroup = productGroup;
        this.price = price;
        this.isCampaignProduct = isCampaignProduct;
        this.campaignDescription = campaignDescription;
    }

    public Product(String name, String productGroup, double price, int quantity) {
        this.name = name;
        this.productGroup = productGroup;
        this.price = price;
        this.quantity = quantity;
    }


    // getters
    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getProductGroup() {
        return this.productGroup;
    }

    public String[] getAllProducts() {
        return this.allProducts;
    }

    public boolean isCampaignProduct() {
        return this.isCampaignProduct;
    }

    public String getCampaignDescription() {
        return this.campaignDescription;
    }

    public int getQuantity() {
        return quantity;
    }


    // setters
    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public void setProductGroup(String newProductGroup) {
        this.productGroup = newProductGroup;
    }

    public void setAllProducts(String[] allProducts) {
        this.allProducts = allProducts;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public double calculatePriceByWeight(double weight) {
        return weight * price;
    }
}
