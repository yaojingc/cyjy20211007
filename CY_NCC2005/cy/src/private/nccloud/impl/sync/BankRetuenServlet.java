package nccloud.impl.sync;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

import nc.bs.framework.adaptor.IHttpServletAdaptor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.swiftpass.XmlUtils;
import nc.utils.transfer.SchoolPaymentToBankVO;
import nccloud.impl.dao.CreditContractBaseDao;

/**
 * 威缴费缴费记录接口
 * 
 * @author rongleia
 *
 */
@SuppressWarnings("restriction")
public class BankRetuenServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			CyCommonUtils.login();
			request.setCharacterEncoding("utf-8");
			String resString = XmlUtils.parseRequst(request);
			File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
			String desktopPath = desktopDir.getAbsolutePath();
			String filePath = desktopPath + "\\" + "接口内容0816.txt";
			FileWriter fwriter = new FileWriter(filePath);
			try {
				// true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
				fwriter = new FileWriter(filePath);
				fwriter.write(resString);
				String idcard = new SchoolPaymentToBankVO().transResptoVo(resString);
				if(CyCommonUtils.isNotEmpty(idcard)) {
					CreditContractBaseDao.updateStatus2(idcard);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				try {
					fwriter.flush();
					fwriter.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
