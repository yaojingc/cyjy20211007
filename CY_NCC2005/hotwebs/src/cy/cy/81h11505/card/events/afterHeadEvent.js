import { FIELD, CARD_ADD_DISABLED_BUTTON, CARD } from "../../constant";
/**
* @description: 表头编辑后事件
* @param: moduleId 区域编码
* @param: key 当前字段编码
*/
export function afterHeadEvent(props, moduleId, key, value, oldValue) {
        if (key === FIELD.org) {
                props.resMetaAfterPkorgEdit(); //选择主组织以后，恢复其他字段的编辑性
                props.button.setButtonDisabled(CARD_ADD_DISABLED_BUTTON, false);//恢复增行编辑性
                props.cardTable.setAllCheckboxAble(CARD.table_code, true);
        }

        // 学费收款合同表头，参照学费减免申请档案（def7）编辑后事件,带出参照中的减免金额
        if(key === 'def7'){

                debugger
                // 原始合同总金额
                let amount = props.form.getFormItemsValue(CARD.form_id, 'def9').value
                // 从参照中获取的减免金额
                let jmamount = value.values.reducation.value;
                // 减免后合同总金额
                let jmafteramount = amount - jmamount;
                jmafteramount = jmafteramount.toFixed(2)
                props.form.setFormItemsValue(CARD.form_id, {
                        ['def8']: { value: jmamount },// 减免金额
                });

                props.form.setFormItemsValue(CARD.form_id, {
                        ['def9']: { value: jmafteramount }// 减免后合同总金额
                });
        }


}
