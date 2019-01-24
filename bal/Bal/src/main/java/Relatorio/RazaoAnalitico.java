package Relatorio;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Tabelas.Actbl;
import Tabelas.Acttr;
import Tabelas.Fcodf;
import Tabelas.Fcodff;
import Tabelas.Strel;
import dao.ActblDao;
import dao.ActtrDao;
import dao.FcodfDao;
import dao.FcodffDao;
import dao.StrelDao;
import net.sf.jasperreports.engine.JRException;

public class RazaoAnalitico {
	Double totaldia;
	Double totalconta;
	Double total;
//	DecimalFormat decimal = new DecimalFormat("###,###,###,##0.00");
	DecimalFormat decimal = new DecimalFormat("###,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt","BR")));
	String oldacc="";
	String oldce1="";
	Double init_bal;
	Double end_bal;
	Double init_bal2;
	Double end_bal2;
	Double bal;
	Double totdb=0.00;
	Double totcr=0.00;
	String accdsc;
	String ce1dsc;
	String dtini;
	String dtfin;
	boolean ajusta;
	List<Razao> listrazao=new ArrayList();
	private String fcodsc;
	public void gerarazao(String fco, Integer fyr, Integer per) {
		ajusta = true;
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
		ActtrDao acttrdao=new ActtrDao();
		Actbl actbl=new Actbl();
		List<Acttr> transactions = new ArrayList();
		transactions = acttrdao.getActtr(fco, fyr, per);
		bal=0.00;
		for (Acttr acttr:transactions) {
			String acc=acttr.getActtr_acc();
			String ce1=acttr.getActtr_ce1();
			if (!oldacc.equals(acc) || !oldce1.equals(ce1)) {
				if (!oldacc.equalsIgnoreCase("")) {
				totais(oldacc, oldce1);
				totdb=0.00;
				totcr=0.00;
				}
//				Actbl actbl=new Actbl();
				actbl=actbldao.getActblwithtr(fco, fyr, acttr.getActtr_acc(), acttr.getActtr_ce1(), ajusta);
				if (actbl.getActbl_acc()==null) {
					continue;
				}
				processavalor(actbl, per);
				strel = streldao.getStrel("COADF", fcodff.getFcodff_coa(), actbl.getActbl_acc());
				accdsc=strel.getStrel_dsc();
				strel = streldao.getStrel("COSDF", fcodff.getFcodff_ce_gr1(), actbl.getActbl_ce1());
				ce1dsc=strel.getStrel_dsc();
				bal=init_bal;
//				Double tot=acttrdao.gettotal(fco, fyr, per,  acttr.getActtr_acc(), acttr.getActtr_ce1());
//				end_bal=init_bal  + tot;
			}
			Razao razao = new Razao();
			razao.acc=acttr.getActtr_acc();
			razao.accdesc=accdsc;
			razao.ce1=acttr.getActtr_ce1();
			razao.ce1desc=ce1dsc;
			razao.accce1= acttr.getActtr_acc() + acttr.getActtr_ce1();
			razao.desc=acttr.getActtr_dsc();
			if (acttr.getActtr_opr().equalsIgnoreCase("D")) {
				razao.cred="";
				razao.deb=decimal.format(acttr.getActtr_trns_amt() / 100);
				totdb = totdb + acttr.getActtr_trns_amt();
			}
			else
			{
				razao.cred=decimal.format(acttr.getActtr_trns_amt() / 100);
				razao.deb="";
				totcr = totcr + acttr.getActtr_trns_amt();
			}
			razao.dia=acttr.getActtr_dt().toString().substring(6,8);
			if (bal > 0) {
				razao.inicialcredito=decimal.format(bal / 100) + "D";
				razao.inicialdebito="";
			}
			else
			{
				if (bal < 0) {
				init_bal2=bal * -1;
				razao.inicialcredito=decimal.format(init_bal2 / 100) + " C";
				razao.inicialdebito="";
				}
				else
				{
					razao.inicialcredito=decimal.format(bal / 100) + "  ";
					razao.inicialdebito="";					
				}
				
			}
			razao.proc=acttr.getActtr_ce3();
			razao.prod=acttr.getActtr_ce2();
			razao.seq=acttr.getActtr_seq().toString();
			razao.tipo=acttr.getActtr_doc_tp();
			if (end_bal>0) {
				razao.finalcredito=decimal.format(end_bal / 100) + " D";
				razao.finaldebito="";
			}
			else
			{
				if (end_bal<0) {
					end_bal2=end_bal * -1;
					razao.finalcredito=decimal.format(end_bal2 / 100) + " C";
					razao.finaldebito="";
				}
				else
				{
					razao.finalcredito=decimal.format(end_bal / 100) + "  ";
					razao.finaldebito="";					
				}
			}
			if (acttr.getActtr_bt_tp()=="GL") {
			razao.proclote=acttr.getActtr_bt_id();
			}
			else
			{
				razao.proclote=acttr.getActtr_doc_id();
			}
			listrazao.add(razao);
			oldacc=acttr.getActtr_acc();
			oldce1=acttr.getActtr_ce1();
		}
		if (oldacc!="") {
			totais(oldacc, oldce1);
		}
		switch (per) {
		case 1:
			dtini="01/01/" + fyr;
			dtfin="31/01/" + fyr;
			break;
		case 2:
			dtini="01/02/" + fyr;
			if (fyr==1980 || fyr==1984 || fyr == 1988 || fyr==1992 || fyr==1996 || fyr==2000 || fyr==2004 || fyr==2008 || fyr==2012 || fyr==2016) {
				dtfin="29/02/" + fyr;				
			}
			else
			{
				dtfin="28/02/" + fyr;
			}
			break;
		case 3:
			dtini="01/03/" + fyr;
			dtfin="31/03/" + fyr;
			break;
		case 4:
			dtini="01/04/" + fyr;
			dtfin="30/04/" + fyr;
			break;
		case 5:
			dtini="01/05/" + fyr;
			dtfin="31/05/" + fyr;
			break;
		case 6:
			dtini="01/06/" + fyr;
			dtfin="30/06/" + fyr;
			break;
		case 7:
			dtini="01/07/" + fyr;
			dtfin="31/07/" + fyr;
			break;
		case 8:
			dtini="01/08/" + fyr;
			dtfin="31/08/" + fyr;
			break;
		case 9:
			dtini="01/09/" + fyr;
			dtfin="30/09/" + fyr;
			break;
		case 10:
			dtini="01/10/" + fyr;
			dtfin="31/10/" + fyr;
			break;
		case 11:
			dtini="01/11/" + fyr;
			dtfin="30/11/" + fyr;
			break;
		case 12:
			dtini="01/12/" + fyr;
			dtfin="31/12/" + fyr;
			break;

		}
		GeraRelatorio gera = new GeraRelatorio();
		try {
			gera.geraRazao(listrazao, fcodsc, dtini, dtfin, "Plano de contas contabeis");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void processavalor (Actbl bl, Integer periodo) {
		switch (periodo) {
		case 1:
			init_bal=bl.getActbl_init_bal();
			end_bal=bl.getActbl_bal1();
			break;
		case 2:
			init_bal=bl.getActbl_bal1();
			end_bal=bl.getActbl_bal2();
			break;
		case 3:
			init_bal=bl.getActbl_bal2();
			end_bal=bl.getActbl_bal3();
			break;
		case 4:
			init_bal=bl.getActbl_bal3();
			end_bal=bl.getActbl_bal4();
			break;
		case 5:
			init_bal=bl.getActbl_bal4();
			end_bal=bl.getActbl_bal5();
			break;
		case 6:
			init_bal=bl.getActbl_bal5();
			end_bal=bl.getActbl_bal6();
			break;
		case 7:
			init_bal=bl.getActbl_bal6();
			end_bal=bl.getActbl_bal7();
			break;
		case 8:
			init_bal=bl.getActbl_bal7();
			end_bal=bl.getActbl_bal8();
			break;
		case 9:
			init_bal=bl.getActbl_bal8();
			end_bal=bl.getActbl_bal9();
			break;
		case 10:
			init_bal=bl.getActbl_bal9();
			end_bal=bl.getActbl_bal10();
			break;
		case 11:
			init_bal=bl.getActbl_bal10();
			end_bal=bl.getActbl_bal11();
			break;
		case 12:
			init_bal=bl.getActbl_bal11();
			end_bal=bl.getActbl_bal12();
			break;
		default:
			break;
		}
	}
		public void totais(String acc, String ce1) {
			Razao razao = new Razao();
			razao.acc=acc;
			razao.accdesc=accdsc;
			razao.ce1=ce1;
			razao.ce1desc=ce1dsc;
			razao.accce1= acc + ce1;
			razao.desc="Total a débito";
			razao.cred = decimal.format(totdb / 100) + "  ";
			razao.deb="";
			razao.dia="";
			razao.proc="";
			razao.prod="";
			razao.seq="";
			razao.tipo="";
			if (bal > 0) {
				razao.inicialcredito=decimal.format(bal / 100) + "D";
				razao.inicialdebito="";
			}
			else
			{
				if (bal < 0) {
				init_bal2=bal * -1;
				razao.inicialcredito=decimal.format(init_bal2 / 100) + " C";
				razao.inicialdebito="";
				}
				else
				{
					razao.inicialcredito=decimal.format(bal / 100) + "  ";
					razao.inicialdebito="";					
				}
				
			}
			
			if (end_bal>0) {
				razao.finalcredito=decimal.format(end_bal / 100) + " D";
				razao.finaldebito="";
			}
			else
			{
				if (end_bal<0) {
					end_bal2=end_bal * -1;
					razao.finalcredito=decimal.format(end_bal2 / 100) + " C";
					razao.finaldebito="";
				}
				else
				{
					razao.finalcredito=decimal.format(end_bal / 100) + "  ";
					razao.finaldebito="";					
				}
			}
			razao.proclote="";
			listrazao.add(razao);
			Razao razao2 = new Razao();
			razao2.acc=acc;
			razao2.accdesc=accdsc;
			razao2.ce1=ce1;
			razao2.ce1desc=ce1dsc;
			razao2.accce1= acc + ce1;
			razao2.desc="Total a crédito";
			razao2.cred = decimal.format(totcr / 100) + "  ";
			razao2.deb="";
			razao2.dia="";
			razao2.proc="";
			razao2.prod="";
			razao2.seq="";
			razao2.tipo="";
			if (bal > 0) {
				razao.inicialcredito=decimal.format(bal / 100) + "D";
				razao.inicialdebito="";
			}
			else
			{
				if (bal < 0) {
				init_bal2=bal * -1;
				razao.inicialcredito=decimal.format(init_bal2 / 100) + " C";
				razao.inicialdebito="";
				}
				else
				{
					razao.inicialcredito=decimal.format(bal / 100) + "  ";
					razao.inicialdebito="";					
				}
				
			}
			
			if (end_bal>0) {
				razao2.finalcredito=decimal.format(end_bal / 100) + " D";
				razao2.finaldebito="";
			}
			else
			{
				if (end_bal<0) {
					end_bal2=end_bal * -1;
					razao2.finalcredito=decimal.format(end_bal2 / 100) + " C";
					razao2.finaldebito="";
				}
				else
				{
					razao2.finalcredito=decimal.format(end_bal / 100) + "  ";
					razao2.finaldebito="";					
				}
			}
			razao2.proclote="";
			listrazao.add(razao2);
			
		}
	
}
