import { high } from 'nc-lightapp-front';

const { Refer } = high;

export default function (props = {}) {
    debugger
    var conf = {
        multiLang: {
            domainName: 'cy',
            currentLocale: 'zh-CN',
            moduleId: '81H10105',
        },

        refType: 'grid',
        refName: '学生档案',/* 国际化处理： 客户税类*/
        placeholder: '搜索',/* 国际化处理： 客户税类*/
        
        queryGridUrl: '/nccloud/cy/cy/StudentGridReferQuery.do',
        columnConfig: [{ name: ['学生名称','学生编码'], code: ['sname','bill_no'] }],/* 国际化处理： 税类编码,税类名称*/
        isMultiSelectedEnabled: false,
        isHasDisabledData: false,
    };

    return <Refer {...conf} {...props} />
}