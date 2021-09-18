package com.getman;
import java.rmi.*;
public interface ServiceServer extends Remote{
	Object[] getServicesArray() throws RemoteException;
	Service getService(Object objectKey) throws RemoteException;
}

