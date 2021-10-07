
package nccloud.itf.cy.cy;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.salesmanfile.AggSalesmanfileHVO;
import nc.vo.pub.BusinessException;

public interface ISalesmanfilehvoMaintain {

        public void delete(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException;

        public AggSalesmanfileHVO[] insert(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException;

        public AggSalesmanfileHVO[] update(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException;

        public AggSalesmanfileHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggSalesmanfileHVO[] save(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException;

        public AggSalesmanfileHVO[] unsave(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException;

        public AggSalesmanfileHVO[] approve(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException;

        public AggSalesmanfileHVO[] unapprove(AggSalesmanfileHVO[] clientFullVOs,
                        AggSalesmanfileHVO[] originBills) throws BusinessException;
}
