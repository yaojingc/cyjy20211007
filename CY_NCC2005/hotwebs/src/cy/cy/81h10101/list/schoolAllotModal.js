
import React, { Component } from "react";
import { ajax, createPage, base, toast } from "nc-lightapp-front";
// 导入人员参照
import PsndocRiartTreeGridRef from '../../../../uapbd/refer/psninfo/PsndocRiartTreeGridRef/index';
// 导入部门参照
import { REQUEST_URL, STATUS } from '../constant';


// const { NCFormControl } = base;

let { NCModal, NCButton } = base;

export default class schoolAllotModal extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showModal: false,
      deptValue: {},
      psndocValue: {},
      psndocValue2: {},
      psndocValue3: {},
      psndocValue4: {},
      selectRowData: {},
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

  onChangePsndoc2 = (e) => {
    this.state.psndocValue2 = e;
    this.setState(this.state.psndocValue2);
  }

  onChangePsndoc3 = (e) => {
    this.state.psndocValue3 = e;
    this.setState(this.state.psndocValue3);
  }

  onChangePsndoc4 = (e) => {
    this.state.psndocValue4 = e;
    this.setState(this.state.psndocValue4);
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
    var pk_psndoc = this.state.psndocValue.refpk;
    var pk_psndoc2 = this.state.psndocValue2.refpk;
    var pk_psndoc3 = this.state.psndocValue3.refpk;
    var pk_psndoc4 = this.state.psndocValue4.refpk;
    // 获取选中行的数据
    var selectRowData = this.state.selectRowData;
    let selectRowDataVOS = [];
    selectRowData.map((item, index) => {
      if (item.data.values.pk_school.value) {
        selectRowDataVOS.push(item.data.values);
      }
    })

    ajax({
      url: REQUEST_URL.allot,
      data: {
        "schoolVOS": selectRowDataVOS,
        "pk_psndoc": pk_psndoc,
        "pk_psndoc2": pk_psndoc2,
        "pk_psndoc3": pk_psndoc3,
        "pk_psndoc4": pk_psndoc4
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
            <NCModal.Title>请选择待分配的业务员或高管</NCModal.Title>
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
            <div>&nbsp;</div>
            <div>
              <div>
                {PsndocRiartTreeGridRef({
                  isShowUnit: false,
                  value: this.state.psndocValue2,
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
                  onChange: this.onChangePsndoc2,
                })}
              </div>
            </div>
            <div>&nbsp;</div>
            <div>
              <div>
                {PsndocRiartTreeGridRef({
                  isShowUnit: false,
                  value: this.state.psndocValue3,
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
                  onChange: this.onChangePsndoc3,
                })}
              </div>
            </div>
            <div>&nbsp;</div>
            <div>
              <div>
                {PsndocRiartTreeGridRef({
                  isShowUnit: false,
                  value: this.state.psndocValue4,
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
                  onChange: this.onChangePsndoc4,
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




schoolAllotModal = createPage({
  initTemplate: ''
})(schoolAllotModal);

