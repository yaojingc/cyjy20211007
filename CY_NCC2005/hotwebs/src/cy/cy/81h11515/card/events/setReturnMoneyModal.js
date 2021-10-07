import React, { Component } from 'react';
import { ajax, createPage, base, toast } from 'nc-lightapp-front';
import { STATUS } from '../../constant';

let { NCModal, NCButton, NCNumber } = base;

export default class setReturnMoneyModal extends Component {
        constructor(props) {
            super(props);
            this.state = {
                showModal: false,
                returnMoney: 0,
                pk_bill: ''
            };
            this.close = this.close.bind(this);
            this.open = this.open.bind(this);
            this.confirmBtn = this.confirmBtn.bind(this);
        }

        // 组件挂载
        componentDidMount() {
            if (this.props.onRef) {
                this.props.onRef(this)
            }
        }

        //关闭
        close() {
            this.setState({ showModal: false });
        }

        //打开
        open(returnMoneyData) {
            debugger;
            this.state.returnMoney = returnMoneyData.returnMoney;
            this.state.pk_bill = returnMoneyData.pk_bill;
            this.setState({ showModal: true });
        }

        // 确认
        confirmBtn() {
            debugger;
            let params = {
                pk_bill: this.state.pk_bill,
                return_money: String(this.state.returnMoney)
            }
            let that = this;
            ajax({
                url: '/nccloud/cy/cy/setReturnMoney.do',
                data: params,
                async: false,
                success: (res) => {
                    if(res.data && res.data.success=='Y'){
                        this.close();
                        toast({ color: STATUS.success, content: " 设置成功！" });
                        that.props.updateMoney(res.data.headvo.retuencost);
                    }
                },
                error: (res) => {
                    toast({ color: 'danger', content: res.message });
                }
            })
        }

        //金额输入变更
        onChangeByReturnMoney = (e) => {
            debugger;
            var value = 0;
            if(e!=undefined && e!=''){
                value = parseFloat(e);
                this.state.returnMoney = value;
                //this.setState(this.state.returnMoney);
            }
        }

        render() {
            const { modal } = this.props;
            let { createModal, show } = modal;
            return (
                <div>
                   <NCModal show={this.state.showModal} onHide={this.close} style={{ width: 500}}>
                        <NCModal.Header closeButton={true}>
                            <NCModal.Title>请设置退款金额</NCModal.Title>
                        </NCModal.Header>

                        <NCModal.Body>
                            <div className='container' style={{display: "flex"}}>
                                <span>退款金额：</span>
                                <NCNumber className='basic-width' scale={2} min={0} style={{width: 200}} value={this.state.returnMoney} onChange={this.onChangeByReturnMoney}/>
                            </div>
                        </NCModal.Body>
                        <NCModal.Footer>
                            <NCButton colors="primary" onClick={() => this.confirmBtn()}>确认</NCButton>
                            <NCButton onClick={() => this.close()} >关闭</NCButton>
                        </NCModal.Footer>
                    </NCModal>
                </div>
            );
        }
}

setReturnMoneyModal = createPage({
    initTemplate: ''
  })(setReturnMoneyModal);