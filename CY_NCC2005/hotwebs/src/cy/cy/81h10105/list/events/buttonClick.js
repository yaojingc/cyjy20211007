import { promptBox ,toast} from 'nc-lightapp-front';
import { listCreate, listRefresh, listHeadDelete, listAttachment, listBillTrack, listCommit, listUnCommit, listLinkApprove, batchTransfer } from './listOperator';
import { LIST_BUTTON, STATUS,LIST } from '../../constant';

//列表头部按钮操作
export function buttonClick(props, id, text, record, index) {
        switch (id) {

                // //行政班级分配
                // case LIST_BUTTON.allot:
                //         debugger
                //         let checkedRows = props.table.getCheckedRows(LIST.table_id);
                //         if (checkedRows.length > 0) {
                //                 this.onClickModal(checkedRows);
                //         } else {
                //                 toast({ color: STATUS.warning, content: "请先选中学生再进行操作！" });
                //         }
                //         break;

                //新增
                case LIST_BUTTON.create:
                        listCreate(props);
                        break;

                //删除
                case LIST_BUTTON.delete:
                        promptBox({
                                color: STATUS.warning,
                                title: this.state.json['81H10105-000000'],/* 国际化处理： 删除*/
                                content: this.state.json['81H10105-000001'],/* 国际化处理： 确定删除吗？*/
                                beSureBtnClick: () => {
                                        listHeadDelete({ ...props, json: this.state.json });
                                }
                        });
                        break;

                //批量生成学生收款合同
                case LIST_BUTTON.batchTransfer:
                        batchTransfer.call(this, { ...props, json: this.state.json });
                        break;

                //附件
                case LIST_BUTTON.attachment:
                        listAttachment.call(this, { ...props, json: this.state.json });
                        break;

                //单据追溯
                case LIST_BUTTON.billTrack:
                        listBillTrack.call(this, { ...props, json: this.state.json });
                        break;

                //审批详情
                case LIST_BUTTON.approvalLink:
                        listLinkApprove.call(this, { ...props, json: this.state.json });
                        break;

                //提交
                case LIST_BUTTON.commit:
                        listCommit.call(this, { ...props, json: this.state.json });
                        break;

                //收回
                case LIST_BUTTON.unCommit:
                        listUnCommit({ ...props, json: this.state.json });
                        break;

                //刷新
                case LIST_BUTTON.refresh:
                        listRefresh({ ...props, json: this.state.json });
                        break;
        }
}