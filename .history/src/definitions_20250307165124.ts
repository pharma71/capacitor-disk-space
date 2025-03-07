// definitions.ts
export interface DiskSpaceInfo {
  totalSpace: number; // in Bytes
  freeSpace: number;  // in Bytes
  appUsedSpace: number; // in Bytes
}

export interface DiskSpacePlugin {
  getDiskSpace(): Promise<DiskSpaceInfo>;
}
