package ru.stqa.mantis.model;

public record MailMessage(String from, String content) {//String from - откого пришло письмо, String content - содержимое письма
    public MailMessage (){
        this ("","");//конструктор без параметров вызывает тот конструктор, который всегда есть в record
    }
    //вспомогательные методы для построения модифицированного объекта MailMessage
    public MailMessage withFrom (String from){//передаем новое значение для свойства
        return new MailMessage(from, this.content);//возвращаем новый объект, где from новое, а this.content - то, которое было
    }
    public MailMessage  withContent (String content){//передаем новое значение для свойства
        return new MailMessage(this.from, content);//возвращаем новый объект, где content новое, а this.from - то, которое было
    }

}
