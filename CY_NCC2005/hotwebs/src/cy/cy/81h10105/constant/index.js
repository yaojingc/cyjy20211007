/**
* @description: 常量
*/

//应用编码
export const APPCODE = '81H10105';

//单据类型
export const BILL_TYPE_CODE = 'XSDA';

/**
* @description: 多语
* @param moduleId: 多语资源名
* @param domainName: 工程名
*/
export const MULTILANG = {
        moduleId: '81H10105',
        domainName: 'cy'
};

/**
 * 列表
 */
export const LIST = {
        page_title: '81H10105-000011',                  //页面标题 /* 国际化处理： 投融资费用*/

        page_code: '81H10105_LIST',                     //页面编码

        search_id: 'list_query',                        //查询区 
        table_id: 'list_head',                          //表格区 

        head_btn_code: 'list_head',                     //表头按钮区 
        body_btn_code: 'list_inner',                                    //表体表格按钮区
};

/**
 * 卡片
 */
export const CARD = {
        page_title: '81H10105-000011',                   //页面标题 /* 国际化处理： 投融资费用*/

        page_code: '81H10105_CARD',                      //页面编码

        form_id: 'card_head',                            //表头表单区
        table_code: 'card_body',                         //表体表格区（学生成绩）
        table_code_change: 'card_body_change',           //表体表格区（学生变动）
        table_code_parent: 'card_body_parent',           //表体表格区（学生家长）
        table_edit_code: 'card_body_edit',               //表体表格编辑态侧拉区
        table_browse_code: 'card_body_browse',           //表体表格浏览态下拉区

        head_btn_code: 'card_head',                      //表头按钮区
        shoulder_btn_code: 'tabs_head',                  //表体肩部按钮区（学生成绩）
        shoulder_btn_code_change: 'tabs_head_change',    //表体肩部按钮区（学生变动）
        shoulder_btn_code_parent: 'tabs_head_parent',    //表体肩部按钮区（学生家长）
        shoulder_btn_code_stu: 'tabs_head_stu',        //弹窗保存按钮tabGroupStu
        body_btn_code: 'tabs_body'                       //表体表格按钮区
};

//领域名.模块名.节点名.自定义名
//缓存标示
export const DATASOURCE = 'tm.fmc.cost.datasource';

//查询区域缓存 
export const SEARCH_CACHE = {
        key: 'tm.fmc.cost.SEARCH_CACHE',                 //查询区域缓存Key
        dataSource: DATASOURCE                                           //查询区域缓存数据的名称空间
}

//卡片缓存 
export const CARD_CACHE = {
        key: 'tm.fmc.cost.CARD_CACHE',                   //卡片区域缓存Key
        dataSource: DATASOURCE                                           //卡片区域缓存数据的名称空间
}

//请求基础路径
export const base_path = '/nccloud/cy/cy/';

//url
export const REQUEST_URL = {
        save: `/nccloud/cy/cy/savestudentHVO.do`,                       //保存
        delete: `/nccloud/cy/cy/deletestudentHVO.do`,                        //删除
        queryCard: `/nccloud/cy/cy/querycardstudentHVO.do`,                      //卡片查询
        queryList: `/nccloud/cy/cy/querypagestudentHVO.do`,              //列表查询
        queryListByPks: `/nccloud/cy/cy/querypagebypkstudentHVO.do`,     //列表分页查询
        commit: `/nccloud/cy/cy/commitstudentHVO.do`,                   //提交
        unCommit: `/nccloud/cy/cy/uncommitstudentHVO.do`,               //收回
        batchTransfer: `/nccloud/cy/cy/batchTransfer.do`,                   //批量生成学生收款合同
        querySchooleExtraData: `/nccloud/cy/cy/querySchooleExtraData.do`,    //查询获取学校信息
        studentBeginSave: `/nccloud/cy/cy/studentBeginSave.do`,                   //学生期初数据录入
        // allot: `/nccloud/cy/cy/classxzstudentHVO.do`,                       //行政班级期初数据录入

        toCard: '/card',
        toList: '/list'
};

export const LIST_BUTTON = {
        create: 'AddBtn',
        delete: 'DelBtn',
        batchTransfer: "BatchTransferBtn",
        commit: 'CommitBtn',
        unCommit: 'UnCommitBtn',
        linkGroup: 'JointBtn',
        attachment: 'AttachmentBtn',
        approvalLink: 'DetailBtn',
        billTrack: 'TrackBtn',
        print: 'PrintBtn',
        output: 'OutputBtn',
        refresh: 'RefreshBtn',
        // allot: 'Allot',                       //行政班级期初数据录入

        bodyUpdate: 'edit',
        bodyDelete: 'delete',
        bodyCommit: 'commit',
        bodyUnCommit: 'unCommit',
        copy: 'copy'
};

//列表默认禁用按钮
export const LIST_DISABLED_BUTTON = [LIST_BUTTON.delete, LIST_BUTTON.commit, LIST_BUTTON.unCommit, LIST_BUTTON.linkGroup, LIST_BUTTON.approvalLink, LIST_BUTTON.billTrack,
LIST_BUTTON.attachment, LIST_BUTTON.print, LIST_BUTTON.output];

export const CARD_BUTTON = {
        beginSave: 'beginSave',// 学生期初信息录入
        save: 'SaveBtn',
        saveAdd: 'SaveAddBtn',
        saveCommit: 'SaveCommitBtn',
        cancel: 'CancelBtn',
        create: 'CreateBtn',
        update: 'UpdateBtn',
        delete: 'DeleteBtn',
        copy: 'CopyBtn',
        commit: 'CommitBtn',
        unCommit: 'UnCommitBtn',
        attachment: 'AttachmentBtn',
        approvalLink: 'DetailBtn',
        billTrack: 'TrackBtn',
        print: 'PrintBtn',
        output: 'OutputBtn',
        refresh: 'RefreshBtn',
        back: 'Back',

        stuUpdate: 'stuUpdate',  //（学生成绩期初录入）
        stuSave: 'stuSave',  //（学生成绩期初录入）
        addRowStu: 'addRowStu',  //（期初弹窗增行）
        deleteRowStu: 'deleteRowStu',
        addRow: 'addRow',  //（学生成绩）
        deleteRow: 'deleteRow',
        addRowChange: 'addRowChange',  //（学生变动）
        deleteRowChange: 'deleteRowChange',
        addRowParent: 'addRowParent',  //（学生家长）
        deleteRowParent: 'deleteRowParent',
        copyRows: 'copyRows',
        pasteTail: 'pasteTail',
        pasteCancel: 'pasteCancel',

        expand: 'expand',
        insertRow: 'insertRow',
        delRow: 'delRow',
        copyRow: 'copyRow',
        fold: 'fold',
        unfold: 'unfold',
        pasteHere: 'pasteHere'
};

//卡片默认禁用按钮
export const CARD_DISABLED_BUTTON = [CARD_BUTTON.deleteRow, CARD_BUTTON.copyRows];
//卡片新增禁用按钮
export const CARD_ADD_DISABLED_BUTTON = [CARD_BUTTON.addRow, CARD_BUTTON.save, CARD_BUTTON.saveAdd, CARD_BUTTON.saveCommit];

//字段名
export const FIELD = {
        org: 'pk_org',                                                   //组织
        //orgV: 'pk_org_v',                                              //组织多版本
        billStatus: 'approvestatus',                             //单据状态字段（用以按钮控制）
        ts: 'ts'                                                                 //时间戳
};

//主属性字段名
export const PRIMARY_KEY = {
        head_id: 'pk_student',           //表头主键字段名
        body_id: 'pk_grade',           //表体主键字段名
        bill_no: 'bill_no',                                               //单据编号字段名
        id: 'id'                                                                 //单据前端缓存标识
};

//状态
export const STATUS = {
        status: 'status',                                                //状态

        edit: 'edit',                                            //编辑态
        browse: 'browse',                                        //浏览态
        add: 'add',                                                              //新增态

        info: 'info',                                                    //帮助
        warning: 'warning',                                              //警告
        success: 'success',                                              //成功
        danger: 'danger',                                                //出错

        NOSTATE: '-1',                                                   //自由 态
        NOPASS: '0',                                                     //未通过 态
        PASSING: '1',                                                    //通过 态
        GOINGON: '2',                                                    //进行中 态
        COMMIT: '3'                                                              //提交 态
};