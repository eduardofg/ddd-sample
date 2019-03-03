package org.ddd.demo.common.i18n;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * Service to get an {@link com.totvs.tjf.i18n.I18nService I18nService}, it's
 * exclusive for internal use of the TJF.
 *
 * @author Eduardo Filipe Gomes
 * 
 */

@Service
public class I18n implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static MessageSource instance;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        I18n.applicationContext = applicationContext;
    }

    public static MessageSource instance() {
        if (instance == null) {
            instance = applicationContext.getBean(MessageSource.class);
        }
        return instance;
    }

}
