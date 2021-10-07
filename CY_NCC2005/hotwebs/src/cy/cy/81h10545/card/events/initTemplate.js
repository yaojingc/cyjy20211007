import { cardCache } from 'nc-lightapp-front';
import { CARD, CARD_BUTTON, STATUS, FIELD, CARD_CACHE, PRIMARY_KEY } from '../../constant';
import { cardCreate, getCardData, copyClear } from './cardOperator';
import { bodyButtonClick } from './bodyButtonClick';
import { buttonVisibilityControl } from './buttonVisibilityControl';
import { afterHeadEvent } from './afterHeadEvent';

/**
* @description: 卡片模板渲染
*/
export function initTemplate(props) {
        props.createUIDom({},
                (data) => {
                        if (data) {
                                if (data.button) {
                                        let button = data.button;
                                        props.button.setButtons(button);
                                        buttonVisibilityControl(props);
                                }
                                if (data.template) {
                                        let meta = data.template;
                                        meta = modifierMeta.call(this, props, meta);
                                        props.meta.setMeta(meta, () => {
                                                let status = this.props.getUrlParam(STATUS.status);
                                                let id = this.props.getUrlParam(PRIMARY_KEY.id);
                                                if (status === STATUS.add) { //新增
                                                        cardCreate(this.props);
                                                } else if (id) {
                                                        getCardData(this.props, id, false, copyClear);
                                                }
                                        });
                                }
                                if (data.context && data.context.pk_org) {
                                        if (props.getUrlParam(STATUS.status) === STATUS.add) {
                                                //设置默认组织
                                                let { pk_org, org_Name } = data.context;
                                                props.form.setFormItemsValue(CARD.form_id, {
                                                        [FIELD.org]: { value: pk_org, display: org_Name }
                                                });
                                                afterHeadEvent(props, CARD.form_id, FIELD.org, { display: org_Name, value: pk_org }, { display: null, value: null });
                                        }
                                        // 将组织信息缓存
                                        let { setDefData } = cardCache;
                                        setDefData(CARD_CACHE.key, CARD_CACHE.dataSource, data.context);
                                }
                                //表体统一参照过滤
                                /*
                                props.cardTable.setQueryCondition(CARD.table_code, {
                                        department: () => {
                                                return {
                                                        pk_org: props.form.getFormItemsValue(CARD.form_id, FIELD.org).value
                                                };
                                        }
                                });
                                */
                        }
                }
        )
}

function modifierMeta(props, meta) {
        //表头参照过滤
        meta[CARD.form_id].items.map(item => {
                if (item.attrcode === FIELD.org) { //财务组织
                        item.queryCondition = () => {
                                return {
                                        funcode: props.getSearchParam('c')//appcode获取
                                };
                        };
                }if (item.attrcode === 'def1'||item.attrcode === 'def2'||item.attrcode === 'salesman'
                ||item.attrcode === 'def3') { 
                        item.queryCondition = () => {
                                let pkOrgsValue = props.form.getFormItemsValue(CARD.form_id, FIELD.org).value;
                                return {
                                        unitPks: pkOrgsValue,
                                        busifuncode: 'it',
                                };
                        };
                } 
        });

        for (let item of Object.keys(meta.gridrelation)) {
                meta[item].items.push({
                        attrcode: 'opr',
                        label: this.state.json['81H10545-000006'],/* 国际化处理： 操作*/
                        itemtype: 'customer',
                        fixed: 'right',
                        className: 'table-opr',
                        visible: true,
                        width: 200,
                        render: (text, record, index) => {
                                let status = props.getUrlParam(STATUS.status);
                                let buttonAry = [];
                                if (status === STATUS.browse) { //浏览态
                                        buttonAry = [record.expandRowStatus ? CARD_BUTTON.fold : CARD_BUTTON.unfold];
                                } else { //编辑态
                                        buttonAry = this.state.isPaste ? [CARD_BUTTON.pasteHere] : [CARD_BUTTON.expand, CARD_BUTTON.insertRow, CARD_BUTTON.delRow, CARD_BUTTON.copyRow];
                                }
                                return props.button.createOprationButton(buttonAry, {
                                        area: CARD.body_btn_code,
                                        buttonLimit: 4,
                                        onButtonClick: (props, key) => bodyButtonClick.call(this, props, key, text, record, index)
                                });
                        }
                })
        }
        return meta;
}
