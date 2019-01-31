package corejsf;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import services.ShopServices;
import test.dto.Shop;

@ManagedBean (name="userShopsBean")
@ViewScoped
public class UserShopsBean {
	private List<Shop> allShops;
	
	public UserShopsBean() {
		allShops = ShopServices.allShopsQuery();
	}
	
	public void setAllShops(List<Shop> allShops) {
		this.allShops = allShops;
	}

	public List<Shop> getAllShops() {
		return allShops;
	}
	
	public String goToMenu() {
		return "userOrder";
	}
}
