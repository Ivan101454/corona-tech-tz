package by.ivan101454.corona.data;

public class ArgumentsData {

    public String[] getArrayOfAllArguments() {
        return new String[]{"--sort=name", "--order=desc", "--output=file", "--path=/src/main/resources"};
    }

    public String[] getArrayOfWithNotCompletePath() {
        return new String[]{"--sort=name", "--order=desc", "--output=file"};
    }
}
