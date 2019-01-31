
package test.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@NamedQueries ({
		@NamedQuery (name = "shopWithName", query = "FROM Shop WHERE name = ?"),
        @NamedQuery (name = "allShops", query = "FROM Shop"),
        @NamedQuery (name = "shopWithOwnerEmail", query = "FROM Shop WHERE owner.email = ?")
})

@Table (name="OTLOB_SHOPS")
public class Shop {


private int id;
private String name;
private String phone;
private Account owner;
private Collection<Menu> menuitem = new ArrayList<Menu>();


@Id
@Column(name="ID")
@GeneratedValue (strategy = GenerationType.TABLE)
public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}

@Basic
@Column(name="NAME")
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}

@Basic
@OneToOne
@JoinColumn (name = "OWNER_ID")
public Account getOwner() {
	return owner;
}
public void setOwner(Account owner) {
	this.owner = owner;
}

@Basic
@OneToMany (mappedBy = "shop")
public Collection<Menu> getMenuitem() {
	return menuitem;
}
public void setMenuitem(Collection<Menu> menuitem) {
	this.menuitem = menuitem;
}

@Basic
@Column(name="PHONE")
public String getPhone() {
return phone;
}
public void setPhone(String phone) {
this.phone = phone;
}
}