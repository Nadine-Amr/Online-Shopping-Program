package test.dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries ({
	@NamedQuery (name = "userWithEmail", query = "FROM Account WHERE email = ?"),
	@NamedQuery (name = "sameAccount", query = "FROM Account WHERE ((name = ?) AND (phone = ?) AND (password = ?) AND (email = ?) AND (type = ?))")
})
@Table (name = "OTLOB_ACCOUNTS")
public class Account {

	private int id; 
	private String name;
	private String phone;
	private String password;
	private String email;
	private int type;
	
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
	@Column (name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Basic
	@Column (name = "PHONE")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Basic
	@Column (name = "PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Basic
	@Column (name = "EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Basic
	@Column (name = "TYPE")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}