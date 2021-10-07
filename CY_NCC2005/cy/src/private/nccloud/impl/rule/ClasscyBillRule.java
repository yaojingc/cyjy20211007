package nccloud.impl.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.classfilecy.ClasscyDetailVO;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 
  * 班级档案 新增修改单据校验
 * 
 * @author tanrg
 *
 * @param <T>
 */
public class ClasscyBillRule<T extends AbstractBill> implements IRule<T> {

	@Override
	public void process(T[] paramArrayOfE) {
		if (MMValueCheck.isNotEmpty(paramArrayOfE)) {
			try {
				billCheck(paramArrayOfE);
			} catch (Exception e) {
				ExceptionUtils.wrappException(e);
			}
		}

	}

	private void billCheck(T[] paramArrayOfE) throws BusinessException {
		Map<String, String> defDocClassYearData = getDefDocClassYearData();
		for (int i = 0; i < paramArrayOfE.length; i++) {
			if (paramArrayOfE[i] instanceof AggClasscyHVO) {
				AggClasscyHVO agg = (AggClasscyHVO) paramArrayOfE[i];
				ClasscyHVO parentVO = agg.getParentVO();
				UFDouble amount = parentVO.getPaystand();// 缴费标准
				String openclas = parentVO.getDef12();// 开班年级
				String contractowner = parentVO.getContractowner();// 合同归属

				if (MMValueCheck.isEmpty(amount)) {
					throw new BusinessException("缴费标准不能为空");
				}
				if (MMValueCheck.isEmpty(contractowner)) {
					throw new BusinessException("合同归属不能为空");
				}

				int opeanYear = 0;
				if (MMValueCheck.isEmpty(openclas)) {
					throw new BusinessException("开班年级为空");
				} else {
					try {
						opeanYear = new Integer(defDocClassYearData.get(openclas).substring(0, 4)).intValue();
					} catch (Exception e) {
						throw new BusinessException("开班年级" + defDocClassYearData.get(openclas) + "格式异常!（2021级）");
					}
				}

				if (opeanYear == 0) {
					throw new BusinessException("开班年级" + defDocClassYearData.get(openclas) + "格式异常!（2021级）");
				}
				int currYear = new UFDateTime().getYear();
				int htnx = 3 - (currYear - opeanYear);

				if (htnx > 3 || htnx <= 0) {
					throw new BusinessException("开班年级" + defDocClassYearData.get(openclas) + "异常!");
				}

				ClasscyDetailVO[] childrenVO = (ClasscyDetailVO[]) agg.getChildrenVO();
				if (MMValueCheck.isEmpty(childrenVO)) {
					throw new BusinessException("子表数据不能为空");
				}
				UFDouble bAmountAll = new UFDouble(0);
				for (int j = 0; j < childrenVO.length; j++) {
					ClasscyDetailVO cdvo = childrenVO[j];
					String rowno = cdvo.getRowno();
					UFDate startdate = cdvo.getStartdate();
					UFDate enddate = cdvo.getEnddate();
					UFDouble planamount = cdvo.getPlanamount();
					if (MMValueCheck.isEmpty(planamount) || MMValueCheck.isEmpty(planamount.doubleValue())
							|| planamount.doubleValue() <= 0) {
						throw new BusinessException("子表第" + rowno + "行开收款金额异常");
					}
					if (!startdate.before(enddate)) {
						throw new BusinessException("子表第" + rowno + "行开始日期大于结束日期");
					}
					if (VOStatus.DELETED != cdvo.getStatus()) {
						bAmountAll = bAmountAll.add(planamount);
					}
				}

				if (!amount.equals(bAmountAll)) {
					throw new BusinessException("子表收款金额之和不等于表头缴费标准");
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static Map<String,String> getDefDocClassYearData() throws DAOException{
		BaseDAO dao = new BaseDAO();
		SqlBuilder sql = new SqlBuilder();
		sql.append(" SELECT doc.name,doc.pk_defdoc FROM bd_defdoc doc join bd_defdoclist doclist ");
		sql.append(" on doc.pk_defdoclist = doclist.pk_defdoclist ");
		sql.append(" WHERE doclist.name like '%年级%' ");
		sql.append(" and nvl(doc.dr,0)=0 and  nvl(doclist.dr,0)=0 ");
		List<Map<String,String>> listMap = (List<Map<String, String>>)dao.executeQuery(sql.toString(), new MapListProcessor());
		Map<String,String> mapData = new HashMap<String, String>();
		if(MMValueCheck.isNotEmpty(listMap)) {
			for (Map<String, String> map : listMap) {
				mapData.put(map.get("pk_defdoc"), map.get("name"));
			}
		}
		return mapData;
	}

}
