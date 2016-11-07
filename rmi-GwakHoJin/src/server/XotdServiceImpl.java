package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import xotd.XotdService;

public class XotdServiceImpl extends UnicastRemoteObject implements XotdService {
	public static final long serialVersionUID = 1L;
	public final String xotd_url = "http://www.quotationspage.com/";
	
	public XotdServiceImpl() throws RemoteException { 
		super(); 
	}
	
	@Override
	public String getQotd() throws RemoteException {
		String urlPath = xotd_url + "qotd.html";
		String pageContents = "";
		StringBuilder contents = new StringBuilder();
		
		System.out.println("[RMI-hjgwak-server] URL : " + urlPath);
		try {
			URL url = new URL(urlPath);
			URLConnection con = (URLConnection)url.openConnection();
			InputStreamReader reader = new InputStreamReader(con.getInputStream(), "utf-8");
			
			BufferedReader buff = new BufferedReader(reader);
			
			while ((pageContents = buff.readLine()) != null) {
				contents.append(pageContents);
				contents.append("\r\n");
			}
			
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents.toString();
	}

	@Override
	public String getWotd() throws RemoteException {
		String urlPath = xotd_url + "wotd.html";
		String pageContents = "";
		StringBuilder contents = new StringBuilder();

		System.out.println("[RMI-hjgwak-server] URL : " + urlPath);
		try {
			URL url = new URL(urlPath);
			URLConnection con = (URLConnection)url.openConnection();
			InputStreamReader reader = new InputStreamReader(con.getInputStream(), "utf-8");
			
			BufferedReader buff = new BufferedReader(reader);
			
			while ((pageContents = buff.readLine()) != null) {
				contents.append(pageContents);
				contents.append("\r\n");
			}
			
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents.toString();
	}

}
