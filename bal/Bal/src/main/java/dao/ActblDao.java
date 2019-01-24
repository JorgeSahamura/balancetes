package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Tabelas.Actbl;

public class ActblDao {
	Long ib=0L;
	Long b1=0L;
	Long b2=0L;
	Long b3=0L;
	Long b4=0L;
	Long b5=0L;
	Long b6=0L;
	Long b7=0L;
	Long b8=0L;
	Long b9=0L;
	Long b10=0L;
	Long b11=0L;
	Long b12=0L;
	Long b13=0L;
	Long b14=0L;
	Double id=0.00;
	Double d1=0.00;
	Double d2=0.00;
	Double d3=0.00;
	Double d4=0.00;
	Double d5=0.00;
	Double d6=0.00;
	Double d7=0.00;
	Double d8=0.00;
	Double d9=0.00;
	Double d10=0.00;
	Double d11=0.00;
	Double d12=0.00;
	Double d13=0.00;
	Double d14=0.00;
	public List<Actbl> getActbl(String fco, Integer fyr, boolean ajusta) {
		List<Actbl> actbllist = new ArrayList();
	    Connection con;
		try {
			con = conexao.FabricaConexao.getconnection();
			PreparedStatement p =  con.prepareStatement("select   "
					+ "actbl_acc,actbl_ce1,actbl_ce2,actbl_ce3,actbl_ce4,"
					+ "actbl_init_bal,actbl_bal1,actbl_bal2,actbl_bal3,actbl_bal4,actbl_bal5,actbl_bal6,actbl_bal7,actbl_bal8,actbl_bal9,	"
					+ "actbl_bal10,actbl_bal11,actbl_bal12,actbl_bal13,actbl_bal14,actbl_db1,actbl_db2,actbl_db3,actbl_db4,actbl_db5,actbl_db6,actbl_db7,	"
					+ "actbl_db8,actbl_db9,actbl_db10,actbl_db11,actbl_db12,actbl_db13,actbl_db14,actbl_cr1,actbl_cr2,actbl_cr3,actbl_cr4,actbl_cr5,	"
					+ "actbl_cr6,actbl_cr7,actbl_cr8,actbl_cr9,actbl_cr10,actbl_cr11,actbl_cr12,actbl_cr13,actbl_cr14 "				 
					+ "from consistgem.actbl where actbl_fco=? and actbl_fyr=? and actbl_amc='ACT'  "
					+ "order by actbl_acc, actbl_ce1");
			p.setString(1, fco);
			p.setInt(2, fyr);
			ResultSet rs=p.executeQuery();
//			int cont=0;				// retirar **************************
			while (rs.next()) {
				Actbl abl = new Actbl();
				abl.setActbl_fco(fco);
				abl.setActbl_fyr(fyr);
				abl.setActbl_acc(rs.getString(1));
				abl.setActbl_ce1(rs.getString(2));
				abl.setActbl_ce2(rs.getString(3));
				abl.setActbl_ce3(rs.getString(4));
				abl.setActbl_ce4(rs.getString(5));
				if (ajusta) 	{
					String c = rs.getString(1);
					String cc="1505101";
					String e = rs.getString(2);
					String ee = "0507";
					if (c.equalsIgnoreCase(cc) && e.equalsIgnoreCase(ee)) {
					switch (fyr) {
					case 2000:
						abl.setActbl_init_bal(rs.getDouble(6));
						b1= (long) rs.getDouble(7);
						b1=b1 - 51474763L;
						d1 = (double) b1;
						b2= (long) rs.getDouble(8);
						b2=b2 - 51474763L;
						d2 = (double) b2;
						b3= (long) rs.getDouble(9);
						b3=b3 - 51474763L;
						d3 = (double) b3;
						b4= (long) rs.getDouble(10);
						b4=b4 - 51474763L;
						d4 = (double) b4;
						b5= (long) rs.getDouble(11);
						b5=b5 - 51474763L;
						d5 = (double) b5;
						b6= (long) rs.getDouble(12);
						b6=b6 - 51474763L;
						d6 = (double) b6;
						b7= (long) rs.getDouble(13);
						b7=b7 - 51474763L;
						d7 = (double) b7;
						b8= (long) rs.getDouble(14);
						b8=b8 - 51474763L;
						d8 = (double) b8;
						b9= (long) rs.getDouble(15);
						b9=b9 - 51474763L;
						d9 = (double) b9;
						b10= (long) rs.getDouble(16);
						b10=b10 - 51474763L;
						d10 = (double) b10;
						b11= (long) rs.getDouble(17);
						b11=b11 - 51474763L;
						d11 = (double) b11;
						b12= (long) rs.getDouble(18);
						b12=b12 - 51474763L;
						d12 = (double) b12;
						b13= (long) rs.getDouble(19);
						b13=b13 - 51474763L;
						d13 = (double) b13;
						b14= (long) rs.getDouble(20);
						b14=b14 - 51474763L;
						d14 = (double) b14;
						abl.setActbl_bal1(d1);
						abl.setActbl_bal2(d2);
						abl.setActbl_bal3(d3);
						abl.setActbl_bal4(d4);
						abl.setActbl_bal5(d5);
						abl.setActbl_bal6(d6);
						abl.setActbl_bal7(d7);
						abl.setActbl_bal8(d8);
						abl.setActbl_bal9(d9);
						abl.setActbl_bal10(d10);
						abl.setActbl_bal11(d11);
						abl.setActbl_bal12(d12);
						abl.setActbl_bal13(d13);
						abl.setActbl_bal14(d14);
						break;
					case 2001:
						abl.setActbl_init_bal(rs.getDouble(6) - 51474763L);
						abl.setActbl_bal1(rs.getDouble(7) - 265813797L);
						abl.setActbl_bal2(rs.getDouble(8) - 265813797L);
						abl.setActbl_bal3(rs.getDouble(9) - 265813797L);
						abl.setActbl_bal4(rs.getDouble(10) - 265813797L);
						abl.setActbl_bal5(rs.getDouble(11) - 265813797L);
						abl.setActbl_bal6(rs.getDouble(12) - 265813797L);
						abl.setActbl_bal7(rs.getDouble(13) - 265813797L);
						abl.setActbl_bal8(rs.getDouble(14) - 265813797L);
						abl.setActbl_bal9(rs.getDouble(15) - 265813797L);
						abl.setActbl_bal10(rs.getDouble(16) - 265813797L);
						abl.setActbl_bal11(rs.getDouble(17) - 265813797L);
						abl.setActbl_bal12(rs.getDouble(18) - 265813797L);
						abl.setActbl_bal13(rs.getDouble(19) - 265813797L);
						abl.setActbl_bal14(rs.getDouble(20) - 265813797L);
						break;
					case 2002:
						abl.setActbl_init_bal(rs.getDouble(6) - 265813797L);
						abl.setActbl_bal1(rs.getDouble(7) - 646140521L);
						abl.setActbl_bal2(rs.getDouble(8) - 646140521L);
						abl.setActbl_bal3(rs.getDouble(9) - 646140521L);
						abl.setActbl_bal4(rs.getDouble(10) - 646140521L);
						abl.setActbl_bal5(rs.getDouble(11) - 646140521L);
						abl.setActbl_bal6(rs.getDouble(12) - 646140521L);
						abl.setActbl_bal7(rs.getDouble(13) - 646140521L);
						abl.setActbl_bal8(rs.getDouble(14) - 646140521L);
						abl.setActbl_bal9(rs.getDouble(15) - 646140521L);
						abl.setActbl_bal10(rs.getDouble(16) - 646140521L);
						abl.setActbl_bal11(rs.getDouble(17) - 646140521L);
						abl.setActbl_bal12(rs.getDouble(18) - 646140521L);
						abl.setActbl_bal13(rs.getDouble(19) - 646140521L);
						abl.setActbl_bal14(rs.getDouble(20) - 646140521L);
						break;
					case 2003:
						abl.setActbl_init_bal(rs.getDouble(6) - 646140521L);
						abl.setActbl_bal1(rs.getDouble(7) - 1059669978L);
						abl.setActbl_bal2(rs.getDouble(8) - 1059669978L);
						abl.setActbl_bal3(rs.getDouble(9) - 1059669978L);
						abl.setActbl_bal4(rs.getDouble(10) - 1059669978L);
						abl.setActbl_bal5(rs.getDouble(11) - 1059669978L);
						abl.setActbl_bal6(rs.getDouble(12) - 1059669978L);
						abl.setActbl_bal7(rs.getDouble(13) - 1059669978L);
						abl.setActbl_bal8(rs.getDouble(14) - 1059669978L);
						abl.setActbl_bal9(rs.getDouble(15) - 1059669978L);
						abl.setActbl_bal10(rs.getDouble(16) - 1059669978L);
						abl.setActbl_bal11(rs.getDouble(17) - 1059669978L);
						abl.setActbl_bal12(rs.getDouble(18) - 1059669978L);
						abl.setActbl_bal13(rs.getDouble(19) - 1059669978L);
						abl.setActbl_bal14(rs.getDouble(20) - 1059669978L);
						break;
					case 2004:
						abl.setActbl_init_bal(rs.getDouble(6) - 1059669978L);
						abl.setActbl_bal1(rs.getDouble(7) - 1629818398L);
						abl.setActbl_bal2(rs.getDouble(8) - 1629818398L);
						abl.setActbl_bal3(rs.getDouble(9) - 1629818398L);
						abl.setActbl_bal4(rs.getDouble(10) - 1629818398L);
						abl.setActbl_bal5(rs.getDouble(11) - 1629818398L);
						abl.setActbl_bal6(rs.getDouble(12) - 1629818398L);
						abl.setActbl_bal7(rs.getDouble(13) - 1629818398L);
						abl.setActbl_bal8(rs.getDouble(14) - 1629818398L);
						abl.setActbl_bal9(rs.getDouble(15) - 1629818398L);
						abl.setActbl_bal10(rs.getDouble(16) - 1629818398L);
						abl.setActbl_bal11(rs.getDouble(17) - 1629818398L);
						abl.setActbl_bal12(rs.getDouble(18) - 1629818398L);
						abl.setActbl_bal13(rs.getDouble(19) - 1629818398L);
						abl.setActbl_bal14(rs.getDouble(20) - 1629818398L);
						break;
						default:
							abl.setActbl_init_bal(rs.getDouble(6));
							abl.setActbl_bal1(rs.getDouble(7));
							abl.setActbl_bal2(rs.getDouble(8));
							abl.setActbl_bal3(rs.getDouble(9));
							abl.setActbl_bal4(rs.getDouble(10));
							abl.setActbl_bal5(rs.getDouble(11));
							abl.setActbl_bal6(rs.getDouble(12));
							abl.setActbl_bal7(rs.getDouble(13));
							abl.setActbl_bal8(rs.getDouble(14));
							abl.setActbl_bal9(rs.getDouble(15));
							abl.setActbl_bal10(rs.getDouble(16));
							abl.setActbl_bal11(rs.getDouble(17));
							abl.setActbl_bal12(rs.getDouble(18));
							abl.setActbl_bal13(rs.getDouble(19));
							abl.setActbl_bal14(rs.getDouble(20));
							
					}
					}
				else
				{
				abl.setActbl_init_bal(rs.getDouble(6));
				abl.setActbl_bal1(rs.getDouble(7));
				abl.setActbl_bal2(rs.getDouble(8));
				abl.setActbl_bal3(rs.getDouble(9));
				abl.setActbl_bal4(rs.getDouble(10));
				abl.setActbl_bal5(rs.getDouble(11));
				abl.setActbl_bal6(rs.getDouble(12));
				abl.setActbl_bal7(rs.getDouble(13));
				abl.setActbl_bal8(rs.getDouble(14));
				abl.setActbl_bal9(rs.getDouble(15));
				abl.setActbl_bal10(rs.getDouble(16));
				abl.setActbl_bal11(rs.getDouble(17));
				abl.setActbl_bal12(rs.getDouble(18));
				abl.setActbl_bal13(rs.getDouble(19));
				abl.setActbl_bal14(rs.getDouble(20));
				}
				}
					else
					{
						abl.setActbl_init_bal(rs.getDouble(6));
						abl.setActbl_bal1(rs.getDouble(7));
						abl.setActbl_bal2(rs.getDouble(8));
						abl.setActbl_bal3(rs.getDouble(9));
						abl.setActbl_bal4(rs.getDouble(10));
						abl.setActbl_bal5(rs.getDouble(11));
						abl.setActbl_bal6(rs.getDouble(12));
						abl.setActbl_bal7(rs.getDouble(13));
						abl.setActbl_bal8(rs.getDouble(14));
						abl.setActbl_bal9(rs.getDouble(15));
						abl.setActbl_bal10(rs.getDouble(16));
						abl.setActbl_bal11(rs.getDouble(17));
						abl.setActbl_bal12(rs.getDouble(18));
						abl.setActbl_bal13(rs.getDouble(19));
						abl.setActbl_bal14(rs.getDouble(20));
						
					}
				abl.setActbl_db1(rs.getDouble(21));
				abl.setActbl_db2(rs.getDouble(22));
				abl.setActbl_db3(rs.getDouble(23));
				abl.setActbl_db4(rs.getDouble(24));
				abl.setActbl_db5(rs.getDouble(25));
				abl.setActbl_db6(rs.getDouble(26));
				abl.setActbl_db7(rs.getDouble(27));
				abl.setActbl_db8(rs.getDouble(28));
				abl.setActbl_db9(rs.getDouble(29));
				abl.setActbl_db10(rs.getDouble(30));
				abl.setActbl_db11(rs.getDouble(31));
				abl.setActbl_db12(rs.getDouble(32));
				abl.setActbl_db13(rs.getDouble(33));
				abl.setActbl_db14(rs.getDouble(34));
				abl.setActbl_cr1(rs.getDouble(35));
				abl.setActbl_cr2(rs.getDouble(36));
				abl.setActbl_cr3(rs.getDouble(37));
				abl.setActbl_cr4(rs.getDouble(38));
				abl.setActbl_cr5(rs.getDouble(39));
				abl.setActbl_cr6(rs.getDouble(40));
				abl.setActbl_cr7(rs.getDouble(41));
				abl.setActbl_cr8(rs.getDouble(42));
				abl.setActbl_cr9(rs.getDouble(43));
				abl.setActbl_cr10(rs.getDouble(44));
				abl.setActbl_cr11(rs.getDouble(45));
				abl.setActbl_cr12(rs.getDouble(46));
				abl.setActbl_cr13(rs.getDouble(47));
				abl.setActbl_cr14(rs.getDouble(48));
				actbllist.add(abl);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return actbllist;
	}
	public Actbl getActblwithtr(String fco, Integer fyr, String acc, String ce1, boolean ajusta) {
		Actbl abl = new Actbl();
		Double init_bal=0.00;
		Double bal1=0.00;
		Double bal2=0.00;
		Double bal3=0.00;
		Double bal4=0.00;
		Double bal5=0.00;
		Double bal6=0.00;
		Double bal7=0.00;
		Double bal8=0.00;
		Double bal9=0.00;
		Double bal10=0.00;
		Double bal11=0.00;
		Double bal12=0.00;
		Double bal13=0.00;
		Double bal14=0.00;
		Double cr1=0.00;
		Double cr2=0.00;
		Double cr3=0.00;
		Double cr4=0.00;
		Double cr5=0.00;
		Double cr6=0.00;
		Double cr7=0.00;
		Double cr8=0.00;
		Double cr9=0.00;
		Double cr10=0.00;
		Double cr11=0.00;
		Double cr12=0.00;
		Double cr13=0.00;
		Double cr14=0.00;
		Double db1=0.00;
		Double db2=0.00;
		Double db3=0.00;
		Double db4=0.00;
		Double db5=0.00;
		Double db6=0.00;
		Double db7=0.00;
		Double db8=0.00;
		Double db9=0.00;
		Double db10=0.00;
		Double db11=0.00;
		Double db12=0.00;
		Double db13=0.00;
		Double db14=0.00;
		String conta="";
		String centro="";
		boolean exst;
	    Connection con;
		try {
			con = conexao.FabricaConexao.getconnection();
			PreparedStatement p =  con.prepareStatement("select   "
					+ "actbl_acc,actbl_ce1,actbl_ce2,actbl_ce3,actbl_ce4,"
					+ "actbl_init_bal,actbl_bal1,actbl_bal2,actbl_bal3,actbl_bal4,actbl_bal5,actbl_bal6,actbl_bal7,actbl_bal8,actbl_bal9,	"
					+ "actbl_bal10,actbl_bal11,actbl_bal12,actbl_bal13,actbl_bal14,actbl_db1,actbl_db2,actbl_db3,actbl_db4,actbl_db5,actbl_db6,actbl_db7,	"
					+ "actbl_db8,actbl_db9,actbl_db10,actbl_db11,actbl_db12,actbl_db13,actbl_db14,actbl_cr1,actbl_cr2,actbl_cr3,actbl_cr4,actbl_cr5,	"
					+ "actbl_cr6,actbl_cr7,actbl_cr8,actbl_cr9,actbl_cr10,actbl_cr11,actbl_cr12,actbl_cr13,actbl_cr14 "				 
					+ "from consistgem.actbl where actbl_fco=? and actbl_fyr=? and actbl_acc=? and actbl_ce1=? and actbl_amc='ACT'  "
					+ "order by actbl_acc, actbl_ce1");
			p.setString(1, fco);
			p.setInt(2, fyr);
			p.setString(3, acc);
			p.setString(4, ce1);
			ResultSet rs=p.executeQuery();
			exst=false;
			while (rs.next()) {
				exst=true;
				abl.setActbl_fco(fco);
				abl.setActbl_fyr(fyr);
				abl.setActbl_acc(rs.getString(1));
				abl.setActbl_ce1(rs.getString(2));
				conta=rs.getString(1);
				centro=rs.getString(2);
				abl.setActbl_ce2(rs.getString(3));
				abl.setActbl_ce3(rs.getString(4));
				abl.setActbl_ce4(rs.getString(5));
				init_bal= init_bal + rs.getDouble(6);
				bal1 = bal1 + rs.getDouble(7);
				bal2 = bal2 + rs.getDouble(8);
				bal3 = bal3 + rs.getDouble(9);
				bal4 = bal4 + rs.getDouble(10);
				bal5 = bal5 + rs.getDouble(11);
				bal6 = bal6 + rs.getDouble(12);
				bal7 = bal7 + rs.getDouble(13);
				bal8 = bal8 + rs.getDouble(14);
				bal9 = bal9 + rs.getDouble(15);
				bal10 = bal10 + rs.getDouble(16);
				bal11 = bal11 + rs.getDouble(17);
				bal12 = bal12 + rs.getDouble(18);
				bal13 = bal13 + rs.getDouble(19);
				bal14 = bal14 + rs.getDouble(20);
				db1 = db1 + rs.getDouble(21);
				db2 = db2 + rs.getDouble(22);
				db3 = db3 + rs.getDouble(23);
				db4 = db4 + rs.getDouble(24);
				db5 = db5 + rs.getDouble(25);
				db6 = db6 + rs.getDouble(26);
				db7 = db7 + rs.getDouble(27);
				db8 = db8 + rs.getDouble(28);
				db9 = db9 + rs.getDouble(29);
				db10 = db10 + rs.getDouble(30);
				db11 = db11 + rs.getDouble(31);
				db12 = db12 + rs.getDouble(32);
				db13 = db13 + rs.getDouble(33);
				db14 = db14 + rs.getDouble(34);
				cr1 = cr1 + rs.getDouble(35);
				cr2 = cr2 + rs.getDouble(36);
				cr3 = cr3 + rs.getDouble(37);
				cr4 = cr4 + rs.getDouble(38);
				cr5 = cr5 + rs.getDouble(39);
				cr6 = cr6 + rs.getDouble(40);
				cr7 = cr7 + rs.getDouble(41);
				cr8 = cr8 + rs.getDouble(42);
				cr9 = cr9 + rs.getDouble(43);
				cr10 = cr10 + rs.getDouble(44);
				cr11 = cr11 + rs.getDouble(45);
				cr12 = cr12 + rs.getDouble(46);
				cr13 = cr13 + rs.getDouble(47);
				cr14 = cr14 + rs.getDouble(48);
			
			}
			if (exst) {
			if (ajusta) {
				String c = conta;
				String cc="1505101";
				String e = centro;
				String ee = "0507";
				if (c.equalsIgnoreCase(cc) && e.equalsIgnoreCase(ee)) {
				switch (fyr) {
				case 2000:
					abl.setActbl_init_bal(init_bal);
					abl.setActbl_bal1(bal1 - 51474763L);
					abl.setActbl_bal2(bal2 - 51474763L);
					abl.setActbl_bal3(bal3 - 51474763L);
					abl.setActbl_bal4(bal4 - 51474763L);
					abl.setActbl_bal5(bal5 - 51474763L);
					abl.setActbl_bal6(bal6 - 51474763L);
					abl.setActbl_bal7(bal7 - 51474763L);
					abl.setActbl_bal8(bal8 - 51474763L);
					abl.setActbl_bal9(bal9 - 51474763L);
					abl.setActbl_bal10(bal10 - 51474763L);
					abl.setActbl_bal11(bal11 - 51474763L);
					abl.setActbl_bal12(bal12 - 51474763L);
					abl.setActbl_bal13(bal13 - 51474763L);
					abl.setActbl_bal14(bal14 - 51474763L);		
					break;
				case 2001:
					abl.setActbl_init_bal(init_bal - 51474763L);
					abl.setActbl_bal1(bal1 - 265813797L);
					abl.setActbl_bal2(bal2 - 265813797L);
					abl.setActbl_bal3(bal3 - 265813797L);
					abl.setActbl_bal4(bal4 - 265813797L);
					abl.setActbl_bal5(bal5 - 265813797L);
					abl.setActbl_bal6(bal6 - 265813797L);
					abl.setActbl_bal7(bal7 - 265813797L);
					abl.setActbl_bal8(bal8 - 265813797L);
					abl.setActbl_bal9(bal9 - 265813797L);
					abl.setActbl_bal10(bal10 - 265813797L);
					abl.setActbl_bal11(bal11 - 265813797L);
					abl.setActbl_bal12(bal12 - 265813797L);
					abl.setActbl_bal13(bal13 - 265813797L);
					abl.setActbl_bal14(bal14 - 265813797L);	
					break;
				case 2002:
					abl.setActbl_init_bal(init_bal - 265813797L);
					abl.setActbl_bal1(bal1 - 646140521L);
					abl.setActbl_bal2(bal2 - 646140521L);
					abl.setActbl_bal3(bal3 - 646140521L);
					abl.setActbl_bal4(bal4 - 646140521L);
					abl.setActbl_bal5(bal5 - 646140521L);
					abl.setActbl_bal6(bal6 - 646140521L);
					abl.setActbl_bal7(bal7 - 646140521L);
					abl.setActbl_bal8(bal8 - 646140521L);
					abl.setActbl_bal9(bal9 - 646140521L);
					abl.setActbl_bal10(bal10 - 646140521L);
					abl.setActbl_bal11(bal11 - 646140521L);
					abl.setActbl_bal12(bal12 - 646140521L);
					abl.setActbl_bal13(bal13 - 646140521L);
					abl.setActbl_bal14(bal14 - 646140521L);		
					break;
				case 2003:
					abl.setActbl_init_bal(init_bal - 646140521L);
					abl.setActbl_bal1(bal1 - 1059669978L);
					abl.setActbl_bal2(bal2 - 1059669978L);
					abl.setActbl_bal3(bal3 - 1059669978L);
					abl.setActbl_bal4(bal4 - 1059669978L);
					abl.setActbl_bal5(bal5 - 1059669978L);
					abl.setActbl_bal6(bal6 - 1059669978L);
					abl.setActbl_bal7(bal7 - 1059669978L);
					abl.setActbl_bal8(bal8 - 1059669978L);
					abl.setActbl_bal9(bal9 - 1059669978L);
					abl.setActbl_bal10(bal10 - 1059669978L);
					abl.setActbl_bal11(bal11 - 1059669978L);
					abl.setActbl_bal12(bal12 - 1059669978L);
					abl.setActbl_bal13(bal13 - 1059669978L);
					abl.setActbl_bal14(bal14 - 1059669978L);
					break;
				case 2004:
					abl.setActbl_init_bal(init_bal - 1059669978L);
					abl.setActbl_bal1(bal1 - 1629818398L);
					abl.setActbl_bal2(bal2 - 1629818398L);
					abl.setActbl_bal3(bal3 - 1629818398L);
					abl.setActbl_bal4(bal4 - 1629818398L);
					abl.setActbl_bal5(bal5 - 1629818398L);
					abl.setActbl_bal6(bal6 - 1629818398L);
					abl.setActbl_bal7(bal7 - 1629818398L);
					abl.setActbl_bal8(bal8 - 1629818398L);
					abl.setActbl_bal9(bal9 - 1629818398L);
					abl.setActbl_bal10(bal10 - 1629818398L);
					abl.setActbl_bal11(bal11 - 1629818398L);
					abl.setActbl_bal12(bal12 - 1629818398L);
					abl.setActbl_bal13(bal13 - 1629818398L);
					abl.setActbl_bal14(bal14 - 1629818398L);		
					break;
					default:
						abl.setActbl_init_bal(init_bal);
						abl.setActbl_bal1(bal1);
						abl.setActbl_bal2(bal2);
						abl.setActbl_bal3(bal3);
						abl.setActbl_bal4(bal4);
						abl.setActbl_bal5(bal5);
						abl.setActbl_bal6(bal6);
						abl.setActbl_bal7(bal7);
						abl.setActbl_bal8(bal8);
						abl.setActbl_bal9(bal9);
						abl.setActbl_bal10(bal10);
						abl.setActbl_bal11(bal11);
						abl.setActbl_bal12(bal12);
						abl.setActbl_bal13(bal13);
						abl.setActbl_bal14(bal14);
					   break;	
				}
			}
			else
			{
			abl.setActbl_init_bal(init_bal);
			abl.setActbl_bal1(bal1);
			abl.setActbl_bal2(bal2);
			abl.setActbl_bal3(bal3);
			abl.setActbl_bal4(bal4);
			abl.setActbl_bal5(bal5);
			abl.setActbl_bal6(bal6);
			abl.setActbl_bal7(bal7);
			abl.setActbl_bal8(bal8);
			abl.setActbl_bal9(bal9);
			abl.setActbl_bal10(bal10);
			abl.setActbl_bal11(bal11);
			abl.setActbl_bal12(bal12);
			abl.setActbl_bal13(bal13);
			abl.setActbl_bal14(bal14);
			}
			}
			else
			{
				abl.setActbl_init_bal(init_bal);
				abl.setActbl_bal1(bal1);
				abl.setActbl_bal2(bal2);
				abl.setActbl_bal3(bal3);
				abl.setActbl_bal4(bal4);
				abl.setActbl_bal5(bal5);
				abl.setActbl_bal6(bal6);
				abl.setActbl_bal7(bal7);
				abl.setActbl_bal8(bal8);
				abl.setActbl_bal9(bal9);
				abl.setActbl_bal10(bal10);
				abl.setActbl_bal11(bal11);
				abl.setActbl_bal12(bal12);
				abl.setActbl_bal13(bal13);
				abl.setActbl_bal14(bal14);
				
			}
			abl.setActbl_db1(db1);
			abl.setActbl_db2(db2);
			abl.setActbl_db3(db3);
			abl.setActbl_db4(db4);
			abl.setActbl_db5(db5);
			abl.setActbl_db6(db6);
			abl.setActbl_db7(db7);
			abl.setActbl_db8(db8);
			abl.setActbl_db9(db9);
			abl.setActbl_db10(db10);
			abl.setActbl_db11(db11);
			abl.setActbl_db12(db12);
			abl.setActbl_db13(db13);
			abl.setActbl_db14(db14);
			abl.setActbl_cr1(cr1);
			abl.setActbl_cr2(cr2);
			abl.setActbl_cr3(cr3);
			abl.setActbl_cr4(cr4);
			abl.setActbl_cr5(cr5);
			abl.setActbl_cr6(cr6);
			abl.setActbl_cr7(cr7);
			abl.setActbl_cr8(cr8);
			abl.setActbl_cr9(cr9);
			abl.setActbl_cr10(cr10);
			abl.setActbl_cr11(cr11);
			abl.setActbl_cr12(cr12);
			abl.setActbl_cr13(cr13);
			abl.setActbl_cr14(cr14);	
			
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return abl;
	}
}
