package org.massonus;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.massonus.config.SpringConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext webApplicationContext =
                new AnnotationConfigWebApplicationContext();

        /*регистрируем конфигурацию*/
        webApplicationContext.register(SpringConfig.class);

        // Manage the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(webApplicationContext));

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = servletContext
                /*название только "dispatcher"*/
                .addServlet("dispatcher", new DispatcherServlet(webApplicationContext));

        /*запустить 1 раз*/
        dispatcher.setLoadOnStartup(1);
        /*ищем view по всему проэкту, избегание ошибок при поиске*/
        dispatcher.addMapping("/");
    }

}
