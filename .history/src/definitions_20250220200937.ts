// definitions.ts
export interface DiskSpaceInfo {
  total: number;
  free: number;
  usedByApp: number;
}

export interface DiskSpacePlugin {
  getDiskSpace(): Promise<DiskSpaceInfo>;
}
