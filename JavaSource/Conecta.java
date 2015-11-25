import org.hibernate.Session;

import com.kurtphpr.sistema.util.HibernateUtil;

public class Conecta {
	public static void main(String[] args) {
		Session sessao = null;
		
		try {
			sessao = HibernateUtil.getSession().openSession();
			System.out.println("Conexão realizada com sucesso.");
		} finally{
			sessao.close();
			System.out.println("Conexão encerrada.");
		}
	}
}