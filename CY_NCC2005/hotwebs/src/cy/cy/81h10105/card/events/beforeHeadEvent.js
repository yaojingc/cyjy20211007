import { CARD } from '../../constant';
import { modifierMeta } from './initTemplate';
/**
* @description: 表头编辑前事件
* @param: moduleId 区域编码
* @param: key 当前字段编码
* @return: 布尔 true表示可编辑
*/
export function beforeHeadEvent(props, moduleId, key, value, data) {
        debugger
        let schoolVal = (props.form.getFormItemsValue(CARD.form_id, 'def2') || {}).value;

        let mate = props.meta.getMeta();
        // def1：班级 def2：学校
        const fromFilterFields = ['def1', 'def2'];
        if (fromFilterFields.includes(key)) {
                mate[CARD.form_id].items.forEach((item) => {
                        if (key === "def2") {
                                props.form.setFormItemsValue(CARD.form_id, {
                                        ["def1"]: { value: null, display: null },
                                        ["def10"]: { value: null, display: null },
                                        ["def11"]: { value: null, display: null },
                                });
                        } else if (key === "def1") {
                                item.queryCondition = () => {
                                        return { schoolValue: schoolVal }
                                }
                        }
                });
        }
        if (key === "def1") {
                let meta = props.meta.getMeta();
                meta[CARD.form_id].items.forEach((item) => {
                        if ("def1" === item.attrcode) {
                                let schoolVal = props.form.getFormItemsValue(CARD.form_id, 'def2').value;
                                item.queryCondition = {
                                        schoolValue: schoolVal,
                                };
                        }
                });
        }

        if (key === "def4") {
                let meta = props.meta.getMeta();
                meta[CARD.form_id].items.forEach((item) => {
                        if ("def4" === item.attrcode) {
                                let schoolVal = props.form.getFormItemsValue(CARD.form_id, 'def2').value;
                                item.queryCondition = {
                                        schoolValue: schoolVal,
                                };
                        }
                });
        }

        return true;
}