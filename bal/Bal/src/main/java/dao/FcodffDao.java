package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Tabelas.Fcodff;
import Tabelas.Strel;

public class FcodffDao {
	public Fcodff getFcodff(String fco, Integer fyr) {
		Fcodff fcodff = new Fcodff();
		Connection con;
	try {
		con = conexao.FabricaConexao.getconnection();
		PreparedStatement p =  con.prepareStatement("select   "
				+ "fcodff_coa,fcodff_bdg_coa,fcodff_ce_gr1,fcodff_ce_gr2,fcodff_ce_gr3,fcodff_ce_gr4 "
				+ "from consistgem.fcodff where fcodff_id=? and fcodff_fyr=? ");
		p.setString(1, fco);
		p.setInt(2, fyr);
		ResultSet rs=p.executeQuery();
		rs.next();
		fcodff.setFcodff_coa(rs.getString(1));
		fcodff.setFcodff_ce_gr1(rs.getString(3));
		fcodff.setFcodff_ce_gr2(rs.getString(4));
		fcodff.setFcodff_ce_gr3(rs.getString(5));
		fcodff.setFcodff_ce_gr4(rs.getString(6));
		} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

return fcodff;

	}
}
