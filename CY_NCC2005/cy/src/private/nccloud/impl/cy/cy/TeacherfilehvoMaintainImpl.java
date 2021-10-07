
package nccloud.impl.cy.cy;

import nccloud.impl.pub.ace.AceAggbusiTeacherfileHVOPubServiceImpl;
import nccloud.itf.cy.cy.ITeacherfilehvoMaintain ;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.teacherfile.AggTeacherfileHVO;
import nc.vo.pub.BusinessException;

public class TeacherfilehvoMaintainImpl extends AceAggbusiTeacherfileHVOPubServiceImpl implements ITeacherfilehvoMaintain  {

        @Override
        public void delete(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException {
                super.pubdeleteBills(clientFullVOs, originBills);
        }

        @Override
        public AggTeacherfileHVO[] insert(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException {
                return super.pubinsertBills(clientFullVOs, originBills);
        }

        @Override
        public AggTeacherfileHVO[] update(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException {
                return super.pubupdateBills(clientFullVOs, originBills);
        }

        @Override
        public AggTeacherfileHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException {
                return super.pubquerybills(queryScheme);
        }

        @Override
        public AggTeacherfileHVO[] save(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException {
                return super.pubsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggTeacherfileHVO[] unsave(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException {
                return super.pubunsendapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggTeacherfileHVO[] approve(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException {
                return super.pubapprovebills(clientFullVOs, originBills);
        }

        @Override
        public AggTeacherfileHVO[] unapprove(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException {
                return super.pubunapprovebills(clientFullVOs, originBills);
        }

}
