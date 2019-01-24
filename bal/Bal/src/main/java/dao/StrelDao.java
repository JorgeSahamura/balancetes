package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Tabelas.Strel;

public class StrelDao {
	public Strel getStrel(String own, String str, String id) {
		Strel strel=new Strel();
	    Connection con;
		try {
			con = conexao.FabricaConexao.getconnection();
			PreparedStatement p =  con.prepareStatement("select   "
					+ "strel_dsc,strel_shrt_dsc,strel_tp,strel_st,strel_lvl,strel_seq,strel_lng_dsc "
					+ "from consistgem.strel where strel_own=? and strel_str=? and strel_id=? ");
			p.setString(1, own);
			p.setString(2, str);
			p.setString(3, id);
			ResultSet rs=p.executeQuery();
			if (!rs.next()){
				strel.setStrel_dsc("");
			} else {
			strel.setStrel_dsc(rs.getString(1));
			strel.setStrel_shrt_dsc(rs.getString(2));
			strel.setStrel_tp(rs.getString(3));
			strel.setStrel_st(rs.getString(4));
			strel.setStrel_lvl(rs.getInt(5));
			// strel.setStrel_seq(rs.getInt(rs.getInt(6)));
			strel.setStrel_lng_dsc(rs.getString(7));
			}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return strel;

	}	
}
