import { CARD } from '../../constant';
/**
* @description: 表头编辑前事件
* @param: moduleId 区域编码
* @param: key 当前字段编码
* @return: 布尔 true表示可编辑
*/
export function beforeHeadEvent(props, moduleId, key, value, data) {

        if (key === "def7") {
                let meta = props.meta.getMeta();
                meta[CARD.form_id].items.forEach((item) => {
                        if ("def7" === item.attrcode) {
                                let pk_student = props.form.getFormItemsValue(CARD.form_id, 'def2').value
                                item.queryCondition = {
                                        pk_student: pk_student,
                                };
                        }
                });
        }

        return true;
}