package br.casa.jasper;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;
import br.casa.dao.ConnectionBD;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class Report {
	
	private static final String JASPER_REPORT = "C:\\Users\\Eduardo\\JaspersoftWorkspace\\MyReports\\Orcamento.jasper";
	
	public void exportReport(){
		JasperPrint jasperPDF = getPrint();
		
		SimpleDateFormat sdf = new SimpleDateFormat("(yyyy-MM-dd)_HH-mm-ss");
		String data = sdf.format(new Date());
		
		String nomepdf = "relatorio_contatos_"+ data + ".pdf";
		
		try {
			JasperExportManager.exportReportToPdfFile(jasperPDF, nomepdf);
			Desktop.getDesktop().open(new File(nomepdf));
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
	}

	private JasperPrint getPrint() {
		Connection con = ConnectionBD.getInstance().getConnection();
		
		try {
			return JasperFillManager.fillReport(JASPER_REPORT, null, con);
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

}
