package ru.stqa.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Generator {
    @Parameter(names={"--type", "-t"})//имя type короткое t
    String type;

    @Parameter(names={"--output", "-o"})//имя output короткое o
    String output;//содержится название файла

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-n"})
    int count;


        public static void main (String[] args) throws IOException {//массив строк
        var generator=new Generator();//создаем генератор и сохраняем его в переменную
        JCommander.newBuilder()
                .addObject(generator)//парсер командной строки
                .build()
                .parse(args);//анализируем опции командной строки
        generator.run();//вызываем метод run()
    }

    private void run() throws IOException {//декларируем что мб исключение
        var data = generate();
        save(data);
    }
    private Object generate() {

            if ("groups".equals(type)){
            return generateGroups();//генерируем группы
        }    else if ("contacts".equals(type)){
            return generateContacts();//генерируем контакты
        } else{
               throw new IllegalArgumentException("Неизвестный тип данных " + type);//генерируем исключение

        }
    }

    private Object generateContacts() {
        return null;//НАПИСАТЬ!!!
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();//создаем список объектов GroupData
        for (int i = 0; i<count; i++) {//заполняем список в цикле
            result.add(new GroupData()
                    .WithName(CommonFunctions.randomString(i*10))
                    .WithHeader(CommonFunctions.randomString(i*10))
                    .WithFooter(CommonFunctions.randomString(i*10)));//В список будет добавляться обьекты типа GroupData со случайно сгенерированным name,heder,footer
        }
        return result;
    }


    private void save(Object data) throws IOException {//декларируем что мб исключение
        if ("json".equals(format)){
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);//сохраняем в файл данные, которые находятся в переменной data. В String output содержится название файла
        }else {throw new IllegalArgumentException("Неизвестный формат данных"+format);}
            }
}
