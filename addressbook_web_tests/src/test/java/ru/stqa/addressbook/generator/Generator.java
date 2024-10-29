package ru.stqa.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;

public class Generator {
    @Parameter(names={"--type", "-t"})//имя type короткое t
    String type;

    @Parameter(names={"--output", "-o"})//имя output короткое o
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-n"})
    int count;

    public static void main (String[]args){//массив строк
        var generator=new Generator();//создаем генератор и сохраняем его в переменную
        JCommander.newBuilder()
                .addObject(generator)//парсер командной строки
                .build()
                .parse(args);//анализируем опции командной строки
        generator.run();//вызываем метод run()
    }

    private void run() {
        var data = generate();
                save(data);
    }
    private Object generate() {
        if ("groups".equals(type)){
            return generateGroups();//генерируем группы
        }    else if ("contacts".equals(type)){
            return generateContacts();//генерируем контакты
        }        else{
            throw new IllegalArgumentException("Неизвестный тип данных"+ type);//генерируем исключение
        }
        return null;
    }

    private Object generateContacts() {
        return null;//НАПИСАТЬ!!!
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();//создаем список объектов GroupData
        for (int i = 0; i<count; i++) {//заполняем список в цикле
            result.add(new GroupData()
                    .WithName(randomString(i*10))
                    .WithHeader(randomString(i*10))
                    .WithFooter(randomString(i*10)));//В список будет добавляться обьекты типа GroupData со случайно сгенерированным name,heder,footer
        }
        return result;
    }

    
    private void save(Object data) {
    }
}
