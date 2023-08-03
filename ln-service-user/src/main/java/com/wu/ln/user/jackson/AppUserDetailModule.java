package com.wu.ln.user.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.wu.ln.user.entity.UserServiceDetail;
import org.springframework.security.jackson2.SecurityJackson2Modules;

public class AppUserDetailModule extends SimpleModule {

    public AppUserDetailModule() {
        super(AppUserDetailModule.class.getName(), new Version(1, 0, 0, null, null, null));
    }

    @Override
    public void setupModule(SetupContext context) {
        SecurityJackson2Modules.enableDefaultTyping(context.getOwner());
        context.setMixInAnnotations(UserServiceDetail.class, AppUserDetailMixin.class);
    }
}
