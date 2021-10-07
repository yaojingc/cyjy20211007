import { high } from 'nc-lightapp-front';

const { Refer } = high;

export default function (props = {}) {
    var conf = {
        multiLang: {
            domainName: 'cy',
            currentLocale: 'zh-CN',
            moduleId: '81h11020',
        },

        refType: 'grid',
        refName: '试卷档案',/* 国际化处理： 客户税类*/
        placeholder: '搜索',/* 国际化处理： 客户税类*/
        
        queryGridUrl: '/nccloud/cy/cy/TestpaperfileGridReferQuery.do',
        columnConfig: [{ name: ['试卷名称','试卷编码'], code: ['testname','testcode'] }],/* 国际化处理： 税类编码,税类名称*/
        isMultiSelectedEnabled: false,
        isHasDisabledData: false
    };

    return <Refer {...conf} {...props} />
}