package com.getcapacitor.community.capacitordiskspace;

import android.os.Environment;
import android.os.StatFs;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "DiskSpace")
public class DiskSpace extends Plugin {
    public void getDiskSpace(PluginCall call) {
        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        long total = (long) stat.getBlockCount() * stat.getBlockSize();
        long free = (long) stat.getAvailableBlocks() * stat.getBlockSize();
        long usedByApp = getAppUsedSpace();

        JSObject ret = new JSObject();
        ret.put("total", total);
        ret.put("free", free);
        ret.put("usedByApp", usedByApp);
        call.resolve(ret);
    }

    private long getAppUsedSpace() {
        return getContext().getFilesDir().getTotalSpace() - getContext().getFilesDir().getFreeSpace();
    }
}
