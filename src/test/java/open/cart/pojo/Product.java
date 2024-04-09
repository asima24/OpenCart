package open.cart.pojo;

public class Product {
	
	private String searchKey;
	private String productName;
	private int productImage;
	public Product(String searchKey,String productName,int productImage) {
		this.searchKey=searchKey;
		this.productName=productName;
		this.productImage=productImage;
	}
	
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductImage() {
		return productImage;
	}
	public void setProductImage(int productImage) {
		this.productImage = productImage;
	}
	
	@Override
	public String toString() {
		return "Product [searchKey=" + searchKey + ", productName=" + productName + ", productImage=" + productImage
				+ "]";
	}
}
