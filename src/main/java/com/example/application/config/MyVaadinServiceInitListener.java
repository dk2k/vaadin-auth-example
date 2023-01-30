package com.example.application.config;

import com.example.application.views.login.LoginView;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;

import static com.example.application.util.Constants.CONSTANT_SESSION_USERNAME;

// https://dantesquevaadin.blogspot.com/2019/04/14-vaadinserviceinitlistener-preventing.html
@SpringComponent
public class MyVaadinServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            uiEvent
                    .getSource()
                    .addUIInitListener(uiInitEvent -> {
                        uiInitEvent
                                .getUI()
                                .addBeforeEnterListener(
                                        beforeEnterEvent -> {
                                            if (VaadinSession
                                                    .getCurrent()
                                                    .getSession()
                                                    .getAttribute(CONSTANT_SESSION_USERNAME) == null) {
                                                if (!LoginView.class.equals(beforeEnterEvent.getNavigationTarget())) {
                                                    // another option - rerouteTo()
                                                    beforeEnterEvent.forwardTo(LoginView.class);
                                                }
                                            }
                                        }
                                );

                    });


        });
    }
}
