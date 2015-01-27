package com.chromia.test.percistence;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import com.chromia.model.Rol;

public class TestHibernateNamedQuery 
{
	public static void main(String[] args) 
	{
		Configuration configuration=new Configuration();  
		   configuration.configure("hibernate.cgf.xml");  
		   
		   ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration
                   .getProperties());
   SessionFactory sessionFactory = configuration
                   .buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
   Session session = sessionFactory.openSession();
   
		    
		//Open the hibernate session
//		Session session = HibernateUtil.getSessionFactory().openSession();
		TestHibernateNamedQuery test = new TestHibernateNamedQuery();
		try
		{
			Rol rol = test.getRolById(1,session);
			System.out.println("Rol : "+ rol.getNombre());
		}
		finally
		{
//			session.getTransaction().commit();
			HibernateUtil.shutdown();
		}
	}
	
	
	public Rol getRolById(int id, Session session) {
		Query query = session.getNamedQuery(Rol.GET_ROL_BY_ID).setInteger("id", id);
		return (Rol) query.uniqueResult();
	}

	public List<Rol> getRols(Session session) {
		Query query = session.getNamedQuery(Rol.GET_ALL_ROLS);
		return (List<Rol>) query.list();
	}
}
