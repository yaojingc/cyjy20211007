import { CARD, CARD_BUTTON, STATUS, PRIMARY_KEY, FIELD, CARD_DISABLED_BUTTON } from '../../constant';

/**
* @description: 卡片按钮可见性
*/
export function buttonVisibilityControl(props) {

    /** 
     * 此处应根据实际情况自由发挥
     * 目标就是将某个状态下的某些按钮展示或隐藏
     * 示例代码可能过于繁琐且难以理解
     * 
     * 可调用如下方法实现
     * props.button.setButtonVisible();
     * props.button.setButtonDisabled();
     * 
     * 例如
     * let btn1 = [ 编辑态下显示的按钮 ];
     * let btn2 = [ 编辑态下不显示的按钮 ];
     * 
     * props.button.setButtonVisible(btn1,true);
     * props.button.setButtonVisible(btn2,false);
     * 即可控制编辑态下的显示按钮。
    */

    let buttons = props.button.getButtons();
    if (!buttons || buttons.length == 0) {
        //模板没渲染完成，暂不渲染按钮
        return;
    }
    let status = props.getUrlParam(STATUS.status);
    let id = props.getUrlParam(PRIMARY_KEY.id);
    let isBrowse = status === STATUS.browse;
    //单据状态
    let billStatus = props.form.getFormItemsValue(CARD.form_id, FIELD.billStatus) && props.form.getFormItemsValue(CARD.form_id, FIELD.billStatus).value;

    let btnObj = {};
    //将要显示的按钮
    let showBtn = [];
    //编辑态显示按钮
    let editBtn = [CARD_BUTTON.save, CARD_BUTTON.saveAdd, CARD_BUTTON.saveCommit, CARD_BUTTON.cancel,
    CARD_BUTTON.addRow, CARD_BUTTON.deleteRow, CARD_BUTTON.copyRows,
    CARD_BUTTON.insertRow, CARD_BUTTON.delRow, CARD_BUTTON.copyRow, CARD_BUTTON.pasteHere, CARD_BUTTON.expand];
    //联查按钮
    let unionBtn = [CARD_BUTTON.billTrack, CARD_BUTTON.approvalLink];
    //获得所有的按钮的编码（不包括按钮组下的按钮）
    let parentButtons = buttons.map(item => item.key);
    //(有重复的按钮编码，但无妨)
    let allBtns = [...parentButtons, ...editBtn, ...unionBtn, CARD_BUTTON.create, CARD_BUTTON.update, CARD_BUTTON.delete, CARD_BUTTON.copy, CARD_BUTTON.output];
    if (!isBrowse) { //编辑态

        showBtn = editBtn;

    } else { //浏览态
        if (!id) {//点击新增然后点击取消后

            showBtn = [CARD_BUTTON.create];

        } else {//单据浏览态
            let commonBtn = [CARD_BUTTON.create, CARD_BUTTON.copy, CARD_BUTTON.attachment, ...unionBtn, CARD_BUTTON.print, CARD_BUTTON.output, CARD_BUTTON.refresh, CARD_BUTTON.fold, CARD_BUTTON.unfold];
            switch (billStatus) {
                case STATUS.NOSTATE:
                    showBtn = [CARD_BUTTON.update, CARD_BUTTON.delete, CARD_BUTTON.commit, ...commonBtn];
                    break;
                case STATUS.PASSING:
                case STATUS.COMMIT:
                case STATUS.GOINGON:
                    showBtn = [CARD_BUTTON.unCommit, ...commonBtn];
                    break;
                default:
                    showBtn = [...commonBtn];
                    break;
            }
        }
    }
    for (let item of allBtns) {
        btnObj[item] = showBtn.includes(item);
    }
    //控制按钮显示与否
    props.button.setButtonVisible(btnObj);
    //设置按钮禁用
    props.button.setButtonDisabled(CARD_DISABLED_BUTTON, true);
    //设置卡片状态
    props.cardTable.setStatus(CARD.table_code, isBrowse ? STATUS.browse : STATUS.edit);
    props.form.setFormStatus(CARD.form_id, status);
}
