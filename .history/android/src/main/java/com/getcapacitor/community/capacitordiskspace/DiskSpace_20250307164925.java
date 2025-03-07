package com.getcapacitor.community.capacitordiskspace;

import android.os.Environment;
import android.os.StatFs;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "DiskSpace")
public class DiskSpace extends Plugin {
    @PluginMethod
    public void getDiskSpace(PluginCall call) {
        try {
            // Gesamter Speicherplatz
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            long totalSpace = statFs.getTotalBytes();

            // Freier Speicherplatz
            long freeSpace = statFs.getAvailableBytes();

            // Von der App verwendeter Speicherplatz
            File appFilesDir = getContext().getFilesDir();
            long appUsedSpace = getFolderSize(appFilesDir);

            // Rückgabe der Daten als JSON
            JSObject ret = new JSObject();
            ret.put("totalSpace", totalSpace);
            ret.put("freeSpace", freeSpace);
            ret.put("appUsedSpace", appUsedSpace);
            call.resolve(ret);
        } catch (Exception e) {
            call.reject("Fehler beim Abrufen der Speicherinformationen", e);
        }
    }

    // Hilfsmethode zur Berechnung der Größe eines Verzeichnisses
    private long getFolderSize(File directory) {
        long length = 0;
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    length += file.length();
                } else {
                    length += getFolderSize(file);
                }
            }
        }
        return length;
    }
}
