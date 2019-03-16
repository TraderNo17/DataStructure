public class test {

	public static void main(String[] args) throws IllegalNullKeyException, DuplicateKeyException {
		// TODO Auto-generated method stub
		HashTableADT<Integer, String> htIntegerKey = new HashTable<Integer,String>(10,0.5);
        htIntegerKey.insert(1, "1");
        htIntegerKey.insert(2, "1");
        htIntegerKey.insert(3, "1");
        htIntegerKey.insert(4, "1");
        htIntegerKey.insert(5, "1");
        htIntegerKey.insert(6, "1");
        System.out.println(htIntegerKey.getLoadFactor());
        System.out.println(htIntegerKey.getLoadFactorThreshold());
        System.out.println(htIntegerKey.numKeys());
        System.out.println(htIntegerKey.getCapacity());
	}
}
