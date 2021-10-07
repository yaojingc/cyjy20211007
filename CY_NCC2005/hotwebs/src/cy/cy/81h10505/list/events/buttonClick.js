import { promptBox, toast } from 'nc-lightapp-front';
import { listCreate, listRefresh, listHeadDelete, listAttachment, listBillTrack, listCommit, listUnCommit, listLinkApprove, teacherAssign, cancelTeacherAssign } from './listOperator';
import { LIST_BUTTON, STATUS, LIST } from '../../constant';

//列表头部按钮操作
export function buttonClick(props, id, text, record, index) {
        switch (id) {
                //新增
                case LIST_BUTTON.create:
                        listCreate(props);
                        break;

                //删除
                case LIST_BUTTON.delete:
                        promptBox({
                                color: STATUS.warning,
                                title: this.state.json['81H10505-000000'],/* 国际化处理： 删除*/
                                content: this.state.json['81H10505-000001'],/* 国际化处理： 确定删除吗？*/
                                beSureBtnClick: () => {
                                        listHeadDelete({ ...props, json: this.state.json });
                                }
                        });
                        break;

                // 老师讲师分配
                case LIST_BUTTON.teacherAssign:
                        let checkedRows = props.table.getCheckedRows(LIST.table_id);
                        if (checkedRows.length === 1) {
                                this.onClickModal(this.props,checkedRows);
                        } else {
                                toast({ color: STATUS.warning, content: "请先选一条数据操作！" });
                        }
                        break;
                // 老师讲师取消分配
                case LIST_BUTTON.cancelTeacherAssign:
                        let cancelCheckedRows = props.table.getCheckedRows(LIST.table_id);
                        if (cancelCheckedRows.length === 1) {
                                console.log("cancelCheckedRows--",cancelCheckedRows)
                                cancelTeacherAssign.call(this, { ...props, json: this.state.json });
                        } else {
                                toast({ color: STATUS.warning, content: "请先选一条数据操作！" });
                        }
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