import { WebPlugin } from '@capacitor/core';

import type { DiskSpacePlugin } from './definitions';

export class DiskSpaceWeb extends WebPlugin implements DiskSpacePlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
