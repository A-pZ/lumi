/**
 *
 */
package lumi.spring;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 * Spring 3.0 BeanNameGenerator ( for Struts2 Action )
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. ) ( http://www.h3.dion.ne.jp/~alpha-pz/ )
 *
 */
@Log4j2
public class Struts2ActionBeanNameGenerator implements BeanNameGenerator {

	private long genCount = 0;

	/**
	 * @see org.springframework.beans.factory.support.BeanNameGenerator#generateBeanName(org.springframework.beans.factory.config.BeanDefinition, org.springframework.beans.factory.support.BeanDefinitionRegistry)
	 */
	public String generateBeanName(BeanDefinition definition,
			BeanDefinitionRegistry registry) {

		genCount ++;

		String className = definition.getBeanClassName();
		log.info("* generateBeanName[{}] : {}" , genCount , className);

		return className;
	}

}
