import { PRIMARY_KEY, LIST_BUTTON, FIELD } from '../../constant';
import { listEdit, listBodyDelete, listCopy, listCommit, listUnCommit } from './listOperator';

//列表操作列按钮操作
export function bodyButtonClick(props, key, text, record, index) {
    let pk = record[PRIMARY_KEY.head_id] && record[PRIMARY_KEY.head_id].value;
    let ts = record[FIELD.ts] && record[FIELD.ts].value;
    let pkMapTs = new Map();
    //主键与tsMap
    if (pk && ts) {
        pkMapTs.set(pk, ts);
    }

    switch (key) {
        //修改
        case LIST_BUTTON.bodyUpdate:
            listEdit(props, pk)
            break;

        //删除
        case LIST_BUTTON.bodyDelete:
            listBodyDelete({ ...props, json: this.state.json }, { pks: [pk], pkMapTs }, index);
            break;

        //复制
        case LIST_BUTTON.copy:
            listCopy(props, pk);
            break;

        //提交
        case LIST_BUTTON.bodyCommit:
            listCommit.call(this, { ...props, json: this.state.json }, { pks: [pk], pkMapTs });
            break;

        //收回
        case LIST_BUTTON.bodyUnCommit:
            listUnCommit({ ...props, json: this.state.json }, { pks: [pk], pkMapTs });
            break;
    }
}
