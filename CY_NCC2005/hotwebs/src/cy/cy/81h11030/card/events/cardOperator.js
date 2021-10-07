import { ajax, cardCache, toast } from 'nc-lightapp-front';
import { REQUEST_URL, CARD, PRIMARY_KEY, CARD_BUTTON, CARD_CACHE, DATASOURCE, STATUS, FIELD, BILL_TYPE_CODE, CARD_ADD_DISABLED_BUTTON } from '../../constant';
import { buttonVisibilityControl } from './buttonVisibilityControl';
import { afterHeadEvent } from './afterHeadEvent';

let { updateCache, addCache, getNextId, deleteCacheById, getCacheById, getCurrentLastId } = cardCache;

// 生成学生上课情况
export function creatSituation(props) {
    debugger
    let data = props.createMasterChildData(CARD.page_code, CARD.form_id, CARD.table_code, 'cardTable');
    ajax({
        url: REQUEST_URL.clasituation,
        data: data,
        success: (res) => {
            let { success, data } = res;
            if (success) {
                if (data && data.head) {
                    props.form.setAllFormValue({ [CARD.form_id]: data.head[CARD.form_id] });
                }
                if (data && data.body) {
                    props.cardTable.setTableData(CARD.table_code, data.body[CARD.table_code]);
                }
                props.pushTo(REQUEST_URL.toList, {});
                toast({ color: STATUS.success });
            }
        }
    });
}

//卡片返回
export function cardBack(props) {
    props.pushTo(REQUEST_URL.toList, {});
}


//卡片新增
export function cardCreate(props) {
    props.setUrlParam({ status: STATUS.add });
    //单据有主组织，新增时,将其他字段设置为不可编辑
    props.initMetaByPkorg();
    props.button.setButtonDisabled(CARD_ADD_DISABLED_BUTTON, true);
    props.cardTable.setAllCheckboxAble(CARD.table_code, false);
    clearAll(props);
    setByStatus(props);

    let { getDefData } = cardCache;
    let context = getDefData(CARD_CACHE.key, CARD_CACHE.dataSource);
    if (context) {
        let { pk_org, org_Name} = context;
        props.form.setFormItemsValue(CARD.form_id, {
            [FIELD.org]: { value: pk_org, display: org_Name }
        });
        afterHeadEvent(props, CARD.form_id, FIELD.org, { display: org_Name, value: pk_org }, { display: null, value: null });
    }
}

/**
 * 根据状态设置卡片
 * @param {*} status  
 */
function setByStatus(props) {
    buttonVisibilityControl(props);
    let status = props.getUrlParam(STATUS.status);
    //设置单据编号
    props.BillHeadInfo.setBillHeadInfoVisible({
        showBackBtn: status === STATUS.browse,
        showBillCode: status === STATUS.browse,
        billCode: props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.bill_no) && props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.bill_no).value
    });
    if (status !== STATUS.add) {
        props.resMetaAfterPkorgEdit(); //选择主组织以后，恢复其他字段的编辑性
    }
    // orgVersionView(props);//组织版本视图
}

/**
 * 组织多版本视图
 * @param {*} props 
 * @param {*} headCode 
 */
function orgVersionView(props) {
    let status = props.getUrlParam(STATUS.status);
    //浏览态显示组织版本，编辑态显示组织
    let showflag = status == STATUS.browse;
    let obj = {};
    obj[FIELD.org] = !showflag;
    props.form.setFormItemsVisible(CARD.form_id, obj);
}

//清空表头表体数据
function clearAll(props) {
    props.form.EmptyAllFormValue(CARD.form_id);
    props.cardTable.setTableData(CARD.table_code, { rows: [] });
}

//复制时清空数据
export function copyClear(props) {
    if (props.getUrlParam('isCopy')) {
        props.form.setFormItemsValue(CARD.form_id, {
            [PRIMARY_KEY.head_id]: { value: null, display: null },
            [PRIMARY_KEY.bill_no]: { value: null, display: null },
            [FIELD.billStatus]: { value: STATUS.NOSTATE, display: '自由' }

        });
        props.cardTable.setColValue(CARD.table_code, PRIMARY_KEY.head_id, { value: null, display: null });
        props.cardTable.setColValue(CARD.table_code, PRIMARY_KEY.body_id, { value: null, display: null });

        var num = props.cardTable.getNumberOfRows(CARD.table_code, false);
        var updateArray = new Array();
        for (var i = 0; i < num; i++) {
            updateArray.push({
                index: i,
                status: '2',//新增态
            });
        }
        props.cardTable.setRowStatusByIndexs(CARD.table_code, updateArray);

        props.setUrlParam({
            id: null,
            isCopy: false,
            status: STATUS.add
        });
    }
}

//卡片保存
export function cardSave(props) {
    return new Promise((resolve, rejected) => {
        let flag = props.form.isCheckNow(CARD.form_id) && props.cardTable.checkTableRequired(CARD.table_code);
        if (flag) {
            let data = props.createMasterChildData(CARD.page_code, CARD.form_id, CARD.table_code, 'cardTable');
            props.validateToSave(data, () => {

                let status = props.getUrlParam(STATUS.status);
                ajax({
                    url: REQUEST_URL.save,
                    data: data,
                    success: (res) => {
                        let { success, data } = res;
                        if (success) {
                            toast({ color: STATUS.success, content: props.json['81H11030-000004'] });/* 国际化处理： 保存成功*/

                            if (res.formulamsg && res.formulamsg instanceof Array && res.formulamsg.length > 0) {
                                props.dealFormulamsg(res.formulamsg);
                            }

                            let id = null;
                            if (res.data) {
                                if (res.data.head && res.data.head[CARD.form_id]) {
                                    id = res.data.head[CARD.form_id].rows[0].values[PRIMARY_KEY.head_id].value;
                                    props.form.setAllFormValue({ [CARD.form_id]: res.data.head[CARD.form_id] });
                                }
                                if (res.data.body && res.data.body[CARD.table_code]) {
                                    props.cardTable.setTableData(CARD.table_code, res.data.body[CARD.table_code]);
                                }
                            }

                            props.setUrlParam({
                                id,
                                status: STATUS.browse
                            });

                            // 缓存
                            if (status === STATUS.add) {
                                addCache(id, data, CARD.form_id, DATASOURCE);
                            } else {
                                updateCache(PRIMARY_KEY.head_id, id, data, CARD.form_id, DATASOURCE);
                            }

                            setByStatus(props);

                            resolve(true);
                        }
                    }
                });

            }, { [CARD.table_code]: 'cardTable' });
        }
    });
}

//卡片修改
export function cardUpdate(props) {
    props.setUrlParam({ status: STATUS.edit });
    setByStatus(props);
}

//卡片修改
export function cardCopy(props) {
    props.setUrlParam({ isCopy: true });
    copyClear(props);
    setByStatus(props);
}

//卡片删除
export function cardDelete(props) {
    let pk = props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.head_id).value || props.getUrlParam(PRIMARY_KEY.id);
    let ts = props.form.getFormItemsValue(CARD.form_id, FIELD.ts) && props.form.getFormItemsValue(CARD.form_id, FIELD.ts).value;
    let pkMapTs = new Map();
    //主键与tsMap
    if (pk && ts) {
        pkMapTs.set(pk, ts);
    }
    let data = { pks: [pk], pkMapTs };
    ajax({
        url: REQUEST_URL.delete,
        data: data,
        success: (res) => {
            if (res.success) {
                toast({ color: STATUS.success, content: props.json['81H11030-000005'] });/* 国际化处理： 删除成功*/

                // 获取下一条数据的id
                let nextId = getNextId(pk, DATASOURCE);
                //删除缓存
                deleteCacheById(PRIMARY_KEY.head_id, pk, DATASOURCE);
                let lastId = nextId ? nextId : null;
                props.setUrlParam({ status: STATUS.browse, id: lastId });
                if (lastId) {
                    getCardData(props, lastId);
                } else {// 删除的是最后一个的操作
                    clearAll(props);
                    setByStatus(props);
                }
            }
        }
    });
}

/**
 * 卡片详情
 * @param {*} id         单据id
 * @param {*} isRefresh  是否刷新按钮，是的话不取缓存数据，直接调取接口，并addCache, 默认否
 */
export function getCardData(props, id, isRefresh = false, callBack) {
    clearAll(props);
    if (!isRefresh) {
        let cardData = getCacheById(id, DATASOURCE);
        if (cardData) {//有缓存
            cardData.head && props.form.setAllFormValue({ [CARD.form_id]: cardData.head[CARD.form_id] });
            cardData.body && props.cardTable.setTableData(CARD.table_code, cardData.body[CARD.table_code]);
            setByStatus(props);
            return;
        }
    }
    ajax({
        url: REQUEST_URL.queryCard,
        data: {
            pk: id,
            pagecode: CARD.page_code
        },
        success: (res) => {
            let { success, data } = res;
            if (success) {
                if (data && data.head) {
                    props.form.setAllFormValue({ [CARD.form_id]: data.head[CARD.form_id] });
                }
                if (data && data.body) {
                    props.cardTable.setTableData(CARD.table_code, data.body[CARD.table_code]);
                }
                // 更新缓存
                updateCache(PRIMARY_KEY.head_id, id, data, CARD.form_id, DATASOURCE);
                callBack && callBack(props);
                setByStatus(props);
            }
        },
        error: (res) => {
            toast({ color: STATUS.danger, content: res.message });
            clearAll(props);
            setByStatus(props);
        }
    });
}

//卡片取消
export function cardCancel(props) {
    let id = props.getUrlParam(PRIMARY_KEY.id);
    props.setUrlParam({ status: STATUS.browse });
    if (id) { //有id切换编辑态
        props.form.cancel(CARD.form_id);
        props.cardTable.resetTableData(CARD.table_code);
        getCardData(props, id);
    } else { //没有id查缓存中最后一条数据
        let currentLastId = getCurrentLastId(DATASOURCE);
        let lastId = currentLastId ? currentLastId : null;
        props.setUrlParam({ id: lastId });
        if (lastId) {
            getCardData(props, lastId);
        } else {
            clearAll(props);
            setByStatus(props);
        }
    }
}

//卡片刷新
export function cardRefresh(props) {
    let id = props.getUrlParam(PRIMARY_KEY.id);
    getCardData(props, id, true);
}

/**
 * 卡片分页
 * @param {*} props  页面内置对象
 * @param {*} pk    下一页的pk
 */
export function pageClick(props, id) {
    props.setUrlParam({ status: STATUS.browse, id });
    getCardData(props, id);
}


//卡片附件
export function cardAttachment(props) {
    let billId = props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.head_id) && props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.head_id).value;
    let billNo = props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.bill_no) && props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.bill_no).value;
    this.setState({
        showUploader: !this.state.showUploader,
        billInfo: { billId, billNo }
    });
}

//卡片单据追溯
export function cardBillTrack(props) {
    let billId = props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.head_id) && props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.head_id).value;
    this.setState({
        showBillTrack: true,
        billTrackBillId: billId,
        billTrackBillType: BILL_TYPE_CODE
    });
}

//卡片联查审批详情
export function cardLinkApprove(props) {
    let billId = props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.head_id) && props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.head_id).value;
    this.setState({
        showApproveDetail: true,
        billId: billId
    });
}


//卡片提交
export function cardCommit(props, data) {
    if (!data) {
        let pk = props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.head_id) && props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.head_id).value;
        let ts = props.form.getFormItemsValue(CARD.form_id, FIELD.ts) && props.form.getFormItemsValue(CARD.form_id, FIELD.ts).value;
        let pkMapTs = new Map();
        //主键与tsMap
        if (pk && ts) {
            pkMapTs.set(pk, ts);
        }
        data = { pks: [pk], pkMapTs };
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
                } else {
                    cardRefresh(props);
                }
            } else { //失败
                toast({ color: STATUS.warning, content: props.json['81H11030-000015'] });/* 国际化处理： 提交失败*/
            }
        }
    });
}


//卡片收回
export function cardUnCommit(props) {
    let pk = props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.head_id) && props.form.getFormItemsValue(CARD.form_id, PRIMARY_KEY.head_id).value;
    let ts = props.form.getFormItemsValue(CARD.form_id, FIELD.ts) && props.form.getFormItemsValue(CARD.form_id, FIELD.ts).value;
    let pkMapTs = new Map();
    //主键与tsMap
    if (pk && ts) {
        pkMapTs.set(pk, ts);
    }
    let data = { pks: [pk], pkMapTs };
    ajax({
        url: REQUEST_URL.unCommit,
        data: data,
        success: (res) => {
            if (res.success) { //成功
                cardRefresh(props);
            } else { //失败
                toast({ color: STATUS.warning, content: props.json['81H11030-000017'] });/* 国际化处理： 收回失败*/
            }
        }
    });
}

