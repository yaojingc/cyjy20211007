import { high } from 'nc-lightapp-front';

const { Refer } = high;

export default function (props = {}) {
    var conf = {
        multiLang: {
            domainName: 'cy',
            currentLocale: 'zh-CN',
            moduleId: '81H10115',
        },

        refType: 'grid',
        refName: '词源班级档案',
        placeholder: '搜索',
        
        queryGridUrl: '/nccloud/cy/cy/ClassCyGridReferQuery.do',
        columnConfig: [{ name: ['班级名称','班级编码'], code: ['clasname','bill_no'] }],
        isMultiSelectedEnabled: false,
        isHasDisabledData: false,
    };

    return <Refer {...conf} {...props} />
}