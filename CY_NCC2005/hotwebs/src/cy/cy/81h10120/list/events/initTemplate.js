import { LIST, LIST_BUTTON, PRIMARY_KEY, CARD, REQUEST_URL, FIELD, STATUS, LIST_DISABLED_BUTTON } from '../../constant';
import { bodyButtonClick } from '../events/bodyButtonClick';

//列表模板渲染
export function initTemplate(props) {
        props.createUIDom(
                {
                        pagecode: LIST.page_code,       //页面code
                },
                (data) => {
                        if (data) {
                                if (data.button) {
                                        //将请求回来的按钮组数据设置到页面的 buttons 属性上
                                        let button = data.button;
                                        props.button.setButtons(button);
                                        props.button.setPopContent(LIST_BUTTON.bodyDelete, this.state.json['81H10120-000008']);/* 国际化处理： 确认要删除吗?*/
                                        props.button.setButtonDisabled(LIST_DISABLED_BUTTON, true);
                                }
                                if (data.template) {
                                        let meta = data.template;
                                        meta = modifierMeta.call(this, props, meta);
                                        //高级查询区赋予组织
                                        if (data.context && data.context.pk_org) {
                                                let { pk_org, org_Name } = data.context;
                                                //遍历查询区域字段，将默认业务单元赋值给组织字段
                                                meta[LIST.search_id].items.map((item) => {
                                                        if (item.attrcode == FIELD.org) {
                                                                item.initialvalue = { display: org_Name, value: pk_org }
                                                        }
//else if (item.attrcode == FIELD.orgV) {
//                                                              item.initialvalue = { display: org_v_Name, value: pk_org_v }
//                                                      }
                                                });
                                        }
                                        props.meta.setMeta(meta);
                                        //查询区赋予组织
                                        //判断查询区域组织是否有值，如果有则表明快速查询方案已个性化定制。无需加载默认业务单元
                                        if (data.context && data.context.pk_org) {
                                                let orgValue = props.search.getSearchValByField(LIST.search_id, FIELD.org);
                                                //let orgVValue = props.search.getSearchValByField(LIST.search_id, FIELD.orgV);
                                                // if (!(orgValue && orgValue.value) && !(orgVValue && orgVValue.value)) {
                                                if (!(orgValue && orgValue.value)) {
                                                        let { pk_org, org_Name } = data.context;
                                                        props.search.setSearchValByField(LIST.search_id, FIELD.org, { display: org_Name, value: pk_org });
                                                        //props.search.setSearchValByField(LIST.search_id, FIELD.orgV, { display: org_v_Name, value: pk_org_v });
                                                }
                                        }
                                }
                        }
                }
        );
}

function modifierMeta(props, meta) {

        //查询区参照过滤
        meta[LIST.search_id].items.map((item) => {
                if (item.attrcode === FIELD.org) { //财务组织过滤
                        item.isMultiSelectedEnabled = true; //财务组织多选
                        item.queryCondition = () => {
                                return {
                                        funcode: props.getSearchParam('c')//appcode获取
                                };
                        };
                }
                //else if (item.attrcode === FIELD.orgV) {
                //      item.isMultiSelectedEnabled = true;
                //      item.queryCondition = () => {
                //              return {
                //                      funcode: props.getSearchParam('c')//appcode获取
                //              };
                //      };
                //}
        });

        //开启分页
        meta[LIST.table_id].pagination = true;

        meta[LIST.table_id].items = meta[LIST.table_id].items.map((item, key) => {
                if (item.attrcode == PRIMARY_KEY.bill_no) {
                        item.render = (text, record, index) => {
                                return (
                                        <a
                                                style={{ cursor: 'pointer' }}
                                                onClick={() => {
                                                        props.pushTo(REQUEST_URL.toCard, {
                                                                status: STATUS.browse,
                                                                id: record[PRIMARY_KEY.head_id].value,
                                                                pagecode: CARD.page_code
                                                        });
                                                }}
                                        >
                                                {record[PRIMARY_KEY.bill_no] && record[PRIMARY_KEY.bill_no].value}
                                        </a>
                                );
                        };
                }
                return item;
        });

        //添加操作列
        //示例代码
        meta[LIST.table_id].items.push({
                itemtype: 'customer',
                attrcode: 'opr',
                label: this.state.json['81H10120-000006'],/* 国际化处理： 操作*/
                width: '180px',
                fixed: 'right',
                className: "table-opr",
                visible: true,
                render: (text, record, index) => {
                        let buttonAry = [];
                        let billStatus = record[FIELD.billStatus] && record[FIELD.billStatus].value;
                        switch (billStatus) {
                                case STATUS.NOSTATE:
                                        buttonAry = [LIST_BUTTON.bodyCommit, LIST_BUTTON.bodyUpdate, LIST_BUTTON.bodyDelete, LIST_BUTTON.copy];
                                        break;
                                case STATUS.PASSING:
                                case STATUS.COMMIT:
                                case STATUS.GOINGON:
                                        buttonAry = [LIST_BUTTON.bodyUnCommit, LIST_BUTTON.copy];
                                        break;
                                default:
                                        buttonAry = [LIST_BUTTON.copy];
                                        break;
                        }

                        return props.button.createOprationButton(buttonAry, {
                                area: LIST.body_btn_code,
                                buttonLimit: 4,
                                onButtonClick: (props, key) => bodyButtonClick.call(this, props, key, text, record, index)
                        });
                }
        });

        return meta;
}

