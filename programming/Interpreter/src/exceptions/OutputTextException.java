package exceptions;

public class OutputTextException extends Exception
{
    private String text;

    public OutputTextException(String text)
    {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
