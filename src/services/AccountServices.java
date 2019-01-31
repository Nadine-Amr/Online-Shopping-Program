package services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import test.dto.Account;

public class AccountServices {

	public static void addAccount (Account account){
	
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(account);
		
		session.getTransaction().commit();
		session.close();
		}

	public  static List<Account> userWithEmailQuery(String accountEmail){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.getNamedQuery("userWithEmail");
		query.setString(0, accountEmail);
		List<Account> usersReturned = (List<Account>) query.list();
		
		session.getTransaction().commit();
		session.close();
		
		return usersReturned;
	}
	
	public  static List<Account> sameAccountQuery(String accountName, String accountPhone, String accountPassword, String accountEmail, int accountType){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.getNamedQuery("sameAccount");
		query.setString(0, accountName);
		query.setString(1, accountPhone);
		query.setString(2, accountPassword);
		query.setString(3, accountEmail);
		query.setInteger(4, accountType);
		List<Account> usersReturned = (List<Account>) query.list();
		session.getTransaction().commit();
		session.close();
		
		return usersReturned;
	}
	
}
	

