import React, { Component } from 'react';
import { createPage, base, high } from 'nc-lightapp-front';
import { initTemplate } from './events/initTemplate';
import { buttonClick } from './events/buttonClick';
import { beforeHeadEvent } from './events/beforeHeadEvent';
import { beforeTableEvent } from './events/beforeTableEvent';
import { afterHeadEvent } from './events/afterHeadEvent';
import { afterTableEvent } from './events/afterTableEvent';
import { bodySelectedEvent, bodySelectedAllEvent } from './events/bodySelectedEvent';
import { MULTILANG, CARD, PRIMARY_KEY, CARD_BUTTON, DATASOURCE, STATUS, BILL_TYPE_CODE } from '../constant';
import { pageClick, cardCommit } from './events/cardOperator';
import { bodyButtonClick } from './events/bodyButtonClick';

/**
* @description: 卡片
*/
const { NCAffix } = base;

class Card extends Component {
        constructor(props) {
                super(props);
                this.state = {
                        json: {},
                        showUploader: false,
                        billInfo: {},
                        compositeData: null, //指派信息
                        compositeDisplay: false, //是否显示指派
                        curPk: null, //当前选中数据的pk
                        showApproveDetail: false, //是否显示审批详情
                        billId: null
                };
        }

        componentWillMount() {
                // json： 多语json格式参数； 
                // status： 是否请求到json多语，可用来判断多语资源是否请求到并进行一些别的操作； 
                // inlt： 可用来进行占位符的一些操作
                let callback = (json, status, inlt) => {
                        if (status) {
                                initTemplate.call(this, this.props); // 如模板内也有多语处理，平台推荐模板加载操作放于此处， 在取到json多语资源后用传参的方式传入intemplate模板函数中

                                // 保存json和inlt到页面state中并刷新页面
                                this.setState({ json, inlt })
                        }
                }
                this.props.MultiInit.getMultiLang({ moduleId: MULTILANG.moduleId, domainName: MULTILANG.domainName, callback });

                // 关闭浏览器
                window.onbeforeunload = () => {
                        let status = this.props.cardTable.getStatus(CARD.table_code);
                        if (status == STATUS.edit) {
                                return this.state.json['81H10555-000007'];/* 国际化处理： 当前单据未保存，您确认离开此页面？*/
                        }
                };
        }

        componentDidMount() {

        }

        handlePageInfoChange = (props, id) => {
                pageClick({ ...props, json: this.state.json }, id);
        }

        //获取列表肩部信息
        getTableHead = () => (
                <div className="shoulder-definition-area">
                        <div className="definition-icons">
                                {this.props.button.createButtonApp({
                                        area: CARD.shoulder_btn_code,
                                        onButtonClick: bodyButtonClick.bind(this),
                                        popContainer: document.querySelector('.header-button-area')
                                })}
                        </div>
                </div>
        );

        //指派提交
        getAssignUser = (value) => {
                cardCommit({ ...this.props, json: this.state.json }, {
                        pks: this.state.curPk,
                        userObj: value
                });
                this.compositeTurnOff();
        };

        //关闭指派
        compositeTurnOff = () => {
                this.setState({
                        compositeData: null,
                        compositeDisplay: false
                });
        };

        render() {
                let { form, cardPagination, BillHeadInfo, cardTable } = this.props;
                let { createCardPagination } = cardPagination;
                let { createForm } = form;
                let { createCardTable } = cardTable;
                let { createBillHeadInfo } = BillHeadInfo;
                let { NCUploader, BillTrack, ApprovalTrans, ApproveDetail } = high;
                let status = this.props.getUrlParam(STATUS.status);
                let id = this.props.getUrlParam(PRIMARY_KEY.id);
                let billNo = this.props.form.getFormItemsValue(CARD.page_code, PRIMARY_KEY.bill_no);
                return (
                        <div className="nc-bill-card">
                                <div className="nc-bill-top-area">
                                        <NCAffix>
                                                <div className="nc-bill-header-area">
                                                        <div>
                                                                {createBillHeadInfo({
                                                                        title: this.state.json[CARD.page_title],//标题
                                                                        billCode: billNo && billNo.value,//单据号
                                                                        backBtnClick: buttonClick.bind(this, this.props, CARD_BUTTON.back)
                                                                })}
                                                        </div>
                                                        <div className="header-button-area">
                                                                {this.props.button.createButtonApp({
                                                                        area: CARD.head_btn_code,
                                                                        onButtonClick: buttonClick.bind(this)
                                                                })}
                                                        </div>
                                                        {status == STATUS.browse && id &&
                                                                <div className="header-cardPagination-area" style={{ float: 'right' }}>
                                                                        {createCardPagination({
                                                                                dataSource: DATASOURCE,
                                                                                pkname: PRIMARY_KEY.head_id,
                                                                                handlePageInfoChange: this.handlePageInfoChange
                                                                        })}
                                                                </div>
                                                        }
                                                </div>
                                        </NCAffix>
                                        <div className="nc-bill-form-area">
                                                {createForm(CARD.form_id, {
                                                        onAfterEvent: afterHeadEvent,
                                                        onBeforeEvent: beforeHeadEvent
                                                })}
                                        </div>
                                </div>

                                <div className="nc-bill-bottom-area">
                                        <div className="nc-bill-table-area">
                                                {createCardTable(CARD.table_code, {
                                                        tableHead: this.getTableHead.bind(this),
                                                        showCheck: true,
                                                        showIndex: true,
                                                        onSelected: bodySelectedEvent,
                                                        onSelectedAll: bodySelectedAllEvent,
                                                        onBeforeEvent: beforeTableEvent,
                                                        onAfterEvent: afterTableEvent,
                                                        modelSave: buttonClick.bind(this, { ...this.props, json: this.state.json }, CARD_BUTTON.save, undefined, true),
                                                })}
                                        </div>
                                </div>

                                {/* 附件 */}
                                {this.state.showUploader && (
                                        <NCUploader
                                                placement={'bottom'}
                                                {...this.state.billInfo}
                                                onHide={() => {
                                                        this.setState({
                                                                showUploader: false
                                                        });
                                                }}
                                        />
                                )}

                                {/*联查单据追溯*/}
                                {
                                        <BillTrack
                                                show={this.state.showBillTrack}
                                                close={() => {
                                                        this.setState({ showBillTrack: false });
                                                }}
                                                pk={this.state.billTrackBillId}
                                                type={this.state.billTrackBillType}
                                        />
                                }

                                {/* 指派 */}
                                {this.state.compositeDisplay && (
                                        <ApprovalTrans
                                                title={this.state.json['81H10555-000018']} /* 国际化处理： 指派*/
                                                data={this.state.compositeData}
                                                display={this.state.compositeDisplay}
                                                getResult={this.getAssignUser}
                                                cancel={this.compositeTurnOff}
                                        />
                                )}

                                {/* 联查审批详情 */}
                                {
                                        <ApproveDetail
                                                show={this.state.showApproveDetail}
                                                billtype={BILL_TYPE_CODE}
                                                billid={this.state.billId}
                                                close={() => {
                                                        this.setState({
                                                                showApproveDetail: false
                                                        });
                                                }}
                                        />
                                }
                        </div>
                );
        }
}

Card = createPage({
        billinfo: {
                billtype: 'card',
                pagecode: CARD.page_code,
                headcode: CARD.form_id,
                bodycode: CARD.table_code
        }
})(Card);
export default Card;
