package corejsf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import services.MenuServices;
import test.dto.Menu;

@ManagedBean (name="userOrderBean")
@ViewScoped
public class UserOrderBean {
	
	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	private List<Menu> allMenuItems;
	private Map<String, Boolean> selectedItems;
	private double orderPrice;
	
	public UserOrderBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
		allMenuItems = MenuServices.allItemsFromShopQuery(params.get("chosenShop"));
		selectedItems = new HashMap<String,Boolean>();
		for(Menu m : allMenuItems) {
			selectedItems.put(m.getItem(), false);
		}
	}

	public Map<String, Boolean> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(Map<String, Boolean> selectedItems) {
		this.selectedItems = selectedItems;
	}

	public List<Menu> getAllMenuItems() {
		return allMenuItems;
	}
	
	public void setAllMenuItems(List<Menu> allMenuItems) {
		this.allMenuItems = allMenuItems;
	}
		
	public void calculatePrice() {
		orderPrice = 0;
		for (Menu item : allMenuItems) {
			if (selectedItems.get(item.getItem())==true) {
				orderPrice += Double.parseDouble(item.getPrice());
			}
		}
	}
}
