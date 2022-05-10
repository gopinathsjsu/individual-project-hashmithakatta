package Factory;

public class Reader {

    public DataReader getInstance(String readerType){
        if (readerType.equals("item")){
            return new ItemReader();
        }else if(readerType.equals("card")){
            return new CardReader();
        }else {
            throw new RuntimeException("Instance of of type not supported"+ readerType);
        }
    }
}
