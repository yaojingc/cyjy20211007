import { promptBox } from 'nc-lightapp-front';
import { CARD, CARD_BUTTON, STATUS } from '../../constant';
import { cardBack, cardCreate, cardSave, cardUpdate, cardDelete, cardCancel, cardRefresh, cardCopy, cardAttachment, cardBillTrack, cardCommit, cardUnCommit, cardLinkApprove } from './cardOperator';

/**
* @description: 卡片页头部按钮操作
* @param: isFromSide 是否来自侧拉区域
* @param: id 当前按钮名
*/
export function buttonClick(props, id, hotkey, isFromSide) {
    switch (id) {
        //返回
        case CARD_BUTTON.back:
            cardBack(props);
            break;

        //头部 新增
        case CARD_BUTTON.create:
            cardCreate(props);
            break;

        //头部 保存
        case CARD_BUTTON.save:
            cardSave({ ...props, json: this.state.json }).then(() => {
                if (isFromSide) {
                    props.cardTable.closeModel(CARD.table_code);
                }
            });
            break;

        //头部 保存新增
        case CARD_BUTTON.saveAdd:
            cardSave({ ...props, json: this.state.json }).then(flag => {
                if (flag) {
                    cardCreate(props);
                }
            });
            break;

        //头部 保存提交
        case CARD_BUTTON.saveCommit:
            cardSave({ ...props, json: this.state.json }).then(flag => {
                if (flag) {
                    cardCommit.call(this, { ...props, json: this.state.json });
                }
            });
            break;

        //头部 修改
        case CARD_BUTTON.update:
            cardUpdate(props);
            break;

        //头部 复制
        case CARD_BUTTON.copy:
            cardCopy(props);
            break;

        //头部 删除
        case CARD_BUTTON.delete:
            promptBox({
                color: STATUS.warning,
                title: this.state.json['81H10120-000000'],/* 国际化处理： 删除*/
                content: this.state.json['81H10120-000001'],/* 国际化处理： 确定删除吗？*/
                beSureBtnClick: () => {
                    cardDelete({ ...props, json: this.state.json });
                }
            });
            break;

        // 头部 取消
        case CARD_BUTTON.cancel:
            promptBox({
                color: STATUS.warning,
                title: this.state.json['81H10120-000002'],/* 国际化处理： 取消*/
                content: this.state.json['81H10120-000003'],/* 国际化处理： 是否确认要取消?*/
                beSureBtnClick: () => {
                    cardCancel({ ...props, json: this.state.json });
                }
            });
            break;

        //头部 刷新
        case CARD_BUTTON.refresh:
            cardRefresh({ ...props, json: this.state.json });
            break;

        //附件
        case CARD_BUTTON.attachment:
            cardAttachment.call(this, props);
            break;

        //单据追溯
        case CARD_BUTTON.billTrack:
            cardBillTrack.call(this, props);
            break;

        //审批详情
        case CARD_BUTTON.approvalLink:
            cardLinkApprove.call(this, props);
            break;

        //单据提交
        case CARD_BUTTON.commit:
            cardCommit.call(this, { ...props, json: this.state.json });
            break;

        //单据收回
        case CARD_BUTTON.unCommit:
            cardUnCommit({ ...props, json: this.state.json });
            break;

        default:
            break;
    }
}
