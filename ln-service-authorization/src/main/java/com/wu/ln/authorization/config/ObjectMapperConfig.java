package com.wu.ln.authorization.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.wu.ln.authorization.entity.AppUserDetail;

public class ObjectMapperConfig extends SimpleModule {

    public ObjectMapperConfig() {
        super(ObjectMapperConfig.class.getName(), new Version(1, 0, 0, null, null, null));
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(AppUserDetail.class, UserDetailMixin.class);
        System.out.println("ObjectMapperConfig setupModule");
    }


}
