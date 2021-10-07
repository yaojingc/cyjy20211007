
import React, { Component } from "react";
import { ajax, createPage, base, toast } from "nc-lightapp-front";
// 导入词源班级参照
import ClasscyTreeGridRef from '../../81h10115/refer/index';
import { REQUEST_URL, STATUS } from '../constant';


// const { NCFormControl } = base;

let { NCModal, NCButton } = base;

export default class classcyAllotModal extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showModal: false,
      classxzValue: {},
      selectRowData: {},
    };
    this.close = this.close.bind(this);
    this.open = this.open.bind(this);
    this.confirmBtn = this.confirmBtn.bind(this);
  }

  onChange = (e) => {
    this.setState({ value: e });
  }

  onChangeClassxz = (e) => {
    this.state.psndocValue = e;
    this.setState(this.state.classxzValue);
  }

  // 组件挂载
  componentDidMount() {
    if (this.props.onRef) {
      this.props.onRef(this)
    }
  }

  close() {
    this.setState({ showModal: false });
  }

  open(checkedRows) {
    this.state.selectRowData = checkedRows;
    this.setState({ showModal: true });
  }

  // 确认按钮
  confirmBtn() {
    debugger
    // 获取人员参照对象
    var pk_classxz = this.state.classxzValue.refpk;
    // 获取选中行的数据
    var selectRowData = this.state.selectRowData;
    let selectRowDataVOS = [];
    selectRowData.map((item, index) => {
      if (item.data.values.pk_classxz.value) {
        selectRowDataVOS.push(item.data.values);
      }
    })

    ajax({
      url: REQUEST_URL.allot,
      data: {
        "schoolVOS": selectRowDataVOS,
        "pk_classxz": pk_classxz
      },    //参数值
      success: (res) => {
        this.close();
        toast({ color: STATUS.success, content: " 分配成功！" });
      },
      error: (res) => {
        toast({ color: 'danger', content: res.message });
      }
    })
  }




  render() {
    let { search } = this.props;
    let { NCCreateSearch } = search;


    return (
      <div>
        <NCModal
          show={
            this.state.showModal
          }
          onHide={
            this.close
          }


          style={{ width: 500, height: 1500 }}
        >
          <NCModal.Header closeButton={true}>
            <NCModal.Title>分配行政班级</NCModal.Title>
          </NCModal.Header>

          <NCModal.Body>
            <div>
              <div>
                {ClasscyTreeGridRef({

                })}
              </div>
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
};




classcyAllotModal = createPage({
  initTemplate: ''
})(classcyAllotModal);

