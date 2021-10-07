package nccloud.cy.cy.clsschedulehvo.cy.stutree;

import nc.vo.platform.workbench.model.AppMenuItemTreeVO;
import nccloud.web.uapbd.commons.tree.TreeWapper;

public class StuTreeWapper	 extends TreeWapper<StuTreeVO> {
	
	
	

	public StuTreeWapper(StuTreeVO[] objs) {
		super(objs);
	}

	@Override
	protected String getId(StuTreeVO T) {
		return T.getCode();
	}

	@Override
	protected String getName(StuTreeVO T) {
		return T.getCode()+" "+T.getName();
	}

	@Override
	protected String getPid(StuTreeVO T) {
		return T.getPid();
	}

	@Override
	protected String getInnercode(StuTreeVO T) {
		return null;
	}

	
	
}
