package test.dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries ({
	@NamedQuery (name = "menuWithItemAndShop", query = "FROM Menu WHERE ((item = ?) AND (shop.name = ?))"),
	@NamedQuery (name = "allItemsFromShop", query = "FROM Menu WHERE shop.name = ?")
	})
@Table (name = "OTLOB_MENUS")
public class Menu {
	private int id;
	private String item;
	private String price;
	private String description;
	private Shop shop;

	@Id
	@GeneratedValue (strategy = GenerationType.TABLE)
	@Column (name = "ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Basic
	@Column (name = "ITEM")
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	@Basic
	@Column (name = "PRICE")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Basic
	@Column (name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic
	@ManyToOne
	@JoinColumn (name = "SHOP_ID")
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
