import React, { Component } from "react";
import { ajax, createPage, base, toast } from "nc-lightapp-front";
// 导入人员参照
import PsndocRiartTreeGridRef from '../../../../uapbd/refer/psninfo/PsndocRiartTreeGridRef/index';
// 导入部门参照
// import DeptTreeRef from '../../../../uapbd/refer/org/DeptTreeRef/index';

import { REQUEST_URL, STATUS } from '../constant';

// const { NCFormControl } = base;

let { NCModal, NCButton, NCInput } = base;

export default class teacherAssignModal extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showModal: false,
      deptValue: {},
      psndocValue: {},
      selectRowData: {},
      indexProps: null
    };
    this.close = this.close.bind(this);
    this.open = this.open.bind(this);
    this.confirmBtn = this.confirmBtn.bind(this);
  }

  onChange = (e) => {
    this.setState({ value: e });
  }

  onChangeDept = (e) => {
    this.state.deptValue = e;
    this.setState(this.state.deptValue);
  }

  onChangePsndoc = (e) => {
    this.state.psndocValue = e;
    this.setState(this.state.psndocValue);
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

  open(indexprop, checkedRows) {
    console.log("indexprop-", indexprop);
    console.log("checkedRows-", checkedRows);
    this.state.selectRowData = checkedRows;
    this.state.indexProps = indexprop;
    this.setState({ showModal: true });
  }

  // 确认按钮
  confirmBtn() {
    debugger
    // 获取人员参照对象
    var pk_psndoc = this.state.psndocValue.refpk;
    // 获取选中行的数据
    var selectRowData = this.state.selectRowData;
    let pk_teacherform = null;
    selectRowData.map((item, index) => {
      if (item.data.values.pk_teacherform.value) {
        pk_teacherform = item.data.values.pk_teacherform.value;
      }
    })
    let data = {
      "pk_teacherform": pk_teacherform,
      "pk_psndoc": pk_psndoc
    }
    this.props.teacherAssignManager(this.state.indexProps, data);
    this.close();
    // ajax({
    //   url: REQUEST_URL.teacherAssign,
    //   data: {
    //     "pk_teacherform": pk_teacherform,
    //     "pk_psndoc": pk_psndoc
    //   },    //参数值
    //   success: (res) => {
    //     toast({ color: STATUS.success, content: " 分配成功！" });
    //   },
    //   error: (res) => {
    //     toast({ color: 'danger', content: res.message });
    //     this.close();
    //   }
    // })
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
            <NCModal.Title>请选择待分配的老师/讲师</NCModal.Title>
          </NCModal.Header>

          <NCModal.Body>
            <div>
              {/* <div> */}
              {/* {DeptTreeRef({
                value: this.state.deptValue,
                isShowUnit: true,
                unitProps: {
                  refType: 'tree',
                  refName: '业务单元',
                  refCode: 'uapbd.refer.org.BusinessUnitTreeRef',
                  rootNode: { refname: '业务单元', refpk: 'root' },
                  placeholder: "业务单元",
                  queryTreeUrl: '/nccloud/uapbd/org/BusinessUnitTreeRef.do',
                  treeConfig: { name: ['编码', '名称'], code: ['refcode', 'refname'] },
                  isMultiSelectedEnabled: false,
                  isShowUnit: false,
                },
                onChange: this.onChangeDept,
              })}
              </div> */}
              <div>
                {PsndocRiartTreeGridRef({
                  isShowUnit: false,
                  value: this.state.psndocValue,
                  queryCondition: function () {
                    return {
                      pk_org: "0001L110000000000GJH",
                      pk_group: "0001L110000000000DY1",
                    }
                  },
                  unitProps: {
                    refType: 'tree',
                    refName: '业务单元',
                    refCode: 'uapbd.refer.org.BusinessUnitTreeRef',
                    rootNode: { refname: '业务单元', refpk: 'root' },
                    placeholder: "业务单元",
                    queryTreeUrl: '/nccloud/uapbd/org/BusinessUnitTreeRef.do',
                    treeConfig: { name: ['编码', '名称'], code: ['refcode', 'refname'] },
                    isMultiSelectedEnabled: false,
                    isShowUnit: false,
                  },
                  onChange: this.onChangePsndoc,
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




teacherAssignModal = createPage({
  initTemplate: ''
})(teacherAssignModal);

