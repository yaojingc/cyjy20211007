import { ajax, cardCache, toast } from 'nc-lightapp-front';
import { REQUEST_URL, SEARCH_CACHE, CARD, LIST, PRIMARY_KEY, LIST_BUTTON, STATUS, BILL_TYPE_CODE, FIELD } from '../../constant';








//列表新增
export function listCreate(props) {
    props.pushTo(REQUEST_URL.toCard, {
        status: STATUS.add,
        pagecode: CARD.page_code
    });
}

//列表刷新
export function listRefresh(props) {
    let { getDefData } = cardCache;
    let queryInfo = getDefData(SEARCH_CACHE.key, SEARCH_CACHE.dataSource);
    listSearch(props, queryInfo);
}

//列表修改
export function listEdit(props, pk) {
    props.pushTo(REQUEST_URL.toCard, {
        status: STATUS.edit,
        id: pk,
        pagecode: CARD.page_code
    });
}

//列表复制
export function listCopy(props, pk) {
    props.pushTo(REQUEST_URL.toCard, {
        status: STATUS.edit,
        id: pk,
        pagecode: CARD.page_code,
        isCopy: true
    });
}

// 学校分配
// export function allotSchool(indexProps, data) {
//     if (data) {
//         ajax({
//             url: REQUEST_URL.allot,
//             data: data,
//             success: (res) => {
//                 if (res.success) { //成功
//                     listRefresh(indexProps);
//                 } else { //失败
//                     toast({ color: STATUS.warning, content: "分配失败" });
//                 }
//             }
//         });
//     }

// }


//列表提交
export function listCommit(props, data) {
    if (!data) {
        let select = checkSelected(props, false);
        if (!select.valid) {
            return;
        }
        if (select.valid) {
            let selectDatas = select.selectDatas;
            let pks = selectDatas && selectDatas.map(item => item.data.values[PRIMARY_KEY.head_id].value);
            let pkMapTs = new Map();
            selectDatas && selectDatas.map(item => {
                let pk = item.data.values[PRIMARY_KEY.head_id].value;
                let ts = item.data.values[FIELD.ts] && item.data.values[FIELD.ts].value;
                //主键与tsMap
                if (pk && ts) {
                    pkMapTs.set(pk, ts);
                }
            });
            data = { pks, pkMapTs };
        }
    }
    ajax({
        url: REQUEST_URL.commit,
        data: data,
        success: (res) => {
            if (res.success) { //成功
                if (res.data && res.data.workflow && (res.data.workflow == 'approveflow' || res.data.workflow == 'workflow')) {
                    this.setState({
                        compositeData: res.data,
                        compositeDisplay: true,
                        curPk: data.pks
                    });
                    PromptMessage.call(this, res);
                } else {
                    PromptMessage.call(this, res);
                    listRefresh(props);
                }
            } else { //失败
                PromptMessage.call(this, res);
                //toast({ color: STATUS.warning, content: props.json['81H10101-000015'] });/* 国际化处理： 提交失败*/
            }
        }
    });
}

//列表收回
export function listUnCommit(props, data) {
    if (!data) {
        let select = checkSelected(props, false);
        if (select.valid) {
            let selectDatas = select.selectDatas;
            let pks = selectDatas && selectDatas.map(item => item.data.values[PRIMARY_KEY.head_id].value);
            let pkMapTs = new Map();
            selectDatas && selectDatas.map(item => {
                let pk = item.data.values[PRIMARY_KEY.head_id].value;
                let ts = item.data.values[FIELD.ts] && item.data.values[FIELD.ts].value;
                //主键与tsMap
                if (pk && ts) {
                    pkMapTs.set(pk, ts);
                }
            });
            data = { pks, pkMapTs };
        }
    }
    ajax({
        url: REQUEST_URL.unCommit,
        data: data,
        success: (res) => {
            if (res.success) { //成功
                listRefresh(props);
            } else { //失败
                toast({ color: STATUS.warning, content: props.json['81H10101-000017'] });/* 国际化处理： 收回失败*/
            }
        }
    });
}


/**
 * 是否选中数据
 * 
 * @param {*} isCheckOne 是否选中一条数据
 * @returns 返回是否校验成功
 */
export function checkSelected(props, isCheckOne) {
    let valid = true;
    let selectDatas = props.table && props.table.getCheckedRows(LIST.table_id);
    if (isCheckOne && selectDatas.length > 1) {
        toast({
            color: STATUS.warning, content: props.json['81H10101-000012']/* 国际化处理： 请选中一行表体数据!*/
        });
        valid = false;
    } else if (selectDatas.length == 0) {
        toast({
            color: STATUS.warning, content: props.json['81H10101-000013']/* 国际化处理： 请选择表体数据操作!*/
        });
        valid = false;
    }
    return { valid, selectDatas };
}

//列表附件
export function listAttachment(props) {
    let select = checkSelected(props, true);
    if (select.valid) {
        let selectDatas = select.selectDatas;
        let billId = selectDatas[0] && selectDatas[0].data.values[PRIMARY_KEY.head_id].value;
        let billNo = selectDatas[0] && selectDatas[0].data.values[PRIMARY_KEY.bill_no].value;
        this.setState({
            showUploader: !this.state.showUploader,
            billInfo: { billId, billNo }
        });
    }
}

//列表单据追溯
export function listBillTrack(props) {
    //单据追溯示例
    let select = checkSelected(props, true);
    if (select.valid) {
        let selectDatas = select.selectDatas;
        let billId = selectDatas[0] && selectDatas[0].data.values[PRIMARY_KEY.head_id].value;
        this.setState({
            showBillTrack: true,
            billTrackBillId: billId,
            billTrackBillType: BILL_TYPE_CODE
        });
    }
}

//列表审批详情
export function listLinkApprove(props) {
    let select = checkSelected(props, true);
    if (select.valid) {
        let selectDatas = select.selectDatas;
        let billId = selectDatas[0] && selectDatas[0].data.values[PRIMARY_KEY.head_id].value;
        this.setState({
            showApproveDetail: true,
            billId: billId
        });
    }
}

//列表表头删除
export function listHeadDelete(props) {
    let data = {};
    let pks = [];
    let select = checkSelected(props, false);
    if (select.valid) {
        let selectDatas = select.selectDatas;
        pks = selectDatas && selectDatas.map(item => item.data.values[PRIMARY_KEY.head_id].value);
        let pkMapTs = new Map();
        selectDatas && selectDatas.map(item => {
            let pk = item.data.values[PRIMARY_KEY.head_id].value;
            let ts = item.data.values[FIELD.ts] && item.data.values[FIELD.ts].value;
            //主键与tsMap
            if (pk && ts) {
                pkMapTs.set(pk, ts);
            }
        });
        data = { pks, pkMapTs };
    }
    ajax({
        url: REQUEST_URL.delete,
        data: data,
        success: (res) => {
            if (res.success) { //成功
                let allTableData = props.table.getAllTableData(LIST.table_id);
                let allPks = allTableData.rows[0] && allTableData.rows.map(item => item.values[PRIMARY_KEY.head_id].value);
                let deleteRowIndexArr = pks.map(item => allPks.findIndex(v => v == item)).filter(item => item != -1);
                props.table.deleteCacheId(LIST.table_id, pks);
                props.table.deleteTableRowsByIndex(LIST.table_id, deleteRowIndexArr);
                toast({ color: STATUS.success, content: props.json['81H10101-000005'] });/* 国际化处理： 删除成功*/
            } else { //失败
                toast({ color: STATUS.warning, content: props.json['81H10101-000009'] });/* 国际化处理： 删除失败*/
            }
        }
    });
}

//列表表体删除
export function listBodyDelete(props, data, index) {
    ajax({
        url: REQUEST_URL.delete,
        data: data,
        success: (res) => {
            if (res.success) { //成功
                props.table.deleteCacheId(LIST.table_id, data.pks[0]);
                props.table.deleteTableRowsByIndex(LIST.table_id, index);
                toast({ color: STATUS.success, content: props.json['81H10101-000005'] });/* 国际化处理： 删除成功*/
            } else { //失败
                toast({ color: STATUS.warning, content: props.json['81H10101-000009'] });/* 国际化处理： 删除失败*/
            }
        }
    });
}

//列表查询
export function listSearch(props, queryInfo) {
    debugger
    let pageInfo = props.table.getTablePageInfo(LIST.table_id);
    if (!queryInfo) {
        queryInfo = props.search.getQueryInfo(LIST.search_id);
    }
    queryInfo.pageInfo = pageInfo;
    queryInfo.pageCode = LIST.page_code;

    // 刷新按钮可用
    props.button.setDisabled({ [LIST_BUTTON.refresh]: false });

    ajax({
        url: REQUEST_URL.queryList,
        data: queryInfo,
        success: (res) => {
            let { success, data } = res;
            if (res.formulamsg && res.formulamsg instanceof Array && res.formulamsg.length > 0) {
                props.dealFormulamsg(res.formulamsg);
            }
            if (success && data && data[LIST.table_id]) {
                props.table.setAllTableData(LIST.table_id, data[LIST.table_id]);
                toast({ color: STATUS.success });
            } else {
                props.table.setAllTableData(LIST.table_id, { rows: [] });
                toast({ color: STATUS.warning, content: props.json['81H10101-000010'] });/* 国际化处理： 未查询出符合条件的数据！*/
            }

            // 将查询条件缓存
            let { setDefData } = cardCache;
            setDefData(SEARCH_CACHE.key, SEARCH_CACHE.dataSource, queryInfo);
        }
    });
}

//分页查询
export function pageInfoClick(props, config, pks) {
    let data = {
        pks,
        pagecode: LIST.page_code
    };

    ajax({
        url: REQUEST_URL.queryListByPks,
        data: data,
        success: (res) => {
            let { success, data } = res;
            if (success && data && data[LIST.table_id]) {
                props.table.setAllTableData(LIST.table_id, data[LIST.table_id]);
                toast({ color: STATUS.success });
            } else {
                props.table.setAllTableData(LIST.table_id, { rows: [] });
                toast({ color: STATUS.warning, content: props.json['81H10101-000010'] });/* 国际化处理： 未查询出符合条件的数据！*/
            }
        }
    });
}

/**
 * 列表消息提示
 * @param {*} res           返回的response
 * @param {*} opername      操作名称
 */
export function PromptMessage(res) {
    let { status, msg } = res.data;
    let content;
    let total = res.data.total;
    let successNum = res.data.successNum;
    let failNum = res.data.failNum;
    content = '共' + '提交' + total + '条';/* 国际化处理： 共,条，*/
    content = content + ',成功' + successNum + '条';/* 国际化处理： 成功,条 ,,成功*/
    content = content + ',失败' + failNum + ',条';/* 国际化处理： 失败,条,条*/
    let errMsgArr = res.data.errormessages;
    //全部成功
    if (status == 0) {
        toast({
            color: "success",
            title: '提交' + msg,
            content: content,
            TextArr: ['展开', '收起', '关闭'],/* 国际化处理： 展开,收起,关闭*/
            groupOperation: true
        });
    }
    //全部失败
    else if (status == 1) {
        toast({
            duration: "infinity",
            color: "danger",
            title: '提交' + msg,
            content: content,
            TextArr: ['展开', '收起', '关闭'],/* 国际化处理： 展开,收起,关闭*/
            groupOperation: true,
            groupOperationMsg: errMsgArr
        });
    }
    //部分成功
    else if (status == 2) {
        toast({
            duration: "infinity",
            color: "warning",
            title: '提交' + msg,
            content: content,
            TextArr: ['展开', '收起', '关闭'],/* 国际化处理： 展开,收起,关闭*/
            groupOperation: true,
            groupOperationMsg: errMsgArr
        });
    }
}

//列表行双击
export function handleDoubleClick(record, index, props) {
    props.pushTo(REQUEST_URL.toCard, {
        status: STATUS.browse,
        id: record[PRIMARY_KEY.head_id].value,
        pagecode: CARD.page_code,
        scene: props.getUrlParam('scene')
    });
};