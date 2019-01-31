package services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import test.dto.Shop;

public class ShopServices {

	public static void addShop (Shop shop){
	
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(shop);
		
		session.getTransaction().commit();
		session.close();
		}

	public static List<Shop> shopWithNameQuery(String shopName){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.getNamedQuery("shopWithName");
		query.setString(0, shopName);
		List<Shop> shopsReturned = (List<Shop>) query.list();
		
		session.getTransaction().commit();
		session.close();
		
		return shopsReturned;
	}
	
	public static List<Shop> allShopsQuery(){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.getNamedQuery("allShops");
		List<Shop> shopsReturned = (List<Shop>) query.list();
		
		session.getTransaction().commit();
		session.close();
		
		return shopsReturned;
	}
	
	public static List<Shop> shopWithOwnerEmailQuery(String ownerEmail){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.getNamedQuery("shopWithOwnerEmail");
		query.setString(0, ownerEmail);
		List<Shop> shopsReturned = (List<Shop>) query.list();
		
		session.getTransaction().commit();
		session.close();
		
		return shopsReturned;
	}
}