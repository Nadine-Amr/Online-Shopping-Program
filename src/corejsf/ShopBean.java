package corejsf;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import services.AccountServices;
import services.ShopServices;
import test.dto.Account;
import test.dto.Shop;

@ManagedBean(name="shopBean")
@ViewScoped
public class ShopBean {
	private Shop shop;
	private String ownerEmail;
	
	public ShopBean(){
		shop = new Shop();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
		ownerEmail = params.get("shopOwnerEmail");
	}
	
	public String getownerEmail() {
		return ownerEmail;
	}

	public void setownerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
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
	
	public void validateName(ComponentSystemEvent event) {
		UIComponent source = event.getComponent();
		UIInput nameInput = (UIInput) source.findComponent("name");
		String n = (String)nameInput.getLocalValue();
		if (Pattern.compile( "[0-9]" ).matcher(n).find()){
		FacesMessage message = new FacesMessage("Invalid Name");
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(source.getClientId(), message);
		context.renderResponse();
		}
	}
	
	public void validatePhone(ComponentSystemEvent event) {
		UIComponent source = event.getComponent();
		UIInput phoneInput = (UIInput) source.findComponent("phone");
		String n = (phoneInput.getLocalValue() == null) ? "null" : (String)phoneInput.getLocalValue();
		if (n!="null" || n!= "") {
			if ((!isNumeric(n)) || (n.length()!=11)) {
				FacesMessage message = new FacesMessage("Invalid Phone");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(source.getClientId(), message);
				context.renderResponse();
			}
		}
	}
	
	public String register() {
		List<Shop> shopsReturned = ShopServices.shopWithNameQuery(shop.getName());
		
		List<Account> accountsReturned =AccountServices.userWithEmailQuery(ownerEmail);
		if (shopsReturned.size()==0){
			shop.setOwner(accountsReturned.get(0));
			ShopServices.addShop(shop);
			return "shopAddItems";
		}
		else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("A shop with this name already exists."));
			return "";
		}
	}
}