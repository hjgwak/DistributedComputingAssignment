package xotd;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.IOException;

public interface XotdService extends Remote {
	public String getQotd() throws RemoteException, IOException;
	public String getWotd() throws RemoteException, IOException;
}
