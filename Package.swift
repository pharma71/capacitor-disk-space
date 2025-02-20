// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorDiskSpace",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "CapacitorDiskSpace",
            targets: ["DiskSpacePlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "DiskSpacePlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/DiskSpacePlugin"),
        .testTarget(
            name: "DiskSpacePluginTests",
            dependencies: ["DiskSpacePlugin"],
            path: "ios/Tests/DiskSpacePluginTests")
    ]
)