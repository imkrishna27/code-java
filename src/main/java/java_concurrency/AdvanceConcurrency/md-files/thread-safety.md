#### Thread Safety

1. Stateless Implementation --> incorrectly sharing states between multiple threads.
2. Immutable Implementations --> Make threads Immutable.
3. Thread-Local Fields --> ThreadLocal run separately.
4. Using Synchronized Collections --> Collection.synchronizedCollection(new ArrayList<>());
5. Using Concurrent Collections --> Concurrent HashMaps and all.
6. Using Atomic Objects --> AtomicInteger, AtomicBoolean , AtomicLong
7. Synchronized methods and statements
8. Volatile Fields and Locks.