import { WebPlugin } from '@capacitor/core';
import type { DiskSpaceInfo, DiskSpacePlugin } from './definitions';

export class DiskSpaceWeb extends WebPlugin implements DiskSpacePlugin {

  async getDiskSpace(): Promise<DiskSpaceInfo> {
    console.warn('DiskSpace plugin is not available on the web.');
    return { totalSpace: 0, freeSpace: 0, appUsedSpace: 0 };
  }
}

const DiskSpace = new DiskSpaceWeb();

export { DiskSpace };