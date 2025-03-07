package com.getcapacitor.community.capacitordiskspace;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.StatFs;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.io.File;
import java.util.List;

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

            // Von allen Apps verwendeter Speicherplatz
            long allAppsUsedSpace = getAllAppsUsedSpace();

            // Rückgabe der Daten als JSON
            JSObject ret = new JSObject();
            ret.put("totalSpace", totalSpace);
            ret.put("freeSpace", freeSpace);
            ret.put("allAppsUsedSpace", allAppsUsedSpace);
            call.resolve(ret);
        } catch (Exception e) {
            call.reject("Fehler beim Abrufen der Speicherinformationen", e);
        }
    }

    // Berechnet den von allen Apps verwendeten Speicherplatz
    private long getAllAppsUsedSpace() {
        long totalSize = 0;
        PackageManager pm = getContext().getPackageManager();
        List<ApplicationInfo> apps = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo app : apps) {
            File appDir = new File(app.sourceDir).getParentFile();
            totalSize += getFolderSize(appDir);
        }

        return totalSize;
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