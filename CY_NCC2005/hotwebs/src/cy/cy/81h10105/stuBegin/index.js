import React, { Component } from 'react';
import { ajax } from 'nc-lightapp-front';
import { CARD, REQUEST_URL, PRIMARY_KEY } from '../constant';
import { bodyButtonClick } from '../card/events/bodyButtonClick';
import { bodySelectedEvent, bodySelectedAllEvent } from '../card/events/bodySelectedEvent';
// 导入词源班级参照
import ClasscyTreeGridRef from '../../81h10115/refer/index';

export default class StuBegin extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentRecord: null,
            currentRowIndex: '',
            json: props.json,
            defaultAddData: null
        }
        this.gridId = 'card_body';
    }
    componentDidMount() {
        this.initData();
    }
    componentWillReceiveProps(nextProps) {
        if (Object.keys(nextProps.json).length !== 0) {
            this.setState({
                json: nextProps.json
            })
        }
    }

    /**
         * 初始化数据
         */
    initData = () => {
        let meta = this.props.meta.getMeta();
        this.props.meta.setMeta(meta, this.loadStudentGrid.bind(this, this.props, () => {

        }))
    }

    加载辅助属性单值信息列表
    loadStudentGrid = (props, callback) => {
        debugger;
        let id = this.props.getUrlParam(PRIMARY_KEY.id);
        // props.setUrlParam({ status: STATUS.edit });
        ajax({
            url: REQUEST_URL.queryCard,
            data: {
                pk: id,
                pagecode: CARD.page_code
            },
            success: (res) => {
                let { success, data } = res;
                if (success) {
                    if (data && data.bodys) {
                        props.cardTable.setTableData(CARD.table_code, data.bodys[CARD.table_code]);
                    }
                }
            },
        })
    }

    onAddAssistant = (props, callback) => {
        !!this.state.defaultAddData && callback && callback.call(this, this.state.defaultAddData)
    }
    onRowClick = (props, moduleId, record, index, e) => {
        this.state.currentRecord = record;
        this.setState(this.state);
    }
    onSelected = (props, moduleId, record, index, status) => {

    }
    onSelectedAll = (props, moduleId, status, length) => {

    }
    addRowCallback = () => {

    }
    getTableHead = () => (
        <div className="shoulder-definition-area">
            <div className="definition-icons">
                {this.props.button.createButtonApp({
                    area: CARD.shoulder_btn_code_stu,
                    onButtonClick: bodyButtonClick.bind(this),
                    popContainer: document.querySelector('.header-button-area'),
                })}
            </div>
        </div>
    );

    close() {
        this.setState({ showModal: false });
    }

    render() {
        let { cardTable } = this.props;
        let { createCardTable } = cardTable
        return (
            <div>
                <div>
                    {ClasscyTreeGridRef({

                    })}
                </div>
                <div>
                    {createCardTable(CARD.table_code, {//（学生成绩）
                        tableHead: this.getTableHead.bind(this),
                        showCheck: true,
                        showIndex: true,
                        onSelected: bodySelectedEvent,
                        onSelectedAll: bodySelectedAllEvent,
                    })}
                </div>
            </div>
        );
    }
}

