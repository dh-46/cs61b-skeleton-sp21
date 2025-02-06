package capers;

import java.io.File;
import java.io.IOException;

import static capers.Utils.*;

/** A repository for Capers 
 * @author Daniel Huang
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = join(CWD, ".capers");

    static final File STORY_FILE = join(CAPERS_FOLDER, "story");

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        // 1. create .capers folder
        createFolderIfNotExists(CAPERS_FOLDER);
        // 2. create .capers/dogs folder
        createFolderIfNotExists(Dog.DOG_FOLDER);
        // 3. create story file
        createFileIfNotExists(STORY_FILE);
    }

    private static void createFileIfNotExists(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("createFileIfNotExists:" + e.getMessage());
            }
        }
    }

    private static void createFolderIfNotExists(File folder) {
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        // Read exists content
        String oldContent = Utils.readContentsAsString(STORY_FILE);
        // Add new text to exists content and write to file
        Utils.writeContents(STORY_FILE, oldContent + text + "\n");
        // print out all content
        System.out.println(Utils.readContentsAsString(STORY_FILE));
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog dog = new Dog(name, breed, age);
        try {
            dog.saveDog();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(dog);
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog dog = Dog.fromFile(name);
        if (dog != null) {
            dog.haveBirthday();
            try {
                dog.saveDog();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
