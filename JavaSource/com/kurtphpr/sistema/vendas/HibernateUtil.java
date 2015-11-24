package com.kurtphpr.sistema.vendas;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory session = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			return cfg.buildSessionFactory();
		} catch (Throwable erro) {
			System.out.println("Erro na Conexão./n" + erro);
			throw new ExceptionInInitializerError();
		}
	}
	
	public static SessionFactory getSession(){
		return session;
	}
}