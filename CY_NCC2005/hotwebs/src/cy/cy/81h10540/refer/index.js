import { high } from 'nc-lightapp-front';

const { Refer } = high;

export default function (props = {}) {
   
    var conf = {
        multiLang: {
            domainName: 'cy',
            currentLocale: 'zh-CN',
            moduleId: '81H10540',
        },

        refType: 'grid',
        refName: '学费减免档案',
        placeholder: '搜索',
        
        queryGridUrl: '/nccloud/cy/cy/XuefeijmGridReferQuery.do',
        columnConfig: [{ name: ['学费减免理由','单据编码','减免金额'], code: ['infonote','bill_no','reducation'] }],/* 国际化处理： 税类编码,税类名称*/
        isMultiSelectedEnabled: false,
        isHasDisabledData: false,
    };

    return <Refer {...conf} {...props} />
}