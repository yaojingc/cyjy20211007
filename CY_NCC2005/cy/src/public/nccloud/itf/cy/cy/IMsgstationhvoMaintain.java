
package nccloud.itf.cy.cy;

import java.sql.SQLException;
import java.util.List;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.msgstation.AggMsgstationHVO;
import nc.vo.cy.msgstation.MsgQueryPOJO;
import nc.vo.cy.msgstation.MsgReturnPOJO;
import nc.vo.pub.BusinessException;

public interface IMsgstationhvoMaintain {

        public void delete(AggMsgstationHVO[] clientFullVOs,
                        AggMsgstationHVO[] originBills) throws BusinessException;

        public AggMsgstationHVO[] insert(AggMsgstationHVO[] clientFullVOs,
                        AggMsgstationHVO[] originBills) throws BusinessException;

        public AggMsgstationHVO[] update(AggMsgstationHVO[] clientFullVOs,
                        AggMsgstationHVO[] originBills) throws BusinessException;

        public AggMsgstationHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggMsgstationHVO[] save(AggMsgstationHVO[] clientFullVOs,
                        AggMsgstationHVO[] originBills) throws BusinessException;

        public AggMsgstationHVO[] unsave(AggMsgstationHVO[] clientFullVOs,
                        AggMsgstationHVO[] originBills) throws BusinessException;

        public AggMsgstationHVO[] approve(AggMsgstationHVO[] clientFullVOs,
                        AggMsgstationHVO[] originBills) throws BusinessException;

        public AggMsgstationHVO[] unapprove(AggMsgstationHVO[] clientFullVOs,
                        AggMsgstationHVO[] originBills) throws BusinessException;

        public  List<MsgReturnPOJO> queryMsg(MsgQueryPOJO querypojo,Integer pagetotal) throws SQLException, Exception;

       
}
