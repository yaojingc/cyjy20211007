import { LIST, LIST_BUTTON, FIELD, LIST_DISABLED_BUTTON, STATUS } from "../../constant";

//选择事件示例

//单选 props, moduleId(区域id), record（行数据）, index（当前index）, status（选框值）
export function selectedEvent(props, moduleId, record, index, status) {
    props.button.setButtonDisabled(LIST_DISABLED_BUTTON, true);

    let showBtn = [], deleteFlag = true, isAllNoState = true, isAllPassing = true;
    let selectDatas = props.table.getCheckedRows(LIST.table_id);

    if (selectDatas.length == 0) {
        return;
    } else if (selectDatas.length > 1) { //多选
        showBtn = [LIST_BUTTON.print, LIST_BUTTON.output];
        for (let selectData of selectDatas) {
            let status = selectData.data.values[FIELD.billStatus].value;
            if (status == STATUS.PASSING || status == STATUS.COMMIT || status == STATUS.GOINGON) {
                isAllNoState = false;
                deleteFlag = false;
            } else if (status == STATUS.NOSTATE) {
                isAllPassing = false;
            }
        }
        if (deleteFlag) {
            showBtn.push(LIST_BUTTON.delete);
        }
        if (isAllNoState) {
            showBtn.push(LIST_BUTTON.commit);
        }
        if (isAllPassing) {
            showBtn.push(LIST_BUTTON.unCommit);
        }
        props.button.setButtonDisabled(showBtn, false);
    } else { //单选
        let busistatus = selectDatas[0].data.values[FIELD.billStatus] && selectDatas[0].data.values[FIELD.billStatus].value;
        if (busistatus == STATUS.NOSTATE) { //自由
            showBtn = [LIST_BUTTON.commit, LIST_BUTTON.delete];
        } else if (busistatus == STATUS.PASSING || busistatus == STATUS.COMMIT || busistatus == STATUS.GOINGON) { //提交
            showBtn = [LIST_BUTTON.unCommit, LIST_BUTTON.approvalLink];
        }
        props.button.setButtonDisabled([...showBtn, LIST_BUTTON.print, LIST_BUTTON.output, LIST_BUTTON.attachment, LIST_BUTTON.linkGroup, LIST_BUTTON.billTrack], false);
    }

}

//全选
export function selectedAllEvent(props, moduleId, status, length) {
    props.button.setButtonDisabled(LIST_DISABLED_BUTTON, true);

    let selectDatas = props.table.getCheckedRows(LIST.table_id);
    if (selectDatas.length > 0) {
        let showBtn = [], deleteFlag = true, isAllNoState = true, isAllPassing = true;
        let selectDatas = props.table.getCheckedRows(LIST.table_id);
        showBtn = [LIST_BUTTON.print, LIST_BUTTON.output];
        for (let selectData of selectDatas) {
            let status = selectData.data.values[FIELD.billStatus].value;
            if (status == STATUS.PASSING || status == STATUS.COMMIT || status == STATUS.GOINGON) {
                isAllNoState = false;
                deleteFlag = false;
            } else if (status == STATUS.NOSTATE) {
                isAllPassing = false;
            }
        }
        if (deleteFlag) {
            showBtn.push(LIST_BUTTON.delete);
        }
        if (isAllNoState) {
            showBtn.push(LIST_BUTTON.commit);
        }
        if (isAllPassing) {
            showBtn.push(LIST_BUTTON.unCommit);
        }
        props.button.setButtonDisabled(showBtn, false);
    }
}
