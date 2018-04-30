package interfacesch;
import java.rmi.Remote;
import java.rmi.RemoteException;

import classes.*;

public interface DBManageinterch extends Remote{//13 metode
public void addFarmacie(String nume,String adresa,String nrtel) throws RemoteException;
public void addMedicament(String nume,String desc,double pret,String poza) throws RemoteException;
public void addMed_Farmacie(int id_farmacie,int id_medicament,int cantitate) throws RemoteException;
public void addMed_Farmacie(String fNume,String pNume,int cantitate) throws RemoteException;
public void addMed_Farmacie(Farmacie f,Medicament p,int cantitate) throws RemoteException;
public void deleteMed_Farmacie(Farmacie f,Medicament p) throws RemoteException;
public void deleteMed_Farmacie(int id_farmacie,int id_medicament) throws RemoteException;
public void deleteMed_Farmacie(Med_Farmacie s) throws RemoteException;
public void deleteMed_Farmacie(int sid) throws RemoteException;
public void deleteMedicament(Medicament p) throws RemoteException;
public void deleteMedicament(int pid) throws RemoteException;
public void deleteMedicament(String mNume) throws RemoteException;
public void deleteFarmacie(Farmacie f) throws RemoteException;
public void deleteFarmacie(int fid) throws RemoteException;
public void deleteFarmacie(String fNUme) throws RemoteException;
}
