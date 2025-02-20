export interface DiskSpacePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
