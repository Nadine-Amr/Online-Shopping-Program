package corejsf;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import services.MenuServices;
import services.ShopServices;
import test.dto.Account;
import test.dto.Menu;
import test.dto.Shop;

@ManagedBean(name="ownerMenuBean")
@ViewScoped
public class OwnerMenuBean {
	private Menu menu;
	private String ownerEmail;
	private List<Menu> allItems;
	private List<Shop> theShop;

    public OwnerMenuBean(){
    	menu = new Menu();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
		ownerEmail = params.get("shopOwnerEmail");
		theShop = ShopServices.shopWithOwnerEmailQuery(ownerEmail);
	    allItems = MenuServices.allItemsFromShopQuery(theShop.get(0).getName());
	}		
    
    public String getOwnerEmail() {
		return ownerEmail;
	}
 
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getAllItems() {
		return allItems;
	}

	public void setAllItems(List<Menu> allItems) {
		this.allItems = allItems;
	}

	public List<Shop> getTheShop() {
		return theShop;
	}

	public void setTheShop(List<Shop> theShop) {
		this.theShop = theShop;
	}
	
	public List<Menu> getallItems() {	
		return allItems;
	}


	public void setallItems(List<Menu> allItems) {
		this.allItems = allItems;
	}														

	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public void validatePrice(ComponentSystemEvent event) {
		UIComponent source = event.getComponent();
		UIInput priceInput = (UIInput) source.findComponent("price");
		String n = (priceInput.getLocalValue() == null) ? "null" : (String)priceInput.getLocalValue();
		if (n!="null" || n!= "") {
			if ((!isNumeric(n)) ) {
				FacesMessage message = new FacesMessage("Please enter a numeric price");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(source.getClientId(), message);
				context.renderResponse();
			}
		}
	}

	public String addItem() {	
		menu.setShop(theShop.get(0));
		List<Menu> menusReturned = MenuServices.menuWithItemAndShopQuery(menu.getItem(), theShop.get(0).getName());
		if (menusReturned.size()==0){
			MenuServices.AddItem(menu);
		}
		else {
			MenuServices.deleteItem(menusReturned.get(0));
			MenuServices.AddItem(menu);
		}
	    allItems = MenuServices.allItemsFromShopQuery(theShop.get(0).getName());
		return "shopAddItems";
	}
}
