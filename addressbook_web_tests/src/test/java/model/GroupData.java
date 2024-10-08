package model;

public record GroupData(String name, String header, String footer) {
    public GroupData() {
        this ("","","");
    }

    public GroupData WithName(String name) {
        return new GroupData(name, this.header, this.footer);//возвращаем новый объект у которого ИМЯ другое, а header/footer такие же как у существующего объекта
    }
    public GroupData WithHeader(String header) {
        return new GroupData(this.name, header, this.footer);
    }
    public GroupData WithFooter(String footer) {
        return new GroupData(this.name, this.header, footer);
    }

    //public Object withName(String modifiedName) {
    //}
}