/*    */ package nccloud.web.uapbd.sminfo.bankacc.action;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import nc.itf.bd.bankacc.base.IBankAccBaseInfoQueryService;
/*    */ import nc.itf.bd.bankacc.base.IBankAccBaseInfoService;
/*    */ import nc.vo.bd.bankaccount.BankAccSubVO;
/*    */ import nc.vo.bd.bankaccount.BankAccbasVO;
/*    */ import nc.vo.pub.BusinessException;
/*    */ import nc.vo.pub.lang.UFBoolean;
/*    */ import nccloud.commons.lang.StringUtils;
/*    */ import nccloud.framework.core.exception.ExceptionUtils;
/*    */ import nccloud.framework.core.json.IJson;
import nccloud.framework.service.ServiceLocator;
/*    */ import nccloud.framework.web.action.itf.ICommonAction;
/*    */ import nccloud.framework.web.container.IRequest;
/*    */ import nccloud.framework.web.json.JsonFactory;
/*    */ import nccloud.framework.web.processor.template.BillCardConvertProcessor;
/*    */ import nccloud.framework.web.ui.pattern.billcard.BillCard;
/*    */ import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
/*    */ import nccloud.framework.web.ui.pattern.billcard.SpecilAggBill;
import nccloud.itf.cy.cy.IReturncosthvoMaintain;
/*    */ import nccloud.web.uapbd.sminfo.bankacc.utils.BankAccSaveParam;
/*    */ import nccloud.web.uapbd.sminfo.bankacc.utils.IBankAccService;
/*    */ 
/*    */ public class BankAccCardSaveAction implements ICommonAction
/*    */ {
/*    */   public Object doAction(IRequest request)
/*    */   {
/* 28 */     BillCardOperator bco = new BillCardOperator();
/* 29 */     List<String> exceptAreas = new ArrayList();
/*    */     
/* 31 */     exceptAreas.add("bankaccuse");
/* 32 */     exceptAreas.add("controlorg");
/* 33 */     BillCardConvertProcessor bcp = new BillCardConvertProcessor(exceptAreas);
/* 34 */     BankAccSaveParam bsp = (BankAccSaveParam)JsonFactory.create().fromJson(request.read(), BankAccSaveParam.class);
/* 35 */     String saveFlag = bsp.getSaveFlag();
/* 36 */     BillCard billcard = bsp.getBillcard();
/* 37 */     SpecilAggBill bill = (SpecilAggBill)bcp.fromBillCard(billcard);
/*    */     
/* 39 */     BankAccbasVO bankaccVO = new BankAccbasVO();
/* 40 */     BillCard bc = new BillCard();
/* 41 */     if (bill == null)
/* 42 */       return null;
/* 43 */     bankaccVO = (BankAccbasVO)bill.getHead();
/* 44 */     BankAccSubVO[] bodys = (BankAccSubVO[])bill.getBodys();
/* 45 */     if (bodys != null) {
/* 46 */       List<BankAccSubVO> bankaccvoList = new ArrayList();
/* 47 */       for (BankAccSubVO superVO : bodys) {
/* 48 */         if ((superVO.getStatus() != 3) || 
/* 49 */           (!StringUtils.isEmpty(superVO.getPk_bankaccbas()))) {
/* 50 */           bankaccvoList.add(superVO);
/*    */         }
/* 52 */         if (superVO.getIstrade() == null) {
/* 53 */           superVO.setIstrade(UFBoolean.FALSE);
/*    */         }
/* 55 */         if ((!StringUtils.isNotBlank(bankaccVO.getPk_bankaccbas())) || ("specil".equals(saveFlag)))
/*    */         {
/* 57 */           superVO.setFronzenstate(Integer.valueOf(0));
/* 58 */           superVO.setPk_bankaccbas(bankaccVO.getPk_bankaccbas());
/* 59 */           superVO.setStatus(2);
/*    */         }
/*    */       }
/* 62 */       bodys = (BankAccSubVO[])bankaccvoList.toArray(new BankAccSubVO[0]);
/*    */     }
/* 64 */     bankaccVO.setBankaccsub((BankAccSubVO[])bodys);
/* 65 */     IBankAccService is = new IBankAccService();
/*    */     try
/*    */     {
			   //========add by csh 20210922 begin======银行账户-财务组织保存时判断，该财务组织下的退款银行账户是否唯一===========================
				IReturncosthvoMaintain returnCostService = ServiceLocator.find(IReturncosthvoMaintain.class);
				BankAccbasVO bankData = returnCostService.queryReturnBankData(bankaccVO);
			   //========end=====================================================================================================================
/* 68 */       if ((!StringUtils.isNotBlank(bankaccVO.getPk_bankaccbas())) || ("specil".equals(saveFlag)))
/*    */       {
/* 70 */         if ("specil".equals(saveFlag)) {
/* 71 */           BankAccbasVO[] queryBankAccBasVOByPks = is.getBankQueryService().queryBankAccBasVOByPks(new String[] { bankaccVO.getPk_bankaccbas() }, false);
/* 72 */           if ((queryBankAccBasVOByPks != null) && (queryBankAccBasVOByPks.length > 0)) {
/* 73 */             throw new BusinessException("该单据已经生成银行账户！");
/*    */           }
/*    */         }
				//========add by csh 20210922 begin======银行账户-财务组织保存时判断，该财务组织下的退款银行账户是否唯一======新增校验=====================
				if(bankData!=null && bankaccVO.getDef1().equals("Y")) {
					throw new BusinessException("当前组织下银行账户"+bankData.getCode()+"为退款银行账户，请勿重复设置！");
				}
				//========end=====================================================================================================================
/* 76 */         bankaccVO.setStatus(2);
/* 77 */         bankaccVO = is.getBankService().insertBankAccBaseInfo(bankaccVO);
/*    */       }
/*    */       else {
				//========add by csh 20210922 begin======银行账户-财务组织保存时判断，该财务组织下的退款银行账户是否唯一======更新校验=====================
				if(bankData!=null && bankaccVO.getDef1()!=null && bankaccVO.getDef1().equals("Y") && !bankData.getCode().equals(bankaccVO.getCode())) {
					throw new BusinessException("当前组织下银行账户"+bankData.getCode()+"为退款银行账户，请勿重复设置！");
				}
			   //========end=====================================================================================================================
/* 80 */         bankaccVO = is.getBankService().updateBankAccBaseInfo(bankaccVO);
/*    */       }
/* 82 */       if (bankaccVO == null)
/* 83 */         return null;
/* 84 */       bill.setBodys(bankaccVO.getBankaccsub());
/* 85 */       bankaccVO.setBankaccsub(null);
/* 86 */       bill.setHead(bankaccVO);
/* 87 */       bc = bco.toCard(bill);
/*    */     } catch (BusinessException e) {
/* 89 */       ExceptionUtils.wrapException(e);
/*    */     }
/* 91 */     return bc;
/*    */   }
/*    */ }