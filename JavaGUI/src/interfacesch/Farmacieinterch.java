package interfacesch;

import java.rmi.Remote;
import java.rmi.RemoteException;

import classes.*;

public interface Farmacieinterch extends Remote{//6 metode
public void changeNume(int fid,String nume) throws RemoteException;
public void changeNume(Farmacie f,String nume) throws RemoteException;
public void changeAdresa(int fid,String adresa) throws RemoteException;
public void changeAdresa(Farmacie f,String adresa) throws RemoteException;
public void changeNrtel(int fid,String nrtel) throws RemoteException;
public void changeNrtel(Farmacie f,String nrtel) throws RemoteException;
}
