package server;

import java.rmi.*;
import java.rmi.server.*;

public class ServerMain {

	public static void main(String[] args) {
		System.out.println("[RMI-hjgwak-Server] START");
		// TODO Auto-generated method stub
//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new RMISecurityManager());
//		}
		
		/*
		 * RMI�� portNumber�� -p�ɼ��� ���� ������ �� �ֵ��� �Ѵ�.
		 * Allow portNumber for RMI by passing argument with -p option.
		 */
		String portNumber = "";
		for (int i = 0; i < args.length; ++i) {
			if (args[i].equals("-p")) {
				try {
					portNumber = args[i+1];
					System.out.println("[RMI-hjgwak-Client] Specified Port Number : " + portNumber);
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("[RMI-hjgwak-Server] Try to bind xotd_service object.");
		boolean bind_success = true;
		
		/*
		 * XotdServiceImpl ��ü�� ���� rmi-hjgwak�̶�� �̸����� ���ε� ��Ų��.
		 * �ɼ����� �־��� portNumber�� ���� ��� �ش� port�� ���ε� ��Ų��.
		 * Binding XotdServiceImpl object to registry named rmi-hjgwak
		 * If portNumber was given through argument, binding to the passed port
		 */
		String url = "rmi://localhost";
		if (!portNumber.equals("")) {
			url += ":" + portNumber;
		}
		url += "/rmi-hjgwak";
		try {
			XotdServiceImpl xotd_service = new XotdServiceImpl();
			Naming.rebind(url, xotd_service);
			while(true);
		} catch (Exception e) {
			bind_success = false;
			e.printStackTrace();
		} finally {
			try { 
				Naming.unbind(url);
				System.out.println("[RMI-hjgwak-Server] unbind xotd_service object.");
			} catch (Exception e) {}
		}
		
		/*
		 * Binding ����� ���
		 * Print Binding result.
		 */
		if (bind_success) {
			System.out.println("[RMI-hjgwak-Server] xotd_service object binding complete.");
		} else {
			System.out.println("[RMI-hjgwak-Server] xotd_service object binding fail.");
		}
	}
}
