# LinkedHashMap vs HashMap
The LinkedHashMap class is very similar to HashMap in most aspects. However, the linked hash map is based on both hash table and linked list to enhance the functionality of hash map.
It maintains a doubly-linked list running through all its entries in addition to an underlying array of default size 16.

To maintain the order of elements, the linked hashmap modifies the Map.Entry class of HashMap by adding pointers to the next and previous entries:
```
LinkedHashMap<Integer, String> map = new LinkedHashMap<>(16, .75f, true);
params = initialCapacity,loadFactor,orderingMode
```
Linked HashMap implements two pointers before and after that's how it maintains insertion order.
This attribute can be of great advantage in an API that receives any map, makes a copy to manipulate and returns it to the calling code. If the client needs the returned map to be ordered the same way before calling the API, then a linked hashmap is the way to go.
Insertion order is not affected if a key is re-inserted into the map.

Just like HashMap, LinkedHashMap performs the basic Map operations of add, remove and contains in constant-time, as long as the hash function is well-dimensioned. It also accepts a null key as well as null values.
However, this constant-time performance of LinkedHashMap is likely to be a little worse than the constant-time of HashMap due to the added overhead of maintaining a doubly-linked list.
Iteration over collection views of LinkedHashMap also takes linear time O(n) similar to that of HashMap. On the flip side, LinkedHashMap‘s linear time performance during iteration is better than HashMap‘s linear time.
This is because, for LinkedHashMap, n in O(n) is only the number of entries in the map regardless of the capacity. Whereas, for HashMap, n is capacity and the size summed up, O(size+capacity).
Load Factor and Initial Capacity are defined precisely as for HashMap. Note, however, that the penalty for choosing an excessively high value for initial capacity is less severe for LinkedHashMap than for HashMap, as iteration times for this class are unaffected by capacity.

# UnmodifiableHashMap vs ImmutableHashMap
An Unmodifiable Map is just a wrapper over a modifiable map and it doesn't allow modifications to it directly:
```
Map<String, String> mutableMap = new HashMap<>();
mutableMap.put("USA", "North America");

Map<String, String> unmodifiableMap = collections.unmodifiableMap(mutableMap);
assertThrows(UnsupportedOperationException.class,
  () -> unmodifiableMap.put("Canada", "North America"));
```
An Immutable Map, on the other hand, contains its own private data and doesn't allow modifications to it. Therefore, the data cannot change in any way once an instance of the Immutable Map is created.
```
ImmutableMap<String, String> immutableMap = ImmutableMap.copyOf(mutableMap);
assertTrue(immutableMap.containsKey("USA"));
```
# Store Duplicate keys in HashMap
1. Storing List as a Value
2. Using MultiMap
3. Using MultiValuedMap
```
Map<String, List<String>> map = new HashMap<>();
map.computeIfAbsent("key1", k -> new ArrayList<>()).add("value1");
map.computeIfAbsent("key1", k -> new ArrayList<>()).add("value2");

assertThat(map.get("key1").get(0)).isEqualTo("value1");
assertThat(map.get("key1").get(1)).isEqualTo("value2");

```
```
MultiMap<String, String> map = new MultiValueMap<>();
map.put("key1", "value1");
map.put("key1", "value2");
assertThat((Collection<String>) map.get("key1"))
  .contains("value1", "value2");

MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
map.put("key1", "value1");
map.put("key1", "value2");
map.put("key1", "value2");
assertThat((Collection<String>) map.get("key1"))
  .containsExactly("value1", "value2", "value2");

MultiValuedMap<String, String> map = new HashSetValuedHashMap<>();
map.put("key1", "value1");
map.put("key1", "value1");
assertThat((Collection<String>) map.get("key1"))
  .containsExactly("value1");
```
