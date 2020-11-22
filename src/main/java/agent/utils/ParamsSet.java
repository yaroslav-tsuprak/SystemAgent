package agent.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * This class is meant to hold a set of (key,value) pairs.<br>
 * They are stored as object but can be retrieved in any type wanted. As long as cast is available.<br>
 * @author Yaroslav
 */
public class ParamsSet
{
    /** Static empty immutable map, used to avoid multiple null checks over the source. */
    public static final ParamsSet EMPTY_STATSET = new ParamsSet(Collections.emptyMap());

    private final Map<String, Object> _set;

    public ParamsSet()
    {
        this(ConcurrentHashMap::new);
    }

    public ParamsSet(Supplier<Map<String, Object>> mapFactory)
    {
        this(mapFactory.get());
    }

    public ParamsSet(Map<String, Object> map)
    {
        _set = map;
    }

    /**
     * Returns the set of values
     * @return HashMap
     */
    public final Map<String, Object> getSet()
    {
        return _set;
    }

    /**
     * Add a set of couple values in the current set
     * @param newSet : StatsSet pointing out the list of couples to add in the current set
     */
    public void merge(ParamsSet newSet)
    {
        _set.putAll(newSet.getSet());
    }

    public ParamsSet clone(Map<String, Object> map)
    {
        ParamsSet newMap = new ParamsSet();
        map.forEach((k, v) -> {
            newMap.set(k, v);
        });
        return newMap;
    }

    /**
     * Add a set of couple values in the current set
     * @param map : Map pointing out the list of couples to add in the current set
     */
    public void merge(Map<String, Object> map)
    {
        _set.putAll(map);
    }

    /**
     * Verifies if the stat set is empty.
     * @return {@code true} if the stat set is empty, {@code false} otherwise
     */
    public boolean isEmpty()
    {
        return _set.isEmpty();
    }

    /**
     * Return the boolean value associated with key.
     * @param key : String designating the key in the set
     * @return boolean : value associated to the key
     * @throws IllegalArgumentException : If value is not set or value is not boolean
     */
    public boolean getBoolean(String key)
    {
        Objects.requireNonNull(key);
        final Object val = _set.get(key);
        if (val == null)
        {
            throw new IllegalArgumentException("Boolean value required, but not specified");
        }
        if (val instanceof Boolean)
        {
            return ((Boolean) val).booleanValue();
        }
        try
        {
            return Boolean.parseBoolean((String) val);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Boolean value required, but found: " + val);
        }
    }

    /**
     * Return the boolean value associated with key.<br>
     * If no value is associated with key, or type of value is wrong, returns defaultValue.
     * @param key : String designating the key in the entry set
     * @param defaultValue
     * @return boolean : value associated to the key
     */
    public boolean getBoolean(String key, boolean defaultValue)
    {
        Objects.requireNonNull(key);
        final Object val = _set.get(key);
        if (val == null)
        {
            return defaultValue;
        }
        if (val instanceof Boolean)
        {
            return ((Boolean) val).booleanValue();
        }
        try
        {
            return Boolean.parseBoolean((String) val);
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    public int getInt(String key)
    {
        Objects.requireNonNull(key);
        final Object val = _set.get(key);
        if (val == null)
        {
            throw new IllegalArgumentException("Integer value required, but not specified: " + key + "!");
        }

        if (val instanceof Number)
        {
            return ((Number) val).intValue();
        }

        try
        {
            return Integer.parseInt((String) val);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Integer value required, but found: " + val + "!");
        }
    }

    public int getInt(String key, int defaultValue)
    {
        Objects.requireNonNull(key);
        final Object val = _set.get(key);
        if (val == null)
        {
            return defaultValue;
        }
        if (val instanceof Number)
        {
            return ((Number) val).intValue();
        }
        try
        {
            return Integer.parseInt((String) val);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Integer value required, but found: " + val);
        }
    }

    public int increaseInt(String key, int increaseWith)
    {
        int newValue = getInt(key) + increaseWith;
        set(key, newValue);
        return newValue;
    }

    public int increaseInt(String key, int defaultValue, int increaseWith)
    {
        int newValue = getInt(key, defaultValue) + increaseWith;
        set(key, newValue);
        return newValue;
    }

    public int[] getIntArray(String key, String splitOn)
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(splitOn);
        final Object val = _set.get(key);
        if (val == null)
        {
            throw new IllegalArgumentException("Integer value required, but not specified");
        }
        if (val instanceof Number)
        {
            return new int[]
                    {
                            ((Number) val).intValue()
                    };
        }
        int c = 0;
        String[] vals = ((String) val).split(splitOn);
        int[] result = new int[vals.length];
        for (String v : vals)
        {
            try
            {
                result[c++] = Integer.parseInt(v);
            }
            catch (Exception e)
            {
                throw new IllegalArgumentException("Integer value required, but found: " + val);
            }
        }
        return result;
    }

    public List<Integer> getIntegerList(String key, String splitOn)
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(splitOn);
        final List<Integer> result = new ArrayList<>();
        for (int i : getIntArray(key, splitOn))
        {
            result.add(i);
        }
        return result;
    }

    public long getLong(String key)
    {
        Objects.requireNonNull(key);
        final Object val = _set.get(key);
        if (val == null)
        {
            throw new IllegalArgumentException("Long value required, but not specified");
        }
        if (val instanceof Number)
        {
            return ((Number) val).longValue();
        }
        try
        {
            return Long.parseLong((String) val);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Long value required, but found: " + val);
        }
    }

    public long getLong(String key, long defaultValue)
    {
        Objects.requireNonNull(key);
        final Object val = _set.get(key);
        if (val == null)
        {
            return defaultValue;
        }
        if (val instanceof Number)
        {
            return ((Number) val).longValue();
        }
        try
        {
            return Long.parseLong((String) val);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Long value required, but found: " + val);
        }
    }

    public long increaseLong(String key, long increaseWith)
    {
        long newValue = getLong(key) + increaseWith;
        set(key, newValue);
        return newValue;
    }

    public long increaseLong(String key, long defaultValue, long increaseWith)
    {
        long newValue = getLong(key, defaultValue) + increaseWith;
        set(key, newValue);
        return newValue;
    }

    public float getFloat(String key)
    {
        Objects.requireNonNull(key);
        final Object val = _set.get(key);
        if (val == null)
        {
            throw new IllegalArgumentException("Float value required, but not specified");
        }
        if (val instanceof Number)
        {
            return ((Number) val).floatValue();
        }
        try
        {
            return Float.parseFloat((String) val);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Float value required, but found: " + val);
        }
    }

    public float getFloat(String key, float defaultValue)
    {
        Objects.requireNonNull(key);
        final Object val = _set.get(key);
        if (val == null)
        {
            return defaultValue;
        }
        if (val instanceof Number)
        {
            return ((Number) val).floatValue();
        }
        try
        {
            return Float.parseFloat((String) val);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Float value required, but found: " + val);
        }
    }

    public float increaseFloat(String key, float increaseWith)
    {
        float newValue = getFloat(key) + increaseWith;
        set(key, newValue);
        return newValue;
    }

    public float increaseFloat(String key, float defaultValue, float increaseWith)
    {
        float newValue = getFloat(key, defaultValue) + increaseWith;
        set(key, newValue);
        return newValue;
    }

    public double getDouble(String key)
    {
        Objects.requireNonNull(key);
        final Object val = _set.get(key);
        if (val == null)
        {
            throw new IllegalArgumentException("Double value required, but not specified");
        }
        if (val instanceof Number)
        {
            return ((Number) val).doubleValue();
        }
        try
        {
            return Double.parseDouble((String) val);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Double value required, but found: " + val);
        }
    }

    public double getDouble(String key, double defaultValue)
    {
        Objects.requireNonNull(key);
        final Object val = _set.get(key);
        if (val == null)
        {
            return defaultValue;
        }
        if (val instanceof Number)
        {
            return ((Number) val).doubleValue();
        }
        try
        {
            return Double.parseDouble((String) val);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Double value required, but found: " + val);
        }
    }

    public double increaseDouble(String key, double increaseWith)
    {
        double newValue = getDouble(key) + increaseWith;
        set(key, newValue);
        return newValue;
    }

    public double increaseDouble(String key, double defaultValue, double increaseWith)
    {
        double newValue = getDouble(key, defaultValue) + increaseWith;
        set(key, newValue);
        return newValue;
    }

    public String getString(String key)
    {
        Objects.requireNonNull(key);
        final Object val = _set.get(key);
        if (val == null)
        {
            throw new IllegalArgumentException("String value required, but not specified");
        }
        return String.valueOf(val);
    }

    public String getString(String key, String defaultValue)
    {
        Objects.requireNonNull(key);
        final Object val = _set.get(key);
        if (val == null)
        {
            return defaultValue;
        }
        return String.valueOf(val);
    }

    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> T getEnum(String key, Class<T> enumClass)
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(enumClass);
        final Object val = _set.get(key);
        if (val == null)
        {
            throw new IllegalArgumentException("Enum value of type " + enumClass.getName() + " required, but not specified");
        }
        if (enumClass.isInstance(val))
        {
            return (T) val;
        }
        try
        {
            return Enum.valueOf(enumClass, String.valueOf(val));
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Enum value of type " + enumClass.getName() + " required, but found: " + val);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> T getEnum(String key, Class<T> enumClass, T defaultValue)
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(enumClass);
        final Object val = _set.get(key);
        if (val == null)
        {
            return defaultValue;
        }
        if (enumClass.isInstance(val))
        {
            return (T) val;
        }
        try
        {
            return Enum.valueOf(enumClass, String.valueOf(val));
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Enum value of type " + enumClass.getName() + " required, but found: " + val);
        }
    }

    @SuppressWarnings("unchecked")
    public <K, V> Map<K, V> getMap(String key, Class<K> keyClass, Class<V> valueClass)
    {
        final Object obj = _set.get(key);
        if ((obj == null) || !(obj instanceof Map<?, ?>))
        {
            return null;
        }

        final Map<?, ?> originalList = (Map<?, ?>) obj;
        if (!originalList.isEmpty())
        {
            if ((!originalList.keySet().stream().allMatch(keyClass::isInstance)) || (!originalList.values().stream().allMatch(valueClass::isInstance)))
            {
                System.out.println("getMap(\"{}\", {}, {}) requested with wrong generic type: {}!" +  key + " " + keyClass.getSimpleName() + " " + valueClass.getSimpleName() + " " + obj.getClass().getGenericInterfaces()[0]);
            }
        }
        return (Map<K, V>) obj;
    }

    public Map getMap() {
        return _set;
    }

    @SuppressWarnings("unchecked")
    public <K, V> Map<K, List<V>> getMapOfList(String key, Class<K> keyClass, Class<V> valueClass)
    {
        final Object obj = _set.get(key);
        if ((obj == null) || !(obj instanceof Map<?, ?>))
        {
            return null;
        }

        final Map<?, ?> originalList = (Map<?, ?>) obj;
        if (!originalList.isEmpty())
        {
            if ((!originalList.keySet().stream().allMatch(keyClass::isInstance)) || (!originalList.values().stream().allMatch(List.class::isInstance)))
            {
                System.out.println("getMap(\"{}\", {}, {}) requested with wrong generic type: {}!" +  key + " " + keyClass.getSimpleName() + " " + valueClass.getSimpleName() + " " + obj.getClass().getGenericInterfaces()[0]);
            }
        }
        return (Map<K, List<V>>) obj;
    }

    public ParamsSet set(String name, Object value)
    {
        if (value == null)
        {
            return this;
        }
        _set.put(name, value);
        return this;
    }

    public ParamsSet set(String key, boolean value)
    {
        _set.put(key, value);
        return this;
    }

    public ParamsSet set(String key, byte value)
    {
        _set.put(key, value);
        return this;
    }

    public ParamsSet set(String key, short value)
    {
        _set.put(key, value);
        return this;
    }

    public ParamsSet set(String key, int value)
    {
        _set.put(key, value);
        return this;
    }

    public ParamsSet set(String key, long value)
    {
        _set.put(key, value);
        return this;
    }

    public ParamsSet set(String key, float value)
    {
        _set.put(key, value);
        return this;
    }

    public ParamsSet set(String key, double value)
    {
        _set.put(key, value);
        return this;
    }

    public ParamsSet set(String key, String value)
    {
        if (value == null)
        {
            return this;
        }
        _set.put(key, value);
        return this;
    }

    public ParamsSet set(String key, Enum<?> value)
    {
        if (value == null)
        {
            return this;
        }
        _set.put(key, value);
        return this;
    }

    public static ParamsSet valueOf(String key, Object value)
    {
        final ParamsSet set = new ParamsSet();
        set.set(key, value);
        return set;
    }

    public void remove(String key)
    {
        _set.remove(key);
    }

    public boolean contains(String name)
    {
        return _set.containsKey(name);
    }

    @Override
    public String toString()
    {
        return "StatsSet{" + "_set=" + _set + '}';
    }
}
