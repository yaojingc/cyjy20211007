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
}
