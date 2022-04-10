import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Encrypt {

    // Global Variables.
    // Declaring all the variables.
    private Scanner sc;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffleList;
    private char character;
    private char[] letters;

    // Constructor.
    Encrypt() throws IOException {
        // Initializing all the variables.
        sc = new Scanner(System.in);
        list = new ArrayList<>();
        shuffleList = new ArrayList<>();
        character = ' ';
        newKey();
        askQn();
    }

    private void askQn() throws IOException {
        // Asking qn contineously.
        while (true) {
            System.out.println("\nWhat do you want to do?");
            System.out.print("N -> NewKey, V -> ViewKey, E -> Encrypt, D -> Decrypt, S -> SaveKey, W -> SetKey, Q -> Quit: ");
            // This will hold the first character of the word/character we supply.
            char response = sc.nextLine().toUpperCase().charAt(0);

            switch (response) {
                case 'N':
                    newKey();
                    break;
                case 'V':
                    viewKey();
                    break;
                case 'E':
                    encryptor();
                    break;
                case 'D':
                    decryptor();
                    break;
                case 'Q':
                    quitProgram();
                    break;
                case 'S':
                    saveKey();
                    break;
                case 'W':
                    setKey();
                    break;

                default:
                    System.out.println("Enter a valid option!");
            }

        }

    }

    private void newKey() {
        character = ' '; // The space will be used as 32 no of ASCII.
        list.clear();
        shuffleList.clear();

        // This will hold the char value of no in ASCII table. 32 - 126.
        for (int i = 32; i < 127; i++) {
            list.add(Character.valueOf(character));
            character++; // This will get the next character.
        }

        // creating a copy of the list.
        shuffleList = new ArrayList<>(list);
        Collections.shuffle(shuffleList);
        System.out.println("Your new key has been generated SUCCESSFULLY!");

    }

    private void viewKey() {

        // Prints out each character of the unshuffled list.
        System.out.print("\nUnshuffledKey:\t");
        for (Character i : list) {
            System.out.print(i);
        }
        System.out.println();

        // Prints out each character of the shuffled list.
        System.out.print("\nShuffledKey:  \t");
        for (Character x : shuffleList) {
            System.out.print(x);
        }
        System.out.println();
    }

    private void encryptor() {
        System.out.print("Enter the message to encrypt: ");
        // Taking input from the user to encypt.
        String message = sc.nextLine();
        letters = message.toCharArray();

        // Outer loop to iterate over the message by each character.
        for (int i = 0; i < letters.length; i++) {
            // Inner loop to check if character from outer loop is in the list.
            for (int j = 0; j < list.size(); j++) {
                if (letters[i] == list.get(j)) {
                    letters[i] = shuffleList.get(j);
                    break;
                }

            }
        }
        System.out.print("Your Encrypted Message: ");
        for (Character x : letters) {
            System.out.print(x);
        }
        System.out.println();
    }

    private void decryptor() {
        System.out.print("Enter the message to decrypt: ");
        // Taking input from the user to encypt.
        String message = sc.nextLine();
        letters = message.toCharArray();

        // Outer loop to iterate over the message by each character.
        for (int i = 0; i < letters.length; i++) {
            // Inner loop to check if character from outer loop is in the list.
            for (int j = 0; j < shuffleList.size(); j++) {
                if (letters[i] == shuffleList.get(j)) {
                    letters[i] = list.get(j);
                    break;
                }

            }
        }
        System.out.print("Your Decrypted Message: ");
        for (Character x : letters) {
            System.out.print(x);
        }
        System.out.println();
    }

    private void saveKey() throws IOException {
        FileWriter fr = new FileWriter("Key.txt");
        for (Character x : shuffleList) {
            fr.append(x);
        }
        fr.close();
        System.out.println("Keys saved to file Key.txt");
    }

    private void setKey() throws IOException {
        /*
         * This method adds the key given by the user as a shuffled key and use it to
         * decrypt
         * or encrypt the message.
         */
        shuffleList.clear();
        System.out.print("Enter your key to set: ");
        String keys = sc.nextLine();

        for (int i = 0; i < keys.length(); i++) {
            shuffleList.add(keys.charAt(i));
        }
    }

    private void quitProgram() {
        System.out.println("Bye Bye!\n");
        System.exit(0);
    }

}
