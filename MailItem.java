public class MailItem
{
    private String To;
    private String From;
    private String Title;
    private String Message;

    public MailItem(String to, String from, String title, String message) {
        this.To = to;
        this.From = from;
        this.Title = title;
        this.Message = message;
    }

    public String getTo() {
        return To;
    }

    public String getFrom() {
        return From;
    }

    public String getTitle() {
        return Title;
    }

    public String getMessage() {
        return Message;
    }
    public void print(){
        System.out.println(" From : "+From);
        System.out.println(" To :"+To);
        System.out.println(" Title "+Title);
        System.out.println(" Message "+Message);
    }
}

