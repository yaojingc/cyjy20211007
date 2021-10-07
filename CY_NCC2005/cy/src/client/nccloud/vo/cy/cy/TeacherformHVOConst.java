
package nccloud.vo.cy.cy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.ISuperVO;
import nccloud.vo.tmpub.precison.PrecisionField;
/**
 * 常量类
 * @since v3.5.6-1903
 */
public class TeacherformHVOConst {
         /**
       * 单据操作
       */
      public static String CONST_ACTION_APPROVE = "APPROVE"; // 审批

      public static String CONST_ACTION_DELETE = "DELETE"; // 删除

      public static String CONST_ACTION_DELVERSION = "DELVERSION"; // 删除版本

      public static String CONST_ACTION_SAVE = "SAVE"; // 提交

      public static String CONST_ACTION_SAVEBASE = "SAVEBASE"; // 保存

      public static String CONST_ACTION_UNAPPROVE = "UNAPPROVE"; // 取消审批

      public static String CONST_ACTION_UNSAVEBILL = "UNSAVEBILL"; // 收回

      /**
       * 单据类型
       */
      public static String CONST_BILLTYPE_COST = "LSSQ";

        /**
        * 主表主键字段
        */
        public static String CONST_PARENT_PRIMARYKEY = "pk_teacherform";

        /**
        * 审批状态字段
        */
        public static String CONST_BILL_STATUS = "approvestatus";
        /**
        * 时间戳
        */
        public static String CONST_TS = "ts";

      /**
       * 编码
       */
      // 老师申请单应用编码
      public static String CONST_CODE_APPCODE = "81H10505";

      // 老师申请单列表页面区域编码
      public static String CONST_CODE_AREACODE_LIST_SEARCH = "list_query";

      // 老师申请单列表页面区域编码
      public static String CONST_CODE_AREACODE_LIST_TABLE = "list_head";

      // 老师申请单卡片页面编码
      public static String CONST_CODE_PAGECODE_COST_CARD = "81H10505_CARD";

      // 老师申请单列表页面编码
      public static String CONST_CODE_PAGECODE_COST_LIST = "81H10505_LIST";

      /**
       * 模块
       */
      public static String CONST_MODULE = "cy";

      public static String CONST_MODULE_CODE = "81H1";

      /**
       * 业务所需常量
       */
      public static String CONST_PFLOW_ISRELOADBILL = "IS_RELOADBILL"; // 取消审批

      /**
       * 常用属性名称
       */
      public static String FIELD_GLOBALMNYFIELD = "globalMnyField";

      public static String FIELD_GLOBALRATE = "globalRateField";

      public static String FIELD_GROUPMNY = "groupMnyField";

      public static String FIELD_GROUPRATE = "groupRateField";

      public static String FIELD_MONEY = "mnyField";

      public static String FIELD_ORGMNY = "orgMnyField";

      public static String FIELD_ORGRATE = "orgRateField";

      public static String FIELD_PK_CURRTYPE = "pk_currtype";

      public static String FIELD_PK_GROUP = "pk_group";

      public static String FIELD_PK_ORG = "pk_org";


      /**
       * 获取表体精度处理字段明细
       *
       * @return key:表体class,value:精度字段列表
       */
      public static Map<String, List<PrecisionField>> getBodyPrecisionFields() {
        Map<String, List<PrecisionField>> map =
            new HashMap<String, List<PrecisionField>>();
        List<PrecisionField> list = new ArrayList<PrecisionField>();
        // 精度字段处理
        map.put("card_body", list);
        return map;
      }

      /**
       *
       * * 构建表头精度处理字段

       * @return
       */
      public static List<PrecisionField> getHeadPrecisionFields() {
        // 精度字段处理
        List<PrecisionField> headPrecisionFields = new ArrayList<PrecisionField>();
        return headPrecisionFields;
      }
}
