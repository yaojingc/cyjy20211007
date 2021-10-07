import { CARD, CARD_DISABLED_BUTTON } from "../../constant";

//单选
export function bodySelectedEvent(props, moduleId, record, index, status) {
    let checkedRows = props.cardTable.getCheckedRows(CARD.table_code);
    if (checkedRows.length > 0) {
        props.button.setButtonDisabled(CARD_DISABLED_BUTTON, false);
    } else {
        props.button.setButtonDisabled(CARD_DISABLED_BUTTON, true);
    }
}

//全选
export function bodySelectedAllEvent(props, moduleId, status, length) {
    props.button.setButtonDisabled(CARD_DISABLED_BUTTON, !(status && length > 0));
}
