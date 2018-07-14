package ebayApp;

public class LoginPageObjects {
	
	String signInImage = "com.ebay.mobile:id/home_sign_in_image";
	String searchField = "com.ebay.mobile:id/home_search_text";
	//com.ebay.mobile:id/search_box
	String searchTextBox = "com.ebay.mobile:id/search_";
	String searchButton = "android:id/search_go_btn";
	
	public String getSignInImage() {
		return signInImage;
	}
	public void setSignInImage(String signInImage) {
		this.signInImage = signInImage;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchTextBox() {
		return searchTextBox;
	}
	public void setSearchTextBox(String searchTextBox) {
		this.searchTextBox = searchTextBox;
	}
	public String getSearchButton() {
		return searchButton;
	}
	public void setSearchButton(String searchButton) {
		this.searchButton = searchButton;
	}

}
