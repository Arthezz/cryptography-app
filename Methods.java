package CryptographyFX;

import javafx.scene.control.Alert;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Methods {


    public String encrypting (String text) {

        String Encrypted;
        StringJoiner tEncrypted = new StringJoiner("+", "", " = ");
        char[] letters = text.toCharArray();
        String[] tmp = new String[letters.length];

        for (int i = 0; i<letters.length; i++){
            tmp[i] = Character.toString(letters[i]).toLowerCase();
            for( int j = 0; j < alphabet.length; j++){
                if ( tmp[i].equals(key[0][j])){
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 10 + 1);
                    tmp[i] = key[randomNum][j];
                    tEncrypted.add(tmp[i]);
                } else {
                    for (int k = 0; k < 6; k++) {
                        if (tmp[i].equals(marks[0][k])) {
                            int randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
                            tmp[i] = marks[randomNum][k];
                            tEncrypted.add(tmp[i]);
                        }
                    }
                }
            }
        }
        Encrypted = tEncrypted.toString();

        if (text.length() == 1)  {
            Encrypted = Encrypted.replaceAll("\\s", "");
            Encrypted= Encrypted.replaceAll("\\=", "");
        }
        return Encrypted;
    }

    public String decrypting (String text) {

        if (text.contains("=")) {
            text = text.replaceAll("\\s", "");
            text = text.replaceAll("\\=", "");
        }

        String cipher[] = text.split("\\+");
        StringJoiner tDecrypted = new StringJoiner("", "", "");

        for (int i = 0; i < cipher.length; i++) {
            for (int j = 1; j < numbers.length / 10 + 1; j++) {
                for (int k = 0; k < alphabet.length; k++) {
                    if (cipher[i].equals(key[j][k])) {
                        tDecrypted.add(key[0][k]);
                    }
                }
            }
            for (int x = 1; x < 6; x++) {
                for (int y = 0; y < 6; y++) {
                    if (cipher[i].equals(marks[x][y])) {
                        tDecrypted.add(marks[0][y]);
                    }
                }
            }
        }

        return tDecrypted.toString();
    }
//key used for encrypting/decrypting
    public void generateKey() {
        int k = 0;

        for (int i = 0; i < alphabet.length; i++) {
            key[0][i] = Character.toString(alphabet[i]);
        }
        for (int i = 1; i < numbers.length/10 + 1; i++){
            for ( int j = 0; j < alphabet.length;j ++){
                for (; k < numbers.length;) {
                    key[i][j] = Integer.toString(numbers[k]);
                    k++;
                    break;
                }
            }
        }
    }

//Warnings
    Alert alert = new Alert(Alert.AlertType.WARNING);

    public void warningEn(){
        alert.setTitle("Warning!");
        alert.setHeaderText("Forbidden key!\n");
        alert.setContentText("Allowed: \n" + "abcdefghijklmnopqrstuvwxyz,.? !_");
        alert.show();
    }

    public void warningDe(){
        alert.setTitle("Warning!");
        alert.setHeaderText("Forbidden key!\n");
        alert.setContentText("Allowed: \n" + "0123456789acvsqrtln/- =*()^");
        alert.show();
    }

//Tables used for generating key
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    int [] numbers = {160,  804, 222, 55, 968, 705, 828, 435, 668, 86, 366, 980, 696, 18, 210, 105, 943, 256, 42, 11, 99, 575, 355, 87, 173, 143, 861,
            49, 964, 880, 675, 505, 625, 138, 595, 147, 729, 159, 394, 74, 918, 897, 986, 566, 907, 775, 806, 94, 849, 674, 985, 287, 710, 991,
            759, 906, 434, 624, 193, 247, 461, 955, 183, 716, 150, 537, 603, 996, 574, 853, 189, 540, 673, 156, 562, 88, 82, 31, 441, 61, 26,
            810, 523, 440, 326, 302, 324, 925, 228, 847, 17, 299, 345, 691, 80, 272, 587, 401, 225, 21, 322, 661, 321, 676, 632, 16, 622, 396,
            707, 229, 877, 436, 533, 699, 463, 614, 318, 348, 312, 112, 310, 46, 512, 34, 689, 812, 928, 914, 500, 281, 783, 102, 830, 755, 417,
            333, 948, 659, 139, 583, 597, 12, 789, 65, 975, 749, 662, 83, 378, 989, 207, 681, 111, 704, 802, 361, 895, 412, 757, 677, 971, 754, 457,
            609, 882, 993, 446, 439, 875, 648, 607, 413, 164, 283, 732, 548, 596, 860, 186, 641, 751, 854, 198, 960, 179, 284, 833, 165, 238, 936,
            364, 381, 967, 340, 940, 266, 33, 29, 788, 516, 169, 586, 20, 195, 170, 215, 373, 184, 836, 137, 590, 451, 856, 565, 700, 279, 572, 37,
            154, 821, 712, 870, 728, 976, 452, 997, 658, 91, 718, 773, 782, 199, 250, 643, 376, 239, 142, 494, 429, 149, 904, 646, 631, 200, 176,
            797, 881, 966, 110, 202, 550, 304, 43, 762, 846, 2, 585, 666, 901, 214};

    String[][] marks = {{",", ".", "?", "!", "_", " "},
            {"3*12", "3sqrt(27)", "2^2", "ln10", "15324/6", "767-248"},
            {"2*31", "5sqrt(623)", "10^3", "ln7", "48/2", "634-629"},
            {"4*56", "sqrt(1024)", "4^6", "ln52", "322/8", "875-235"},
            {"89*22", "3sqrt(81)", "7^3", "ln743", "581/9", "100-69"},
            {"9*20", "4sqrt(20124)", "25^2", "ln429", "3455/5", "491-584"}};

   String allowedKeysEn1 = ("abcdefghijklmnopqrstuvwxyz,. ?!_");
   String allowedKeysEn2 = ("abcdefghijklmnopqrstuvwxyz/1-");
   String allowedKeysDe1 = ("0123456789sqrtln/- =acv");
   String allowedKeysDe2 = ("8906=");


   String[][] key = new String[numbers.length/10 + 1][alphabet.length];

}
