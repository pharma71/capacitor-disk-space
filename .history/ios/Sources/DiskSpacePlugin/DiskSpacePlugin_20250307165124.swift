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

            // Von der App verwendeter Speicherplatz
            let appUsedSpace = try getAppUsedDiskSpace()

            // Rückgabe der Daten als JSON
            let result = [
                "totalSpace": totalSpace,
                "freeSpace": freeSpace,
                "appUsedSpace": appUsedSpace
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

    private func getAppUsedDiskSpace() throws -> Int {
        let documentDirectory = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).last!
        let appUsedSpace = try FileManager.default.contentsOfDirectory(at: documentDirectory, includingPropertiesForKeys: [.fileSizeKey], options: [])
            .reduce(0) { (size, url) -> Int in
                let resourceValues = try url.resourceValues(forKeys: [.fileSizeKey])
                return size + (resourceValues.fileSize ?? 0)
            }
        return appUsedSpace
    }
}