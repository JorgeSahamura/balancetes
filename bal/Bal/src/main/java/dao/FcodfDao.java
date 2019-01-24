package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Tabelas.Fcodf;
import conexao.FabricaConexao;

public class FcodfDao {
	public Fcodf getFcodf(String id) {
		Fcodf fcodf=new Fcodf();
			  try {
			    Connection con=conexao.FabricaConexao.getconnection();
				PreparedStatement p =  con.prepareStatement("select fcodf_dsc,fcodf_ent_gr,fcodf_ent,	fcodf_site,	fcodf_fnc_cuy,fcodf_syn_ind,fcodf_st,fcodf_acct_bt_gr  from consistgem.fcodf where fcodf_id=? ");
				p.setString(1, id);
				ResultSet rs=p.executeQuery();
				rs.next();
				fcodf.setFcodf_dsc(rs.getString(1));
				fcodf.setFcodf_id(id);
				fcodf.setFcodf_ent_gr(rs.getString(2));
				fcodf.setFcodf_ent(rs.getString(3));
				fcodf.setFcodf_site(rs.getString(4));
				fcodf.setFcodf_fnc_cuy(rs.getString(5));
				fcodf.setFcodf_syn_ind(rs.getString(6));
				fcodf.setFcodf_st(rs.getString(7));
				fcodf.setFcodf_acct_bt_gr(rs.getString(8));
			    p.close();
			    con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return fcodf;
		
	}
}
