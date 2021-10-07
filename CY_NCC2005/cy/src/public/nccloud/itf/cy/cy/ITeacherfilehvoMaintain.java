
package nccloud.itf.cy.cy;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cy.teacherfile.AggTeacherfileHVO;
import nc.vo.pub.BusinessException;

public interface ITeacherfilehvoMaintain {

        public void delete(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException;

        public AggTeacherfileHVO[] insert(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException;

        public AggTeacherfileHVO[] update(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException;

        public AggTeacherfileHVO[] query(IQueryScheme queryScheme)
                        throws BusinessException;

        public AggTeacherfileHVO[] save(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException;

        public AggTeacherfileHVO[] unsave(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException;

        public AggTeacherfileHVO[] approve(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException;

        public AggTeacherfileHVO[] unapprove(AggTeacherfileHVO[] clientFullVOs,
                        AggTeacherfileHVO[] originBills) throws BusinessException;
}
