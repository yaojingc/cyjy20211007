package nc.api.cy.rest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类注解标记
 * @author NCC
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZdyClassTagAnnotation {
	
	/**
	 * 
	 * 实际业务中是为否多实体，目前NCC中的所有单据，均发布成了主子表形式，单表业务的表单也发布成了主子表
	 *<p>true代表的是多实体(多实体需要给子页签VO赋值)</p>
     *<p>false代表的是单实体(单实体只需要给主VO赋值)</p>
	 * @return
	 */
	boolean isMultiEntity();
	
	/**
	 * 类路径
	 * <p>isMultiEntity为true时该项忽略无效，为false时采用，主要是为了用于实际业务为单表的实体进行转换成主表vo来使用的</p>
	 * @return
	 */
	String nccVO() default "";

}
