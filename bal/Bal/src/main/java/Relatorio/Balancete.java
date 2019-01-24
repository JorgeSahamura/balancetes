package Relatorio;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import Tabelas.Actbl;
import Tabelas.Fcodf;
import Tabelas.Fcodff;
import Tabelas.Strel;
import dao.ActblDao;
import dao.FcodfDao;
import dao.FcodffDao;
import dao.StrelDao;
import net.sf.jasperreports.engine.JRException;

public class Balancete {
	double ib1=0;
	double ib2=0;
	double ib4=0;
	double ib5=0;
	double ib7=0;
	double ib7T=0;
	double db1=0;
	double db2=0;
	double db4=0;
	double db5=0;
	double db7=0;
	double db7T=0;
	double cr1=0;
	double cr2=0;
	double cr4=0;
	double cr5=0;
	double cr7=0;
	double cr7T=0;
	double bal1=0;
	double bal2=0;
	double bal4=0;
	double bal5=0;
	double bal7=0;
	double bal7T=0;
	double init_bal=0;
	double debito=0;
	double credito=0;
	double totib=0;
	double totdeb=0;
	double totcred=0;
	double totbal=0;
	double bal=0;
	double old1;
	double old2;
	double old4;
	double old5;
	double old7;
	String oldacc1="";
	String oldacc2="";
	String oldacc4="";
	String oldacc5="";
	String oldacc="";
	String oldce1="";
	String acc;
	String accaux;
	String ce1;
	String dsc;
	String inicialalfa;
	String debitoalfa;
	String creditoalfa;
	String saldoalfa;
	String movimentoalfa;
	String fcodsc;
	double movimento;
	String desclinha;
	boolean printdsc;
	boolean ajusta;
	DecimalFormat decimal = new DecimalFormat("###,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt","BR")));
	List<Analitico> analiticos=new ArrayList();
	List<Sintetico> sinteticos=new ArrayList();
	public void gerabalancete(String fco, Integer fyr, Integer per, String tipo) {
	ajusta=true;
	List<Actbl> actbllist = new ArrayList<Actbl>();
	FcodffDao fcodffdao = new FcodffDao();
	Fcodff fcodff=new Fcodff();
	fcodff=fcodffdao.getFcodff(fco, fyr);
	ActblDao actbldao=new ActblDao();
	FcodfDao fcodfdao=new FcodfDao();
	Fcodf fcodf = new Fcodf();
	fcodf=fcodfdao.getFcodf(fco);
	fcodsc=fco + " - " + fcodf.getFcodf_dsc();
	Strel strel=new Strel();
	StrelDao streldao=new StrelDao();
	actbllist=actbldao.getActbl(fco, fyr, ajusta);
	for (Actbl actbl:actbllist) {
		printdsc=false;
		processavalor(actbl, per);
		if (init_bal==0 && debito==0 && credito==0 && bal==0) {
			continue;
		}
		if (tipo=="analitico") {
			acc=actbl.getActbl_acc().substring(0, 7);
			ce1=actbl.getActbl_ce1();
		}
		else
		{
			acc=actbl.getActbl_acc().substring(0, 7);
			ce1=" ";
		}
		if (oldacc=="") {
			strel=streldao.getStrel("COADF", fcodff.getFcodff_coa(), acc);
			dsc=strel.getStrel_dsc();
			printdesc(dsc, true);
		}
	accaux=acc.substring(0, 7);
	if (!accaux.equalsIgnoreCase(oldacc) && oldacc != "" && tipo=="analitico" ) {
		printdsc=true;
		strel=streldao.getStrel("COSDF", fcodff.getFcodff_ce_gr1(), oldce1);
		dsc=strel.getStrel_dsc();
		if (dsc!="") {
		Analitico analitico=new Analitico();
		movimento = db7 - cr7;
		desclinha=montadesc(oldacc, oldce1, dsc);
		analitico.setDescricao(desclinha);
		analitico.setInicial(decimal.format(ib7 / 100));
		analitico.setMovimento(decimal.format(movimento / 100));
		analitico.setDebito(decimal.format(db7 / 100));
		analitico.setCredito(decimal.format(cr7 / 100));
		analitico.setSaldo(decimal.format(bal7 / 100));
		analiticos.add(analitico);
		strel=streldao.getStrel("COADF", fcodff.getFcodff_coa(), oldacc);
		dsc=strel.getStrel_dsc();
		Analitico analiticot=new Analitico();
		movimento = db7T - cr7T;
		desclinha=montadesc(oldacc, " ", dsc);
		analiticot.setDescricao(desclinha);
		analiticot.setInicial(decimal.format(ib7T / 100));
		analiticot.setMovimento(decimal.format(movimento / 100));
		analiticot.setDebito(decimal.format(db7T / 100));
		analiticot.setCredito(decimal.format(cr7T / 100));
		analiticot.setSaldo(decimal.format(bal7T / 100));
		analiticos.add(analiticot);
		ib7=0;
		db7=0;
		cr7=0;
		bal7=0;
		ib7T=0;
		db7T=0;
		cr7T=0;
		bal7T=0;
		}
	} else
	{
		if ((!accaux.equalsIgnoreCase(oldacc) && oldacc != "" ) || (!ce1.equalsIgnoreCase(oldce1) && tipo=="analitico" && oldce1!="")) {
			if (tipo=="analitico") {
				strel=streldao.getStrel("COSDF", fcodff.getFcodff_ce_gr1(), oldce1);
			} else
			{
				strel=streldao.getStrel("COADF", fcodff.getFcodff_coa(), oldacc);	
			}
			dsc=strel.getStrel_dsc();
			if (dsc!="") {
			if (tipo=="analitico") {
			Analitico analitico=new Analitico();
			movimento = db7 - cr7;
			desclinha=montadesc(oldacc, oldce1, dsc);
			analitico.setDescricao(desclinha);
			analitico.setInicial(decimal.format(ib7 / 100));
			analitico.setMovimento(decimal.format(movimento / 100));
			analitico.setDebito(decimal.format(db7 / 100));
			analitico.setCredito(decimal.format(cr7 / 100));
			analitico.setSaldo(decimal.format(bal7 / 100));
			analiticos.add(analitico);
			}
			else
			{
				Sintetico sintetico=new Sintetico();
				movimento = db7 - cr7;
				sintetico.setConta(oldacc);
				sintetico.setDescricao(dsc);
				sintetico.setInicial(decimal.format(ib7 / 100));
				sintetico.setDebito(decimal.format(db7 / 100));
				sintetico.setCredito(decimal.format(cr7 / 100));
				sintetico.setMovimento(decimal.format(movimento / 100));
				sintetico.setSaldo(decimal.format(bal7 / 100));	
				sinteticos.add(sintetico);
			}
			ib7=0;
			db7=0;
			cr7=0;
			bal7=0;	
		}
		}
	}
		accaux=acc.substring(0,5);
		if (!accaux.equalsIgnoreCase(oldacc5) && oldacc5!=""){
			strel=streldao.getStrel("COADF", fcodff.getFcodff_coa(), oldacc5);
			dsc=strel.getStrel_dsc();
			if (dsc!="") {
			if (tipo=="analitico") {
				Analitico analiticob=new Analitico();
				 analiticob.setInicial(" ");
				 analiticob.setMovimento(" ");
				 analiticob.setDebito(" ");
				 analiticob.setCredito(" ");
				 analiticob.setSaldo(" ");
				analiticob.setDescricao(" ");
				analiticos.add(analiticob);
			Analitico analitico=new Analitico();
			movimento = db5 - cr5;
			desclinha=montadesc(oldacc5, " ", dsc);
			analitico.setDescricao(desclinha);
			analitico.setInicial(decimal.format(ib5 / 100));
			analitico.setMovimento(decimal.format(movimento / 100));
			analitico.setDebito(decimal.format(db5 / 100));
			analitico.setCredito(decimal.format(cr5 / 100));
			analitico.setSaldo(decimal.format(bal5 / 100));
			analiticos.add(analitico);
			}
			else
			{
				Sintetico sinteticob=new Sintetico();
				sinteticob.setConta("");
				sinteticob.setDescricao(" ");
				sinteticob.setInicial(" ");
				sinteticob.setDebito(" ");
				sinteticob.setCredito(" ");
				sinteticob.setMovimento(" ");
				sinteticob.setSaldo(" ");
				sinteticos.add(sinteticob);
			Sintetico sintetico=new Sintetico();
			movimento = db5 - cr5;
			sintetico.setConta(oldacc5);
			sintetico.setDescricao(dsc);
			sintetico.setInicial(decimal.format(ib5 / 100));
			sintetico.setDebito(decimal.format(db5 / 100));
			sintetico.setCredito(decimal.format(cr5 / 100));
			sintetico.setMovimento(decimal.format(movimento / 100));
			sintetico.setSaldo(decimal.format(bal5 / 100));
			sinteticos.add(sintetico);
			Sintetico sinteticoc=new Sintetico();
			sinteticoc.setConta("");
			sinteticoc.setDescricao(" ");
			sinteticoc.setInicial(" ");
			sinteticoc.setDebito(" ");
			sinteticoc.setCredito(" ");
			sinteticoc.setMovimento(" ");
			sinteticoc.setSaldo(" ");
			sinteticos.add(sinteticoc);
			}
			ib5=0;
			db5=0;
			cr5=0;
			bal5=0;				
		}
		}
		accaux=acc.substring(0,4);
		if (!accaux.equalsIgnoreCase(oldacc4) && oldacc4!=""){
			strel=streldao.getStrel("COADF", fcodff.getFcodff_coa(), oldacc4);
			dsc=strel.getStrel_dsc();
			if (dsc!="") {
			if (tipo=="analitico") {
				Analitico analiticob=new Analitico();
				 analiticob.setInicial(" ");
				 analiticob.setMovimento(" ");
				 analiticob.setDebito(" ");
				 analiticob.setCredito(" ");
				 analiticob.setSaldo(" ");
				analiticob.setDescricao(" ");
				analiticos.add(analiticob);
			Analitico analitico=new Analitico();
			movimento = db4 - cr4;
			desclinha=montadesc(oldacc4, " ", dsc);
			analitico.setDescricao(desclinha);
			analitico.setInicial(decimal.format(ib4 / 100));
			analitico.setMovimento(decimal.format(movimento / 100));
			analitico.setDebito(decimal.format(db4 / 100));
			analitico.setCredito(decimal.format(cr4 / 100));
			analitico.setSaldo(decimal.format(bal4 / 100));
			analiticos.add(analitico);
			}
			else
			{
				Sintetico sinteticob=new Sintetico();
				sinteticob.setConta("");
				sinteticob.setDescricao(" ");
				sinteticob.setInicial(" ");
				sinteticob.setDebito(" ");
				sinteticob.setCredito(" ");
				sinteticob.setMovimento(" ");
				sinteticob.setSaldo(" ");
				sinteticos.add(sinteticob);
			Sintetico sintetico=new Sintetico();
			movimento = db4 - cr4;
			sintetico.setConta(oldacc4);
			sintetico.setDescricao(dsc);
			sintetico.setInicial(decimal.format(ib4 / 100));
			sintetico.setDebito(decimal.format(db4 / 100));
			sintetico.setCredito(decimal.format(cr4 / 100));
			sintetico.setMovimento(decimal.format(movimento / 100));
			sintetico.setSaldo(decimal.format(bal4 / 100));
			sinteticos.add(sintetico);
			Sintetico sinteticoc=new Sintetico();
			sinteticoc.setConta("");
			sinteticoc.setDescricao(" ");
			sinteticoc.setInicial(" ");
			sinteticoc.setDebito(" ");
			sinteticoc.setCredito(" ");
			sinteticoc.setMovimento(" ");
			sinteticoc.setSaldo(" ");
			sinteticos.add(sinteticoc);
			}
			ib4=0;
			db4=0;
			cr4=0;
			bal4=0;				
		}
		}
		accaux=acc.substring(0,2);
		if (!accaux.equalsIgnoreCase(oldacc2) && oldacc2!=""){
			strel=streldao.getStrel("COADF", fcodff.getFcodff_coa(), oldacc2);
			dsc=strel.getStrel_dsc();
			if (dsc!="") {
			if (tipo=="analitico") {
				Analitico analiticob=new Analitico();
				 analiticob.setInicial(" ");
				 analiticob.setMovimento(" ");
				 analiticob.setDebito(" ");
				 analiticob.setCredito(" ");
				 analiticob.setSaldo(" ");
				analiticob.setDescricao(" ");
				analiticos.add(analiticob);
			Analitico analitico=new Analitico();
			movimento = db2 - cr2;
			desclinha=montadesc(oldacc2, " ", dsc);
			analitico.setDescricao(desclinha);
			analitico.setInicial(decimal.format(ib2 / 100));
			analitico.setMovimento(decimal.format(movimento / 100));
			analitico.setDebito(decimal.format(db2 / 100));
			analitico.setCredito(decimal.format(cr2 / 100));
			analitico.setSaldo(decimal.format(bal2 / 100));
			analiticos.add(analitico);
			}
			else
			{
				Sintetico sinteticob=new Sintetico();
				sinteticob.setConta("");
				sinteticob.setDescricao(" ");
				sinteticob.setInicial(" ");
				sinteticob.setDebito(" ");
				sinteticob.setCredito(" ");
				sinteticob.setMovimento(" ");
				sinteticob.setSaldo(" ");
				sinteticos.add(sinteticob);
			Sintetico sintetico=new Sintetico();
			movimento = db2 - cr2;
			sintetico.setConta(oldacc2);
			sintetico.setDescricao(dsc);
			sintetico.setInicial(decimal.format(ib2 / 100));
			sintetico.setDebito(decimal.format(db2 / 100));
			sintetico.setCredito(decimal.format(cr2 / 100));
			sintetico.setMovimento(decimal.format(movimento / 100));
			sintetico.setSaldo(decimal.format(bal2 / 100));
			sinteticos.add(sintetico);
			Sintetico sinteticoc=new Sintetico();
			sinteticoc.setConta("");
			sinteticoc.setDescricao(" ");
			sinteticoc.setInicial(" ");
			sinteticoc.setDebito(" ");
			sinteticoc.setCredito(" ");
			sinteticoc.setMovimento(" ");
			sinteticoc.setSaldo(" ");
			sinteticos.add(sinteticoc);

			}
			ib2=0;
			db2=0;
			cr2=0;
			bal2=0;				
		}
		}
		accaux=acc.substring(0,1);
		if (!accaux.equalsIgnoreCase(oldacc1) && oldacc1!=""){
			strel=streldao.getStrel("COADF", fcodff.getFcodff_coa(), oldacc1);
			dsc=strel.getStrel_dsc();
			if (dsc!="") {
			if (tipo=="analitico") {
			Analitico analitico=new Analitico();
			movimento = db1 - cr1;
			desclinha=montadesc(oldacc1, " ", dsc);
			Analitico analiticob=new Analitico();
			 analiticob.setInicial(" ");
			 analiticob.setMovimento(" ");
			 analiticob.setDebito(" ");
			 analiticob.setCredito(" ");
			 analiticob.setSaldo(" ");
			analiticob.setDescricao(" ");
			analiticos.add(analiticob);
			analitico.setDescricao(desclinha);
			analitico.setInicial(decimal.format(ib1 / 100));
			analitico.setMovimento(decimal.format(movimento / 100));
			analitico.setDebito(decimal.format(db1 / 100));
			analitico.setCredito(decimal.format(cr1 / 100));
			analitico.setSaldo(decimal.format(bal1 / 100));
			analiticos.add(analitico);
			}
			else
			{
				Sintetico sinteticob=new Sintetico();
				sinteticob.setConta("");
				sinteticob.setDescricao(" ");
				sinteticob.setInicial(" ");
				sinteticob.setDebito(" ");
				sinteticob.setCredito(" ");
				sinteticob.setMovimento(" ");
				sinteticob.setSaldo(" ");
				sinteticos.add(sinteticob);
			Sintetico sintetico=new Sintetico();
			movimento = db1 - cr1;
			sintetico.setConta(oldacc1);
			sintetico.setDescricao(dsc);
			sintetico.setInicial(decimal.format(ib1 / 100));
			sintetico.setDebito(decimal.format(db1 / 100));
			sintetico.setCredito(decimal.format(cr1 / 100));
			sintetico.setMovimento(decimal.format(movimento / 100));
			sintetico.setSaldo(decimal.format(bal1 / 100));
			sinteticos.add(sintetico);
			Sintetico sinteticoc=new Sintetico();
			sinteticoc.setConta("");
			sinteticoc.setDescricao(" ");
			sinteticoc.setInicial(" ");
			sinteticoc.setDebito(" ");
			sinteticoc.setCredito(" ");
			sinteticoc.setMovimento(" ");
			sinteticoc.setSaldo(" ");
			sinteticos.add(sinteticoc);
			}
			ib1=0;
			db1=0;
			cr1=0;
			bal1=0;				
			}
			
		}
		if (printdsc) {
			strel=streldao.getStrel("COADF", fcodff.getFcodff_coa(), acc);
			dsc=strel.getStrel_dsc();
			printdesc(dsc, false);
		}
		somavalores();
		oldacc1=acc.substring(0, 1);
		oldacc2=acc.substring(0, 2);
		oldacc4=acc.substring(0, 4);
		oldacc5=acc.substring(0, 5);
//		if (tipo=="analitico") {
		oldacc=acc.substring(0, 7);
//		} else {
//		oldacc=acc;
//		}
		oldce1=ce1;
	}
	totais(tipo, strel ,fcodff);
	GeraRelatorio gera = new GeraRelatorio();
	try {
		dsc=fco + "-" + fcodf.getFcodf_dsc();
		if (tipo=="analitico")
		{
		gera.geraAnalitico(analiticos, fcodsc, fyr, per);
		}
		else
		{
			gera.geraSintetico(sinteticos, fcodsc, fyr, per);			
		}
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void processavalor (Actbl bl, Integer periodo) {
		switch (periodo) {
		case 1:
			init_bal=bl.getActbl_init_bal();
			debito=bl.getActbl_db1();
			credito=bl.getActbl_cr1();
			bal=bl.getActbl_bal1();
			break;
		case 2:
			init_bal=bl.getActbl_bal1();
			debito=bl.getActbl_db2();
			credito=bl.getActbl_cr2();
			bal=bl.getActbl_bal2();
			break;
		case 3:
			init_bal=bl.getActbl_bal2();
			debito=bl.getActbl_db3();
			credito=bl.getActbl_cr3();
			bal=bl.getActbl_bal3();
			break;
		case 4:
			init_bal=bl.getActbl_bal3();
			debito=bl.getActbl_db4();
			credito=bl.getActbl_cr4();
			bal=bl.getActbl_bal4();
			break;
		case 5:
			init_bal=bl.getActbl_bal4();
			debito=bl.getActbl_db5();
			credito=bl.getActbl_cr5();
			bal=bl.getActbl_bal5();
			break;
		case 6:
			init_bal=bl.getActbl_bal5();
			debito=bl.getActbl_db6();
			credito=bl.getActbl_cr6();
			bal=bl.getActbl_bal6();
			break;
		case 7:
			init_bal=bl.getActbl_bal6();
			debito=bl.getActbl_db7();
			credito=bl.getActbl_cr7();
			bal=bl.getActbl_bal7();
			break;
		case 8:
			init_bal=bl.getActbl_bal7();
			debito=bl.getActbl_db8();
			credito=bl.getActbl_cr8();
			bal=bl.getActbl_bal8();
			break;
		case 9:
			init_bal=bl.getActbl_bal8();
			debito=bl.getActbl_db9();
			credito=bl.getActbl_cr9();
			bal=bl.getActbl_bal9();
			break;
		case 10:
			init_bal=bl.getActbl_bal9();
			debito=bl.getActbl_db10();
			credito=bl.getActbl_cr10();
			bal=bl.getActbl_bal10();
			break;
		case 11:
			init_bal=bl.getActbl_bal10();
			debito=bl.getActbl_db11();
			credito=bl.getActbl_cr11();
			bal=bl.getActbl_bal11();
			break;
		case 12:
			init_bal=bl.getActbl_bal11();
			debito=bl.getActbl_db12();
			credito=bl.getActbl_cr12();
			bal=bl.getActbl_bal12();
			break;
		case 13:
			init_bal=bl.getActbl_bal12();
			debito=bl.getActbl_db13();
			credito=bl.getActbl_cr13();
			bal=bl.getActbl_bal13();
			break;
		default:
			break;
		}
	}
	public void somavalores () {
		ib1= ib1 + init_bal;
		ib2= ib2 + init_bal;
		ib4= ib4 + init_bal;
		ib5= ib5 + init_bal;
		ib7= ib7 + init_bal;
		ib7T= ib7T + init_bal;
		db1= db1 + debito;
		db2= db2 + debito;
		db4= db4 + debito;
		db5= db5 + debito;
		db7= db7 + debito;
		db7T= db7T + debito;
		cr1= cr1 + credito;
		cr2= cr2 + credito;
		cr4= cr4 + credito;
		cr5= cr5 + credito;
		cr7= cr7 + credito;
		cr7T= cr7T + credito;
		bal1= bal1 + bal;
		bal2= bal2 + bal;
		bal4= bal4 + bal;
		bal5= bal5 + bal;
		bal7 = bal7 + bal;
		bal7T = bal7T + bal;
		totib = totib + init_bal;
		totdeb = totdeb + debito;
		totcred = totcred + credito;
		totbal=totbal + bal;
	}
	public void totais (String tipo, Strel strel, Fcodff fcodff) {
		StrelDao streldao=new StrelDao();
		if (tipo=="analitico") {
			strel=streldao.getStrel("COSDF", fcodff.getFcodff_ce_gr1(), ce1);
		} else
		{
			strel=streldao.getStrel("COADF", fcodff.getFcodff_coa(), acc);	
		}
		dsc=strel.getStrel_dsc();
		if (dsc!="") {
		if (tipo=="analitico") {
		Analitico analitico=new Analitico();
		movimento = db7 - cr7;
		desclinha=montadesc(acc, ce1, dsc);
		analitico.setDescricao(desclinha);
		analitico.setInicial(decimal.format(ib7 / 100));
		analitico.setMovimento(decimal.format(movimento / 100));
		analitico.setDebito(decimal.format(db7 / 100));
		analitico.setCredito(decimal.format(cr7 / 100));
		analitico.setSaldo(decimal.format(bal7 / 100));
		analiticos.add(analitico);
		strel=streldao.getStrel("COADF", fcodff.getFcodff_coa(), acc);
		dsc=strel.getStrel_dsc();
		Analitico analiticot=new Analitico();
		movimento = db7T - cr7T;
		desclinha=montadesc(acc, " ", dsc);
		analiticot.setDescricao(desclinha);
		analiticot.setInicial(decimal.format(ib7T / 100));
		analiticot.setMovimento(decimal.format(movimento / 100));
		analiticot.setDebito(decimal.format(db7T / 100));
		analiticot.setCredito(decimal.format(cr7T / 100));
		analiticot.setSaldo(decimal.format(bal7T / 100));
		analiticos.add(analiticot);
		}
		else
		{
			Sintetico sintetico=new Sintetico();
			movimento = db7 - cr7;
			sintetico.setConta(acc);
			sintetico.setDescricao(dsc);
			sintetico.setInicial(decimal.format(ib7 / 100));
			sintetico.setDebito(decimal.format(db7 / 100));
			sintetico.setCredito(decimal.format(cr7 / 100));
			sintetico.setMovimento(decimal.format(movimento / 100));
			sintetico.setSaldo(decimal.format(bal7 / 100));	
			sinteticos.add(sintetico);
		}
		}
		if (acc.substring(0, 5) != oldacc5 && oldacc5!=""){
			strel=streldao.getStrel("COADF", fcodff.getFcodff_coa(), oldacc5);
			dsc=strel.getStrel_dsc();
			if (dsc!="") {
			if (tipo=="analitico") {
				Analitico analiticob=new Analitico();
				 analiticob.setInicial(" ");
				 analiticob.setMovimento(" ");
				 analiticob.setDebito(" ");
				 analiticob.setCredito(" ");
				 analiticob.setSaldo(" ");
				analiticob.setDescricao(" ");
				analiticos.add(analiticob);
			Analitico analitico=new Analitico();
			movimento = db5 - cr5;
			desclinha=montadesc(oldacc5, " ", dsc);
			analitico.setDescricao(desclinha);
			analitico.setInicial(decimal.format(ib5 / 100));
			analitico.setMovimento(decimal.format(movimento / 100));
			analitico.setDebito(decimal.format(db5 / 100));
			analitico.setCredito(decimal.format(cr5 / 100));
			analitico.setSaldo(decimal.format(bal5 / 100));
			analiticos.add(analitico);
			}
			else
			{
				Sintetico sinteticob=new Sintetico();
				sinteticob.setConta("");
				sinteticob.setDescricao(" ");
				sinteticob.setInicial(" ");
				sinteticob.setDebito(" ");
				sinteticob.setCredito(" ");
				sinteticob.setMovimento(" ");
				sinteticob.setSaldo(" ");
				sinteticos.add(sinteticob);
			Sintetico sintetico=new Sintetico();
			movimento = db5 - cr5;
			sintetico.setConta(oldacc5);
			sintetico.setDescricao(dsc);
			sintetico.setInicial(decimal.format(ib5 / 100));
			sintetico.setDebito(decimal.format(db5 / 100));
			sintetico.setCredito(decimal.format(cr5 / 100));
			sintetico.setMovimento(decimal.format(movimento / 100));
			sintetico.setSaldo(decimal.format(bal5 / 100));
			sinteticos.add(sintetico);
			}
		}
		}
		if (acc.substring(0, 4) != oldacc4 && oldacc4!=""){
			strel=streldao.getStrel("COADF", fcodff.getFcodff_coa(), oldacc4);
			dsc=strel.getStrel_dsc();
			if (dsc!="") {
			if (tipo=="analitico") {
				Analitico analiticob=new Analitico();
				 analiticob.setInicial(" ");
				 analiticob.setMovimento(" ");
				 analiticob.setDebito(" ");
				 analiticob.setCredito(" ");
				 analiticob.setSaldo(" ");
				analiticob.setDescricao(" ");
				analiticos.add(analiticob);
			Analitico analitico=new Analitico();
			movimento = db4 - cr4;
			desclinha=montadesc(oldacc4, " ", dsc);
			analitico.setDescricao(desclinha);
			analitico.setInicial(decimal.format(ib4 / 100));
			analitico.setMovimento(decimal.format(movimento / 100));
			analitico.setDebito(decimal.format(db4 / 100));
			analitico.setCredito(decimal.format(cr4 / 100));
			analitico.setSaldo(decimal.format(bal4 / 100));
			analiticos.add(analitico);
			}
			else
			{
				Sintetico sinteticob=new Sintetico();
				sinteticob.setConta("");
				sinteticob.setDescricao(" ");
				sinteticob.setInicial(" ");
				sinteticob.setDebito(" ");
				sinteticob.setCredito(" ");
				sinteticob.setMovimento(" ");
				sinteticob.setSaldo(" ");
				sinteticos.add(sinteticob);
			Sintetico sintetico=new Sintetico();
			movimento = db4 - cr4;
			sintetico.setConta(oldacc4);
			sintetico.setDescricao(dsc);
			sintetico.setInicial(decimal.format(ib4 / 100));
			sintetico.setDebito(decimal.format(db4 / 100));
			sintetico.setCredito(decimal.format(cr4 / 100));
			sintetico.setMovimento(decimal.format(movimento / 100));
			sintetico.setSaldo(decimal.format(bal4 / 100));
			sinteticos.add(sintetico);
			}
		}
		}
		if (acc.substring(0, 2) != oldacc2 && oldacc2!=""){
			strel=streldao.getStrel("COADF", fcodff.getFcodff_coa(), oldacc2);
			dsc=strel.getStrel_dsc();
			if (dsc!="") {
			if (tipo=="analitico") {
				Analitico analiticob=new Analitico();
				 analiticob.setInicial(" ");
				 analiticob.setMovimento(" ");
				 analiticob.setDebito(" ");
				 analiticob.setCredito(" ");
				 analiticob.setSaldo(" ");
				analiticob.setDescricao(" ");
				analiticos.add(analiticob);
			Analitico analitico=new Analitico();
			movimento = db2 - cr2;
			desclinha=montadesc(oldacc2, " ", dsc);
			analitico.setDescricao(desclinha);
			analitico.setInicial(decimal.format(ib2 / 100));
			analitico.setMovimento(decimal.format(movimento / 100));
			analitico.setDebito(decimal.format(db2 / 100));
			analitico.setCredito(decimal.format(cr2 / 100));
			analitico.setSaldo(decimal.format(bal2 / 100));
			analiticos.add(analitico);
			}
			else
			{
				Sintetico sinteticob=new Sintetico();
				sinteticob.setConta("");
				sinteticob.setDescricao(" ");
				sinteticob.setInicial(" ");
				sinteticob.setDebito(" ");
				sinteticob.setCredito(" ");
				sinteticob.setMovimento(" ");
				sinteticob.setSaldo(" ");
				sinteticos.add(sinteticob);
			Sintetico sintetico=new Sintetico();
			movimento = db2 - cr2;
			sintetico.setConta(oldacc2);
			sintetico.setDescricao(dsc);
			sintetico.setInicial(decimal.format(ib2 / 100));
			sintetico.setDebito(decimal.format(db2 / 100));
			sintetico.setCredito(decimal.format(cr2 / 100));
			sintetico.setMovimento(decimal.format(movimento / 100));
			sintetico.setSaldo(decimal.format(bal2 / 100));
			sinteticos.add(sintetico);
			}
			}
		}
		if (acc.substring(0, 1) != oldacc1 && oldacc1!=""){
			strel=streldao.getStrel("COADF", fcodff.getFcodff_coa(), oldacc1);
			dsc=strel.getStrel_dsc();
			if (dsc!="") {
			if (tipo=="analitico") {
				Analitico analiticob=new Analitico();
				 analiticob.setInicial(" ");
				 analiticob.setMovimento(" ");
				 analiticob.setDebito(" ");
				 analiticob.setCredito(" ");
				 analiticob.setSaldo(" ");
				analiticob.setDescricao(" ");
				analiticos.add(analiticob);
			Analitico analitico=new Analitico();
			movimento = db1 - cr1;
			desclinha=montadesc(oldacc1, " ", dsc);
			analitico.setDescricao(desclinha);
			analitico.setInicial(decimal.format(ib1 / 100));
			analitico.setMovimento(decimal.format(movimento / 100));
			analitico.setDebito(decimal.format(db1 / 100));
			analitico.setCredito(decimal.format(cr1 / 100));
			analitico.setSaldo(decimal.format(bal1 / 100));
			analiticos.add(analitico);
			}
			else
			{
				Sintetico sinteticob=new Sintetico();
				sinteticob.setConta("");
				sinteticob.setDescricao(" ");
				sinteticob.setInicial(" ");
				sinteticob.setDebito(" ");
				sinteticob.setCredito(" ");
				sinteticob.setMovimento(" ");
				sinteticob.setSaldo(" ");
				sinteticos.add(sinteticob);
			Sintetico sintetico=new Sintetico();
			movimento = db1 - cr1;
			sintetico.setConta(oldacc1);
			sintetico.setDescricao(dsc);
			sintetico.setInicial(decimal.format(ib1 / 100));
			sintetico.setDebito(decimal.format(db1 / 100));
			sintetico.setCredito(decimal.format(cr1 / 100));
			sintetico.setMovimento(decimal.format(movimento / 100));
			sintetico.setSaldo(decimal.format(bal1 / 100));
			sinteticos.add(sintetico);
			}
			}
		}
		
		if (tipo=="analitico") {
			Analitico analiticob=new Analitico();
			 analiticob.setInicial(" ");
			 analiticob.setMovimento(" ");
			 analiticob.setDebito(" ");
			 analiticob.setCredito(" ");
			 analiticob.setSaldo(" ");
			analiticob.setDescricao(" ");
			analiticos.add(analiticob);
		Analitico analitico=new Analitico();
		movimento = totdeb - totcred;
		analitico.setDescricao("Total");
		analitico.setInicial(decimal.format(totib / 100));
		analitico.setMovimento(decimal.format(movimento / 100));
		analitico.setDebito(decimal.format(totdeb / 100));
		analitico.setCredito(decimal.format(totcred / 100));
		analitico.setSaldo(decimal.format(totbal / 100));
		analiticos.add(analitico);
		}
		else
		{
			Sintetico sinteticob=new Sintetico();
			sinteticob.setConta("");
			sinteticob.setDescricao(" ");
			sinteticob.setInicial(" ");
			sinteticob.setDebito(" ");
			sinteticob.setCredito(" ");
			sinteticob.setMovimento(" ");
			sinteticob.setSaldo(" ");
			sinteticos.add(sinteticob);
		Sintetico sintetico=new Sintetico();
		movimento = totdeb - totcred;
		sintetico.setConta("");
		sintetico.setDescricao("Total");
		sintetico.setInicial(decimal.format(totib / 100));
		sintetico.setDebito(decimal.format(totdeb / 100));
		sintetico.setCredito(decimal.format(totcred / 100));
		sintetico.setMovimento(decimal.format(movimento / 100));
		sintetico.setSaldo(decimal.format(totbal / 100));
		sinteticos.add(sintetico);
		}
		
	}
	public String montadesc (String acc, String ce1, String desc) {
        char[] chars=new char [60];
        Arrays.fill(chars, ' ');
        acc.getChars(0,acc.length(), chars, 0);        
        ce1.getChars(0, ce1.length(), chars, 8);
        desc.getChars(0, desc.length(), chars, 17);
        String linha=new String(chars);	
		return linha;
	}
	public void printdesc (String dsc, boolean primeiro) {
		if (dsc!="") {
			if (!primeiro) {
			Analitico analitico=new Analitico();
			 analitico.setInicial(" ");
			 analitico.setMovimento(" ");
			 analitico.setDebito(" ");
			 analitico.setCredito(" ");
			 analitico.setSaldo(" ");
			analitico.setDescricao(" ");
			analiticos.add(analitico);
			}
			Analitico analitico2 = new Analitico();
			 analitico2.setInicial(" ");
			 analitico2.setMovimento(" ");
			 analitico2.setDebito(" ");
			 analitico2.setCredito(" ");
			 analitico2.setSaldo(" ");
			analitico2.setDescricao(dsc);
			analiticos.add(analitico2);
		}
	}
}

