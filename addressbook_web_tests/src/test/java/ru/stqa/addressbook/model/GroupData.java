package ru.stqa.addressbook.model;

public record GroupData(String id, String name, String header, String footer) {
    public GroupData() {
        this ("", "", "", "");
    }

    public GroupData WithName(String name) {return new GroupData(this.id, name, this.header, this.footer);//возвращаем новый объект у которого ИМЯ другое, а header/footer такие же как у существующего объекта
    }
    public GroupData WithHeader(String header) {return new GroupData(this.id, this.name, header, this.footer);}
    public GroupData WithId(String id) {return new GroupData(id, this.name, this.header, this.footer);}//метод,
    // который позволяет строить объект с модифицированном идентификатором. В качестве параметра передаем новое значение идентификатора. Остальные свойства как у текущего объекта
    public GroupData WithFooter(String footer) {return new GroupData(this.id, this.name, this.header,footer);}

    //public Object withName(String modifiedName) {
    //}
}