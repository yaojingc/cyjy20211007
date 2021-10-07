package nc.api.cy.rest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 翻译注解
* @author NCC
*
*/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZdyTranslateAnnotation {
	
	/**
	  * 类型共分为一下四种类型
	 * <p>1、实体参照</p>
	 * <p>2、自定义档案参照</p>
	 * <p>3、枚举</p>
	 * @return
	 */
	int type();
	
	/**
	  * 枚举标记。注意，此属性如有修改，出补丁的时候开发环境千万别启动着服务出补丁，否则将有大祸降临！！！！！！！！
	 * <p>注意：只有type为3时，该属性才生效</p>
	 * @return
	 */
	String enumTag() default "";
	
	/**
	 * 自定义档案编码。注意，此属性如有修改，出补丁的时候开发环境千万别启动着服务出补丁，否则将有大祸降临！！！！！！！！
	 * <p>注意：只有type为2时，该属性才生效</p>
	 * @return
	 */
	String defdocCode() default "";
	
	/**
	 * 翻译时，所用的查询语句，所查询出的字段一定要与当前字段封装的实体对应。注意，此语句如有修改，出补丁的时候开发环境千万别启动着服务出补丁，否则将有大祸降临！！！！！！！！
	 * <p>注意：只有type为1/2时，该属性才生效</p>
	 * @return
	 */
	String refQuerySql() default "";
	
	
}
