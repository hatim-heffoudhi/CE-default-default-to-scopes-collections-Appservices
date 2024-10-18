# CE-default-default-to-scopes-collections-Appservices


## Overview

This project contains two main packages:

1. **_default_default**
2. **scopes_collections**

### _default_default

The `_default_default` package provides default access to the Couchbase **default scope** and **default collection**. It serves as the starting point for interacting with the bucket before any migration has been applied. Use this package to access the default environment settings.

- **Purpose**: To maintain backward compatibility with the default collection in the pre-migration bucket.
- **Usage**: This package is primarily used when accessing the bucket in its unmodified, default state.

### scopes_collections

The `scopes_collections` package contains the functionality related to Couchbase **scopes** and **collections**. This package should be executed after the migration process is complete, reflecting the new structure of the bucket.

- **Purpose**: To interact with the specific scopes and collections in the newly migrated bucket.
- **Usage**: Once migration is complete, run the `main()` method in this package to manage or interact with the scoped and collection-based structure.

## Migration Process

1. **Before migration**: Use the `_default_default` package to interact with the bucket using the default scope and collection.
2. **After migration**: Run the `main()` function from the `scopes_collections` package to transition to the new scopes and collections model for managing data.

## How to Run

- **Pre-migration**: Run `_default_default.Main` for default access.
- **Post-migration**: Run the `scopes_collections.Main` to interact with the updated structure.

## Notes
- This example uses the Couchbase **travel-sample** bucket. Make sure that the **travel-sample** bucket is loaded and available on your Couchbase instance before running the code.
- Ensure that the bucket migration is complete before running `scopes_collections.main()`.
