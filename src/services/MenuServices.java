package services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import test.dto.Account;
import test.dto.Menu;
import test.dto.Shop;

public class MenuServices {
	public static void AddItem (Menu menu){
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(menu);
		session.getTransaction().commit();
		session.close();
		}
	
		public static List<Menu> menuWithItemAndShopQuery(String menuItem, String shopName){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.getNamedQuery("menuWithItemAndShop");
		query.setString(0, menuItem);
		query.setString(1, shopName);
		List<Menu> MenuReturned = (List<Menu>) query.list();
		
		session.getTransaction().commit();
		session.close();
		
		return MenuReturned;
	}
		
	public static List<Menu> allItemsFromShopQuery(String shopName){
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Query query = session.getNamedQuery("allItemsFromShop");
			query.setString(0, shopName);
			List<Menu> itemsReturned = (List<Menu>) query.list();
			
			session.getTransaction().commit();
			session.close();
			
			return itemsReturned;
	}
	public static void deleteItem(Menu menu){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Menu theMenu = (Menu) session.get(Menu.class, menu.getId());
		session.delete(theMenu);
		
		session.getTransaction().commit();
		session.close();
    }
}
