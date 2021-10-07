
package nccloud.bs.cy.cy.aggbusi.ace.bp;


import nccloud.bs.cy.cy.aggbusi.plugin.bpplugin.TeacherformHVOPluginPoint;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceTeacherformHVODeleteBP {

        public void delete(AggTeacherformHVO[] bills) {

                DeleteBPTemplate<AggTeacherformHVO> bp = new DeleteBPTemplate<AggTeacherformHVO>(
TeacherformHVOPluginPoint.DELETE);
                // 增加执行前规则
                this.addBeforeRule(bp.getAroundProcesser());
                // 增加执行后业务规则
                this.addAfterRule(bp.getAroundProcesser());
                bp.delete(bills);
        }

        private void addBeforeRule(AroundProcesser<AggTeacherformHVO> processer) {
                // TODO 前规则
//              IRule<AggTeacherformHVO> rule = null;
//              rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//              processer.addBeforeRule(rule);
        }

        /**
         * 删除后业务规则
         *
         * @param processer
         */
        private void addAfterRule(AroundProcesser<AggTeacherformHVO> processer) {
                // TODO 后规则

        }
}
