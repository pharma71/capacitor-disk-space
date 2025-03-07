package com.getcapacitor.community.capacitordiskspace;

import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "DiskSpace")
public class DiskSpacePlugin extends DiskSpace {
    @PluginMethod
    public void getDiskSpace(com.getcapacitor.PluginCall call) {
        super.getDiskSpace(call);
    }
}
