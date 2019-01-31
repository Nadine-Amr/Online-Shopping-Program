package corejsf;

import java.util.List;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import services.AccountServices;
import test.dto.Account;

@ManagedBean(name="accountBean")
@ViewScoped
public class AccountBean {
	private Account account;
	//private String gttt;
	public AccountBean(){
		account =new Account();
		//String gttt = "";
	}
	
	/*public String getGttt() {
		return gttt;
	}

	public void setGttt(String gttt) {
		this.gttt = gttt;
	}
	*/

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

	public void validatePassword(ComponentSystemEvent event) {
		UIComponent source = event.getComponent();
		UIInput passwordInput = (UIInput) source.findComponent("password");
		String n = (String)passwordInput.getLocalValue();
		if (n.length()<8){
		FacesMessage message = new FacesMessage("please enter a password with atleast 8 characters");
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(source.getClientId(), message);
		context.renderResponse();
		}
	}
	
	public String signUp() {
		List<Account> accountsReturned = AccountServices.userWithEmailQuery(account.getEmail());
		if (accountsReturned.size()==0){
			AccountServices.addAccount(account);
			if(account.getType()== 1)
				return "userChooseShop";
			else 
				return "shopRegister";
		}
		else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("An account with this email already exists."));
			return "";
		}
	}
	
	public String signIn() {
		List<Account> accountsReturned = AccountServices.sameAccountQuery(account.getName(), account.getPhone(), account.getPassword(), account.getEmail(), account.getType());
		if (accountsReturned.size()==0){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("No account was previously registered with this email or there are mismatches in the entries."));
			return "";
		}
		else {
			//if ((accountsReturned.get(0).getName() == account.getName()) && (accountsReturned.get(0).getPassword() == account.getPassword()) && (accountsReturned.get(0).getPhone() == account.getPhone()) && (accountsReturned.get(0).getType() == account.getType())) {
			//if (accountsReturned.size()==1) {	
			    if(account.getType()== 1)
					return "userChooseShop";
				else 
					return "shopAddItems";
			}
	}
}
