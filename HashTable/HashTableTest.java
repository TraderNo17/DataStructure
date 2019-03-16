// TODO: add imports as needed

import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*; 
import org.junit.jupiter.api.Assertions;
 
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
 
import java.util.Random;



/** TODO: add class header comments here*/
public class HashTableTest{

    // TODO: add other fields that will be used by multiple tests
    
    // TODO: add code that runs before each test method
    @Before
    public void setUp() throws Exception {

    }

    // TODO: add code that runs after each test method
    @After
    public void tearDown() throws Exception {

    }
    
    /** 
     * Tests that a HashTable returns an integer code
     * indicating which collision resolution strategy 
     * is used.
     * REFER TO HashTableADT for valid collision scheme codes.
     */
    @Test
    public void test000_collision_scheme() {
        HashTableADT htIntegerKey = new HashTable<Integer,String>();
        int scheme = htIntegerKey.getCollisionResolution();
        if (scheme < 1 || scheme > 9) 
            fail("collision resolution must be indicated with 1-9");
    }
        
    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that insert(null,null) throws IllegalNullKeyException
     */
    @Test
    public void test001_IllegalNullKey() {
        try {
            HashTableADT htIntegerKey = new HashTable<Integer,String>();
            htIntegerKey.insert(null, null);
            fail("should not be able to insert null key");
        } 
        catch (IllegalNullKeyException e) { /* expected */ } 
        catch (Exception e) {
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    // TODO add your own tests of your implementation
    @Test
    public void test002_DuplicateKey() {
        try {
            HashTableADT htIntegerKey = new HashTable<Integer,String>();
            htIntegerKey.insert(1, "2");
            htIntegerKey.insert(1, "3");
            fail("should not be able to insert duplicat key");
        } 
        catch (DuplicateKeyException e) { /* expected */ } 
        catch (Exception e) {
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    @Test
    public void test003_Insertmany_Resize() {
    	HashTableADT<Integer, String> htIntegerKey = new HashTable<Integer,String>(10,0.5);
        
        try {
        	htIntegerKey.insert(1, "1");
        	htIntegerKey.insert(2, "1");
        	htIntegerKey.insert(3, "1");
        	htIntegerKey.insert(4, "1");
			htIntegerKey.insert(5, "1");
			htIntegerKey.insert(6, "1");
			if(htIntegerKey.getCapacity()==10) {
				fail("capacity does not change");
			}
		} catch (IllegalNullKeyException | DuplicateKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
            fail("unexpected exceptoin");
        }
    }
    
    @Test
    public void test004_KeyNotFound() {
        try {
            HashTableADT htIntegerKey = new HashTable<Integer,String>();
            htIntegerKey.insert(1, "2");
            htIntegerKey.get(1);
            //fail("should not be able to find a key");
        } 
        catch (KeyNotFoundException e) { /* expected */ } 
        catch (Exception e) {
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    @Test
    public void test005_Insertmany_NumKeys() {
    	HashTableADT<Integer, String> htIntegerKey = new HashTable<Integer,String>(10,0.5);
        try {
        	htIntegerKey.insert(1, "1");
        	htIntegerKey.insert(2, "1");
        	htIntegerKey.insert(3, "1");
        	htIntegerKey.insert(4, "1");
			htIntegerKey.insert(5, "1");
			htIntegerKey.insert(6, "1");
			if(htIntegerKey.numKeys() != 6) {
				fail("size sohuld be six");
			}
		} catch (IllegalNullKeyException | DuplicateKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
            fail("unexpected exceptoin");
        }
    }
    
    @Test
    public void test006_Insertmany_Remove_NumKeys() {
    	HashTableADT<Integer, String> htIntegerKey = new HashTable<Integer,String>(10,0.5);
        try {
        	htIntegerKey.insert(1, "1");
        	htIntegerKey.insert(2, "1");
        	htIntegerKey.insert(3, "1");
        	htIntegerKey.insert(4, "1");
			htIntegerKey.insert(5, "1");
			htIntegerKey.insert(6, "1");
			htIntegerKey.remove(6);
			if(htIntegerKey.numKeys() == 5) {
				fail("size sohuld be 5");
			}
		} catch (IllegalNullKeyException | DuplicateKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
            fail("unexpected exceptoin");
        }
    }
    
    @Test
    public void test007_Insertmany_Remove_Get() {
    	HashTableADT<Integer, String> htIntegerKey = new HashTable<Integer,String>(10,0.5);
        try {
        	htIntegerKey.insert(1, "1");
        	htIntegerKey.insert(2, "1");
        	htIntegerKey.insert(3, "1");
        	htIntegerKey.insert(4, "1");
			htIntegerKey.insert(5, "1");
			htIntegerKey.insert(6, "1");
			htIntegerKey.remove(6);
			htIntegerKey.remove(5);
			htIntegerKey.remove(3);
			//htIntegerKey.get(6);
			if(htIntegerKey.numKeys() == 3) {
				fail("size sohuld be 5");
			}
		} catch (DuplicateKeyException e) {
			//expected
			e.printStackTrace();
			fail("expected exceptoin");
		}catch (Exception e) {
            fail("unexpected exceptoin");
        }
    }
}
