package model;

public record GroupData(String id, String header, String footer, String name) {
    public GroupData() {
        this ("", "", "", "");
    }

    public GroupData WithName(String name) {return new GroupData(this.id, this.header, this.footer, name);//возвращаем новый объект у которого ИМЯ другое, а header/footer такие же как у существующего объекта
    }
    public GroupData WithHeader(String header) {return new GroupData(this.id, header, this.footer, this.name);}
    public GroupData WithId(String id) {return new GroupData(id, this.header, this.footer, this.name);}//метод,
    // который позволяет строить объект с модифицированном идентификатором. В качестве параметра передаем новое значение идентификатора. Остальные свойства как у текущего объекта
    public GroupData WithFooter(String footer) {return new GroupData(this.id, this.header,footer,this.name);}

    //public Object withName(String modifiedName) {
    //}
}