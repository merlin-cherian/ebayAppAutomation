package ebayApp;

public class ReviewScreenObjects {
	
	String name;
	String price;
	String nameID = "com.ebay.mobile:id/item_title";
	String priceID= "com.ebay.mobile:id/textview_item_price";
	String revButtonId = "com.ebay.mobile:id/take_action";
	String buyButtonId = "com.ebay.mobile:id/button_bin";
	
	public String getRevButtonId() {
		return revButtonId;
	}
	public void setRevButtonId(String revButtonId) {
		this.revButtonId = revButtonId;
	}
	public String getBuyButtonId() {
		return buyButtonId;
	}
	public void setBuyButtonId(String buyButtonId) {
		this.buyButtonId = buyButtonId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNameID() {
		return nameID;
	}
	public void setNameID(String nameID) {
		this.nameID = nameID;
	}
	public String getPriceID() {
		return priceID;
	}
	public void setPriceID(String priceID) {
		this.priceID = priceID;
	}
}
