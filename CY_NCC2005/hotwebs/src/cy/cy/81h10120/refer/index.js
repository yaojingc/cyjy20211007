import { high } from 'nc-lightapp-front';

const { Refer } = high;

export default function (props = {}) {
    var conf = {
        multiLang: {
            domainName: 'cy',
            currentLocale: 'zh-CN',
            moduleId: '81H10120',
        },

        refType: 'grid',
        refName: '行政班级档案',
        placeholder: '搜索',
        
        queryGridUrl: '/nccloud/cy/cy/ClassXzGridReferQuery.do',
        columnConfig: [{ name: ['班级名称','班级编码'], code: ['clasname','bill_no'] }],/* 国际化处理： 税类编码,税类名称*/
        isMultiSelectedEnabled: false,
        isHasDisabledData: false
    };

    return <Refer {...conf} {...props} />
}