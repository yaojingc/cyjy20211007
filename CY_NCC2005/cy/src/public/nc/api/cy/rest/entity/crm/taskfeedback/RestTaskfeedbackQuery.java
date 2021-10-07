package nc.api.cy.rest.entity.crm.taskfeedback;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;

public class RestTaskfeedbackQuery extends QueryPage{
	/**
	 * 制单人
	 */
	private String billmaker;
	
	public String getBillmaker() {
		return billmaker;
	}

	public void setBillmaker(String billmaker) {
		this.billmaker = billmaker;
	}
	/**
	 * 被指派人
	 */
	private String appointedman;

	public String getAppointedman() {
		return appointedman;
	}

	public void setAppointedman(String appointedman) {
		this.appointedman = appointedman;
	}


}
