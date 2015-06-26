/**
 * 
 */
package com.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Alex
 *
 */
public class HibernateUtil {

	private static final SessionFactory fabrica = buidSessionFactory();

	private static SessionFactory buidSessionFactory() {
		try{
			Configuration configuracao = new Configuration();
			configuracao.configure("hibernate.cfg.xml");
			return configuracao.buildSessionFactory();
		}catch(Throwable e){
			System.err.println("Criação inicial do objeto SessionFactory falhou. Erro: "+e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getFabrica() {
		return fabrica;
	}
	
}
