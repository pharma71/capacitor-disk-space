# Capacitor Disk Space Plugin

A Capacitor plugin to retrieve disk space information on Android and iOS.

## Installation
```sh
npm install capacitor-disk-space
npx cap sync
```

## Usage
```ts
import { DiskSpace } from 'capacitor-disk-space';

async function checkDiskSpace() {
  const info = await DiskSpace.getDiskSpace();
  console.log(`Total: ${info.total} bytes`);
  console.log(`Free: ${info.free} bytes`);
  console.log(`Used by App: ${info.usedByApp} bytes`);
}

checkDiskSpace();
```

## API

### `getDiskSpace()`
Returns an object containing:
- `total`: Total disk space in bytes.
- `free`: Free disk space in bytes.
- `usedByApp`: Space used by the app in bytes.

## License
MIT

<docgen-index>

* [`getDiskSpace()`](#getdiskspace)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getDiskSpace()

```typescript
getDiskSpace() => Promise<DiskSpaceInfo>
```

**Returns:** <code>Promise&lt;<a href="#diskspaceinfo">DiskSpaceInfo</a>&gt;</code>

--------------------


### Interfaces


#### DiskSpaceInfo

| Prop            | Type                |
| --------------- | ------------------- |
| **`total`**     | <code>number</code> |
| **`free`**      | <code>number</code> |
| **`usedByApp`** | <code>number</code> |

</docgen-api>
