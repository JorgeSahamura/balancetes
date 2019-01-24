package Relatorio;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class GeraRelatorio {
	HashMap map=new HashMap(); 
	public void geraAnalitico(List<Analitico> analiticos, String fco, Integer fyr, Integer per) throws JRException {
		map.put("fco", fco);
		map.put("fyr", fyr.toString());
		map.put("per",per.toString());
	  JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("REPORTS/Analitico.jasper");
	  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JRBeanCollectionDataSource(analiticos)); 
	  JasperViewer jrviewer = new JasperViewer(jasperPrint, false);  
	  jrviewer.setVisible(true); 
	  jrviewer.toFront();
//  JasperViewer.viewReport(jasperPrint,false);
}
	public void geraSintetico(List<Sintetico> sinteticos, String fco, Integer fyr, Integer per) throws JRException {
		map.put("fco", fco);
		map.put("fyr", fyr.toString());
		map.put("per",per.toString());
	  JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("REPORTS/Sintetico.jasper");	  
	  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JRBeanCollectionDataSource(sinteticos)); 
	  JasperViewer jrviewer = new JasperViewer(jasperPrint, false);  
	  jrviewer.setVisible(true); 
	  jrviewer.toFront();
	}
	public void geraRazao(List<Razao> razaolist, String fco, String initdt, String enddt, String coa) throws JRException {
		map.put("fco", fco);
		map.put("init_dt", initdt);
		map.put("end_dt", enddt);
		map.put("coa", coa);
	  JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("REPORTS/Razao.jasper");	  
	  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JRBeanCollectionDataSource(razaolist)); 
	  JasperViewer jrviewer = new JasperViewer(jasperPrint, false);  
	  jrviewer.setVisible(true); 
	  jrviewer.toFront();
	}
}
