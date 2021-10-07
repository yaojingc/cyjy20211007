package nccloud.cy.cy.clsschedulehvo.action;

import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nccloud.framework.core.io.WebFile;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.itf.cy.cy.IClsschedulehvoMaintain;

/**
 * 课表导入
 * 
 * @author rongleia
 *
 */
public class ImportKBexcel implements ICommonAction {

	@SuppressWarnings("resource")
	@Override
	public Object doAction(IRequest request) {

		try {
			WebFile[] files = request.readFiles();
			if (CyCommonUtils.isNotEmpty(files)) {
				for (WebFile webFile : files) {
					String filename = webFile.getFileName();
					FileInputStream inputStream = (FileInputStream) webFile.getInputStream();
					// 截取文件后缀类型
					String suffix = MMValueCheck.isNotEmpty(filename.split("\\.")) ? filename.split("\\.")[1] : "";
					Workbook wb = null;
					if ("xlsx".equalsIgnoreCase(suffix)) {
						wb = new XSSFWorkbook(inputStream);
					} else if ("xls".equalsIgnoreCase(suffix)) {
						wb = new HSSFWorkbook(inputStream);
					} else {
//					ExceptionUtils.wrapBusinessException();
						return "导入的文件格式不正确，请检查！";
					}
					// 获取页签名称，并校验
					// 获取第一个考勤日报页签
					Sheet sheet = wb.getSheetAt(0);
					// 批量插入 kqVOList
					List<AggClsscheduleHVO> buildVOForInsert = CyCommonUtils.getInstance(IClsschedulehvoMaintain.class)
							.buildVOForInsert(sheet);
					if (CyCommonUtils.isNotEmpty(buildVOForInsert)) {
						String vnote = buildVOForInsert.get(0).getParentVO().getVnote();
						if (CyCommonUtils.isEmpty(vnote)) {
							AggClsscheduleHVO[] aggarr = buildVOForInsert
									.toArray(new AggClsscheduleHVO[buildVOForInsert.size()]);
							AggClsscheduleHVO[] insert = CyCommonUtils.getInstance(IClsschedulehvoMaintain.class)
									.insert(aggarr, null);
							if (MMValueCheck.isNotEmpty(insert)) {
								BillCardOperator billCardOperator = new BillCardOperator();
								billCardOperator.toCard(aggarr);
								return true;
							}
						} else {
							return vnote;
						}
					}
				}
			}
			return "导入失败 ！";
		} catch (Exception e) {
			return "导入失败 ！";
//			ExceptionUtils.wrapBusinessException();
		}
	}
}
