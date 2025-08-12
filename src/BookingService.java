import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.print.Book;

public class BookingService {
    private List<Train> trainList = new ArrayList<>();

    private List<Ticket> ticketList = new ArrayList<>();

    public BookingService(){
        trainList.add(new Train(101,"Rajdahni Express" , "Delhi" , "Nagpur" , 100));
        trainList.add(new Train(102,"Shatabdi Express" , "Delhi" , "Mumbai" , 60));
        trainList.add(new Train(103,"Duronto  Express" , "Agra" , "Delhi" , 70));
        trainList.add(new Train(104,"Tejas  Express" , "Delhi" , "Goa" , 100));
        trainList.add(new Train(105,"Vande Bharat Express" , "Kolkata" , "Andra Pradesh" , 90));
        trainList.add(new Train(106,"Vivek  Express" , "Delhi" , "Bangaluru" , 80));

    }
    public List<Train> searchTrain(String source , String destination)
    {
        List<Train> res = new ArrayList<>();
        for(Train train : trainList){
            if(train.getSource().equalsIgnoreCase(source) && train.getDestination().equalsIgnoreCase(destination))
            {
                res.add(train);
            }
        }
        return res;
    }

    public Ticket bookTicket(User user , int trainId , int seatCount)
    {
        for(Train train : trainList){
            if(train.getTrainId() == trainId)
            {
                if(train.bookSeats((seatCount))){
                    Ticket ticket = new Ticket(user , train , seatCount);
                    ticketList.add(ticket);
                    return ticket;
                }
                else{
                    System.out.println("No enough seats available");
                    return null;
                }
            }
        }

        System.out.println("Train ID not found");
        return null;
    }

    public List<Ticket> getTicketByUser(User user)
    {
        List<Ticket> res = new ArrayList<>();
        for(Ticket ticket : ticketList)
        {
            if(ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())){
                res.add(ticket);
            }
        }
        return res;
    }

    public boolean cancelTicket(int ticketId , User user)
    {
        Iterator<Ticket> it = ticketList.listIterator();
        while(it.hasNext())
        {
            Ticket ticket = it.next();
            if(ticket.getTicketId() == ticketId &&
                    ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())){
                Train train = ticket.getTrain();
                train.cancelSeats(ticket.getSeatBooked());
                it.remove();
                System.out.println("Ticket "+ticketId + " Cancelled Successfully");
                return true;
            }
        }
        System.out.println("Ticket not found and does not belong to current user");
        return false;
    }

    public void listAllTrains(){
        System.out.println("List of all trains: ");
        for (Train train : trainList)
        {
            System.out.println(train);
        }
    }
}
