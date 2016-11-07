package client;

import java.util.*;
import java.rmi.*;
import xotd.XotdService;

public class XotdClientMain {

	public static void printModes() {
		System.out.println("[RMI-hjgwak-Client] Please Input Mode number : ");
		System.out.println("[RMI-hjgwak-Client] ### 1 : Get Quotes of the Day [Qotd]");
		System.out.println("[RMI-hjgwak-Client] ### 2 : Get Word of the Day [Wotd]");
		System.out.println("[RMI-hjgwak-Client] ### 3 : Quit");
		System.out.print("[RMI-hjgwak-Client] Input : ");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("[RMI-hjgwak-Client] START");
//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new RMISecurityManager());
//		}
		
		/*
		 * RMI의 portNumber를 -p옵션을 통해 지정할 수 있도록 한다.
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
		
		/*
		 * XotdService 인터페이스 객체를 생성한 뒤, 서버의 오브젝트를 lookup한다.
		 * 표준입력으로 console을 통해 입력을 받아 
		 * 1일 경우 qotd를 출력하고
		 * 2일 경우 wotd를 출력하고
		 * 3일 경우 종료한다.
		 * Make XotdService interface, lookup the object.
		 * Input from user through stdin.
		 * When input is 1, print qotd
		 * When input is 2, print wotd
		 * When input is 3, quit.
		 */
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("[RMI-hjgwak-Client] Try to lookup xotd_service object.");
		try {
			String url = "rmi://localhost";
			if (!portNumber.equals("")) {
				url += ":" + portNumber;
			}
			url += "/rmi-hjgwak";
			XotdService xotd = (XotdService)Naming.lookup(url);
			int mode = 0;
			do {
				printModes();
				mode = scan.nextInt();
				switch (mode) {
				case 1 : 
					String qotd = xotd.getQotd();
					System.out.println("[RMI-hjgwak-Client] Qotd : " + qotd);
					break;
				case 2 :
					String wotd = xotd.getWotd();
					System.out.println("[RMI-hjgwak-Client] Qotd : " + wotd);
					break;
				case 3 :
					break;
				default:
					System.out.println("[RMI-hjgwak-Client] Wrong number Input, please try again.");
				}
			} while(mode != 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("[RMI-hjgwak-Client] Complete.");
	}

}
