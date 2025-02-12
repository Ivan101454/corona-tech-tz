package by.ivan101454.corona.util;

public class IncorrectStringCreator {

    /**
     * Метод создает строку, с работниками, чьи поля которых содержат невалидные значения
     * @param mas массив неккоректных данных
     * @return возвращает строку невалидных значение
     */
    public String createResultString(String[] mas) {
        StringBuilder sb = new StringBuilder();
        int length = mas.length;
        for (String field : mas) {
            length--;
            sb.append(field);
            if(length != 0) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}
