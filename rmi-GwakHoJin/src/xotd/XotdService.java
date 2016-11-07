package xotd;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface XotdService extends Remote {
	public String getQotd() throws RemoteException;
	public String getWotd() throws RemoteException;
}
