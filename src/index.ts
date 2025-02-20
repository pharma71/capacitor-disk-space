import { registerPlugin } from '@capacitor/core';

import type { DiskSpacePlugin } from './definitions';

const DiskSpace = registerPlugin<DiskSpacePlugin>('DiskSpace', {
  web: () => import('./web').then((m) => new m.DiskSpaceWeb()),
});

export * from './definitions';
export { DiskSpace };
