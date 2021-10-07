import { promptBox,getBusinessInfo } from 'nc-lightapp-front';
import { CARD } from '../../constant';

/**
* @description: 表头编辑前事件
* @param: moduleId 区域编码
* @param: key 当前字段编码
* @return: 布尔 true表示可编辑
*/
export function beforeHeadEvent(props, moduleId, key, value, data) {
        // if (key === "def1" && value && value!="" && value.length>0) {
        //         promptBox({
        //                 color: "warning",
        //                 title: "班级变更后将清除子表数据",/* 国际化处理： 取消*/
        //                 content: "确认变更班级吗？",/* 国际化处理： 是否确认要取消?*/
        //                 beSureBtnClick: () => {
        //                         props.form.setFormItemsValue(CARD.form_id, {
        //                                 ["def2"]: { value: null, display: null }
        //                         });
        //                         return;
        //                 }
        //         });
        // }
        
        if (key === "def2") {
                let meta = props.meta.getMeta();
                meta[CARD.form_id].items.forEach((item) => {
                        if ("def2" === item.attrcode) {
                                let schoolVal = props.form.getFormItemsValue(CARD.form_id, 'def1').value;
                                item.queryCondition = {
                                        schoolValue: schoolVal,
                                };
                        }
                });
        }
        //学校参照根据登陆人过滤
        if (key === "def1") {
                debugger
                //获取登陆人id
                let userId = getBusinessInfo().userId;
                let meta = props.meta.getMeta();
                meta[CARD.form_id].items.forEach((item) => {
                        if ("def1" === item.attrcode) {
                                item.queryCondition = {
                                        userIdValue: userId,
                                };
                        }
                });
        }
        return true;

}