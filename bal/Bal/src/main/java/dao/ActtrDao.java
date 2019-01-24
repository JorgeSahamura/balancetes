package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Tabelas.Acttr;

public class ActtrDao {
	public List<Acttr> getActtr(String fco, Integer fyr, Integer per){
		List<Acttr> transactions = new ArrayList();
		Connection con;
			try {
				con = conexao.FabricaConexao.getconnection();
				PreparedStatement p =  con.prepareStatement("select   "
						+ " acttr_doc_tp, acttr_doc_id, acttr_seq, acttr_ssq, acttr_bt_tp, acttr_bt_id, acttr_je, acttr_acc, acttr_ce1, acttr_ce2, "
						+ " acttr_ce3, acttr_ce4, acttr_amc, acttr_amc_tp, acttr_amc_syn, acttr_dt, acttr_opr, acttr_opr_syn,acttr_trns_un_tp, "	
						+ " acttr_trns_un_id, acttr_trns_amt, acttr_trns_dec, acttr_fco_un_tp,  acttr_fco_un_id, acttr_fco_amt, acttr_fco_dec, acttr_std_id, "
						+ " acttr_std_vb, acttr_dsc, acttr_ref_dt, acttr_rev_dt, acttr_syn, acttr_uf1, acttr_uf2, acttr_uf3, acttr_uf4,acttr_st "
						+ " from consistgem.acttr where acttr_fco=? and acttr_fyr=? and acttr_per=?  and acttr_st='2' and acttr_amc='ACT' "
						+ " order by acttr_acc, acttr_ce1, acttr_dt, acttr_doc_tp, acttr_doc_id, acttr_bt_id, acttr_seq " );
				p.setString(1, fco);
				p.setInt(2, fyr);
				p.setInt(3, per);
				ResultSet rs=p.executeQuery();
//				int cont=0;				// retirar **************************
				while (rs.next()) {
					Acttr acttr=new Acttr();
					acttr.setActtr_fco(fco);
					acttr.setActtr_fyr(fyr);
					acttr.setActtr_per(per);
					acttr.setActtr_doc_tp(rs.getString(1));
					acttr.setActtr_doc_id(rs.getString(2));
					acttr.setActtr_seq(rs.getInt(3));
					acttr.setActtr_ssq(rs.getInt(4));
					acttr.setActtr_bt_tp(rs.getString(5));
					acttr.setActtr_bt_id(rs.getString(6));
					acttr.setActtr_je(rs.getString(7));
					acttr.setActtr_acc(rs.getString(8));
					acttr.setActtr_ce1(rs.getString(9));
					acttr.setActtr_ce2(rs.getString(10));
					acttr.setActtr_ce3(rs.getString(11));
					acttr.setActtr_ce4(rs.getString(12));
					acttr.setActtr_amc(rs.getString(13));
					acttr.setActtr_amc_tp(rs.getString(14));
					acttr.setActtr_amc_syn(rs.getString(15));
					acttr.setActtr_dt(rs.getInt(16));
					acttr.setActtr_opr(rs.getString(17));
					acttr.setActtr_opr_syn(rs.getString(18));
					acttr.setActtr_trns_un_tp(rs.getString(19));
					acttr.setActtr_trns_un_id(rs.getString(20));
					acttr.setActtr_trns_amt(rs.getDouble(21));
					acttr.setActtr_trns_dec(rs.getInt(22));
					acttr.setActtr_fco_un_tp(rs.getString(23));
					acttr.setActtr_fco_un_id(rs.getString(24));
					acttr.setActtr_fco_amt(rs.getDouble(25));
					acttr.setActtr_fco_dec(rs.getInt(26));
					acttr.setActtr_std_id(rs.getString(27));
					acttr.setActtr_std_vb(rs.getString(28));
					acttr.setActtr_dsc(rs.getString(29));
					acttr.setActtr_ref_dt(rs.getInt(30));
					acttr.setActtr_rev_dt(rs.getInt(31));
					acttr.setActtr_syn(rs.getString(32));
					acttr.setActtr_uf1(rs.getString(33));
					acttr.setActtr_uf2(rs.getString(34));
					acttr.setActtr_uf3(rs.getString(35));
					acttr.setActtr_uf4(rs.getString(36));
					acttr.setActtr_st(rs.getString(37));
					transactions.add(acttr);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return transactions;
	}
	public Double gettotal(String fco, Integer fyr, Integer per, String acc, String ce1) {
		Connection con;
		Double tot=0.00;
			try {
				con = conexao.FabricaConexao.getconnection();
				PreparedStatement p =  con.prepareStatement("select sum(acttr_trns_amt), acttr_opr "
						+ " from consistgem.acttr where acttr_fco=? and acttr_fyr=? and acttr_per=?  "
						+ " and acttr_acc=? and acttr_ce1 = ? and acttr_st='2' and acttr_amc='ACT' "
						+ " group by acttr_opr " );
				p.setString(1, fco);
				p.setInt(2, fyr);
				p.setInt(3, per);
				p.setString(4, acc);
				p.setString(5, ce1);
				ResultSet rs=p.executeQuery();
//				int cont=0;				// retirar **************************
				while (rs.next()) {
					String opr=rs.getString(2);
					if (opr.equalsIgnoreCase("D")) {
						tot=tot + rs.getDouble(1);
					}
					if (opr.equalsIgnoreCase("C")) {
						tot=tot - rs.getDouble(1);
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return tot;
	}
}
