import Foundation
import Capacitor

@objc(DiskSpacePlugin)
public class DiskSpacePlugin: CAPPlugin {

    @objc func getDiskSpaceInfo(_ call: CAPPluginCall) {
        do {
            // Gesamter Speicherplatz
            let totalSpace = try getTotalDiskSpace()

            // Freier Speicherplatz
            let freeSpace = try getFreeDiskSpace()

            // Von allen Apps verwendeter Speicherplatz (geschätzt)
            let allAppsUsedSpace = totalSpace - freeSpace

            // Rückgabe der Daten als JSON
            let result = [
                "totalSpace": totalSpace,
                "freeSpace": freeSpace,
                "allAppsUsedSpace": allAppsUsedSpace
            ]
            call.resolve(result)
        } catch {
            call.reject("Fehler beim Abrufen der Speicherinformationen")
        }
    }

    private func getTotalDiskSpace() throws -> Int {
        let systemAttributes = try FileManager.default.attributesOfFileSystem(forPath: NSHomeDirectory())
        guard let totalSpace = systemAttributes[.systemSize] as? NSNumber else {
            throw NSError(domain: "DiskSpacePlugin", code: 1, userInfo: [NSLocalizedDescriptionKey: "Total space could not be determined"])
        }
        return totalSpace.intValue
    }

    private func getFreeDiskSpace() throws -> Int {
        let systemAttributes = try FileManager.default.attributesOfFileSystem(forPath: NSHomeDirectory())
        guard let freeSpace = systemAttributes[.systemFreeSize] as? NSNumber else {
            throw NSError(domain: "DiskSpacePlugin", code: 2, userInfo: [NSLocalizedDescriptionKey: "Free space could not be determined"])
        }
        return freeSpace.intValue
    }
}