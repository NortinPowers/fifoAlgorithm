import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class mainMetod {
    public static void main(String[] args) {
        ArrayList<Double> entrance = new ArrayList<>(); //динамический массив поступления/выдачи
        ArrayList<String> documents = new ArrayList<>(); //динамический массив документов на материал
        boolean start = true;
        int count = 0; //счетчик для условного документа на материал
        while (start) { //запуск моделирования работы базы
            Scanner sc = new Scanner(System.in); //считывание поступления/выдачи
            try {
                double x = sc.nextDouble();
                //заполение базы
                if (x > 0) {
                    entrance.add(x);
                    documents.add("Entrance " + "'" + x + "'" + " N " + count); // добавление условного документа
                    count++;
                    System.out.println("now + " + entrance); //вывод баланса базы материала
                    //чистка масивов
                    if (entrance.get(0) == 0.0) {
                        entrance.remove(0);
                        documents.remove(0);
                    }
                } else {
                    if (entrance.size() == 0) {
                        System.out.println("Buy material, min " + Math.abs(x)); //условие отсутствия материала
                    }
                    Label:
                    for (int i = 0; i < entrance.size(); i++) { //обход выдачи по базе
                        if (entrance.size() > 0 & entrance.lastIndexOf(0.0) != entrance.size()) { //условие непустого масива & условие ненулевого последнего элемента
                            double t = entrance.get(i);
                            double p = t + x;
                            //проверка значения внесенного в базу
                            if (p > 0) {
                                entrance.set(i, entrance.get(i) + x);
                                System.out.println("issued " + Math.abs(x) + "  " + documents.get(i)); //вывод документа списания материала
                                System.out.println("now - " + entrance); //вывод баланса базы материала
                                //чистка масивов
                                if (entrance.get(0) == 0.0) {
                                    entrance.remove(0);
                                    documents.remove(0);
                                }
                                break; //прерывание обхода
                            } else {
                                if (p < 0) {
                                    double r = Math.abs(x) - entrance.get(i);
                                    System.out.println("issued " + entrance.get(i) + "  " + documents.get(i)); //вывод документа списания материала
                                    entrance.set(i, 0.0);
                                    x = -r;
                                    System.out.println("now - " + entrance); //вывод баланса базы материала
                                    if (entrance.lastIndexOf(0.0) == entrance.size()) { //условие ненулевого последнего элемента
                                        System.out.println("Buy material, min " + r);
                                    }
                                    continue Label; // возвращение в цикл списания материала
                                } else {
                                    System.out.println("issued " + Math.abs(x) + "  " + documents.get(i)); //вывод документа списания материала
                                    entrance.set(i, 0.0);
                                    System.out.println("now - " + entrance); //вывод баланса базы материала
                                    //чистка масивов
                                    if (entrance.get(0) == 0.0) {
                                        entrance.remove(0);
                                        documents.remove(0);
                                    }
                                    break; //прерывание обхода
                                }
                            }
                        }
                    }
                }
            }
            //проверка данных ввода
            catch (InputMismatchException e) {
                System.out.println("Incorrect input");
            }
        }
    }
}
