package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import xotd.XotdService;

public class XotdServiceImpl extends UnicastRemoteObject implements XotdService {
	public XotdServiceImpl() throws RemoteException { 
		super(); 
	}
	
	@Override
	public String getQotd() throws RemoteException {
		// TODO Auto-generated method stub
		return "Qotd";
	}

	@Override
	public String getWotd() throws RemoteException {
		// TODO Auto-generated method stub
		return "Wotd";
	}

}
