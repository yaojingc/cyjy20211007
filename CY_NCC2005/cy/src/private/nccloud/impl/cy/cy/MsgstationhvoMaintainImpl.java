
package nccloud.impl.cy.cy;

import java.sql.SQLException;
import java.util.List;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.msgstation.AggMsgstationHVO;
import nc.vo.cy.msgstation.MsgQueryPOJO;
import nc.vo.cy.msgstation.MsgReturnPOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.impl.pub.ace.AceAggbusiMsgstationHVOPubServiceImpl;
import nccloud.itf.cy.cy.IMsgstationhvoMaintain ;

public class MsgstationhvoMaintainImpl extends AceAggbusiMsgstationHVOPubServiceImpl implements IMsgstationhvoMaintain  {

        @Override
        public void delete(AggMsgstationHVO[] clientFullVOs,
                        AggMsgstationHVO[] originBills) throws BusinessException {
                super.pubdeleteBills(clientFullVOs, originBills);
        }

        @Override
        public AggMsgstationHVO[] insert(AggMsgstationHVO[] clientFullVOs,
                        AggMsgstationHVO[] originBills) throws BusinessException {
                return super.pubinsertBills(clientFullVOs, originBills);
        }

        @Override
        public AggMsgstationHVO[] update(AggMsgstationHVO[] clientFullVOs,
                        AggMsgstationHVO[] originBills) throws BusinessException {
                return super.pubupdateBills(clientFullVOs, originBills);
        }

        @Override
        public AggMsgstationHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException {
                return super.pubquerybills(queryScheme);
        }

        @Override
        public AggMsgstationHVO[] save(AggMsgstationHVO[] clientFullVOs,
                        AggMsgstationHVO[] originBills) throws BusinessException {
                return super.pubsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggMsgstationHVO[] unsave(AggMsgstationHVO[] clientFullVOs,
                        AggMsgstationHVO[] originBills) throws BusinessException {
                return super.pubunsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggMsgstationHVO[] approve(AggMsgstationHVO[] clientFullVOs,
                        AggMsgstationHVO[] originBills) throws BusinessException {
                return super.pubapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggMsgstationHVO[] unapprove(AggMsgstationHVO[] clientFullVOs,
                        AggMsgstationHVO[] originBills) throws BusinessException {
                return super.pubunapprovebills(clientFullVOs, originBills);
        }
        
        
        /**
         * 需要添加全员消息推送，union all 上所有人都收的到的状态的数据
         */
        @Override
        public List<MsgReturnPOJO> queryMsg(MsgQueryPOJO querypojo,Integer pagetotal) throws SQLException, Exception {
        	int index = querypojo.getIndex();// 第几页
    		int size = querypojo.getSize();// 每页数据量
    		
    		UFDouble start = (new UFDouble(index).sub(new UFDouble(1))).multiply(new UFDouble(size)).add(new UFDouble(1));
    		UFDouble end = new UFDouble(index).multiply(new UFDouble(size));
    		
    		BaseDAO dao = new BaseDAO();
    		SqlBuilder sql = new SqlBuilder();
    		
    		sql.append(" SELECT * ");
    		sql.append(" FROM (SELECT tt.*, ROWNUM AS rowno ");
    		sql.append(" 	FROM (SELECT * ");
    		sql.append(" 			FROM cy_msgstationhvo t ");
    		sql.append(" 			WHERE ");
    		sql.append(" receiverid ",querypojo.getReceiverid());
    		sql.append(" AND ");
    		sql.append(" nvl(dr,0) ", 0);
    		sql.append(" ORDER BY T.CREATIONTIME DESC) tt) table_alias ");
    		sql.append(" WHERE table_alias.rowno BETWEEN "+start+" AND "+end+" ");

    		List<MsgReturnPOJO> listvos = (List<MsgReturnPOJO>) dao.executeQuery(sql.toString(),
    				new BeanListProcessor(MsgReturnPOJO.class));
        	
    		return listvos;
    	}

 
    
}
