package trash;

public enum CommandExecutionMode
{
    MANUAL_INPUT,
    FROM_FILE;

    public static class XMLAndString<T> {
        public String s1;
        public  String s2;
        public T t;

        public XMLAndString(String s1 , String s2, T t)
        {
            this.s1= s1;
            this.s2 = s2;
            this.t = t;
        }}
}
