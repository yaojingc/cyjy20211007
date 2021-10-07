package nc.api.cy.rest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
  * 字段注解标记
 * @author NCC
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZdyFieldTagAnnotation {
	
	/**
	  * 普通字段，还是实体字段
	  *<p>true代表实体字段</p>
      *<p>false代表的普通字段</p>
	  * @return
	 */
	boolean isEntityFiled();
	
	/**
	 * 类路径：对应应该转换为的NCC实体
	 * <p>如果是普通字段，该项不需要</p>
	 * @return
	 */
	String nccVO() default "";
	

}
