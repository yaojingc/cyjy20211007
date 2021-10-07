import { FIELD, CARD_ADD_DISABLED_BUTTON, CARD } from "../../constant";
import { ajax, toast } from 'nc-lightapp-front';

/**
* @description: 表头编辑后事件
* @param: moduleId 区域编码
* @param: key 当前字段编码
*/
export function afterHeadEvent(props, moduleId, key, value, oldValue) {
        if (key === FIELD.org) {
                props.resMetaAfterPkorgEdit(); //选择主组织以后，恢复其他字段的编辑性
                props.button.setButtonDisabled(CARD_ADD_DISABLED_BUTTON, false);//恢复增行编辑性
                props.cardTable.setAllCheckboxAble(CARD.table_code, true);
        }
        debugger;
        //add by csh 退学类型为退学不退款模式自动，退款金额自动为0，否则清空
        if(key === 'def1'){
                if(value!=undefined && value.refname !=undefined){
                        if(value.refname=='退学不退款'){
                                props.form.setFormItemsValue('card_head',{'retuencost':{value:0,display:0}});//退费金额
                        }else{
                                props.form.setFormItemsValue('card_head',{'retuencost':{value:null,display:null}});//退费金额
                        }
                }else{
                        props.form.setFormItemsValue('card_head',{'retuencost':{value:null,display:null}});//退费金额
                }
        }
        //add by csh 退费申请单选择完学生后，将该学生的收款合同上的一些金额数据取过来进行自动填充到退费申请单中
        if (key === 'def2') {
                if(value!=undefined && value.refpk !=undefined){
                        let params = {
                                pk_customer: value.refpk,
                        }
                        ajax({
                                url: '/nccloud/cy/cy/queryContractByStu.do',
                                data: params,
                                async: false,
                                success: (res) => {
                                    if(res.data && res.data.ctarvo){
                                        let ntotalorigmny =  res.data.ctarvo.ntotalorigmny;//合同总金额
                                        if(ntotalorigmny!=undefined){
                                                props.form.setFormItemsValue('card_head',{'totalmoney':{value:ntotalorigmny,display:ntotalorigmny}});//学费总金额
                                        }

                                        let norigpshamount =  res.data.ctarvo.norigpshamount;//已交学费\合同信息累计收款金额
                                        if(norigpshamount!=undefined){
                                                props.form.setFormItemsValue('card_head',{'paymoney':{value:norigpshamount,display:norigpshamount}});//已交学费
                                                props.form.setFormItemsValue('card_head',{'collection':{value:norigpshamount,display:norigpshamount}});//累计原币收款金额
                                        }

                                        let norigcopamount =  res.data.ctarvo.norigcopamount;//收款合同累计应收金额
                                        if(norigcopamount!=undefined){
                                                props.form.setFormItemsValue('card_head',{'receivable':{value:norigcopamount,display:norigcopamount}});//累计原币应收金额
                                        }
                                    }else{
                                        props.form.setFormItemsValue('card_head',{'totalmoney':{value:null,display:null}});//学费总金额
                                        props.form.setFormItemsValue('card_head',{'paymoney':{value:null,display:null}});//已交学费
                                        props.form.setFormItemsValue('card_head',{'collection':{value:null,display:null}});//累计原币收款金额
                                        props.form.setFormItemsValue('card_head',{'receivable':{value:null,display:null}});//累计原币应收金额
                                        toast({ color: 'danger', content: '当前学生未检测到收款合同信息，请检查！' });     
                                    }
                                },
                                error: (res) => {
                                    toast({ color: 'danger', content: res.message });
                                }
                            })
                }    
        }
}
