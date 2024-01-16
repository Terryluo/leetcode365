package util;

import java.util.*;

/*
380. Insert Delete GetRandom O(1)
Implement the RandomizedSet class:

RandomizedSet() Initializes the RandomizedSet object.
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
You must implement the functions of the class such that each function works in average O(1) time complexity.


Example 1:

Input
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
Output
[null, true, false, true, 2, true, false, 2]

Explanation
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
randomizedSet.insert(2); // 2 was already in the set, so return false.
randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.


Constraints:

-2^31 <= val <= 2^31 - 1
At most 2 * 10^5 calls will be made to insert, remove, and getRandom.
There will be at least one element in the data structure when getRandom is called.
* */
public class RandomizedSet {
    private List<Integer> numberSet;
    private Map<Integer, Integer> numberAndIndices;
    private Random random;
    public RandomizedSet() {
        this.numberSet = new ArrayList<>();
        this.numberAndIndices = new HashMap<Integer,Integer>();// save the element and index
        this.random = new Random();
    }

    public boolean insert(int val) {
        // when insert, check if the element exists. If exist return false, then insert to the last place of the array;
        if (numberAndIndices.containsKey(val)) {
            return false;
        }
        numberSet.add(val);
        numberAndIndices.put(val, numberSet.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        // when remove, get the Random number from the array, then swap the last element to removed index
        if (!numberAndIndices.containsKey(val)) {
            return false;
        }
        int index = numberAndIndices.get(val);
        numberSet.set(index, numberSet.get(numberSet.size() - 1));
        numberAndIndices.put(numberSet.get(index), index);
        numberAndIndices.remove(val);
        numberSet.remove(numberSet.size() - 1);
        return true;
    }

    public int getRandom() {
        // return a number from 0 to size(excluded), assume size is not equals to 0
        return numberSet.get(random.nextInt(numberSet.size()));
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        //runscript1(randomizedSet);
        runscript2(randomizedSet);
        //runscript3(randomizedSet);
    }
    private static void runscript1(RandomizedSet randomizedSet) {
        System.out.println(randomizedSet.insert(1)); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
        System.out.println(randomizedSet.remove(2)); // Returns false as 2 does not exist in the set.
        System.out.println(randomizedSet.insert(2)); // Inserts 2 to the set, returns true. Set now contains [1,2].
        System.out.println(randomizedSet.getRandom()); // getRandom() should return either 1 or 2 randomly.
        System.out.println(randomizedSet.remove(1)); // Removes 1 from the set, returns true. Set now contains [2].
        System.out.println(randomizedSet.insert(2)); // 2 was already in the set, so return false.
        System.out.println(randomizedSet.getRandom()); // Since 2 is the only number in the set, getRandom() will always return 2.
    }
    private static void runscript2(RandomizedSet randomizedSet) {
        System.out.println(randomizedSet.insert(0));
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.remove(1));
        System.out.println(randomizedSet.getRandom());
    }
    private static void runscript3(RandomizedSet randomizedSet) {
        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.insert(0));
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.insert(0));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */