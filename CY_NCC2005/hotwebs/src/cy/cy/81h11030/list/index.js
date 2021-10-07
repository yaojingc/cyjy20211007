import React, { Component } from 'react';
import { createPage, createPageIcon, cardCache, high ,toast} from 'nc-lightapp-front';
import { initTemplate } from './events/initTemplate';
import { buttonClick } from './events/buttonClick';
import { searchBtnClick } from './events/searchBtnClick';
import { selectedEvent, selectedAllEvent } from './events/selectedEvent';
import { LIST_BUTTON, SEARCH_CACHE, LIST, MULTILANG, PRIMARY_KEY, BILL_TYPE_CODE, REQUEST_URL } from '../constant';
import { pageInfoClick, handleDoubleClick, listCommit } from './events/listOperator';

class List extends Component {
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
        }

        componentDidMount() {
                let { getDefData } = cardCache;
                if (getDefData(SEARCH_CACHE.key, SEARCH_CACHE.dataSource)) {
                        this.props.button.setDisabled({
                                [LIST_BUTTON.refresh]: false
                        });
                } else {
                        this.props.button.setDisabled({
                                [LIST_BUTTON.refresh]: true
                        });
                }
        }

        handlePageInfoChange = (props, config, pks) => {
                pageInfoClick({ ...props, json: this.state.json }, config, pks);
        }

        onRowDoubleClick = (record, index, props) => {
                handleDoubleClick(record, index, { ...props, json: this.state.json });
        }

        clickSearchBtn = (props) => {
                searchBtnClick({ ...props, json: this.state.json });
        }

        clickSelectBtn = (props, moduleId, record, index, status) => {
                selectedEvent(props, moduleId, record, index, status);
        }

        clickSelectAllBtn = (props, moduleId, status, length) => {
                selectedAllEvent(props, moduleId, status, length);
        }

        //指派提交
        getAssignUser = (value) => {
                listCommit({ ...this.props, json: this.state.json }, {
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
                let { table, search } = this.props;
                let { createSimpleTable } = table;
                let { NCCreateSearch } = search;
                let { NCUploader, BillTrack, ApprovalTrans, ApproveDetail } = high;
                return (
                        <div className="nc-bill-list">
                                <div className="nc-bill-header-area">
                                        <div className="header-title-search-area">
                                                {createPageIcon()}
                                                <h2 className="title-search-detail">{this.state.json[LIST.page_title]}</h2>
                                        </div>

                                        <div className="header-button-area">
                                                <input style={{ display: 'none' }} type='file' id='fileInfo' onChange={(v) => { this.upload(v) }} />
                                                {this.props.button.createButtonApp({
                                                        area: LIST.head_btn_code,
                                                        onButtonClick: buttonClick.bind(this)
                                                })}
                                        </div>
                                </div>

                                <div className="nc-bill-search-area">
                                        {NCCreateSearch(LIST.search_id, {
                                                clickSearchBtn: this.clickSearchBtn
                                        })}
                                </div>

                                <div className="table-area">
                                        {createSimpleTable(LIST.table_id, {
                                                showCheck: true,
                                                dataSource: SEARCH_CACHE.dataSource,
                                                pkname: PRIMARY_KEY.head_id,
                                                handlePageInfoChange: this.handlePageInfoChange,
                                                onRowDoubleClick: this.onRowDoubleClick,
                                                onSelected: this.clickSelectBtn,
                                                onSelectedAll: this.clickSelectAllBtn,
                                        })}
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
                                                title={this.state.json['81H11030-000018']} /* 国际化处理： 指派*/
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

        // 课表导入
        upload(v) {
                //导入
                debugger
                this.props.table.setAllTableData('importHead', { rows: [] });
                window.hh = this
                if (!v) {
                        return;
                }
                let formData = new FormData();
                let dome = document.querySelector('#fileInfo')
                formData.append('imgFile', dome.files[0]);
                axios.post(REQUEST_URL.importExcelKB, formData).then(result => {
                        debugger
                        let res = result.data.data;
                        if (res === 'true') {
                                toast({ color: 'success', content: '导入成功' });
                        }else{
                                toast({ color: 'warning', content: res });
                        }
                })
        }
}

List = createPage({
})(List);
export default List;
