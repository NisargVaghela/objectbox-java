package io.objectbox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.objectbox.exception.PagesCorruptException;
import io.objectbox.model.ValidateOnOpenMode;
import org.greenrobot.essentials.io.IoUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests validation (and recovery) options on opening a store.
 */
public class BoxStoreValidationTest extends AbstractObjectBoxTest {

    private BoxStoreBuilder builder;

    @Override
    protected BoxStore createBoxStore() {
        // Standard setup of store not required
        return null;
    }

    @Before
    public void setUpBuilder() {
        BoxStore.clearDefaultStore();
        builder = new BoxStoreBuilder(createTestModel(null)).directory(boxStoreDir);
    }

    @Test
    public void validateOnOpen() {
        // Create a database first; we must create the model only once (ID/UID sequences would be different 2nd time)
        byte[] model = createTestModel(null);
        builder = new BoxStoreBuilder(model).directory(boxStoreDir);
        builder.entity(new TestEntity_());
        store = builder.build();

        TestEntity object = new TestEntity(0);
        object.setSimpleString("hello hello");
        long id = getTestEntityBox().put(object);
        store.close();

        // Then re-open database with validation and ensure db is operational
        builder = new BoxStoreBuilder(model).directory(boxStoreDir);
        builder.entity(new TestEntity_());
        builder.validateOnOpen(ValidateOnOpenMode.Full);
        store = builder.build();
        assertNotNull(getTestEntityBox().get(id));
        getTestEntityBox().put(new TestEntity(0));
    }


    @Test(expected = PagesCorruptException.class)
    public void validateOnOpenCorruptFile() throws IOException {
        File dir = prepareTempDir("object-store-test-corrupted");
        File badDataFile = prepareBadDataFile(dir);

        builder = BoxStoreBuilder.createDebugWithoutModel().directory(dir);
        builder.validateOnOpen(ValidateOnOpenMode.Full);
        try {
            store = builder.build();
        } finally {
            boolean delOk = badDataFile.delete();
            delOk &= new File(dir, "lock.mdb").delete();
            delOk &= dir.delete();
            assertTrue(delOk);  // Try to delete all before asserting
        }
    }

    @Test
    public void usePreviousCommitWithCorruptFile() throws IOException {
        File dir = prepareTempDir("object-store-test-corrupted");
        prepareBadDataFile(dir);
        builder = BoxStoreBuilder.createDebugWithoutModel().directory(dir);
        builder.validateOnOpen(ValidateOnOpenMode.Full).usePreviousCommit();
        store = builder.build();
        String diagnoseString = store.diagnose();
        assertTrue(diagnoseString.contains("entries=2"));
        store.validate(0, true);
        store.close();
        assertTrue(store.deleteAllFiles());
    }

    @Test
    public void usePreviousCommitAfterFileCorruptException() throws IOException {
        File dir = prepareTempDir("object-store-test-corrupted");
        prepareBadDataFile(dir);
        builder = BoxStoreBuilder.createDebugWithoutModel().directory(dir);
        builder.validateOnOpen(ValidateOnOpenMode.Full);
        try {
            store = builder.build();
            fail("Should have thrown");
        } catch (PagesCorruptException e) {
            builder.usePreviousCommit();
            store = builder.build();
        }

        String diagnoseString = store.diagnose();
        assertTrue(diagnoseString.contains("entries=2"));
        store.validate(0, true);
        store.close();
        assertTrue(store.deleteAllFiles());
    }

    private File prepareBadDataFile(File dir) throws IOException {
        assertTrue(dir.mkdir());
        File badDataFile = new File(dir, "data.mdb");
        try (InputStream badIn = getClass().getResourceAsStream("corrupt-pageno-in-branch-data.mdb")) {
            try (FileOutputStream badOut = new FileOutputStream(badDataFile)) {
                IoUtils.copyAllBytes(badIn, badOut);
            }
        }
        return badDataFile;
    }

}
