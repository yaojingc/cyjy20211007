import { CARD } from '../../constant';
/**
* @description: 表头编辑前事件
* @param: moduleId 区域编码
* @param: key 当前字段编码
* @return: 布尔 true表示可编辑
*/
export function beforeHeadEvent(props, moduleId, key, value, data) {

        let schoolVal = (props.form.getFormItemsValue(CARD.form_id, 'def1')||{}).value;
        let meta = props.meta.getMeta();
        meta[CARD.form_id].items.forEach((item) => {
                if ("def2" === item.attrcode) {
                        debugger
                        item.queryCondition = {
                                schoolValue: schoolVal,
                                // TreeRefActionExt: 'nccloud.cy.cy.classcyhvo.action.ClassCyGridReferQueryAction'
                        };
                }
        });
        return true;
}