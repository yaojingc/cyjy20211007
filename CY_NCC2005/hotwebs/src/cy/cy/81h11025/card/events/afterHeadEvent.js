import { FIELD, CARD_ADD_DISABLED_BUTTON, CARD } from "../../constant";
import { getAllStuOfClasscy } from './cardOperator';
import { promptBox } from 'nc-lightapp-front';

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
        if (key === "def2") {
                // 班级字段变更时清空表体所有数据，待加弹框提示
                // promptBox({
                //         color: "warning",
                //         title: "班级变更后将清除子表数据",/* 国际化处理： 取消*/
                //         content: "确认变更班级吗？",/* 国际化处理： 是否确认要取消?*/
                //         beSureBtnClick: () => {
                // 班级变更表体数据状态设置为删除
                props.cardTable.selectAllRows(CARD.table_code, true);
                let checkedRows = props.cardTable.getCheckedRows(CARD.table_code);
                let checkedIndex = checkedRows && checkedRows.map(item => item.index);
                if (checkedRows.length > 0) {
                    props.cardTable.delRowsByIndex(CARD.table_code, checkedIndex);
                //     props.button.setButtonDisabled(CARD_DISABLED_BUTTON, true);
                }
                props.cardTable.selectAllRows(CARD.table_code, false);

                // var num = props.cardTable.getNumberOfRows(CARD.table_code, false);
                // var updateArray = new Array();
                // for (var i = 0; i < num; i++) {
                //         updateArray.push({
                //         index: i,
                //         status: '3',//删除态
                //         });
                // }
                // props.cardTable.setRowStatusByIndexs(CARD.table_code, updateArray);
                
                // let cardAllRows = props.cardTable.getAllRows(CARD.table_code);
                // let allrowids = cardAllRows && cardAllRows.map(item => item.rowid);
                // console.log("allrowids-", allrowids);
                // if (allrowids.length > 0) {
                //         for (var i = 0; i < allrowids.length; i++) {
                //                 props.cardTable.delRowByRowId(CARD.table_code, allrowids[i]);
                //         }
                //         // props.cardTable.delTabData(CARD.table_code,{ rows: cardAllRows })

                // }
                // // 以下方式删除不干净
                // cardAllRows = props.cardTable.getAllRows(CARD.table_code);
                // console.log("cardAllRows-", cardAllRows);
                // if (cardAllRows.length > 0) {
                //         cardAllRows.map(item => {
                //                 console.log("item-----", item);
                //                 item.isOptimized=false;
                //                 console.log("item-----", item);
                //                 let delrowid = item.rowid;
                //                 console.log("delrowid-----", delrowid);
                //                 props.cardTable.delRowByRowId(CARD.table_code, delrowid);
                //         });
                // }
                // props.cardTable.setTableData(CARD.table_code, { rows: [] });
                if (value && value.value) {
                        let data = {
                                "pk_classcy": value.value
                        }
                        getAllStuOfClasscy(props, data);
                }
                //         }
                // });
        }
        if (key === 'def3') {
                console.log("value-", value);

                props.form.setFormItemsValue(CARD.form_id, {
                        ["testcode"]: { value: value.refcode, display: value.refcode },
                        ["testname"]: { value: value.refname, display: value.refname }
                });
        }
}
