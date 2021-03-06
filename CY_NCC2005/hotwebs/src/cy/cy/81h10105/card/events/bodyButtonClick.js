import { deepClone } from 'nc-lightapp-front';
import { CARD_BUTTON, CARD, STATUS, CARD_DISABLED_BUTTON, PRIMARY_KEY } from '../../constant';
import {  studentBeginSave, windowUpdate } from './cardOperator';
import  StuBegin from '../../stuBegin/index'

/**
* @description: 表体按钮点击事件
* @param: key 当前触发按钮名称
* @param: index 当前行序
* @param: record 当前行信息
*/
export function bodyButtonClick(props, key, text, record, index) {

    switch (key) {
        //弹窗 修改（弹窗）
        case CARD_BUTTON.stuUpdate:
            windowUpdate(props);
            break;

        //弹窗 保存（弹窗）
        case CARD_BUTTON.stuSave:
            studentBeginSave({ ...props, json: this.state.json }).then(() => {
            });
            props.modal.close('card_body');
            break;
        
         //肩 新增（弹窗）
         case CARD_BUTTON.addRowStu:
            props.cardTable.addRow(CARD.table_code);
            break;

        //肩 删除（弹窗）
        case CARD_BUTTON.deleteRowStu:
            let checkedRowsStu = props.cardTable.getCheckedRows(CARD.table_code);
            let checkedIndexStu = checkedRowsStu && checkedRowsStu.map(item => item.index);
            if (checkedRowsStu.length > 0) {
                props.cardTable.delRowsByIndex(CARD.table_code, checkedIndexStu);
                props.button.setButtonDisabled(CARD_DISABLED_BUTTON, true);
            }
            break;

        //肩 新增（学生成绩）
        case CARD_BUTTON.addRow:
            props.cardTable.addRow(CARD.table_code);
            break;

        //肩 删除（学生成绩）
        case CARD_BUTTON.deleteRow:
            let checkedRows = props.cardTable.getCheckedRows(CARD.table_code);
            let checkedIndex = checkedRows && checkedRows.map(item => item.index);
            if (checkedRows.length > 0) {
                props.cardTable.delRowsByIndex(CARD.table_code, checkedIndex);
                props.button.setButtonDisabled(CARD_DISABLED_BUTTON, true);
            }
            break;

        //肩 新增（学生变动）
        case CARD_BUTTON.addRowChange:
            props.cardTable.addRow(CARD.table_code_change);
            break;

        //肩 删除（学生变动）
        case CARD_BUTTON.deleteRowChange:
            let checkedRowsChange = props.cardTable.getCheckedRows(CARD.table_code_change);
            let checkedIndexChange = checkedRowsChange && checkedRowsChange.map(item => item.index);
            if (checkedRowsChange.length > 0) {
                props.cardTable.delRowsByIndex(CARD.table_code_change, checkedIndexChange);
                props.button.setButtonDisabled(CARD_DISABLED_BUTTON, true);
            }
            break;

        //肩 新增（学生家长）
        case CARD_BUTTON.addRowParent:
            props.cardTable.addRow(CARD.table_code_parent);
            break;

        //肩 删除（学生家长）
        case CARD_BUTTON.deleteRowParent:
            let checkedRowsParent = props.cardTable.getCheckedRows(CARD.table_code_parent);
            let checkedIndexParent = checkedRowsParent && checkedRowsParent.map(item => item.index);
            if (checkedRowsParent.length > 0) {
                props.cardTable.delRowsByIndex(CARD.table_code_parent, checkedIndexParent);
                props.button.setButtonDisabled(CARD_DISABLED_BUTTON, true);
            }
            break;

        //行 展开
        case CARD_BUTTON.expand:
            props.cardTable.openTabModel(CARD.table_code, STATUS.edit, record, index);
            break;

        //行 展开（浏览态）
        case CARD_BUTTON.unfold:
        //行 收起
        case CARD_BUTTON.fold:
            props.cardTable.toggleTabRowView(CARD.table_code, record);
            break;

        //行 插行
        case CARD_BUTTON.insertRow:
            props.cardTable.addRow(CARD.table_code, index);
            break;

        //行 删行
        case CARD_BUTTON.delRow:
            props.cardTable.delRowsByIndex(CARD.table_code, index);
            break;

        //行 复制行
        case CARD_BUTTON.copyRow:
            props.cardTable.pasteRow(CARD.table_code, index, [PRIMARY_KEY.body_id]);
            break;

        //肩 复制行
        case CARD_BUTTON.copyRows:
            copyControl.call(this, props, true);
            break;

        //肩 取消复制
        case CARD_BUTTON.pasteCancel:
            copyControl.call(this, props, false);
            break;

        //肩 粘贴至末行
        case CARD_BUTTON.pasteTail:
            let lastIndex = props.cardTable.getNumberOfRows(CARD.table_code);
            props.cardTable.insertRowsAfterIndex(CARD.table_code, getPasteRows(props), lastIndex);
            copyControl.call(this, props, false);
            props.cardTable.selectAllRows(CARD.table_code, false);
            break;

        //行 粘贴至此
        case CARD_BUTTON.pasteHere:
            props.cardTable.insertRowsAfterIndex(CARD.table_code, getPasteRows(props), index);
            copyControl.call(this, props, false);
            props.cardTable.selectAllRows(CARD.table_code, false);
            break;
    }
    if (![CARD_BUTTON.unfold, CARD_BUTTON.fold].includes(key)) {
        props.cardTable.setStatus(CARD.table_code, STATUS.edit);
    }
}

//复制时的状态控制
function copyControl(props, flag) {
    //肩部按钮显示控制
    props.button.setButtonVisible({
        [CARD_BUTTON.addRow]: !flag,
        [CARD_BUTTON.deleteRow]: !flag,
        [CARD_BUTTON.copyRows]: !flag,

        [CARD_BUTTON.pasteTail]: flag,
        [CARD_BUTTON.pasteCancel]: flag,
    });
    props.cardTable.setAllCheckboxAble(CARD.table_code, !flag);
    this.setState({ isPaste: flag });
}

/**
 * 获取粘贴行数据
 *
 * @returns 返回粘贴行数据
 */
function getPasteRows(props) {
    let checkedRows = props.cardTable.getCheckedRows(CARD.table_code);
    let selectRowCopy = deepClone(checkedRows);
    let selectArr = selectRowCopy.map(item => {
        item.data.selected = false;
        item.data.values[PRIMARY_KEY.head_id] ? item.data.values[PRIMARY_KEY.head_id].value = null : null;
        item.data.values[PRIMARY_KEY.body_id] ? item.data.values[PRIMARY_KEY.body_id].value = null : null;
        return item.data;
    });
    return selectArr;
}
