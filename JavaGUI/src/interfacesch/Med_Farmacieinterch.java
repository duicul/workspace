package interfacesch;

import java.rmi.Remote;
import java.rmi.RemoteException;

import classes.*;

public interface Med_Farmacieinterch extends Remote{
 public void changeQuantity(int sid,int quantity) throws RemoteException;
 public void changeQuantity(Med_Farmacie s,int quantity) throws RemoteException;
}
